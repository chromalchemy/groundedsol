(ns gs.pages.notes
  (:require
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [gs.content :as c]
    [gs.components :as common]
    [lambdaisland.ornament :as o]
    [lambdaisland.ornament :refer [defstyled]]
    [garden.selectors :as gs]
    [gs.airtable :as at]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]
    [clojure.string :as string]
    [gs.color :as color])
  (:use
    [gs.color]
    [gs.util]))

;toolbar=0&

(defstyled pdf-embed :embed
  :w-full :h-1200px  :md:h-2200px :md:w-850px :block :mx-auto
  ([filename]
   [:<> {:src (str "/media/" filename ".pdf" "#navpanes=0&scrollbar=0") :type "application/pdf"}]))


(defstyled pdf-link :div
  :text-2xl :text-center
  [:a :mb-4 :inline-block]
  [:.link-type :text-lg :text-gray-300 :ml-4]
  [:.description :text-lg :mt-2]
  ([filename link-text]
   [:<>
    [:a
     {:href
      (str "/media/" filename ".pdf")}
     link-text
     [:span.link-type "(pdf)"]]]))



(defstyled pdf-block :div
  ([filename link-text]
   [:<>
    [pdf-link filename link-text]
    [pdf-embed filename]]))


;[:p.description "Includes Seasonal Planting Guide, & how to \"Prune like Fire\"!"]]]))

(defstyled container common/container)

(def page-hiccup
  [common/container
   [common/page-title "Garden Notes"]
   [common/fancy-divider]
   [pdf-block "Convert your Landscape" "Convert your Landscape"]
   [common/fancy-divider]
   [pdf-block "Pruning_Notes_for_Native_Plants_3" "Pruning Notes Newsletter"]
   (common/spacer 20)])

