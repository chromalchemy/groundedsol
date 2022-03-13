(ns gs.pages.email_confirmation
  (:require
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [gs.content :as c]
    [gs.components :as common]
    [gs.airtable :as at]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]))

(def divider
  (common/fancy-divider))

(def page-title
  [:h1.center "Thank You"])

(def raw-page
  [:div.container
   [:div.inside
    page-title
    divider
    [:p.center "Your form has been successfully submitted and we should be receiving it shortly." [:br] "We make every
		attempt to answer within one business day." [:br] "Please note that we keep your email address confidential."]
    [:p.center [:img {:alt "" :src "images/samples/mail.png"}]]
    [:p.center " "]]])

(def content-blocks
  [raw-page])

(common/write-page
  :email-confirmation
  content-blocks)