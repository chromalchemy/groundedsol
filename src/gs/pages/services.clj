(ns gs.pages.services
  (:require
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [gs.content :as c]
    [gs.components :as common]
    [lambdaisland.ornament :refer [defstyled]]
    [gs.airtable :as at]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]))


(def divider
  (common/fancy-divider))


(def consult-level
  [{:title "Site Analysis Consultation"
    :price "$150"
    :time "1 hour"
    :notes
      "We will walk your yard and discuss opportunities, problematic areas, and how to bring the most out of your landscape."}
   {:title "Small Area Design, Hand Drawn"
    :price "$400 - 600"
    :time "2-3 hours"
    :notes
      (list
        "The first run at a creative interpretation of a new landscape."
        " "
        "You may just want some ideas, versus a detailed plan. Not to scale. ")}
   {:title "Full Property Digital Design"
    :price "$1,200 - $1,600"
    :time "3-4 Weeks"
    :notes
      (list
        [:p
         "This time is used to brainstorm, sketch, and plan landscape details, with long-term plant development in mind."]
        " "
        [:p
         "I typically collect a $650 deposit (towards the full cost) at the site analysis consultation, to get us started."
         " "
         "Please have a property survey available to work from. A digital copy is best."])}])

(defstyled example-block :div
  :mt-8
  :text-center)

(def examples
  (example-block
   "More native plant photos and horiticultural information on my "
   [:a {:href "https://www.facebook.com/groundedsol/" } "Facebook"]
   " and "
   [:a {:href "https://www.instagram.com/groundedsolution/" } "Instagram"]
   " pages."
   " "
   [:br] [:br]
   "Tour a full native landscape by Grounded Solutions"
   ;[:a {:href "https://youtu.be/ewdExZt_98c" } "project tour video"]
   [:br]
   [:iframe {:style {:margin "auto" :display "block"} :width "560" :height "315" :src "https://www.youtube.com/embed/ewdExZt_98c" :title "YouTube video player" :frameborder "0" :allow "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" :allowfullscreen "true"}]
   [:p "Please Like and Follow as we grow"]
   [:br]))

(defstyled rate-title :span
  :font-bold
  :text-2xl
  {:font-family "'Poiret One', Verdana, Helvetica, sans-serif"})

(defstyled rate-price :span
  :ml-2
  :text-xl
  {:font-family "'Poiret One', Verdana, Helvetica, sans-serif"})

(defstyled rate-notes :span)

(defstyled rate-time :span)

(defstyled rate-item-block :div
  :mb-4)

(def rates-list
  (for [c consult-level]
    (let [{title :title
           price :price
           time-est :time
           notes :notes} c]
      (rate-item-block
       (rate-title title)
       " "
       (rate-price price)
       [:br]
       [:div
        {:style {:padding-left "15px"}}
        ;"Typically takes "
        (rate-time time-est)
        [:br]
        (rate-notes notes)]))))


(def rates
  [:<>
   [:h1.center "Services & Rates"]
   divider
   rates-list
   [:p
    {:style {:text-align "center"
             :font-size "1.3em"}}
    [:a {:href "/contact.html"} "Contact me"]
    " to set up a consultation appointment!"]])


(def faq
  [:dl#acc
   (for [f c/faq]
     (let [question (first f)
           answer (last f)]
       (list
         [:dt question]
         [:dd
          [:p.dropcap answer]
          [:p]])))
   [:hr.noshow]])

(def faq-block
  [:<>
    ;[:h4 "FREQUENTLY ASKED QUESTIONS"]
    [:ul.list2
     [:li "Below are a few frequently asked questions that
  typically come up during the consultation. Since the
  consult is based on an hourly fee, I thought I would
  address a few of these now so that your time is best
  spent on the consultation."]]
    faq])

(def page-title
  [:h1.center "Consultation & Design"])

(def page-hiccup
  [:<>
   [:div.container
    [:div.inside
     rates
     [:p
      {:style {:text-align "center"}}
      [:a {:href "/consultationanddesign.html"} "Read more about my Consultation & Design Process here"]]
     examples]]
   [:div.container
    [:div.inside
     [:h1.center "Frequently Asked Questions"]
     divider
     faq-block]]])