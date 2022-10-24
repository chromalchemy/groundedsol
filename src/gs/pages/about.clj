(ns gs.pages.about
  (:require
    [gs.content :as content]
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [gs.content :as c]
    [gs.airtable :as at]
    [lambdaisland.ornament :as o :refer [defstyled]]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]])
  (:use [gs.components]))

(defstyled page-title :h1.center)



(defstyled in-the-works :blockquote.rightside
  [:p :span :font-bold]
  ([]
   [:<>
    [:p [:span "In the works"]
     content/in-the-works-text]]))

(defstyled about-me-article :div
  :mt-10 :mb-20
  ([]
   [:<>
     [left-img {:alt "About Amanda"
                :src "images/samples/amanda2.jpg"
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

(defstyled content-box :section
  :w-98% :m-1% :p-0)

(defstyled content-box-4 content-box
  :md:w-48%)

(defstyled experience-item content-box
  ([{:keys [img-path text img-alt-text]}]
   [:<>
    [:p
     [left-img
      {:alt img-alt-text
       :size 125
       :src (str "images/" img-path)}]
     text]]))

(defstyled role-title :h3
  ([title company-name]
   [:<> (str title ":")
    [:br]
    company-name]))

(defstyled role-block :div
  ([{:keys [role company experience-items]}]
   [:<>
    [role-title role company]
    [group
     (map experience-item experience-items)]]))

(defstyled resume :div
  ([]
   (map role-block content/ajm-resume-experience)))

(defstyled biography-title :h2)

(def page-hiccup
  [:<>
   [container
    [inside
     [page-title "Meet Amanda Martin"]
     [drop-cap-p content/about-me-intro-text]
     [biography-title "Getting Growing"]
     [heading-line]
     [about-me-article]
     [what-is-horticulturist?]]]])
     ;[fancy-divider]
     ;[resume]]]]
