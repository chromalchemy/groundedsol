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
    [lambdaisland.hiccup :as hiccup])
  (:use [gs.util]
        [gs.site]
        [gs.garden.page]
        [gs.meta]
        [com.rpl.specter]))


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
  :text-left
  [:a :tracking-wide :text-#000 :text-sm :font-normal
   :py-12px]
  [:a:hover :text-#d1c583 :no-underline]
  ([p]
   [:<>
    [:a {:href (html-filename p)}
     (page-name p)]]))

(defstyled nav-links-wrapper :ul #_.slimmenu
  :text-center :mx-auto
  :flex :justify-center :sm:justify-end :flex-wrap :space-x-8
  :list-none :m-0 :mt-10px :p-0 :w-full :text-right :text-13px
  ([links]
   [:<> links]))
  ;:md:text-right])

(defstyled nav-menu :nav
  ([page-keys]
   [:<>
    [nav-links-wrapper
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
  :x :flex
  ([icon-folder-path]
   [:<>
    (for [[img-filename link] social-data]
      [social-icon (str icon-folder-path img-filename) link])]))

;--------------------------------

(defstyled container :div
  :my-0 :mx-auto :xl:container :px-5)

(defstyled inside :div #_:div.inside
  :w-96% :py-0 :px-2%)

(defstyled card-stack :div #_:div.group
  :flex :flex-col :md:flex-row :sm:space-x-8 :justify-center :mx-auto)

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
       :src (str "images/" img-path)}]
     text]]))

(defstyled masthead :header.noborder
  ([]
   [:<>
    [container
     [:div.inside
      [:div.logo
       [:div.brand [:a {:href (site/html-filename :home)} [:img {:alt (c/images :logo/alt) :height "53" :src (gs.site/img-path (c/images :logo/file)) :width "200"}]]]
       [:div.slogan c/slogan]]
      [nav-menu page-keys]]
     [:hr.noshow]]]))

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
;      [:img {:alt "Facebook" :height "32" :src "images/icons/fb.png" :title "Facebook" :width "32"}]])])


(defstyled footer-social-icons :div
  [:div :a :w-130px]
  [:div :flex :space-x-4 :-left]
  ([]
   [:<>
    [social-icons "images/social-icons/small/"]]))

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
  [:script {:src "scripts/calendar02.js" :type "text/javascript"}])

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

(def get-date-script
  [:script {:type "text/javascript"} "document.write(new Date().getFullYear());"])

(defstyled fancy-divider :hr.fancy)

(defstyled copyright :p.copyright
  ([]
   [:<>
    "© " ;"&copy;"
    get-date-script
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
  {:font
   {:size (px 38)
    :variant "normal"
    :family "'Poiret One', Verdana, Helvetica, sans-serif"}}
  [:&:before
   {:right (em 0.4)
    :margin-left (percent -50)}]
  [:&:after
   {:left (em 0.4)
    :margin-right (percent -50)}])


(defstyled small-round-img :img.img-round.img-small
  :mx-auto :block)

(defstyled footer-bottom :div.footerbottom
  ([]
   [:<>
    [fancy-divider]
    [:h1 "Grounded Solutions, Inc"]
    [copyright]]))

(defstyled footer :footer
  :clear-both
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

(defstyled body-elem :body
  ([content]
   [:<>
    [scroll-to-top]
    [masthead]
    content
    [footer]
    script-files]))

(defn html-el [page-key content]
  [:html {:lang "en"}
   (head-elem page-key)
   [body-elem content]])