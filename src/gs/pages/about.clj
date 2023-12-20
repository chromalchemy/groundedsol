(ns gs.pages.about
  (:require
    [gs.content :as content]
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [gs.content :as c]
    [gs.components :as common]
    #_[gs.airtable :as at]
    [lambdaisland.ornament :as o :refer [defstyled]]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]])
  (:use [gs.components]))


(defstyled in-the-works :blockquote.rightside
  [:p :span :font-bold]
  ([]
   [:<>
    [:p [:span "In the works"]
     content/in-the-works-text]]))

(defstyled about-me-article :div
  :mt-10px :mb-20px
  ([]
   [:<>
     [left-img {:alt "About Amanda"
                :src "img/samples/amanda2.jpg"
                :size 300}]
     [in-the-works]
     (for [text content/about-me-text]
       [:p text])]))

(defstyled horticulturist-title :h3)

(defstyled what-is-horticulturist? content-section
  ([]
   [:<>
    [horticulturist-title "What Is A Horticulturist?"]
    [heading-line]
    [:p content/horticulturalist-description]]))

(defstyled content-box-4 content-box
  :md:w-48%)

(defstyled role-title :h3
  ([title company-name]
   [:<> (str title ":")
    [:br]
    company-name]))

(defstyled role-block :div
  ([{:keys [role company experiences]}]
   [:<>
    [role-title role company]
    [card-stack
     (map card experiences)]]))

(defstyled resume :div
  :mb-100px
  ([]
   [:<>
    (map role-block content/ajm-resume-experience)]))

(defstyled biography-title :h2
  :mb-0)
  ;{:border-bottom
  ; {:width (px 1)
  ;  :style "solid"
  ;  :color "#d1c583"}})
   ;:height (em 0.2)})

(def page-hiccup
  [common/container
   [common/fancy-divider]
   [common/page-title "Meet Amanda Martin"]
   [drop-cap-p content/about-me-intro-text]
   [biography-title "Getting Growing"]
   [heading-line]
   [about-me-article]
   [what-is-horticulturist?]
   [common/fancy-divider]
   [resume]])
