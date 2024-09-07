(ns gs.pages.consultation
  (:require
    [gs.color :as color]
    [gs.content :as content]
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [garden.selectors]
    [lambdaisland.ornament :refer [defstyled]]
    #_[gs.airtable :as at]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]
    [gs.components :as c]
   [gs.groundedsol.ui :as ui]
            )
  (:use
    [gs.util]))


(defstyled process-title :h2)

(defstyled calendar-icon :span.newsDate
   :text-#666 :text-left :relative
  [::before :absolute
   {:font-family "FontAwesome"
    :content "\f073"
    :top "0"
    :right "0"}]
  {:font-size (em 0.9)
   :letter-spacing (px 1)})


(defstyled calendar-link :a.calendar-link
  ([]
   [:<>
    {:href "/contact"}
    [calendar-icon]]))

(defstyled step-title :h6
  :mb-8
  :pb-0
  :border-none
  :flex
  :items-center
  [:.title-text 
   :text-center :mx-auto
   {:color color/gold-yellow}
   :text-2xl]
  [:a.calendar-link :block
   {:white-space "nowrap"}]
   ;{:border-bottom-style :solid
   ; :border-color "#bdaf64"
   ; :border-bottom-width (px 1)}]
  ([s]
   [:<> 
    [:div.title-text s]
    [calendar-link]]))

(defstyled process-img-left c/left-img
  c/img-shadow
  c/img-rotate-left
  :mt-2 :ml-0 :mr-8 :mb-4)


(defstyled process-img-right c/right-img
  c/img-shadow
  c/img-rotate-right
  :mt-1 :mr-0 :ml-8 :mb-4)

;todo: compose this left/right stuff better? more conditionally?
(defstyled process-step :div
  :mb-12
  [:p :mt-0]
  ([{:keys [title alt src text image-side] :as m}]
   [:<>
    ;{:style {:text-align "center"}}
    [step-title title]
    [:div
     (case image-side
       :left
       [process-img-left
        {:alt alt :src src}]
       :right
       [process-img-right
        {:alt alt :src src}])
     text]
    [c/fancy-divider]]))

(defstyled process :div
  ([]
   [:<>
    [process-title "The Process"]
    (for [step content/consultion-steps]
      [process-step step])]))

(def page-hiccup
  [c/container
   [c/page-title "Consultation & Design"]
   [c/fancy-divider]
   [process]])

(defn page
  [{:keys [recaptcha/site-key params] :as ctx}]
  (ui/page
    (assoc ctx 
      ::ui/recaptcha false
      #_:base/title
      #_"Grounded Solutions Native Landscape Consultation & Design")
    page-hiccup))