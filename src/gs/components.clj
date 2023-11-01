(ns gs.components
  (:require
    [lambdaisland.ornament :as o :refer [defstyled]]
    [gs.content :as c]
    [gs.garden.page]
    [clojure.string :as string]
    [gs.site :as site :refer [pages html-filename]]
    [garden.compiler :as gc]
    [garden.selectors]
    [gs.color :as color]
    [lambdaisland.hiccup :as hiccup]
    [gs.garden.styles :as styles])
  (:use [gs.util]
        [gs.site]
        [gs.garden.page]
        [gs.meta]
        [com.rpl.specter]
        :reload))

(defstyled pdf-embed :embed
  :w-full :h-1200px  :md:h-2200px :md:w-850px :block :mx-auto
  ([filename]
   [:<> {:src (str "/media/" filename ".pdf" "#navpanes=0&scrollbar=0")
         :type "application/pdf"}]))

(defstyled pdf-link :div
  :text-2xl :text-center
  [:a :mb-4 :inline-block]
  [:.link-type :text-lg :text-gray-300 :ml-4]
  [:.description :text-lg :mt-2]
  ([filename link-text]
   [:<>
    [:a
     {:href
      (str "/media/" filename ".pdf")}
     link-text
     [:span.link-type "(pdf)"]]]))

(defn border [color]
  (let [color-str
        (cond
          (string? color) color
          (keyword? color) (name color))]
    [(pred-k :border "-" color-str ) :border-1 :border-solid]))
(comment
  (border :green-500)
  (border "green-500"))

;starting Portfolio demo (need cljs compiler..)
(comment
  (do
    (defstyled mycomponent :h1)

    (defscene name
      (mycomponent))))

(defstyled page-title :h1
  :text-#d1c583 :text-center)

(defstyled content-box :section
  #_:w-98% #_:m-1% :p-0)

(defstyled left-img :img
  :block
  :mt-0.4em :mr-15px :float-left
  ([{:keys [src alt size]}]
   [:<>
    {:width (px size)
     :height (px size)
     :src src}]))

(defstyled right-img :img
  :mt-0.4em :ml-15px :float-right
  ([{:keys [src alt size]}]
   [:<> {:width (px size) :height (px size)}]))

(defstyled img-rotate-left :img
  ;:m-20
  {:transform "rotate(-4deg)"})

(defstyled img-rotate-right :img
  ;:m-20
  {:transform "rotate(4deg)"})

;todo: can i use this mor generically, not on just an img?
(defstyled img-shadow :img
  {:box-shadow "0 10px 15px -9px rgba(0, 0, 0, 0.5)"})

(defstyled drop-cap-p :p.dropcap)

(defstyled alternate-header-1 :h1.alternate1)
(defstyled alternate-header-2 :h4.alternate2)


(defn spacer [n]
  [:div
   {:style {:height (px n)}}])

(defstyled btn :a
  :border
  ;:bg-white
  :border-#333
  :text-#333
  :border-solid
  :inline-block
  :text-lg
  :font-normal
  :mb-0
  :py-4px :px-12px
  :text-center
  :align-middle
  :whitespace-nowrap
  :rounded-4px
  :no-underline)

(defstyled flex-stack :div
  :flex :md:flex-row :flex-col)

(def fancy-font
  {:font-family "'Poiret One', Verdana, Helvetica, sans-serif"})

;----------------------------------------
(defstyled nav-link :li
  :text-left :block
  #_(styles/border "green")
  ;[:border-red-500 :border-1 :border-solid]
  ;;(border :green-500)
  [:a :block :tracking-wide :text-#000 :text-sm :font-normal
   :py-1 :md:py-2 :px-2 #_(styles/border "red")

   [:&:hover :text-#d1c583 :no-underline]]

   ;[:&:first-child]]
  ;[:& border]
  ;(styles/border "red")

  ([page-key]
   [:<>
    [:a {:href (html-filename page-key)}
     (page-name page-key)]]))


;nav {
;     width: 58%;
;     margin-right: 2%;
;     padding: 5px 0 0 0;
;     list-style: none;
;     text-align: right;
;     height: 48px;
;     color: #000;
;     box-sizing: border-box;
;     text-transform: uppercase;
;     float: right});



(defstyled nav-menu :nav
  :block  #_:w-50%
  :w-full
  :mb-2 :min-h-3rem
  ;:h-4.5em
  [:ul
   :flex :flex-grow :justify-center #_:sm:justify-end :flex-wrap :space-x-1 :md:space-x-0
   :list-none
   :m-0 :p-0
   #_(styles/border "blue")]
  ([page-keys]
   [:<>
    [:ul
     (for [page-key page-keys]
       [nav-link page-key])]]))

(comment
  (map nav-link page-keys))

;--------------------------------

(defstyled social-icon :a
  ([img-filepath link]
   [:<> {:href link}
    [:img {:src img-filepath}]]))

(def social-data
  [["instagram-icon.png" "https://www.instagram.com/groundedsolution/"]
   ["youtube-icon.png" "https://www.youtube.com/channel/UCNqZxB-qW4lf4xey7xiT0Lg"]
   ["facebook-icon.png" "https://www.facebook.com/groundedsol"]])

(defstyled social-icons :div
  :x :flex :mb-4
  :justify-center
  :w-75% :md:w-30% :mx-auto
  :pl-15px
  :md:pl-0
  ([icon-folder-path]
   [:<>
    (for [[img-filename link] social-data]
      [social-icon (str icon-folder-path img-filename) link])]))

;--------------------------------

(defstyled container :div
  :mx-auto
  :xl:container
  :my-0
  :box-border
  :py-0 :pb-2 :px-5
  :w-full)

(comment
  (hiccup/render
    [container "hello"]
    {:doctype? false})
  )

(defstyled inside :div
  :w-96% :py-0 :px-2%)


(defstyled card-stack :div #_:div.group
  :flex :flex-col :md:flex-row :gap-8 :justify-center :mx-auto)

(defstyled card content-box
  :border-2px :border-black
  :lg:w-33% :mb-6
  [:p :mt-0]
  ([{:keys [img-path text img-alt-text]}]
   [:<>
    [:p
     [left-img
      {:alt img-alt-text
       :size 125
       :src (str "img/" img-path)}]
     text]]))

(defstyled hr-noshow :hr
  :bg-transparent :border-0 :border-none :text-#fff :h-0
  :clear-both :hidden)


(defstyled logo-block :div
  #_(styles/border "pink")
  :text-center :md:text-left :md:w-40%)



(defstyled slogan :div
  fancy-font
  :text-#333
  ;:text-#000
  :tracking-wide
  :uppercase
  ;:pl-0
  :text-sm
  ;:md:text-md
  ;:text-left
  :font-normal)

(defstyled brand-logo :a
  :block  :m-0 :p-0 :no-underline
  [:img #_:w-200px]
  ([]
   [:<>
    {:href (site/html-filename :home)}
    [:img
     {:alt (c/images :logo/alt)
      :src (gs.site/img-path (c/images :logo/file))}]]))

(defstyled masthead container
  :pt-2
  :border-b-0 :md:flex :justify-between #_:flex-col
  ;(styles/border "green")
  ;:min-h-100px
  ;[:.test (styles/border "green") :flex :p-2 :w-full]
  ;[:.child (styles/border "red") :w-8]
  ([]
   [:<>
    [logo-block
     [brand-logo]
     [slogan c/slogan]]
    [nav-menu page-keys]
    #_
    [:div.test
     [:div.child]
     [:div.child]
     [:div.child]]]))
    ;[hr-noshow]]))




(defstyled footer-nav-link nav-link
  :mb-1
  ([page-key]
   [:<>
    [:a {:href (html-filename page-key)}
     (page-name page-key)]]))

(defstyled footer-menu :section.contentBox4a
  [:ul :text-left]
  ([]
   [:<>
    [:h5 "Menu:"]
    [:ul
     (map footer-nav-link page-keys)]]))

(comment
  (->>
    [footer-menu]
    hiccup/html
    hiccup/render-html*))

;(def social-media-links
;  [:p.socialmedia
;   (list
;     [:a {:href "https://www.facebook.com/groundedsol/" :target "_blank"}
;      [:img {:alt "Facebook" :height "32" :src "img/icons/fb.png" :title "Facebook" :width "32"}]])])


(defstyled footer-social-icons :div
  [:div :a :w-130px]
  [:div :flex :space-x-4 :-left]
  ([]
   [:<>
    [social-icons "img/social-icons/small/"]]))

(defstyled hours :p.hours)
(defstyled phone :p.phone)
(defstyled home :p.home)
(defstyled email :p.email)

(defstyled contact :section.contentBox4b
  ([]
   [:<>
    [:h5 "Contact:"]
    [footer-social-icons]
    [home "1821 Amherst Ave." [:br] "Orlando, FL 32804"]
    [email
     [:a {:href "mailto:groundedsolution@gmail.com"}
      "groundedsolution@gmail.com"]
     [:br]]
    [phone "352-219-5381"]
    [hours "By appointment only" [:br]]]))

(defstyled certifications :section.contentBox4c
  ([]
   [:<>
    [:h5 "Certifications / Affiliations"]
    [:i.fa.fa-trophy.fa-3x.img-left.color3.icon-shadow] "B.S. Environmental Science"
    [:br] "Certified Wetland Delineator"
    [:br] "Commercial Applicator License"
    [:br] [:br] "Member of Sierra Club"
    [:i.fa.fa-leaf.fa-3x.img-left.color3.icon-shadow]
    [:br] "Florida Native Plant Society"
    [:br] "Orange Audubon Society"
    [:br] [:br]
    [:i.fa.fa-camera.fa-3x.img-left.color3.icon-shadow] "Photography by Amanda Martin. All rights reserved"
    [:br] [:br]]))

(def calendar-script
  [:script {:src "js/calendar02.js" :type "text/javascript"}])

(defstyled contact-btn :p.center
  [:a {:color color/gold-yellow}]
  ([]
   [:<>
    [:a.btn.btn-main
     {:href (site/html-filename :contact)}
     "Contact"]]))

(defstyled news :section.contentBox4d
  ([]
   [:<>
    ;[:h5 {:id "calendar"} "News & Events:"]
    [:p.center]
    calendar-script
    (contact-btn)]))

;&raquo;


(defstyled fancy-divider :hr.fancy)

(defstyled year :span
  :mr-2
  ([]
   [:<>
    [:script {:type "text/javascript"} 
     "document.write(new Date().getFullYear());"]]))


(defstyled copyright :p.copyright
  ([]
   [:<>
    "© "
    [year]
    "All Rights Reserved"]))

(defstyled hidden-hr :hr.noshow)

(defstyled content-section :section.content)

(defstyled clear :div.clear)

(defstyled heading-line :div.heading-line)

(defstyled middle-of-line-title :h3
  :text-#fff :my-1 :mx-0 :text-center :relative :overflow-hidden :normal-case
  [:&:before :&:after
   :inline-block :relative :align-middle :w-50% :mb-1 :text-#fff
   {:border-bottom
      {:width (px 1)
       :style "solid"
       :color "#fff"}
    :content "' '"
    :height (em 0.2)}]
  fancy-font
  {:font
   {:size (px 38)
    :variant "normal"}}
  [:&:before
   {:right (em 0.4)
    :margin-left (percent -50)}]
  [:&:after
   {:left (em 0.4)
    :margin-right (percent -50)}])


(defstyled small-round-img :img
  ;:border-#ffffff :border-5px :border-solid
  :rounded-full #_:mx-auto #_:block
  :max-w-150px :h-auto :float-left :mr-20px :mb-10px
  ([]
   [:<>
    {:alt "Amanda"
     :src (img-path (jpeg "mandy") "samples")}]))


(defstyled footer-bottom :div.footerbottom
  ([]
   [:<>
    [fancy-divider]
    [:h1 "Grounded Solutions, Inc"]
    [copyright]]))

(defstyled footer :footer
  :clear-both :flex-shrink-0
  ([]
   [:<>
    [container
     [inside
      [footer-menu]
      [contact]
      [certifications]
      [news]
      [hidden-hr]
      [footer-bottom]]]]))


(defstyled scroll-to-top :div.scroll-to-top
  ([]
   [:<>
    [:a {:href "#"}
     [:i.fa.fa-angle-double-up.fa-2x]]]))
;
;flex-grow
;flex-shrink
;flex-basis

(defstyled main-elem :section
  :flex :flex-grow :flex-shrink-0 :w-auto
  [:.inner :w-full]
  ([content-hiccup]
   [:<>
    [:div.inner
     content-hiccup]]))



(defstyled test-comp container
  :bg-green-500 :h-200px (styles/border "red"))

(defstyled body-elem :body
  :h-full :flex :flex-col
  ([content]
   [:<>
    [scroll-to-top]
    ;[test-comp]
    [masthead]
    [main-elem content]
    [footer]]))

(defstyled html-elem :html
  :h-full
  ([page-key content]
   [:<> {:lang "en"}
    [:head 
     (head-stuff [] false)]
    [body-elem content]]))