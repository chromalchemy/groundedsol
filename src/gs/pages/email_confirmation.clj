(ns gs.pages.email_confirmation
  (:require
    [gs.content :as c]
    [gs.components :as common]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]))


(def page-hiccup
  [common/container
   [common/page-title "Thank You"]
   [common/fancy-divider]
   [:p "Your form has been successfully submitted and we should be receiving it shortly." [:br] "We make every
   attempt to answer within one business day." [:br] "Please note that we keep your email address confidential."]
   [:p [:img {:alt "" :src "images/samples/mail.png"}]]
   [:p " "]])

