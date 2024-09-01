(ns gs.pages.email_confirmation
  (:require
    [gs.content :as content]
    [gs.components :as c]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]))


(def page-hiccup
  [c/container
   [c/page-title "Thank You"]
   [c/fancy-divider]
   [:p "Your form has been successfully submitted and we should be receiving it shortly." [:br] "We make every
   attempt to answer within one business day." [:br] "Please note that we keep your email address confidential."]
   [:p [:img {:alt "" :src "img/samples/mail.png"}]]
   [:p " "]])

