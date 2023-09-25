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
            [gs.build :as build]
            [lambdaisland.hiccup :as hiccup]
            [xtdb.api :as xt]))

(def email-disabled-notice
  [:div.text-sm.mt-3.bg-blue-100.rounded.p-2
   "Until you add API keys for Postmark and reCAPTCHA, we'll print your sign-up "
   "link to the console. See config.edn."])

(defn signup-form [{:keys [recaptcha/site-key params]}]
  (bhiccup/form
    {:id "signup"
     :action "/auth/send-link"
     :hidden {:on-error "/"}
     :class "sm:max-w-xs w-full"}
    (biff/recaptcha-callback "submitSignup" "signup")
    [:input#email
     {:name "email"
      :type "email"
      :autocomplete "email"
      :placeholder "Enter your email address"
      :class '[border
               border-gray-300
               rounded
               w-full
               focus:border-teal-600
               focus:ring-teal-600]}]
    [:div.h-3]
    [:button
     (merge (when site-key
              {:data-sitekey site-key
               :data-callback "submitSignup"})
       {:type "submit"
        :class '[bg-teal-600
                 hover:bg-teal-800
                 text-white
                 py-2
                 px-4
                 rounded
                 w-full
                 g-recaptcha]})
     "Join the waitlist"]
    (when-some [error (:error params)]
      [:<>
       [:div.h-1]
       [:div.text-sm.text-red-600
        (case error
          "recaptcha" (str "You failed the recaptcha test. Try again, "
                        "and make sure you aren't blocking scripts from Google.")
          "invalid-email" "Invalid email. Try again with a different address."
          "send-failed" (str "We weren't able to send an email to that address. "
                          "If the problem persists, try another address.")
          "There was an error.")]])))

(defn signup-page [ctx]
  (ui/page
    (assoc ctx ::ui/recaptcha true)
    [:div.bg-orange-50.flex.flex-col.flex-grow.items-center.p-3
     [:div.h-12.grow]
     [:img.w-40 {:src "/img/eel.svg"}]
     [:div.h-6]
     [:div.text-2xl.sm:text-3xl.font-semibold.sm:text-center.w-full
      "The world's finest discussion platform"]
     [:div.h-2]
     [:div.sm:text-lg.sm:text-center.w-full
      "Communities, channels, messages, even RSS—eelchat has it all. Coming soon."]
     [:div.h-6]
     (signup-form ctx)
     [:div.h-12 {:class "grow-[2]"}]
     [:div.text-sm biff/recaptcha-disclosure]
     [:div.h-6]]))

(defn link-sent [{:keys [params] :as ctx}]
  (ui/page
   ctx
   [:h2.text-xl.font-bold "Check your inbox"]
   [:p "We've sent a sign-in link to " [:span.font-bold (:email params)] "."]))

(defn verify-email-page [{:keys [params] :as ctx}]
  (ui/page
   ctx
   [:h2.text-2xl.font-bold (str "Sign up for " settings/app-name)]
   [:div.h-3]
   (bhiccup/form
    {:action "/auth/verify-link"
     :hidden {:token (:token params)}}
    [:div [:label {:for "email"}
           "It looks like you opened this link on a different device or browser than the one "
           "you signed up on. For verification, please enter the email you signed up with:"]]
    [:div.h-3]
    [:div.flex
     [:input#email {:name "email" :type "email"
                    :placeholder "Enter your email address"}]
     [:div.w-3]
     [:button.btn {:type "submit"}
      "Sign in"]])
   (when-some [error (:error params)]
     [:div.h-1]
     [:div.text-sm.text-red-600
      (case error
        "incorrect-email" "Incorrect email address. Try again."
        "There was an error.")])))

(defn signin-page [{:keys [recaptcha/site-key params] :as ctx}]
  (ui/page
   (assoc ctx ::ui/recaptcha true)
   (bhiccup/form
    {:action "/auth/send-code"
     :id "signin"
     :hidden {:on-error "/signin"}}
    (biff/recaptcha-callback "submitSignin" "signin")
    [:h2.text-2xl.font-bold "Sign in to " settings/app-name]
    [:div.h-3]
    [:div.flex
     [:input#email {:name "email"
                    :type "email"
                    :autocomplete "email"
                    :placeholder "Enter your email address"}]
     [:div.w-3]
     [:button.btn.g-recaptcha
      (merge (when site-key
               {:data-sitekey site-key
                :data-callback "submitSignin"})
             {:type "submit"})
      "Sign in"]]
    (when-some [error (:error params)]
      [:<>
       [:div.h-1]
       [:div.text-sm.text-red-600
        (case error
          "recaptcha" (str "You failed the recaptcha test. Try again, "
                           "and make sure you aren't blocking scripts from Google.")
          "invalid-email" "Invalid email. Try again with a different address."
          "send-failed" (str "We weren't able to send an email to that address. "
                             "If the problem persists, try another address.")
          "invalid-link" "Invalid or expired link. Sign in to get a new link."
          "not-signed-in" "You must be signed in to view that page."
          "There was an error.")]])
    [:div.h-1]
    [:div.text-sm "Don't have an account yet? " [:a.link {:href "/"} "Sign up"] "."]
    [:div.h-3]
    biff/recaptcha-disclosure
    email-disabled-notice)))

(defn enter-code-page [{:keys [recaptcha/site-key params] :as ctx}]
  (ui/page
   (assoc ctx ::ui/recaptcha true)
   (bhiccup/form
    {:action "/auth/verify-code"
     :id "code-form"
     :hidden {:email (:email params)}}
    (biff/recaptcha-callback "submitCode" "code-form")
    [:div [:label {:for "code"} "Enter the 6-digit code that we sent to "
           [:span.font-bold (:email params)]]]
    [:div.h-1]
    [:div.flex
     [:input#code {:name "code" :type "text"}]
     [:div.w-3]
     [:button.btn.g-recaptcha
      (merge (when site-key
               {:data-sitekey site-key
                :data-callback "submitCode"})
             {:type "submit"})
      "Sign in"]])
   (when-some [error (:error params)]
     [:div.h-1]
     [:div.text-sm.text-red-600
      (case error
        "invalid-code" "Invalid code."
        "There was an error.")])
   [:div.h-3]
   (bhiccup/form
    {:action "/auth/send-code"
     :id "signin"
     :hidden {:email (:email params)
              :on-error "/signin"}}
    (biff/recaptcha-callback "submitSignin" "signin")
    [:button.link.g-recaptcha
     (merge (when site-key
              {:data-sitekey site-key
               :data-callback "submitSignin"})
            {:type "submit"})
     "Send another code"])))

(concat [1 2] [3 4])

(do
  (def plugin
    {:routes
     (vec
       (concat
         [["" {:middleware [mid/wrap-redirect-signed-in]}
           ["/"                  {:get gs.pages.index/home-page}]]
          ["/link-sent"          {:get link-sent}]
          ["/verify-link"        {:get verify-email-page}]
          ["/signin"             {:get signin-page}]
          ["/signup"             {:get signup-page}]
          ["/verify-code"        {:get enter-code-page}]
          #_["/consultationanddesign"        {:get gs.pages.consultation/page}]
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

