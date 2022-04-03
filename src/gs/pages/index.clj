(ns gs.pages.index
  (:require
    [gs.content :as c]
    [gs.components :as common]
    [gs.airtable :as at]
    [lambdaisland.ornament :as o :refer [defstyled]]
    [lambdaisland.hiccup :as h :refer [html]]
    [clojure.string :as string]
    [garden.compiler :as gc]
    [cybermonday.core :as md])
  (:use
    [com.rpl.specter]
    [gs.util :as u]))

(comment
  (u/default-keymap (c/intros :consult)))

(defn intro-block [m]
  (let [{title :title,
         subtitle :subtitle,
         body :body,
         link-text :link-text,
         link :link} m]
    [:section.contentBox3a
     [:h3 title]
     [:div.heading-line]
     [:p.lead subtitle]
     [:p body]
     [:p [:a.btn.btn-main {:href link} link-text]]]))

(def intro-blocks
  [:div.row1
   [:div.container
    [:div.inside
     [:div.group
      (for [m (vals c/intros)]
        (intro-block m))
      [:div.clear]]]]])

(def welcome
  (let [m c/welcome]
    [:div.photoblock
     [:div.container
      [:div.inside
       [:div.photoblockInside
        [:h1.big (:title m)]
        [:p.lead (:body m)]
        [:p [:a.btn.btn-main {:href (:link m)} (:link-text m)]]]]]]))

(def hot-plants
  (list
    [:h3.alternate1 (c/hot-plant-gallery :title)]
    [:p.center
     (let [img-folder (:img-folder c/hot-plant-gallery)]
       (for [m (:images c/hot-plant-gallery)]
         [:a.lightbox.wow.fadeIn
          {:data-lightbox-gallery (:gallery-name c/hot-plant-gallery)
           :data-wow-delay (:delay m)
           :href (gs.site/img-path (str "//" img-folder "//" (:img-file m)))
           :title (:title m)}
          [:img {:alt (m :title)
                 :src (gs.site/img-path (str "//" img-folder "//" (:thumb-file m)))}]]))]))

(def hot-plant-gallery
  [:div.row2
   [:div.container
    [:div.inside
     hot-plants
     [:p [:a.btn.btn-color {:href "floridaplants.html"} "View More"]]]]])

(def about-us
  [:section.contentBox3a.wow.zoomIn {:data-wow-delay ".2s"}
   [:h4.alternate2 "About Us"]
   [:img.img-round.img-small
    {:style {:margin "auto"
             :display "block"}
     :alt "Amanda" :src "images/samples/mandy.jpg"}]
   [:p
    "Orlando native Amanda Martin is no stranger to the seasonal changes of Central Florida and the beauty a planned landscape can bring to a home."]
   [:p "With expansive knowledge in horticulture, landscape design and agricultural practices, Amanda brings passion and creativity to each sustainable landscape project."]])

(def discover
  [:section.contentBox3b.wow.zoomIn {:data-wow-delay ".6s"}
   [:h4.alternate2 "Discover"]
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
        "Native Plants offer leaves as food, nectar for sugar, pollen with complex proteins, nuts and other seeds for birds and larger animals. A well balanced diet for our wildlife will help our local populations become more resilient and the climate changes."]])
    [:p]]])

(def get-started
  [:section.contentBox3c.wow.zoomIn {:data-wow-delay "1.0s"}
   [:h4.alternate2 "Get Started"]
   [:div {:style {:display :flex
                  :justify-content :center}}
      [:span.bignumber "1"]
      [:span.bignumber "2"]
      [:span.bignumber "3"]]

   [:p {:style {:clear "both"}}
    "Let’s walk your landscape! Schedule an appointment for an On-site analysis to look at what’s growing, identify existing problems, and begin to dream up a new plan."]
   [:p "Next we review a new concept for the property, look up each native plant and discuss longterm maintenance strategies."]
   [:p "Design in hand, we can assist in delivering, staging and planting native plants with the help of the DIY homeowner."]])

(def review-blocks
  (for [m c/reviews]
    (let [customer-name (:name m)
          img-path (str "images/reviews/" (:img m))]
      [:li
       [:section.content
        [:p [:img.img-round-border {:alt customer-name :src img-path}]]
        [:p (:review m)]
        [:p.small (str "~ " customer-name)]
        [:hr.noshow]]])))

(def reviews
  [:div.row3
   [:div.container
    [:div.inside
     [:h1.alternate1 "Review"]
     [:div.reviews
      [:ul#ticker
       review-blocks]]]]])

(def generic-row
  [:div.row1
   [:div.container
    [:div.inside
     about-us
     discover
     get-started
     [:div.clear]]]])

(def page-hiccup
  [:<>
   welcome
   intro-blocks
   hot-plant-gallery
   generic-row
   reviews])
