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
    [gs.color :as color]))

(def divider
  (common/fancy-divider))

(def page-title
  [:h1.center "Contact Information"])

(defstyled facebook-widget :iframe
  :mb-8  :overflow-hidden :mx-auto :block :rounded
  (color/hex-k :border color/gold-yellow) :border-solid
  ([]
   ^ {:src "https://www.facebook.com/plugins/page.php?href=https://www.facebook.com/groundedsol/%2Ffacebook&tabs=timeline&width=340&height=500&small_header=false&adapt_container_width=true&hide_cover=false&show_facepile=true&appId"
      :scrolling "no"
      :height "500px"
      :width "360px"
      :frameborder "0"
      :allowTransparency "true"
      :allow "encrypted-media"}
   [:<>]))

(def captcha-button
  [:div.h-captcha {:data-sitekey "1a09d58a-4353-4310-b15d-1e78b0064ec2"}])

(defstyled contact-form :form.contactform
  :mt-6
  ([]
   ;[:form.contactform]
   ^{:action "scripts/send_form_email.php" :method "post" :name "contactform"}
   [:<>
    ;[:h4 "Contact Info"]
    [:label {:for "first_name"} "First Name *"] [:input {:maxlength "50" :name "first_name" :size "30" :type "text"}] [:label {:for "last_name"} "Last Name *"]
    [:input {:maxlength "50" :name "last_name" :size "30" :type "text"}] [:label {:for "email"} "Email Address *"] [:input {:maxlength "80" :name "email" :size "30" :type "text"}]
    [:label {:for "telephone"} "Telephone Number"] [:input {:maxlength "30" :name "telephone" :size "30" :type "text"}] [:label {:for "comments"} "Comments *"]
    [:textarea {:cols "30" :maxlength "1000" :name "comments" :rows "5"}] "* = required" [:br]
     ;captcha-button
     [:input.submit {:type "submit" :value "Submit »"}]]))




(defstyled flower :img
  :block :w-200px :mx-auto :object-none :self-start
  ([]
   ^{:alt "Flower" :src "images/samples/flowercontact.png"}
   [:<>]))

(defstyled contact-text :div
  ([]
   [:<>
    [:p.dropcap "Thank you for visiting Grounded Solutions."]
    [:p "We are here to help you get started with a sustainable landscape!"]
    [:p "To schedule your consultation please fill in the form at left and as soon as we
          can, we will contact you to create your beautiful Florida garden. — Amanda"]]))


(defstyled contact-intro common/flex-stack
  :mb-8)

(defstyled content-blocks common/flex-stack
  :w-full
  ;:border-1 :border-solid
  :space-x-12)
  ;[:* :mx-9])


(def page-hiccup
  [:div.container
   [:div.inside
    [:div.inside
     page-title
     divider
     (content-blocks
      (contact-form)
      (contact-intro
        (contact-text)
        (flower))
      [:div
       (common/social-icons "images/social-icons/")
       (facebook-widget)])
     [:p]]]])


