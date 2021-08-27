(ns groundedsol.pages.email_confirmation
  (:require
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [groundedsol.content :as c]
    [groundedsol.common :as common]
    [groundedsol.airtable :as at]
    [groundedsol.util :as u]
    [rum.core :as rum]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]))

(def divider
  [:hr.fancy])

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