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
    [clojure.string :as string]
    [gs.color :as color])
  (:use
    [gs.color]
    [gs.util]))

(defstyled image-shadow :img
  {:box-shadow "0 10px 15px -9px rgba(0, 0, 0, 0.5)"})

(defn image-rotate [n]
  {:transform "rotate(" n "deg)"})

(defstyled hand-sketch-image :img
  ;{:transform "rotate(4deg)"}
  ;(image-rotate 4)
  :w-full
  :md:w-50%
  :lg:w-40%
  :rounded
  :float-right
  :ml-8
  :mb-4
  image-shadow)


;(defstyled )
[:a.lightbox.wow.fadeIn.animated
 {:data-lightbox-gallery "catalog1"
  :data-wow-delay ".6s"
  :title "Firebush, Hamelia patens"
  :style "visibility: visible; animation-delay: 0.6s; animation-name: fadeIn;"}
 [:img {:alt "Firebush, Hamelia patens" :src "images///samples//plant2s.jpg"}]]

(defstyled final-design-image :a
  :ml-8 :mt-0 :mb-4 :float-right
   ;:transform "rotate(4deg)"}
  :w-40%
  :rounded
  image-shadow
  ([]
   ^{:class ["wow" "zoomIn" "lightbox"]
     :data-lightbox-gallery "catalog1"
     :href "images/final-designs/design-1-vert.png"
     :data-wow-delay "1.0s"}
   [:<>
    [:img
     {:src "images/final-designs/design-1-vert.png"
      :alt "Digital Final Design"}]]))

(def final-design-files
  [;"p.jpg" already used by primary
   "am.jpg"
   "jz.jpg"
   "tb.jpg"
   "bf.jpg"
   "cl.jpg"
   "gs.jpg"
   "jac.jpg"
   "mil.jpg"
   "mit-concept.jpg"
   "mit-zone.jpg"
   "dt.jpg"])

(defstyled final-design-gallery-img :a
  :hidden
  ([filename]
   ^{:class ["lightbox"]
     :data-lightbox-gallery "catalog1"
     :href (str "images/final-designs/" filename)}
   [:<>]))


(defstyled design-stages :div
  :flex :flex-col :md:flex-row)

(defstyled design-stage :div
  :w-full :md:w-50%
  [:h6 {:color gold-yellow
        :border :none
        :font-size (px 24)}])

(defstyled lead-text :a
  :text-#666
  :m-0 :p-0
  {:font-size "1.3em"
   :font-variant "small-caps"
   :line-height "1.3em"
   :letter-spacing (px 1)})

(defstyled consult-btn lead-text
  :self-start
  :rounded-lg
  :mx-auto
  :text-black
  :bg-white
  :shadow-lg
  :font-bold
  :inline-block
  ;:border-3px
  ;;:border-#dddac9
  ;:border-#ddc07f
  ;:border-solid
  :hover:no-underline
  ;:text-sm
  :mb-0 :my-2
  :py-1 :px-6
  :text-center
  :rounded-4px
  :no-underline
  fancy-font
  :uppercase
  :text-base
  {:box-shadow (str "5px 5px 10px #5c524080")})

(def consultation-appt-btn
  ;[:div {:style {:text-align "center"}}]
  (consult-btn
    {:href "/contact.html"}
    "Set up a Consultation Today"))

(defstyled callbox :div
  :text-white
  :bg-#c4b830
  :mx-2
  :my-0
  :rounded
  :text-center
  :py-2
  :px-3
  {:box-shadow "0 0 0 3px #c4b830 inset, 0 0 0 4px #ffffff inset"}
  [* {:color "white"}])

(def consultation-process-btn
  ;[:p
  ; {:style {:text-align "center"}}]
  (consult-btn
    {:href "/consultationanddesign.html"}
    "Consultation & Design Details"))


(defstyled analysis-body common/flex-stack
  [:.text :md:w-70% :pr-6]
  [:div :md:w-30%])

(defstyled consultation-links callbox
  :flex :flex-col
  :px-4 :mx-0 :justify-center)

  ;:py-2)
  ;:mr-0)
  ;:position-relative)

(def consult-level
  [{:title "Site Analysis Consultation"
    :price "$150"
    :time "1 Hour"
    :notes
      [analysis-body
       [:p.text "During a site analysis consultation we discuss desirable elements of your landscape, problematic areas and where you would like recommendations for change. A full discussion lets you decide what are the most important priorities in the landscape and how a long term native landscape plan can be implemented around your home."]
       (consultation-links
         {:class ["wow" "zoomIn"]
          :data-wow-delay ".3s"}
         consultation-appt-btn
         consultation-process-btn)]}

   {:title "Small Area Design, Hand Drawn"
    :price "$400-$600"
    :time "2-3 Hours"
    :notes
      (list
        [:p
         (hand-sketch-image
           {:class ["wow""zoomIn"]
            :data-wow-delay ".6s"
            :alt "Hand Sketch"
            :src "images/hand-sketch.jpg"})
         "Looking to add a butterfly garden? Wanting to feed the bees? Desire to plant more privacy with a fence-line screen or buffer the property line with bird attracting shrubs?"]
        [:p
         "Small area designs start with a well-defined area that is ready to be reimagined with native plants. A site analysis consultation is included in this package. We meet, we review the areas in question and a hand-rendered design is made. Each design provides plant names, sizes, quantities, and spatial details you need for a DIY installation. Plant acquisition and delivery services can be provided."])}

   {:title "Full Property Digital Design"
    :price "$1,200 - $1,600"
    :time "4-8 Weeks"
    :notes
    (list
      [:div
       [:p
         (final-design-image)
         (for [x final-design-files]
           (final-design-gallery-img x))
         (final-design-image-2)

        (sentences
          "A digital design starts with producing a flexible concept where the goals of each area is spelled out. Using our consultation discussions and photos of the property, extra time is used to brainstorm, sketch out and identify landscape details, keeping long-term plant development and maintenance in mind."
          "To get us started. I collect a"
          [:strong "$750 deposit"]
          "(towards the full cost) at the initial consultation."
          "Please have a property survey available to work from, a digital copy is best.")]

       [:p
        "The second meeting is for our concept review. With a large-format printed design, we review the concept, discuss what works and what doesn't and walk the landscape to envision the changes. Any needed alterations are made after the concept review and a viable planting plan with plant specs and planting details is provided as a high resolution pdf."]
       [:p
        "With the planting details in place, we are finished with the design process. You can choose to implement the design yourself, request plant installation bids from other companies, or request Grounded Solutions assist you in acquiring the plants and adjusting them in the landscape for planting."]

       [:div
        ;{:style {:clear "both"}}
        [:p
         "Each design contains different information."]
        (design-stages
          (design-stage
            [:h6
             "Concept Design"]
            ;(hand-sketch-image
            ;  {:alt "Digital Hand Sketch"
            ;   :src "images/digital-hand-sketch.jpg"})
            [:span
             {:style {:padding-left (px 10)}}
             "Concepts and ideas include:"]
            [:ul
             [:li "Flow of of the landscape and use of each area"]
             [:li "Multiple Plant Options"]
             [:li "Flower Color"]
             [:li "Seasonal Bloom"]
             [:li "Larval host species"
              [:li "Bird Foraging and habitat opportunities"]]])
          (design-stage
            [:h6
             {:style {:color gold-yellow
                      :border :none}}
             "Final Design"]
            [:span
             {:style {:padding-left (px 10)}}
             "Fully specified installation plan includes:"]
            [:ul
             [:li "Plant Common & Botanical Names"]
             [:li "Recommended Plant Sizes"]
             [:li "Quantity of Plants"]
             [:li "Recommended Planting Spacing"]
             [:li "Average Plant Heights when mature"]
             [:li
              "HOA submission can be made with finalized design"]]))]])}])


(defstyled example-block :div
  ;(hex-k :bg color/gold-yellow)
  :text-center
  :pt-2)

(defstyled social-icons :div
  :w-300px :mx-auto
  ([]
   [:<>
    (common/social-icons "images/social-icons/")]))


(defstyled video-title :h2
  :text-2xl :text-center :mb-2 :pb-0)


(defstyled video-embed :iframe
  :mx-auto :block :border-8 :border-solid
  (hex-k :border color/gold-yellow)


  ([]
   ^{:width "560"
     :height "315"
     :src "https://www.youtube.com/embed/ewdExZt_98c"
     :title "YouTube video player"
     :frameborder "0"
     :allow "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
     :allowfullscreen "true"}
   [:<>]))

(comment
  "More native plant photos and horiticultural information on my "
  [:a {:href "https://www.facebook.com/groundedsol/" } "Facebook"]
  " and "
  [:a {:href "https://www.instagram.com/groundedsolution/" } "Instagram"]
  " pages."
  " ")

(def examples
  (example-block
   (video-title "Tour a full native landscape by Grounded Solutions")
   (video-embed)
   [:p "Please Like and Follow as we grow"]
   (social-icons)))

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
  :mb-6)


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

(defstyled rate-notes :span)


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
   rates-list])


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
     examples]]
   (common/fancy-divider)
   [:div.container
    [:div.inside
     [:h1.center "Frequently Asked Questions"]
     faq-block]]])