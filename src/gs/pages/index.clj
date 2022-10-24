(ns gs.pages.index
  (:require
    [gs.content :as content]
    [garden.selectors :as gs]
    [garden.core :as g :refer [css style]]
    ;[gs.components :as common]
    [gs.airtable :as at]
    [lambdaisland.ornament :as o :refer [defstyled]]
    [lambdaisland.hiccup :as hiccup :refer [html]]
    [clojure.string :as string]
    [cybermonday.core :as md])
  (:use
    [gs.components]
    [com.rpl.specter]
    [gs.util :as u]
    [gs.site]))

(comment
  (u/default-keymap (content/intros :consult)))

(defstyled intro-block :section.contentBox3a
  ([{:keys [title subtitle body link-text link]}]
   [:<>
    [:h3 title]
    [heading-line]
    [:p.lead subtitle]
    [:p body]
    [:p [:a.btn.btn-main
         {:href (html-filename link)} link-text]]]))

(defstyled intro-blocks :div.row1
  ([]
   [:<>
    [container
     [inside
      [group
       (for [m (vals content/intros)]
         [intro-block m])
       [clear]]]]]))

(defstyled welcome :div.photoblock
  ([]
   (let [{:keys [title body]} content/welcome]
     [:<>
      [container
       [inside
        [:div.photoblockInside
         [:h1.big title]
         [:p.lead body]]]]])))
        ;[:p [:a.btn.btn-main {:href (:link m)} (:link-text m)]]]]]]))


;.row2 .alternate1 {color: #fff};}
;.row2 .alternate1:before, .row2 .alternate1:after {border-bottom: 1px solid #fff};}


(defstyled hot-plants-link :a.lightbox.wow.fadeIn
  [:img :m-10px :rounded-full :border-2 :border-#fff :border-solid :max-w-200px :h-auto
   [:&:hover :border-#af9e41]]
  ([{:keys [delay img-file title thumb-file]} img-folder]
   [:<>
    {:data-lightbox-gallery
       (:gallery-name content/hot-plant-gallery)
     :data-wow-delay delay
     :href
       (gs.site/img-path
         (str "//" img-folder "//" img-file))
     :title title}
    [:img {:alt title
           :src (gs.site/img-path (str "//" img-folder "//" thumb-file))}]]))

(defstyled hot-plants :div
  [:div :text-center]
  ([{:keys [title images img-folder]}]
   [:<>
     [middle-of-line-title title]
     [:div
      (for [img-map images]
        [hot-plants-link img-map img-folder])]]))

(defstyled hot-plant-gallery container
  :bg-#d1c583 :text-#fff :py-40px :px-0 :text-center :w-full
  ([]
   [:<>
    [inside
     [hot-plants content/hot-plant-gallery]
     [:p
      [:a.btn.btn-color
       {:href "floridaplants.html"}
       "View More"]]]]))

;----------------

(defstyled about-us :section.contentBox3a.wow.zoomIn
  ([]
   [:<> {:data-wow-delay ".2s"}
    [alternate-header-2 "About Us"]
    [small-round-img
     {::o/attrs
      {:alt "Amanda" :src "images/samples/mandy.jpg"}}]
    [:p
     "Orlando native Amanda Martin is no stranger to the seasonal changes of Central Florida and the beauty a planned landscape can bring to a home."]
    [:p "With expansive knowledge in horticulture, landscape design and agricultural practices, Amanda brings passion and creativity to each sustainable landscape project."]]))

(defstyled discover :section.contentBox3b.wow.zoomIn
  ([]
   [:<> {:data-wow-delay ".6s"}
    [alternate-header-2 "Discover"]
    [:div.callbox
     [:h6 "Why Buy Natives"]
     [:img {:alt "" :height "111" :src "images/samples/GS%20Icon.png" :width "114"}]
     (let [p-style
           {:style {:padding-left "15px"
                    :padding-right "15px"
                    :text-align "justify"}}]
       [:<>
        [:p p-style
         "Residential landscapes are often filled with plants that grow well but offer little else to the surrounding environment."]
        [:p p-style
         "Native Plants offer leaves as food, nectar for sugar and pollen for complex proteins.  Seeds, nuts and acorns can provide a meal for birds and larger animals. A well balanced diet for our wildlife will help our local populations become more resilient as the climate changes."]])
     [:p]]]))

(defstyled bold-number :span
  :text-#b12b5a
  :border-1
  :circle
  :border-solid
  :border-#b12b5a
  :my-1
  :mx-2
  :text-center
  :uppercase
  :h-50px
  :w-50px
  :rounded-full
  {:font "normal 30px/50px Garamond, Georgia, serif;"})

(defstyled inline-bold-number bold-number
  :border-none
  {:font "normal 30px/50px Garamond, Georgia, serif;"})

(defstyled step-title :h2
  :w-40px
  :text-center
  :text-2xl
  :text-red-700
  :float-left :mr-6 :mb-1 :pt-0 :mt-0)

(defstyled step :div
  :mb-4
  :clear-both
  ;:h-40
  ;[:p {:color "red"}]
  ([step-number step-str]
   [:<>
     [step-title step-number]
     step-str]))

;color: #b12b5a;
;        border: 1px solid #b12b5a;
;        display: block;
;font: normal 30px/50px Garamond, Georgia, serif;
;margin: 5px 10px 5px 0;
;text-align: center;
;text-transform: uppercase;
;height: 50px;
;width: 50px;
;bo

(defstyled numbers :div
  :flex :justify-center :mb-4
  ([]
   (for [n ["1" "2" "3"]]
     [bold-number n])))

(def steps-strs content/steps)

(defstyled get-started :section.contentBox3c
  ([]
   [:<>
    {:class "wow zoomIn"
     :data-wow-delay "1.0s"}
    [alternate-header-2 "Get Started"]
    [numbers]
    (for [step-str steps-strs]
      [step
       (case (+ 1 (.indexOf steps-strs step-str))
         1 "One"
         2 "Two"
         3 "Three")
       step-str])]))

(defstyled review-img :p
  ([customer-name img-path]
   [:<>
    [:img.img-round-border
     {:alt customer-name :src img-path}]]))

(defstyled reviewer-name :p.small)

(defstyled review-text :p)

(def review-blocks
  (for [{:keys [name img review]} content/reviews]
    (let [customer-name name
          img-path (str "images/reviews/" img)]
      [:li
       [content-section
        [review-img customer-name img-path]
        [review-text review]
        [reviewer-name (str "~ " customer-name)]
        [hidden-hr]]])))

(defstyled reviews :div.row3
  ([]
   [:<>
    [container
     [inside
      [middle-of-line-title "Review"]
      [:div.reviews
       [:ul#ticker
        review-blocks]]]]]))

(defstyled generic-row :div.row1
  ([]
   [:<>
    [container
     [inside
      [about-us]
      [discover]
      [get-started]
      [clear]]]]))

(defstyled pruning-notes-pdf-link :div
  :text-2xl :text-center
  [:a :mx-3]
  [:.new :text-red-500 :font-bold]
  [:.link-type :text-xl :text-gray-300]
  [:.description :text-lg :mt-2]
  ([]
   [:<>
    [:span.new "New!"]
    [:a
     {:href "/media/Pruning_Notes_for_Native_Plants_3.pdf"}
     "Pruning Notes Newsletter"]
    [:span.link-type "(pdf)"]
    [:p.description "Includes Seasonal Planting Guide, & how to \"Prune like Fire\"!"]]))


(comment
  (defstyled mybox :div
    {:border "1px solid black"})

  (defstyled myelem :p
    :text-red-500
    [:&:before :&:after
     :text-blue-500
     {:content "'------------'"}]))


(def page-hiccup
  [:<>
   ;[mybox [myelem "hello"]]
   [welcome]
   [pruning-notes-pdf-link]
   [intro-blocks]
   [hot-plant-gallery]
   [generic-row]
   [reviews]])

