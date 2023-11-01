(ns gs.pages.contact
  (:require
    [clojure.string :as string]
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [gs.content :as content]
    [garden.selectors :as gs]
    [garden.core :refer [css]]
    [lambdaisland.ornament :refer [defstyled]]
    [gs.components :as common]
    [camel-snake-kebab.core :as csk]
    [gs.airtable :as at]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]
    [gs.color :as color]
   [gs.groundedsol.ui :as ui])
  (:use
    [gs.util]
    [gs.components]))


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

(defstyled captcha-button :div.g-recaptcha
  ([]
   [:<>
    {:data-sitekey "6Lc0QbgkAAAAAMQbmtTZM3-qeduE8iReMNkzir5-"}]))

(do
  (defstyled submit-btn :div
    ;{:background-color}
    :flex :sm:justify-end :justify-center
    [:button
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
     [(gs/& gs/active) :bg-red-500]
     :text-lg
     :select-none
     {:font-family "'Oswald', Verdana, Helvetica, sans-serif"}
     :font-normal
     ;:py-2px :px-18px
     :no-underline
     :border-none
     ;:border :border-solid :border-#333
     :rounded-md]
    ([]
     [:<>
      [:button.g-recaptcha
       {:data-sitekey "6Lc0QbgkAAAAAMQbmtTZM3-qeduE8iReMNkzir5-"
        :data-callback "onSubmit"
        :data-action "submit"}
       "Send"]]))
      ;[:div.g-recaptcha
      ; {:data-sitekey "6Lc0QbgkAAAAAMQbmtTZM3-qeduE8iReMNkzir5-"}]
      ;[:input#submit-btn
      ; {:value "Send"
      ;  :type "submit"}]]))
      ;[:label
      ; {:for "submit-btn"}]]))
  (html [submit-btn]))

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
  :items-center :flex :gap-8 :flex-col :sm:flex-row :tracking-wide
  [:p :text-xl :leading-relaxed :text-lg :leading-normal]
  [:a.contact :text-#008550 :font-black :uppercase]
  ([]
   [:<>
    [:p
     "To schedule a consultation, please email me at "
     [:a.contact {:href "mailto:groundedsolution@gmail.com"}
      "groundedsolution@gmail.com"]
     ", or call "
     [:a.contact {:href "tel:352-219-5381"}
      "352-219-5381"]
     ", I will contact you shortly to help create your beautiful "
     [:em "Florida"]
     " garden."]
    #_[:p
     "To schedule a consultation, please leave your name and information. "
     "We will contact you as soon as we can to help create your beautiful" [:em " Florida "] "garden."]
    [flower]]))

(comment

  (println
    (css
      [:div
       [:& placeholder
        {:color "red"}]]))

  (println
    (css
      [:div (a ":hover")
       {:color "red"}])))

(gs/defselector a)

(defstyled input :input
  :rounded-lg
  :w-full
  ;:min-w-100px :md:min-w-auto
  :text-md
  :focus:outline-none :focus:border-#b8ecd5
  :bg-#f7fffb
  :block :p-3
  :text-#076100
  :border-5 :border-#deebe4 :border-solid
  :font-sans
  :box-border)

(defstyled label :label
  :hidden :sans-serif)

(defstyled required-label label
  :font-bold :text-red-500)

(defstyled textarea :textarea
  :mb-4
  :box-border
  :w-100%
  ;:max-w-100%
  input
  :block)

(gs/defpseudoelement placeholder)


(def form-data
  [{:field-name :first-name}
   {:field-name :last-name}
   {:field-name :email}
   {:field-name :telephone
    :required? false}])


(defstyled contact-input-block :div
  :w-full
  ([{:keys [field-name required?]
     :or {required? true}}]
   (let
     [hook-str (csk/->snake_case_string field-name)
      proper-str
      (-> field-name name (string/replace "-" " ") string/capitalize)]
     [:<>
      ;[required-label
      ; {:for hook-str}
      ; proper-str]
      [input
       {:name hook-str
        :type "text"
        :placeholder
        (if required?
          (str proper-str "*") proper-str)}]])))

(defstyled comments-input :div
  ([]
   [:<>
    [required-label
     {:for "comments"}
     "Comments or Questions?"]
    [textarea
     {
      :cols "30"
      :placeholder "Comments or Questions?*"
      ;:maxlength "1000"
      :name "comments"
      :rows "7"}]]))


(do

  (defstyled field-row :div
    :flex :gap-3 :mb-4 :flex-wrap :sm:flex-nowrap
    :justify-center #_:md:justify-start)

  (defstyled contact-form :form ;.contact-form
    :block
    :mt-6 :p-8 :pb-8 :pt-0 :mt-8
    :mb-1
    [placeholder #_:font-bold :italic :text-green-500 :tracking-wider]
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
       :method "POST"}
       ;:name "contactform"}
      [field-row
       (for [x [{:field-name :first-name}
                {:field-name :last-name}]]
         [contact-input-block x])]
      [field-row
       (for [x [{:field-name :email}
                {:field-name :telephone
                 :required? false}]]
         [contact-input-block x])]
      [comments-input]
      ;[captcha-button]
      [submit-btn]]))

  (html [contact-form]))



(defstyled contact-title :h1.title
  :text-center :text-4xl :tracking-wider
  {:color color/gold-yellow})

(defstyled contact-intro :div
  :mx-auto
  [contact-title]
  [:p :text-lg :text-center :tracking-wider]
  ([]
   [:<>
    [contact-title "Thank you for visiting Grounded Solutions"]
    [:p "We are here to help you get started with a sustainable landscape!"]]))

(defstyled social-block :div #_common/flex-stack
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


(defstyled contact-block :div#contact-block)


(defstyled error-block :div#contact-error-warning
  :text-red-500 :hidden :text-center :my-2
  ([]
   [:<>
    "Sorry, there appears to be a problem with the form you submitted."
    [:br]
    "Please check that the required fields are included. Thank you."]))

(defstyled sucess-block container
  :hidden
  :text-center
  [:p :center]
  [:img :center]
  ([]
   [:<>
    {:id "contact-success"}
    [inside
     [:h1.center "Thank You"]
     ;[fancy-divider]
     [:p "Your form has been successfully submitted and we should be receiving it shortly." [:br] "We make
           every
           attempt to answer within one business day." [:br] "Please note that we keep your email address confidential."]
     [:img {:alt "" :src "img/samples/mail.png"}]]]))




(defstyled bottom-half :div
  ;:flex :flex-col :md:flex-row
  ;[:.flower :md:hidden]
  ([]
   [:<>
    ;[:button#mybtn
    ; {:onclick "contactFormError()"}
    ; "show error"]
    ;[:button#mybtn
    ; {:onclick "contactFormSuccess()"}
    ; "toggle success"]
    [contact-block
     [contact-text]
     #_[error-block]
     #_[contact-form]
     [bottomflower]]
    #_[sucess-block]
    [social-block
     [mobile-divider]
     [common/social-icons "img/social-icons/"]
     [facebook-widget]]]))


(do
  (def page-hiccup
    [common/container
     [contact-intro]
     [common/fancy-divider]
     [bottom-half]])


  (html page-hiccup))

(defn page [ctx]
  (ui/page
    (assoc ctx ::ui/recaptcha false)
    page-hiccup))
