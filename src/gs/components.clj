(ns gs.components
  (:require
    [lambdaisland.ornament :as o :refer [defstyled]]
    [gs.content :as c]
    [gs.garden.page]
    [clojure.string :as string]
    [gs.site :as site :refer [pages html-filename]]
    [garden.compiler :as gc]
    [gs.color :as color])
  (:use [gs.util]
        [gs.site]
        [gs.garden.page]
        [gs.meta]
        [com.rpl.specter]))

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
;----------------------------------------
(defstyled nav-link :li
  ([p]
   [:a {:href (html-filename p)}
    (page-name p)]))

(defstyled nav-links-wrapper :ul.slimmenu
  :text-center)
   ;:md:text-right])

(defstyled nav-menu :nav
  ([page-keys]
   (nav-links-wrapper
     (map nav-link page-keys))))

(comment
  (map nav-link page-keys))


;--------------------------------

(defstyled social-icon :a
  ([img-filepath link]
   ^{:href link}
   [:<>
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
      (social-icon (str icon-folder-path img-filename) link))]))



;--------------------------------



(def masthead
  [:header.noborder
   [:div.container
    [:div.inside
     [:div.logo
      [:div.brand [:a {:href (site/html-filename :home)} [:img {:alt (c/images :logo/alt) :height "53" :src (gs.site/img-path (c/images :logo/file)) :width "200"}]]]
      [:div.slogan c/slogan]]
     (nav-menu page-keys)]
    [:hr.noshow]]])

(defstyled footer-nav-link nav-link
  :mb-1
  ([p]
   [:a {:href (html-filename p)}
    (page-name p)]))

(def footer-menu
  [:ul
   {:style
    {:text-align "left"}}
   (map
     footer-nav-link
     page-keys)])

;(def social-media-links
;  [:p.socialmedia
;   (list
;     [:a {:href "https://www.facebook.com/groundedsol/" :target "_blank"}
;      [:img {:alt "Facebook" :height "32" :src "images/icons/fb.png" :title "Facebook" :width "32"}]])])


(defstyled footer-social-icons :div
  [:div :a :w-130px]
  [:div :flex :space-x-4 :-left]
  ([]
   (social-icons "images/social-icons/small/")))

(def contact
  [:section.contentBox4b
   [:h5 "Contact:"]
   (footer-social-icons)
   [:p.home "1821 Amherst Ave." [:br] "Orlando, FL 32804"]
   [:p.email
      [:a {:href "mailto:groundedsolution@gmail.com"}
       "groundedsolution@gmail.com"]
    [:br]]
   [:p.phone "352-219-5381"]
   [:p.hours "By appointment only" [:br]]])

(def certifications
  [:section.contentBox4c
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
   [:br] [:br]])

(def calendar-script
  [:script {:src "scripts/calendar02.js" :type "text/javascript"}])

(defstyled contact-btn :p.center
  [:a {:color color/gold-yellow}]
  ([]
   [:a.btn.btn-main
    {:href (site/html-filename :contact)}
    "Contact"]))


(def news
  [:section.contentBox4d
   ;[:h5 {:id "calendar"} "News & Events:"]
   [:p.center]
   calendar-script
   (contact-btn)])


;&raquo;

(def get-date-script
  [:script {:type "text/javascript"} "document.write(new Date().getFullYear());"])

(defstyled fancy-divider :hr.fancy)

(defstyled footer :footer
  :clear-both
  ([]
   [:<>
    [:div.container
     [:div.inside
      [:section.contentBox4a
       [:h5 "Menu:"]
       footer-menu]
      contact
      certifications
      news
      [:hr.noshow]
      [:div.footerbottom
       (fancy-divider)
       [:h1 "Grounded Solutions, Inc"]
       [:p.copyright
        "© " ;"&copy;"
        get-date-script
        "All Rights Reserved"]]]]]))

(def scroll-to-top
  [:div.scroll-to-top
   [:a {:href "#"}
    [:i.fa.fa-angle-double-up.fa-2x]]])

(defstyled body :body
  ([content]
   [:<>
    scroll-to-top
    masthead
    content
    (footer)
    script-files]))

(defn html-el [page-key content]
  [:html {:lang "en"}
   (head page-key)
   (body content)])