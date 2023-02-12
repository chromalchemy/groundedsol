(ns gs.pages.contact
  (:require
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [gs.content :as c]
    [garden.selectors :as gs]
    [lambdaisland.ornament :refer [defstyled]]
    [gs.components :as common]
    [gs.airtable :as at]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]
    [gs.color :as color])
  (:use
    [gs.components]))


(def page-title
  [:h1.center ""])

(defstyled facebook-widget :iframe
  :mb-8  :overflow-hidden :mx-auto :block :rounded
  (color/hex-k :border color/gold-yellow) :border-solid
  ([]
   [:<>
    {:src "https://www.facebook.com/plugins/page.php?href=https://www.facebook.com/groundedsol/%2Ffacebook&tabs=timeline&width=340&height=500&small_header=false&adapt_container_width=true&hide_cover=false&show_facepile=true&appId"
     :scrolling "no"
     :height "500px"
     :width "360px"
     :frameborder "0"
     :allowTransparency "true"
     :allow "encrypted-media"}]))

(def captcha-button
  [:div.h-captcha {:data-sitekey "1a09d58a-4353-4310-b15d-1e78b0064ec2"}])

(defstyled submit-btn :input
  :rounded-md
  :text-center :w-100px :mt-10px :mb-20px :my-0 :cursor-pointer :text-#333
  :hover:no-underline :hover:text-green-600
  :hover:border-green-600
  :text-lg
  {:font-family "'Oswald', Verdana, Helvetica, sans-serif"}
  :py-2px :px-18px :no-underline
  :border :border-solid :border-#333 :rounded-sm)


(defstyled flower :img
  :w-150px
  :block :sm:w-250px :mx-auto :object-scale-down :self-start
  ([]
   [:<>
    {:alt "Flower" :src "images/samples/flowercontact.png"}]))

(defstyled contact-text :div
  :text-lg :clear-both
  :items-center :flex :gap-8 :flex-col :sm:flex-row
  [:p :text-xl :leading-relaxed]
  ([]
   [:<>
    [:p
     "To schedule your consultation please fill in the form at left and as soon as we
          can, we will contact you to create your beautiful Florida garden."]
    [flower]]))

(defstyled contact-form :div
  :mt-6 :p-8 :rounded-3xl
  :mb-12
  :border-yellow-700
  :border-opacity-10
  :border-solid :border-8px
  {:background {:color "#fcfbf9"}}
  [:.row :flex :gap-12 :clear-both
   [:label :text-base]
   [:div :w-50%]
   [:input :w-full :bg-#ffffff :rounded-md]]
  [:.comments :w-full :bg-#ffffff :rounded-md]
  ([]
   [:<>
    ;[:h3 "Contact"]
    [contact-text]
    [:span
     {:style {:float "right"}}
     "* = required"]
    [:br]
    [:form.contactform
     {:action "scripts/send_form_email.php"
      :method "post"
      :name "contactform"}
     [:div.row
      [:div
       [:label {:for "first_name"} "First Name *"]
       [:input {:maxlength "50" :name "first_name" :type "text"}]]
      [:div
       [:label {:for "last_name"} "Last Name *"]
       [:input {:maxlength "50" :name "last_name"  :type "text"}]]]
     [:div.row
      [:div
       [:label {:for "email"} "Email *"]
       [:input {:maxlength "80" :name "email" :size "30" :type "text"}]]

      [:div
       [:label {:for "telephone"} "Telephone"]
       [:input {:maxlength "30" :name "telephone" :size "30" :type "text"}]]]

     [:label {:for "comments"} "Comments *"]
     [:textarea.comments {:cols "30" :maxlength "1000" :name "comments" :rows "5"}]

     ;captcha-button
     [submit-btn { :value "Submit"}]]]))



(defstyled contact-intro :div
  :mx-auto
  [:h1.title  :text-center :text-4xl]
  [:p :text-lg :text-center]
  ([]
   [:<>
    [:h1.title "Thank you for visiting Grounded Solutions"]
    [:p
      "We are here to help you get started with a sustainable landscape!"]]))

(defstyled content-blocks common/flex-stack
  :w-full
  ;:border-1 :border-solid
  :space-x-12)
  ;[:* :mx-9])



(def page-hiccup
  [:<>
   [common/container
    ;[common/inside]
    #_page-title
    [contact-intro]
    [common/fancy-divider]
    [content-blocks
     [:div
      [common/social-icons "images/social-icons/"]
      [facebook-widget]]


     [contact-form]]]])



