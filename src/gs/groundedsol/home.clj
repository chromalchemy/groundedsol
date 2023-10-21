(ns gs.groundedsol.home
  (:require [clj-http.client :as http]
            [com.biffweb :as biff]
            [gs.groundedsol.middleware :as mid]
            [gs.groundedsol.ui :as ui]
            [gs.site]
            [gs.groundedsol.settings :as settings]
            [gs.pages.index]
            [gs.pages.fl-plants]
            [gs.pages.consultation]
            [gs.pages.services]
            [gs.pages.contact]
            [gs.pages.about]
            [gs.pages.notes]
            [gs.hiccup :as bhiccup]
            [com.biffweb.impl.util :as butil]
            [com.biffweb.impl.auth :as auth]
            [com.biffweb.impl.xtdb :as bxt]
            [gs.build :as build]
            [lambdaisland.hiccup :as hiccup]
            [xtdb.api :as xt]))

(def email-disabled-notice
  [:div
   "Until you add API keys for Postmark and reCAPTCHA, we'll print your sign-up "
   "link to the console. See config.edn."])

(defn passed-recaptcha? [{:keys [biff/secret biff.recaptcha/threshold params]
                          :or {threshold 0.5}}]
  (or (nil? (secret :recaptcha/secret-key))
    (let [{:keys [success score]}
          (:body
           (http/post "https://www.google.com/recaptcha/api/siteverify"
             {:form-params {:secret (secret :recaptcha/secret-key)
                            :response (:g-recaptcha-response params)}
              :as :json}))]
      (and success (or (nil? score) (<= threshold score))))))



(defn send-email! [{:keys [biff.auth/email-validator
                          biff/send-email
                          params]
                   :as ctx}]
  (let [email (butil/normalize-email (:email params))
        url ""
        user-id (atom nil)]
    (cond
      (not (passed-recaptcha? ctx))
      {:success false :error "recaptcha"}

      (not (email-validator ctx email))
      {:success false :error "invalid-email"}

      (not (send-email ctx
             {:template :signin-link
              :to email
              :url url
              :user-exists false}))
      {:success false :error "send-failed"}

      :else
      {:success true :email email :user-id @user-id})))

(defn send-email-handler [{:keys [biff.auth/single-opt-in
                                 biff.auth/new-user-tx
                                 biff/db
                                 params]
                          :as ctx}]
  (let [{:keys [success error email user-id]} (auth/send-link! ctx)]
    (when (and success single-opt-in (not user-id))
      (bxt/submit-tx (assoc ctx :biff.xtdb/retry false) (new-user-tx ctx email)))
    {:status 303
     :headers {"location" (if success
                            (str "/link-sent?email=" (:email params))
                            (str (:on-error params "/") "?error=" error))}}))

(defn contact-form [{:keys [recaptcha/site-key params]}]
  (bhiccup/form
    {:id "contact-form"
     :action "/send-email"
     :hidden {:on-error "/"}}
    (biff/recaptcha-callback "submitContact" "contact-form")
    [:input#email
     {:name "email"
      :type "email"
      :autocomplete "email"`
      :placeholder "Enter your email address"}]
    [:button
     (merge (when site-key
              {:data-sitekey site-key
               :data-callback "submitSignup"})
       {:type "submit"
        :class '[g-recaptcha]})
     "Contact"]
    (when-some [error (:error params)]
      [:<>
       [:div
        (case error
          "recaptcha" (str "You failed the recaptcha test. Try again, "
                        "and make sure you aren't blocking scripts from Google.")
          "invalid-email" "Invalid email. Try again with a different address."
          "send-failed" (str "We weren't able to send an email to that address. "
                          "If the problem persists, try another address.")
          "There was an error.")]])))

(defn contact-page [ctx]
  (ui/page
    (assoc ctx ::ui/recaptcha false)
    [:div.
     #_(contact-form ctx)
     #_[:div biff/recaptcha-disclosure]
     ]))

(defn confirmation-page [{:keys [params] :as ctx}]
  (ui/page
   ctx
   [:h2.text-xl.font-bold "Check your inbox"]
   [:p "We've sent a sign-in link to " [:span.font-bold (:email params)] "."]))

(defn foo [request]
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "foo response"})

(do
  (def plugin
    {:routes
     (vec
       (concat
         [["/foo" {:det foo}]
          ["/send-email"          {:post send-email-handler}]
          ["" {:middleware [mid/wrap-redirect-signed-in]}
           ["/"                  {:get gs.pages.index/home-page}]]
          ["/contact-confirmed"  {:get confirmation-page}]
          #_["/contact"             {:get contact-page}]
          ]
         (->>
           {:notes gs.pages.notes/page-hiccup
            :florida-plants gs.pages.fl-plants/page-hiccup
            :services gs.pages.services/page-hiccup
            :consultation gs.pages.consultation/page-hiccup
            :contact gs.pages.contact/page-hiccup
            :about gs.pages.about/page-hiccup}
           (mapv
             (fn [[page-key page-hiccup]]
               (let [rout-base-str (str "/" (gs.site/html-filename page-key))
                     route-fn
                     (fn [{:keys [recaptcha/site-key params] :as ctx}]
                       (ui/page
                         (assoc ctx ::ui/recaptcha false)
                         page-hiccup))]
                 [[(str rout-base-str ".html") {:get route-fn}]
                  [rout-base-str {:get route-fn}]])))
           (apply concat))))})
  plugin)

