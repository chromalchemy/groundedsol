(ns gs.pages.consultation
  (:require
    [gs.color :as color]
    [gs.content :as content]
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [gs.content :as c]
    [garden.selectors]
    [lambdaisland.ornament :refer [defstyled]]
    [gs.airtable :as at]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]])
  (:use
    [gs.util]
    [gs.components]))

(defstyled process-title :h2)

(defstyled news-date :p.newsDate
  :relative :text-#666 :text-right
  :pr-20 :pb-10
  [::before :absolute {:font-family "FontAwesome"
                       :content "\f073"
                       :top "0"
                       :right "0"}]
  {:font-size (em 0.9)
   :letter-spacing (px 1)
   :margin-top (px -2)})

(defstyled calendar-link :a
  ([]
   [:<>
    {:href "contact.html"}
    [news-date]]))

(defstyled step-title :h6
  ;:border-none
  {:color color/gold-yellow}
  :text-center
  :mx-auto
  :self-center
  ;:w-75%
  :text-2xl
  :mb-8
  :pb-0
  :border-none
  [:span
   :inline-block]
   ;{:border-bottom-style :solid
   ; :border-color "#bdaf64"
   ; :border-bottom-width (px 1)}]
  ([s]
   [:<> [:span s]]))

(defstyled process-img-left left-img
  img-shadow
  img-rotate-left)

(defstyled process-img-right right-img
  img-shadow
  img-rotate-right)

;todo: compose this left/right stuff better? more conditionally?
(defstyled process-step :div
  :mb-12
  ([{:keys [title alt src text image-side] :as m}]
   [:<>
    ;{:style {:text-align "center"}}
    [step-title title]
    [calendar-link]
    (case image-side
      :left
      [process-img-left
       {:alt alt :src src}]
      :right
      [process-img-right
       {:alt alt :src src}])
    text
    [fancy-divider]]))

(defstyled process :div
  ([]
   [:<>
    [process-title "The Process"]
    (map process-step content/consultion-steps)]))

(defstyled page-title :h1.center)


(def page-hiccup
  [container
   [inside
    [page-title "Consultation & Design"]
    [fancy-divider]
    [process]]])
