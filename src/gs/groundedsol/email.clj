(ns gs.groundedsol.email
  (:require [camel-snake-kebab.core :as csk]
            [camel-snake-kebab.extras :as cske]
            [clj-http.client :as http]
            [gs.hiccup :refer [render-static-markup]]
            [gs.groundedsol.settings :as settings]
            [clojure.tools.logging :as log]
            ))

(defn signin-link [{:keys [to url user-exists]}]
  {:to to
   :subject "Join the list"
   :html-body (render-static-markup
               [:html
                [:body
                 [:p "We received a request to join " settings/app-name
                  " using this email address. Click this link to join:"]
                 [:p [:a {:href url :target "_blank"} "Click here to sign in."]]
                 [:p [:a {:href url :target "_blank"} "Join the list"]]
                 [:p "This link will expire in one hour. "
                  "If you did not request this link, you can ignore this email."]]])
   :text-body (str "We received a request to join" settings/app-name
                   " using this email address. Click this link to <action>\n"
                   "\n"
                   url "\n"
                   "\n"
                   "This link will expire in one hour. If you did not request this link, "
                   "you can ignore this email.")
   :message-stream "outbound"})

(defn signin-code [{:keys [to code user-exists]}]
  {:to to
   :subject (if user-exists
              (str "Sign in to " settings/app-name)
              (str "Sign up for " settings/app-name))
   :html-body (render-static-markup
               [:html
                [:body
                 [:p "We received a request to sign in to " settings/app-name
                  " using this email address. Enter the following code to sign in:"]
                 [:p {:style {:font-size "2rem"}} code]
                 [:p
                  "This code will expire in three minutes. "
                  "If you did not request this code, you can ignore this email."]]])
   :text-body (str "We received a request to sign in to " settings/app-name
                   " using this email address. Enter the following code to sign in:\n"
                   "\n"
                   code "\n"
                   "\n"
                   "This code will expire in three minutes. If you did not request this code, "
                   "you can ignore this email.")
   :message-stream "outbound"})

(defn template [k ctx]
  ((case k
     :signin-link signin-link
     :signin-code signin-code)
   ctx))

(comment
  (cske/transform-keys csk/->kebab-case-keyword 
    {"helloWorld" "world"
     nil "str"
     "key" nil})
  )

(defn send-postmark 
  [{:keys [biff/secret postmark/from]} form-params]
  (let [result 
        (http/post "https://api.postmarkapp.com/email"
          {:headers
           {"X-Postmark-Server-Token"
            (secret :postmark/api-key)}
           :as :json
           :content-type :json
           :form-params 
           (merge {:from from} 
             (cske/transform-keys csk/->PascalCase form-params))
           :throw-exceptions false})
        success (< (:status result) 400)]
    (when-not success
      (log/error (:body result)))
    success))

(defn send-console [ctx form-params]
  (println "TO:" (:to form-params))
  (println "SUBJECT:" (:subject form-params))
  (println)
  (println (:text-body form-params))
  (println)
  (println "To send emails instead of printing them to the console, add your"
           "API keys for Postmark and Recaptcha to config.edn.")
  true)

(defn send-email [{:keys [biff/secret recaptcha/site-key] :as ctx} ctx]
  (let [form-params (if-some [template-key (:template ctx)]
                      (template template-key ctx)
                      ctx)]
    (if (every? some? [(secret :postmark/api-key)
                       (secret :recaptcha/secret-key)
                       site-key])
      (send-postmark ctx form-params)
      (send-console ctx form-params))))
