(ns gs.components
  (:require
    [lambdaisland.ornament :as o :refer [defstyled]]
    [gs.content :as c]
    [gs.garden.page]
    [clojure.string :as string]
    [gs.site :as site :refer [pages html-filename]]
    [garden.compiler :as gc])
  (:use [gs.util]
        [gs.site]
        [gs.garden.page]
        [gs.meta]
        [com.rpl.specter]))

(def page-keys
  [:home :services :florida-plants :faq :about :contact])

(defn page-name [page-key]
  (let [page (pages page-key)
        specified-name (:name page)]
    (if specified-name specified-name
      (string/capitalize (name page-key)))))

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

(def masthead
  [:header.noborder
   [:div.container
    [:div.inside
     [:div.logo
      [:div.brand [:a {:href (site/html-filename :home)} [:img {:alt (c/images :logo/alt) :height "53" :src (gs.site/img-path (c/images :logo/file)) :width "200"}]]]
      [:div.slogan c/slogan]]
     (nav-menu page-keys)]
    [:hr.noshow]]])

(def footer-menu
  [:ul])
   ;nav-links])

(def social-media-links
  [:p.socialmedia
   (list
     [:a {:href "https://www.facebook.com/groundedsol/" :target "_blank"}
      [:img {:alt "Facebook" :height "32" :src "images/icons/fb.png" :title "Facebook" :width "32"}]])])

(def contact
  [:section.contentBox4b
   [:h5 "Contact:"]
   social-media-links
   [:p.home "1821 Amherst Ave." [:br] "Orlando, FL 32804"]
   [:p.email [:a {:href "mailto:groundedsolution@gmail.com"} "groundedsolution@gmail.com"] [:br]]
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

(def news
  [:section.contentBox4d
   [:h5 "News & Events:"]
   [:p.center]
   calendar-script
   [:p.center [:a.btn.btn-main {:href (site/html-filename :consultation)} "More Info ≫"]]])

;&raquo;

(def get-date-script
  [:script {:type "text/javascript"} "document.write(new Date().getFullYear());"])

(defstyled fancy-divider :hr.fancy)

(defstyled footer :footer
  {:clear "both"}
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