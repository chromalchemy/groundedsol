(ns gs.pages.notes
  (:require
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [gs.content :as content]
    [gs.components :as c]
    [lambdaisland.ornament :as o]
    [lambdaisland.ornament :refer [defstyled]]
    [garden.selectors :as gs]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]
    [clojure.string :as string]
    [gs.color :as color])
  (:use
    [gs.color]
    [gs.util]))

;toolbar=0&




(defstyled pdf-block :div
  ([filename link-text]
   [:<>
    [c/pdf-link filename link-text]
    [c/pdf-embed filename]]))


;[:p.description "Includes Seasonal Planting Guide, & how to \"Prune like Fire\"!"]]]))

(defstyled container c/container)

(def page-hiccup
  [c/container
   [c/page-title "Garden Notes"]
   [c/fancy-divider]
   [pdf-block 
    "Pruning_Notes_for_Native_Plants_3" 
    "Prune Like Fire!"]
   [c/fancy-divider]
   [pdf-block "Convert your Landscape" 
    "Convert your Landscape"]
   (c/spacer 20)])

