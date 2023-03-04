(ns gs.pages.new
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

(defstyled page-title :h1
  :text-center :text-#d1c583
  ([]
   [:<>
    "New Postings & Buzzings About"]))



(defstyled pruning-notes-pdf-link :div
  :text-2xl :text-center
  [:a :mx-3]
  [:.new :text-red-500 :font-bold]
  [:.link-type :text-xl :text-gray-300]
  [:.description :text-lg :mt-2]
  ([]
   [:<>
    ;[:span.new "New!"]
    [:a
     {:href "/media/Pruning_Notes_for_Native_Plants_3.pdf"}
     "Pruning Notes Newsletter"]
    [:span.link-type "(pdf)"]
    [:p.description "Includes Seasonal Planting Guide, & how to \"Prune like Fire\"!"]]))


(defstyled container common/container
  :pb-12)

(def page-hiccup
  [:<>
   [container
    [:div.inside
     [page-title]
     [common/fancy-divider]
     [pruning-notes-pdf-link]]]])
