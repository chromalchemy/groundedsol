(ns groundedsol.pages.contact
  (:require
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [groundedsol.content :as c]
    [groundedsol.common :as common]
    [groundedsol.airtable :as at]
    [groundedsol.util :as u]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]))

(def divider
  (common/fancy-divider))

(def page-title
  [:h1.center "Contact Information"])

(def facebook-widget
  [:iframe
   {:src "https://www.facebook.com/plugins/page.php?href=https://www.facebook.com/groundedsol/%2Ffacebook&tabs=timeline&width=340&height=500&small_header=false&adapt_container_width=true&hide_cover=false&show_facepile=true&appId"
    :width "340"
    :height "500"
    :style {:border "none" :overflow "hidden"}
    :scrolling "no"
    :frameborder "0"
    :allowTransparency "true"
    :allow "encrypted-media"}])

(def captcha-button
  [:div.h-captcha {:data-sitekey "1a09d58a-4353-4310-b15d-1e78b0064ec2"}])

(def raw-page
  [:div.container
   [:div.inside
    [:div.inside
     page-title
     divider
     [:div.group
      [:section.contentBox2a
       [:h4 "Contact Form"]
       [:form.contactform {:action "scripts/send_form_email.php" :method "post" :name "contactform"}
        [:label {:for "first_name"} "First Name *"] [:input {:maxlength "50" :name "first_name" :size "30" :type "text"}] [:label {:for "last_name"} "Last Name *"]
        [:input {:maxlength "50" :name "last_name" :size "30" :type "text"}] [:label {:for "email"} "Email Address *"] [:input {:maxlength "80" :name "email" :size "30" :type "text"}]
        [:label {:for "telephone"} "Telephone Number"] [:input {:maxlength "30" :name "telephone" :size "30" :type "text"}] [:label {:for "comments"} "Comments *"]
        [:textarea {:cols "30" :maxlength "1000" :name "comments" :rows "5"}] "* = required" [:br]
        ;captcha-button
        [:input.submit {:type "submit" :value "Submit »"}]]]
      [:section.contentBox2b
       [:p.dropcap "Thank you for visiting Grounded Solutions."]
       [:img.img-right {:alt "Flower" :src "images/samples/flowercontact.png"}]
       [:p "We are here to help you get started with a sustainable landscape!"]
       [:p "To schedule your consultation please fill in the form at left and as soon as we
         can, we will contact you to create your beautiful Florida garden. — Amanda"]
       [:p
        facebook-widget]]]
     [:p]]]])

(def content-blocks
  [raw-page])

(defn write-page []
  (common/write-page
    :contact
    content-blocks)
  (println "bulding contact page"))