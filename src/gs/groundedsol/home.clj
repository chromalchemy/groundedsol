(ns gs.groundedsol.home
  (:require [clj-http.client :as http]
            [com.biffweb :as biff :refer [pprint] ]
            [gs.groundedsol.middleware :as mid]
            [gs.groundedsol.ui :as ui]
            [gs.site]
            [gs.groundedsol.settings :as settings]
            [gs.pages.index]
            [gs.pages.fl-plants]
            [gs.pages.consultation]
            [lambdaisland.ornament :as o :refer [defstyled]]
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
            [xtdb.api :as xt]
            [camel-snake-kebab.core :as csk]
            [camel-snake-kebab.extras :as cske]
            [clj-http.client :as http]
            [clojure.tools.logging :as log]
            ))

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


(defstyled email-input :input 
  :w-300px :mr-2)

(defstyled email-submit-btn :button)

(defstyled email-error-message :div
  ([error-text]
    [:<> error-text]))

(defn recaptcha-error-text [error]
  (case error
    "recaptcha"
    (str "You failed the recaptcha test. Try again, "
      "and make sure you aren't blocking scripts from Google.")
  
    "invalid-email"
    "Invalid email. Try again with a different address."
  
    "send-failed"
    (str "We weren't able to send an email to that address. "
      "If the problem persists, try another address.")
  
    "There was an error."))

(defn form-body [{:keys [] :as ctx}]
  (let [email-address
        (get (:form-params ctx) "email")]
    [:form
     {:hx-post "/send-email"
      :hx-disabled-elt "this"
      :hx-swap "outerHTML"}
     
     (cond 
       (= email-address "")
       [:p "Please add email address."]
       (nil? email-address)
       [:p "Please leave your email address and we'll get in touch."])
     
     
     [email-input
      {:name "email"
       :type "email"
       :autocomplete "email"
       :placeholder "Email"}]
     
     [:button
      {:type "submit"
       :class '[g-recaptcha]}
      "Contact"]]))

(defstyled thanks-for-sending :div    
  ([]
   [:<>
    "Thanks for your interest. We will reach out to you shortly."]))


(defn contact-form [{:keys [] :as ctx}]
  (let [email-address 
        (get (:form-params ctx) "email")]
    #_(pprint ctx)
    (println "email address")
    (pprint email-address)

    (cond 
      (nil? email-address)
      (form-body ctx) 

      (= email-address "")
      (form-body ctx) 

      :else 
      [thanks-for-sending]
        )
    )
  )

(defstyled contact-form-container :div 
  :w-75% #_:border-solid #_:border-2px #_:border-green-600 :mx-auto :my-4 :p-4
  :text-center
  )

(defn contact-page [ctx]
  (ui/page
    (assoc ctx ::ui/recaptcha false)
    [contact-form-container
     (contact-form ctx)
     #_[:div biff/recaptcha-disclosure]
     ]))

(defn confirmation-page [{:keys [params] :as ctx}]
  (ui/page
   ctx
   [:h2.text-xl.font-bold "Check your inbox"]
   [:p "We've sent a sign-in link to " [:span.font-bold (:email params)] "."]))


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



(defn send-email-custom [{:keys [biff/secret postmark/from]} form-params]
  (let [gsol-email-address from
        contact-email-address (get form-params "email")
       
        send-email-result
        (fn [email-params]
          (http/post "https://api.postmarkapp.com/email"
            {:headers {"X-Postmark-Server-Token" "1816d048-a01c-4d1a-bb22-f263949c89ab" #_(secret :postmark/api-key)}
             :as :json
             :content-type :json
             :form-params email-params
             :throw-exceptions false}))
        send-email-to-groundedsol-result
        (send-email-result
          {:From gsol-email-address
           :To gsol-email-address
           :Subject "New Contact Inquery from Groundesol website"
           :TextBody (str "You recieved an inquery from " contact-email-address)})
        send-email-to-groundedsol-success 
        (< (:status send-email-to-groundedsol-result) 400)
        
        send-email-to-contact-result
        (send-email-result
          {:From gsol-email-address
           :To contact-email-address
           :Subject "Thank's for contacting us"
           :TextBody "We will contact you shortly to discuss your beautify Florida Native landscape! \n https://groundedsol.com\n352-219-5381" })
        send-email-to-contact-success
        (< (:status send-email-to-contact-result) 400)]
    
    (when-not send-email-to-groundedsol-success 
      (log/error (:body send-email-to-groundedsol-result)))
    send-email-to-groundedsol-success
    
    (when-not send-email-to-contact-success
      (log/error (:body send-email-to-contact-result)))
    send-email-to-contact-success))

(defn send-email-handler
  [ctx]
  (let [form-params (:form-params ctx)
        pr 
        (do
          (println "form params:")
          (pprint form-params))
        email-address (get form-params "email")
        request 
        (send-email-custom ctx form-params)]
    (contact-form ctx)
    ))


(defn foo [request]
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "foo response"})


(defn add-html-route [[path-str m]]
  [[(str path-str ".html") m]
   [path-str m]])

(defn route-fn [page-hiccup]
  (fn 
    [{:keys [recaptcha/site-key params] :as ctx}]
    (ui/page
      (assoc ctx ::ui/recaptcha false)
      page-hiccup)))

(defn make-routes [[page-key page-hiccup]]
  (let [route-base-str 
        (str "/" (gs.site/html-filename page-key))]
    (add-html-route 
      [route-base-str {:get (route-fn page-hiccup)}])))


(def new-routes
  [#_["/foo" 
    {:det foo}]
   ["/send-email" 
    {:post send-email-handler}]
   ["" 
    {:middleware [mid/wrap-redirect-signed-in]}
    ["/" 
     {:get gs.pages.index/home-page}]]
   ["/contact-confirmed"  
    {:get confirmation-page}]
   ["/contact" 
    {:get contact-page}]])

(def original-page-routes
  (->>
    {:notes gs.pages.notes/page-hiccup
     :florida-plants gs.pages.fl-plants/page-hiccup
     :services gs.pages.services/page-hiccup
     :consultation gs.pages.consultation/page-hiccup
   #_#_ :contact gs.pages.contact/page-hiccup
     :about gs.pages.about/page-hiccup}
    (mapv make-routes)
    (apply concat)))


(def all-routes
  (vec  
    (concat
         new-routes
         original-page-routes)
    ))

(def plugin
  {:routes all-routes})

