(ns gs.pages.index
  (:require
    [hickory.core :as hickory]
    [cybermonday.core :as cm]
    [gs.content :as c]
    [gs.page]
    [gs.common :as common]
    [gs.airtable :as at]
    [lambdaisland.ornament :as o :refer [defstyled]]
    [lambdaisland.hiccup :as h :refer [html]]
    [clojure.string :as string]
    [garden.compiler :as gc])
  (:use
    [com.rpl.specter]
    [gs.util :as u]))

(def global-garden-style-rules
  [])

(comment
  (->> global-garden-style-rules
    gc/compile-css))

(defn local-styles []
  (list
    [:style (o/defined-styles)]
    [:style (gc/compile-css global-garden-style-rules)]))

(defn concatenated-styles-string []
  (apply str
    (interpose "\n\n"
      [
       (o/defined-styles)
       (gc/compile-css global-garden-style-rules)])))

(comment
  (write-hiccup-to-html-file filename local-page))

(comment
  ;;spit to template file
  (spit
    (str template-path filename ".html")
    (->> bc-hiccup h/html h/render-html*)))

(comment
  (def running-system
    (start-live-browser-view! filename))
  (stop-system! running-system))

;(defn html-entity [s]
;  (gstring/unescapeEntities s))

;(require 'goog.string)
;(s/transform [(s/walker string?)] goog.string.unescapeEntities my-hiccup-vector)

;markdown parse test with hickory + specter
(comment
  (def myvar
    (str (gs.util/my-airtable-data))))

;(into [] (reverse [  "Hello World" :h1]))
      ;(setval [FIRST FIRST]
      ;  :h2
      ;  (map hickory/as-hiccup
      ;       (hickory/parse-fragment
      ;         (gs.util/my-airtable-data)))))
;---------------------------
(comment
  [:div {:dangerouslySetInnerHTML {:__html myvar}}])

;markdown parse test with gonday
;(def md-hiccup
;  (cybermonday.core/parse-md
;    (:markdown-test c)))

(u/default-keymap (c/intros :consult))

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
           :href (u/img-path (str "//" img-folder "//" (:img-file m)))
           :title (:title m)}
          [:img {:alt (m :title)
                 :src (u/img-path (str "//" img-folder "//" (:thumb-file m)))}]]))]))

(def hot-plant-gallery
  [:div.row2
   [:div.container
    [:div.inside
     hot-plants
     [:p [:a.btn.btn-color {:href "floridaplants.html"} "View More"]]]]])


(def about-us
  [:section.contentBox3a.wow.zoomIn {:data-wow-delay ".2s"}
   [:h4.alternate2 "About Us"]
   [:p [:img.img-left.img-round.img-small {:alt "Amanda" :src "images/samples/mandy.jpg"}] "A long time Winter Park resident, Amanda Martin is no stranger to the beauty that a
            planned landscape brings to our Florida neighborhoods. With expansive knowledge in horticulture,
            agricultural research, and landscape design, Amanda’s passion is
            bringing out the best in our sustainable landscapes."]
   [:p]])

(def discover
  [:section.contentBox3b.wow.zoomIn {:data-wow-delay ".6s"}
   [:h4.alternate2 "Discover"]
   [:div.callbox
    [:h6 "Why Buy Natives"]
    [:img {:alt "" :height "111" :src "images/samples/GS%20Icon.png" :width "114"}]
    [:p "Residential landscapes are often filled with plants that grow well in our climate
         but offer little else to the surrounding environment. My goal is to bring a botanical
         garden-like atmosphere to the landscape while keeping valuable resource
         uses, like water, to a minimum."]
    [:p]]])

(def get-started
  [:section.contentBox3c.wow.zoomIn {:data-wow-delay "1.0s"}
   [:h4.alternate2 "Get Started"]
   [:p [:span.bignumber "1"] "Let’s walk your grounds together.
			Consultations take about an hour and involve identifying existing problems, future dreams or a whole new plan."]
   [:p [:span.bignumber "2"] "Next, we work together to review plants, producing a design or viable plan for the
            best solutions."]
   [:p [:span.bignumber "3"] "Design in hand, you are connected
to established installers or plant material is delivered for the DIY
crowd that wants to get their hands dirty."]])

(def review-blocks
  (for [m c/reviews]
    (let [title (str (:name m) ", " (:date m))
          img-path (str "images/samples/" (:img m))]
      [:li
       [:section.content
        [:p [:img.img-round-border {:alt title :src img-path}]]
        [:p (:review m)]
        [:p.small (str "~ " title)]
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

(use 'gs.pages.index)

(def content
  [welcome
   intro-blocks
   hot-plant-gallery
   generic-row
   reviews])
