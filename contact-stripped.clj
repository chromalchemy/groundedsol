(ns gs.pages.contact-stripped
  (:require
   [com.biffweb :as biff :refer [pprint]]
   [clojure.string :as string]
   [hickory.core :as h]
   [cybermonday.core :as cm]
   [gs.content :as content]
   [garden.selectors :as gs]
   [garden.core :refer [css]]
   [lambdaisland.ornament :refer [defstyled]]
   [gs.components :as c]
   [camel-snake-kebab.core :as csk]
   [camel-snake-kebab.extras :as cske]
   [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE AFTER-ELEM]]
    [gs.color :as color]
   [gs.groundedsol.ui :as ui]
   [clj-http.client :as http]
   [clojure.tools.logging :as log]
   [garden.core :as garden]
   [ring.middleware.anti-forgery :as csrf]
   [com.biffweb.impl.auth :as auth]
   [squint.compiler :as sq])
  (:use
    [gs.util]
    [gs.components]))

(def fresh-field? nil?)

(def returned-field? string?)

(def empty-field? empty?)

(defn filled-field? [x]
  (not (empty-field? x)))

(def empty-returned-field?
  (every-pred
    returned-field?
    empty-field?))

(def filled-returned-field
  (every-pred
    filled-field?
    returned-field?))  


(defn contact-input
  [{field-name :name
    field-value :value
    :keys [name required? error?]
    :or {required? false
         type "text"}
    :as params}]
  (let
    [field-label-str
     (-> field-name 
       (string/replace "-" " ") 
       (string/capitalize))
     field-empty? 
     (empty-returned-field? field-value)]
    [input
     (->
       {:placeholder
        (str field-label-str (when required? "*"))
        :class []}
       (merge params)
       (cond->> 
         required? (add-class "required")
         error? (add-class "error")
         field-empty? (add-class "empty")
         ))]))

(def form-data
  [{:field-name :first-name}
   {:field-name :last-name}
   {:field-name :email}
   {:field-name :telephone
    :required? false}])

(defn comments-input 
  [:div
   [textarea
    {:cols "30"
     :placeholder "What are your landscape goals?"
     :maxlength "1500"
     :name "comments"
     :rows "7"}]])

;; ___________________________________ .

(defn send-email-api-call! [postmark-api-key email-params]
  (http/post "https://api.postmarkapp.com/email"
    {:headers {"X-Postmark-Server-Token" postmark-api-key}
     :as :json
     :content-type :json
     :form-params email-params
     :throw-exceptions false}))

(defn email-success? [{:keys [status] :as result}]
  (< status 400))

(defn send-email-w-error-logging! [postmark-api-key email-params]
  (let [result (send-email-api-call! postmark-api-key email-params)
        success? (email-success? result)]
    (when-not success?
      (log/error (:body result)))
    success?))

(defn send-email-from-gsol!
  [{gsol-email-address :postmark/from
    :keys [biff/secret form-params] :as ctx}
   email-params]
  (send-email-w-error-logging! (secret :postmark/api-key)
    (merge
      {:From gsol-email-address}
      email-params)))

(defn send-contact-emails! 
  [{gsol-email-address :postmark/from
    :keys [form-params] :as ctx}]
  (let
    [{email-address :email
      client-name :name
      client-comment :comments
      :keys [email telephone address]}
     (cske/transform-keys csk/->kebab-case-keyword
       form-params)]
    
    (println "ran (send-contact-emails!)")
    
    (send-email-from-gsol! ctx
       {:To gsol-email-address
        :Subject "New inquiry from groundedsol.com contact page"
        :TextBody 
        (str 
          "You recieved an inquery from " 
          email-address
          (when client-name 
            (str "\n" "Name: " client-name"\n")) 
          (when telephone
            (str "\n" "Telephone: " telephone "\n")) 
          (when address
            (str "\n" "Address: " address "\n")) 
          (when client-comment 
            (str "\n" "Goals:\n" client-comment "\n")))}) 
         
    (send-email-from-gsol! ctx
       {:To email-address
        :Subject "Thanks for contacting Grounded Solutions"
        :TextBody "We will contact you shortly to discuss your beautiful Florida Native landscape.🌻 \n https://groundedsol.com\n352-219-5381"})))

;; ___________________________________ .
  
  
(defn valid-email-str? [s]
  (and
    (auth/email-valid? {} s)
    (some? s)
    (string? s)
    (string/includes? s "@")
    (not= "" s)
    (<= (count s) 100)))

(defn contact-sent []
  [:p "Your form has been successfully submitted."])

(defn contact-error-message [h]
  [:div
   [:p "Sorry, there appears to be a problem with the form you submitted."]
   [:p.form-correction h]]) 

(defn recaptcha-callback [fn-name form-id]
  [::hiccup/unsafe-html
   (str
     "<script>"
     "function " fn-name "(token) { "
     "document.getElementById('" form-id "').requestSubmit();"
     "}"
     "</script>")])

(defstyled error-text :div 
  :text-sm :text-green-600
  ([error]
    [:<>
     (case error
       "empty form"
       "Please enter your contact information."

       "invalid email"
       "Email address is invalid. Please update."
       
       "missing required fields"
       "Please enter the required* fields."
       
       "recaptcha"
       "You failed the recaptcha test. Please try again, and make sure you aren't blocking scripts from Google."
       
       default-form-message
       )]))

(defn contact-form 
  [{:keys [recaptcha/site-key form-params params ::render-recaptcha] :as ctx}]
  (let [error (:error params)
        {email-address :email
         client-name :name
         client-comment :comment
         :keys [email telephone address]
         :as form-params}
        (cske/transform-keys csk/->kebab-case-keyword
          form-params)]
    [:div#contact
     [schedule-title
      "Schedule a Consultation"]
  
     (when error
       [error-text error])
  
     (biff/form
       {:hx-post "/send-contact"
        :hx-swap "outerHTML"
        :hx-target "#contact"
        :id "contact-form"
        :hidden {:on-error "/"}
        :_ (when render-recaptcha
             (str "init call grecaptcha.render('g-recaptcha-id', {sitekey:'" site-key "'})"))
          ;;  :hx-disabled-elt "this"
        }
  
       (recaptcha-callback "submitContact" "contact-form")
  
       [:div.inputs
        [contact-input
         {:name "email"
            ;;  :type "email"
          :value email
          :autocomplete "email"
          :required? true
          :error? (= error "invalid email")
          :placeholder "Enter your email address"}]
  
        [contact-input
         {:name "name"
          :required? true
          :value client-name}]
  
        [contact-input
         {:name "telephone"
          :type "tel"
          :required? true
          :value telephone}]
  
        [contact-input
         {:name "address"
          :type "text"
          :value address
          :required? true}]
  
        [comments-input]]
  
       [email-submit-btn
        (merge
          (when site-key
            {:data-sitekey site-key
             :data-callback "submitContact"})
          {:type "submit"
           :class '[g-recaptcha]
           :id "g-recaptcha-id"})
  
        "Send"]
  
       [recaptcha-disclosure])]))
     

(defn passed-recaptcha? [{:keys [biff/secret biff.recaptcha/threshold params]
                          :or {threshold 0.5}}]
  (let [{:keys [success score]}
        (:body
         (http/post "https://www.google.com/recaptcha/api/siteverify"
           {:form-params {:secret (secret :recaptcha/secret-key)
                          :response (:g-recaptcha-response params)}
            :as :json}))]
    (and success (or (nil? score) (<= threshold score)))))


(defn handle-form-submission 
  [{:keys [form-params params] :as ctx}]
  (let [captcha-passed? (passed-recaptcha? ctx)
        
        {email-address :email
         client-name :name
         client-comment :comment
         :keys [email telephone address]
         :as form-params}
        (cske/transform-keys csk/->kebab-case-keyword
          form-params)
        
        all-fields (vec (vals form-params))
        
        required-fields
        [client-name
         email-address
         telephone
         address]
        
        error 
        (cond
          (every? fresh-field? all-fields)
          "empty form"

          (not (valid-email-str? email-address))
          "invalid email"

          (not 
            (every? filled-returned-field required-fields))
          "missing required fields") ]
    
    (and (not (some? error)) captcha-passed?
      (send-contact-emails! ctx))
    (if error
      [contact-form 
       (-> ctx
         (assoc-in [:params :error] error)
         (assoc ::render-recaptcha true))]
      [contact-sent])))

;; example response header, instead of returning html
(comment 
  {:status 303
     :headers {"location" (str "/contact?success=" "success")}})

  
(defn page [{:keys [path-params] :as ctx}]
  (ui/page
    (assoc ctx ::ui/recaptcha true)
    [container
     (contact-form ctx)]))


    