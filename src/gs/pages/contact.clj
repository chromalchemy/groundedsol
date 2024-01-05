(ns gs.pages.contact
  (:require
   [com.biffweb :as biff :refer [pprint]]
   [clojure.string :as string]
   [hickory.core :as h]
   [cybermonday.core :as cm]
   [gs.content :as c]
   [garden.selectors :as gs]
   [garden.core :refer [css]]
   [lambdaisland.ornament :refer [defstyled]]
   [gs.components :as common]
   [camel-snake-kebab.core :as csk]
   [camel-snake-kebab.extras :as cske]
   #_[gs.airtable :as at]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE AFTER-ELEM]]
    [gs.color :as color]
   [gs.groundedsol.ui :as ui]
   [clj-http.client :as http]
   [clojure.tools.logging :as log]
   [garden.core :as garden]
   [ring.middleware.anti-forgery :as csrf]
   [com.biffweb.impl.auth :as auth])
  (:use
    [gs.util]
    [gs.components]))

(gs/defpseudoelement placeholder)

(defstyled facebook-widget :iframe
  ;:box-border
  :mb-8  #_:overflow-hidden :mx-auto :block
  :w-340px
  (color/hex-k :border "#ebe5cd" #_color/gold-yellow)
  :border-solid
  :border-30
  :border-t-50
  :border-b-50
  :text-center
  {:border-radius (px 10)}
  ([]
   [:<>
    {:src "https://www.facebook.com/plugins/page.php?href=https://www.facebook.com/groundedsol/%2Ffacebook&tabs=timeline&width=340&height=500&small_header=false&adapt_container_width=true&hide_cover=false&show_facepile=true&appId"
     :scrolling "no"
     :height "500px"
     :width "360px"
     :frameborder "0"
     :allowTransparency "true"
     :allow "encrypted-media"}]))

(def flower-img-data
  {:alt "Flower"
   :src "img/samples/flowercontact.png"})

(defstyled flower :img
   :w-150px :sm:w-150px :mx-auto :object-scale-down :self-start :hidden :md:block
  ([] [:<> flower-img-data]))

(defstyled bottomflower flower
  :block :md:hidden
  ([] [:<> flower-img-data]))

(defstyled contact-text :div
  :md:max-w-800px
  :mx-auto
  :text-lg :clear-both
  :items-center :flex :flex-col :tracking-wide
  [:p :text-xl :leading-relaxed :text-lg :leading-normal :mb-1 :text-center])

(comment
  
  (gs/defselector a)

  (println
    (css
      [:div
       [:& placeholder
        {:color "red"}]]))

  (println
    (css
      [:div (a ":hover")
       {:color "red"}])))

(defstyled label :label
  :hidden :sans-serif)

(defstyled required-label label
  :font-bold :text-red-500)

#_[required-label
   {:for hook-str}
   proper-str]

(defstyled input :input
  :rounded-lg
  :w-full
  ;:min-w-100px :md:min-w-auto
  :text-md
  :focus:outline-none :focus:border-#b8ecd5
  :bg-#f7fffb
  :block :p-3
  :border-5 :border-#deebe4 :border-solid
  :font-sans
  :box-border
  [(gs/& "::placeholder") :italic #_:text-green-600 :tracking-widest])

(comment
  (gs/->CSSSelector)
  (css
    [:input
     [(gs/+ gs/& "::placeholder") {:font-size "12px"}]])
  (gs/defselector a)
  a
  (gs/css-selector a)
  (gs/css-selector (gs/input placeholder))
  (gs/css-selector a ":hover")
  (gs/defselector input-placeholder 
    (:input placeholder))

  (css
    [ {:font-size "12px"}]))

(defstyled placeholder-text :div
  ["input::placeholder" :italic :tracking-wide])
  


(defstyled contact-input :div
  #_placeholder-text
  :w-full :block
  ["input.required::placeholder" :font-bold :text-green-600]
  [:input.error :bg-#eeffe7]
  
  ([{field-name :name
     field-value :value
     :keys [name required?]
     :or {required? false
          type "text"}
     :as params}]
   (let
     [field-label-str
      (-> field-name 
        (string/replace "-" " ") 
        (string/capitalize))
      pr 
      (do
        (println "value:")
        (pprint 
          [field-value
           (empty-returned-field? field-value)]))]
     [input
      (->
        {:placeholder
         (str field-label-str (when required? "*"))
         :class []}
        (merge params)
        (cond->> 
          required?
          (setval [:class AFTER-ELEM] "required")
          
          (empty-returned-field? field-value)
          (setval [:class AFTER-ELEM] "error")
          ))])))

(defstyled textarea :textarea
  input
  required-field
  [gs/& placeholder :text-green-600 :italic]
  
  :font-bold
  
  :mb-4
  :box-border
  :w-full
  ;:max-w-100%
  :block)

(def form-data
  [{:field-name :first-name}
   {:field-name :last-name}
   {:field-name :email}
   {:field-name :telephone
    :required? false}])

(defstyled comments-input :div
  ([]
   [:<>
    #_[required-label
       {:for "comments"}
       "Any comments or questions?"]
    [textarea
     {:cols "30"
      :placeholder "What are your landscape goals?"
      :maxlength "1500"
      :name "comments"
      :rows "7"}]]))

(defstyled field-row :div
  :flex :gap-3 :mb-4 :flex-wrap :sm:flex-nowrap
  :justify-center #_:md:justify-start)

(defstyled old-contact-form :form ;.contact-form
  :block
  :mt-6 :p-8 :pb-8 :pt-0 :mt-8
  :mb-1
  #_[placeholder #_:font-bold :italic :text-green-500 :tracking-wider]
    ;:w-full
  :rounded-2xl
    ;:border-black :border-solid :border-4
  :md:max-w-800px
  :mx-auto
    ;{:background {:color "#fcfbf9"}}
     ;[:div #_:w-50%]]
  ([]
   [:<>
    {:id "contact-form"
     :action "send_form_email.php"
     :method "POST"}]))
       ;:name "contactform"}
    
(defstyled contact-title :h1.title
  :text-center :text-3xl :tracking-wider
  {:color color/gold-yellow})

(defstyled social-block :div #_flex-stack
  ;:md:w-33%
  :mx-auto
  :mb-10)
  ;border)
  ;:flex :md:flex-row :flex-col
  ;:w-full
  ;:border-1 :border-solid
  ;:space-x-12)
  ;[:* :mx-9])

(defstyled mobile-divider :hr
  :my-4 :md:hidden)

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
      client-comment :comment
      :keys [email telephone address]}
     (cske/transform-keys csk/->kebab-case-keyword
       form-params)]
    
    (println "ran (send-contact-emails!)")
    [contact-form ctx]

    #_(send-email-from-gsol! ctx
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
         
    #_(send-email-from-gsol! ctx
       {:To email-address
        :Subject "Thanks for contacting Grounded Solutions"
        :TextBody "We will contact you shortly to discuss your beautiful Florida Native landscape.🌻 \n https://groundedsol.com\n352-219-5381"})))



;; ___________________________________ .

(defstyled email-input :input
  contact-input)
  ;; :w-300px :mr-2 :text-lg :px-2 :py-1
  

(defstyled email-submit-btn :button
  :text-lg :p-1 :bg-green-500 :text-white :rounded :border-0 :px-3 :rounded
  :tracking-wider
  :box-border
       ;:h-20
       ;:w-40
  :py-1
  :px-8
  :inline-block
  :text-center
  :mt-10px :mb-20px :my-0 :cursor-pointer
  :text-#333
  :hover:no-underline
  :bg-green-500 :text-white
  :hover:border-green-600 #_:hover:text-green-600
  :hover:bg-green-600
  #_[(gs/& gs/active) :bg-red-500]
  :text-lg
  :select-none
  {:font-family "'Oswald', Verdana, Helvetica, sans-serif"}
  :font-normal
       ;:py-2px :px-18px
  :no-underline
  :border-none
       ;:border :border-solid :border-#333
  :rounded-md)

(defn valid-email-str? [s]
  (and
    (some? s)
    (string? s)
    (string/includes? s "@")
    (= "")
    (<= (count s) 100)))


(defstyled contact-sent container
  :text-center
  [:h1 :text-center]
  [:p :text-center]
  [:img :mx-auto]
  ([]
   [:<>
    [:h1 "Thanks for your interest"]
    [:p "Your form has been successfully submitted."
     [:br]
     "We make every attempt to answer within one business day."
     [:br]
     "Please note that we keep your email address confidential."]
    [:img {:alt "" :src "img/samples/mail.png"}]]))

(defstyled contact-error-message :div
  :my-2
  :text-red-500  :text-center :text-sm
  [:.form-correction :text-green-500]
  ([h]
   [:<>
    [:p "Sorry, there appears to be a problem with the form you submitted."]
     
    [:p.form-correction 
     h]])) 


(defn recaptcha-callback [fn-name form-id]
  [::hiccup/unsafe-html
   (str "<script>"
     "function " fn-name "(token) { "
     
     "htmx.ajax('POST', '/send-contact', {target:'#"
     form-id
     "', swap:'outerHTML'});"
     
     
    ;;  "document.getElementById('" form-id "').submit();"
     
     "}"
     "</script>")])

(comment
  (hiccup/render
    (recaptcha-callback "submitContact" "contact-form")
    {:doctype? false}))
  

(defstyled schedule-title :h2
  :text-center)

(def default-form-message 
  [:<>
   [:p
    #_"Please leave your name and contact information. "
    "We will reach out as soon as we can" [:br]
    "to help create your beautiful Florida garden."]
   #_[:span.start "Let's starts the conversation."]])

(defstyled contact-form :div#contact
    :my-4 :p-4
    :text-center
    :text-lg
    [:.inputs :w-75% :mx-auto
     :flex :flex-col :gap-2]
  [:.start :italic :mb-4 :block]
  [:form 
   :block
   :p-8 :pt-0
   :mt-8 :mb-1
   :rounded-2xl
   :md:max-w-800px
   :mx-auto
       #_{:background {:color "#fcfbf9"}}
        #_[:div #_:w-50%]]
     
  
  ([{:keys [recaptcha/site-key form-params contact-error] :as ctx}]
   (let [{email-address :email
          client-name :name
          client-comment :comment
          :keys [email telephone address]
          :as form-params}
         (cske/transform-keys csk/->kebab-case-keyword
           form-params)]
     
     
     
     (do
       (println "ran contact form")
       (println "email form params")
       (pprint form-params)
       )
     
     [:<>
      [schedule-title
       "Schedule a Consultation"]
      
      (when contact-error
        [:div
         (case contact-error
      
           "empty form"
           "form is empty"
      
           "missing required fields"
           "please fill out all the required fields"
      
           "invalid email"
           "that email address is invalid"
      
           default-form-message)])
      
      (biff/form
        {:hx-post "/send-contact"
         :hx-swap "outerHTML"
         :hx-target "#contact"
                  ;; :action "/send-contact"
         :id "contact-form"}
                  ;; :hx-disabled-elt "this"
        
        #_(recaptcha-callback "submitContact" "contact-form")
        
        [:div.inputs
         [contact-input
          {:name "email"
           :type "email"
           :value email
           :autocomplete "email"
           :required? true}]
         
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
           #_(when site-key
               {:data-sitekey site-key
                :data-callback "submitContact"})
           {:type "submit"})
                    ;; :class '[g-recaptcha]
         
         "Send"]
        
        
        biff/recaptcha-disclosure)])))
     



(empty-returned-field? nil)

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

(comment
  (every? filled-returned-field
    ["" "" "" ""]))


(defn handle-form-submission 
  [{:keys [form-params] :as ctx}]
  (let [
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

          (not 
            (every? filled-returned-field required-fields))
          "missing required fields"

          (not (valid-email-str? email-address))
          "invalid email"

          )
        ]
    (println "")
    (println "testing:")
    (println "form params")
    (pprint form-params)
    (pprint 
      [client-name
       email-address
       telephone
       address])
    (pprint
      (not 
        (every? filled-returned-field 
             [client-name
              email-address
              telephone
              address])))
    (println "")
    
    
    (when-not error
      (send-contact-emails! ctx))
    (if error
      [contact-form (assoc ctx :contact-error error)]
      [contact-sent])))



;; ___________________________________ .

(def page-hiccup
  [:<>])
  
(defstyled hello :div 
  :text-xl :text-black)

(defstyled contact-links :div
  :px-4 :md:px-2
  :flex :gap-3 :md:gap-8 :justify-around :flex-col :md:flex-row
  :mb-2 :md:mb-0
  [:a  :inline-block 
   :text-green-600 :text-2xl  :md:text-2xl :no-underline :font-bold :tracking-normal 
   :mx-auto :text-center]
   
  ["a:before" :text-gray-300] 
  ;;  :text-4xl :mr-2 :inline-block
   
  ([]
   [:<>
    #_"You can send any questions directly to"
    [email-link]
    #_", or call me at "
    [phone-link]]))

(defn page [{:keys [path-params] :as ctx}]
  ;; (println "contact page path params")
  ;; (pprint path-params)
  (ui/page
    (assoc ctx ::ui/recaptcha false)
    [container
     [contact-title "We are here to help you get started with a sustainable landscape!"]
     [fancy-divider]
     [contact-text
      [contact-links]
      [bottomflower]]
     (contact-form ctx)
     [flower]
     [social-block
      [mobile-divider]
      [social-icons "img/social-icons/"]
      [facebook-widget]]]))


(defn contact-result-page [{:keys [path-params] :as ctx}]
  (println "contact page results path params")
  (pprint path-params)
  (ui/page
    (assoc ctx ::ui/recaptcha true)
    [container
     #_[contact-title "Success!"]
     #_[fancy-divider]
     #_[contact-text
        [contact-links]
        [bottomflower]]
     (contact-form ctx)
     [flower]
     [social-block
      [mobile-divider]
      [social-icons "img/social-icons/"]
      [facebook-widget]]]))
      
     
    