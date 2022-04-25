(ns gs.pages.services
  (:require
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [gs.content :as c]
    [gs.components :as common]
    [lambdaisland.ornament :refer [defstyled]]
    [garden.selectors :as gs]
    [gs.airtable :as at]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]
    [clojure.string :as string])
  (:use
    [gs.color]
    [gs.util]))

(def consult-level
  [{:title "Site Analysis Consultation"
    :price "$150"
    :time "1 Hour"
    :notes
      "During the site analysis consultation we’ll discuss desirable aspects of your landscape, problematic areas, and how you would like various elements to change. A full discussion lets you decide what are the most important priorities and decide how a long term native landscape fits around your home."}

   {:title "Small Area Design, Hand Drawn"
    :price "$400-$600"
    :time "2-3 Hours"
    :notes
      (list
        "Looking to add a pollinator or butterfly garden? Want to add natives to a fence-line or around the pool? Small area designs are for well-defined areas that make great opportunities for a new native plant bed. During our site consultation, I will hand-render a custom design that provides plant names, sizes, quantities, and spatial details you need for DIY installation.")}
   {:title "Full Property Digital Design"
    :price "$1,200 - $1,600"
    :time "4-8 Weeks"
    :notes
    (list
      [:div
       [:p
        (sentences
          "We produce a flexible concept with the goals of each area spelled out. Time is needed to brainstorm, sketch, and plan landscape details, with long-term plant development and maintenance in mind."
          "To get us started. I collect a  "
          [:strong "$750 deposit"]
          " (towards the full cost) at the site analysis consultation."
          " "
          "Please have a property survey available to work from. A digital copy is best.")]

       [:p
        "We meet for a concept review with a large-format printed design, reviewing and discussing what works and what doesn't. Alterations are made, and a viable planting plan with specs and details is provided."]
       [:p
        "With planting details in place, we are finished with the design process. You can choose to implement the design yourself, request plant installation bids from other companies, or request Grounded Solutions assist you aquiring and delivering plant lists."]]

      [:div
       [:p
        "Designs are typically a two phase process:"]
       [:h6
        {:style {:color gold-yellow
                 :border :none}}
        "1) Concept Design"]
       [:span
        {:style {:padding-left (px 10)}}
        "Concepts, and ideas:"]
       [:ul
        [:li "Flow of of the landscape, and use of each area"]
        [:li "Multiple Plant Options"]
        [:li "Flower Color"]
        [:li "Seasonal Bloom"]
        [:li "Larvel host species"
         [:li "Bird Foraging and habitat opportunities"]]]

       [:h6
        {:style {:color gold-yellow
                 :border :none}}
        "2) Final Design"]
       [:span
        {:style {:padding-left (px 10)}}
        "Fully specifed installation plan includes:"]
       [:ul
        [:li "Plant Common & Botanical Names"]
        [:li "Recommended Plant Sizes"]
        [:li "Quantity of Plants"]
        [:li "Recommended Planting Spacing"]
        [:li "Average Plant Heights when mature"]
        [:li
         "HOA submission can be made with finalized design"]]])}])




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

(def fancy-font
  {:font-family "'Poiret One', Verdana, Helvetica, sans-serif"})


;font: 34px 'Oswald', Verdana, Helvetica, sans-serif;
;    color: #333;
;    margin: 0.3em 0;
;    text-align: left;
;    position: relative;
;    overflow: hidden;





(defstyled rate-item-block :div
  :inline-block
  :mb-12)


(defstyled rate-header :div
  :flex
  :items-center)

;{:style {:display :flex
;         :flex "1"
;         :flex-wrap "on"}}

(defstyled rate-time :div.lead
  fancy-font
  :my-2
  :mb-0
  :pb-0)


(def middle-line-style
  {:border-bottom "1px solid #d1c583"
   :content "\"\""
   :display "inline-block"
   :height "0.2em"
   :position "relative"
   :vertical-align "middle"
   :width "100%"
   :margin-left (px 10)
   :margin-bottom "0.25em"
   :margin-right "-60%"})

(defstyled rate-title :div
  :font-bold
  :text-2xl
  :pr-30px
  :flex-grow
  {:color gold-yellow
   :position "relative"
   :overflow "hidden"}
  [:&:after middle-line-style]
  :capitalize
  :md:text-3xl
  :py-1
  fancy-font)




(defstyled rate-price :span
  ;:ml-2
  :text-xl
  :block
  :pl-2
  ;:float-right
  fancy-font)

(def rates-list
  (for [c consult-level]
    (let [{title :title
           price :price
           time-est :time
           notes :notes} c]
      (rate-item-block
       (rate-header
        (rate-title title)
        (rate-price price))
       [:div
        {:style {:padding-left "15px"}}
        ;[:div.heading-line
        ;  {:style {:width (percent 50)}}]
        (rate-time time-est)
        (rate-notes notes)]))))


(defstyled rate-notes :span)
;--------------------------------

(defstyled btn common/btn
  :mx-2
  ;:rounded-lg
  :border-2px
  :my-2
  :px-8
  :hover:no-underline
  ;(pred-k :hover (hsl-k :text 160 100 40))
  ;(pred-k :hover (hsl-k :border 160 50 70))
  (pred-k :border "-" gold-yellow))
  ;(hsl-k :border 160 100 40))


(def rates
  [:<>
   [:h1.center "Services & Rates"]
   (common/fancy-divider)
   (common/spacer 20)
   rates-list
   ;[:p
   ; {:style {:text-align "center"
   ;          :font-size "1.2em"}}]
    ;"Set up a consultation appointment!"
   [:div {:style {:text-align "center"}}
    (btn
     {:href "/contact.html"}
     "Set up a consultation appointment!")]])

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
      (btn
        {:href "/consultationanddesign.html"}
        "Consultation & Design Process")]
     examples]]
   (common/fancy-divider)
   [:div.container
    [:div.inside
     [:h1.center "Frequently Asked Questions"]
     faq-block]]])