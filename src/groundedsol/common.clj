(ns groundedsol.common
  (:require
    ;[groundedsol.routes :as routes]
    ;[bidi.bidi :as b]
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [groundedsol.content :as c]
    [groundedsol.util :as u]
    [groundedsol.airtable :as at]
    [rum.core :as rum]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]))
    ;[goog.string :as gstring]))

(def pages
  {:home/file               "index"
   :home/name               "Home"
   :home/title              "Grounded Solutions Landscape Consultation and Design Company"
   :services/name           "Services"
   :services/file           "consultationanddesign"
   :services/title  "Consultation and Design Eco-Logical Landscapes"
   :consultation/name       "CONSULTATION"
   :design/name             "DESIGN"
   :pop-up-shop/name        "POP UP Shop"
   :consultation/file       "consultationanddesign"
   :florida-plants/name     "Plants"
   :florida-plants/file     "floridaplants"
   :ecosystems/file         "ecosystems"
   :ecosystems/name         "Ecosytems"
   :about/file              "about"
   :about/name              "About"
   :florida-plants-411/name "FL Plants 411"
   :contact/file            "contact"
   :contact/name            "Contact"})


(defn page-kw [page-kw tail-str]
  (keyword (str (name page-kw) "/" tail-str)))

(def page-keys [:home :services :florida-plants :about :contact])


(def page-description
  "We provide ecologically sound landscaping services focused on habitat enhancement using Florida native plant species")

(def css-files
  ["http://fonts.googleapis.com/css?family=Open+Sans|Poiret+One|Oswald:300"
   "https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"
   "css/default.css"
   "css/groundedsol.css"
   "css/animate.css"
   "css/nivo-lightbox.css"
   "images/lightbox/default.css"])
   ;"https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css"])

(def css-links
  (for [css-url css-files]
    [:link {:href css-url :rel "stylesheet" :type "text/css" :media "screen"}]))

(defn head [page]
  [:head
   [:title (:home/title pages)]
   [:meta {:charset "utf-8"}]
   [:meta {:content page-description :name "description"}]
   [:LINK {:REL "SHORTCUT ICON" :HREF "icongs.ico" :type "image/x-icon"}]
   [:meta {:content "width=device-width, initial-scale=1.0" :name "viewport"}]
   ;[:link {:href "http://fonts.googleapis.com/css?family=Open+Sans|Poiret+One|Oswald:300" :rel "stylesheet" :type "text/css"}]
   css-links])


(def script-files
  (list
    ;[:script {:src "https://unpkg.com/htmx.org@1.4.1"}]
    [:script {:src "http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"}]
    [:script {:src "scripts/main.js" :type "text/javascript"}]
    [:script {:src "scripts/jquery.slimmenu.js" :type "text/javascript"}]
    [:script {:src "scripts/nivo-lightbox.js" :type "text/javascript"}]
    [:script {:src "scripts/accordionscript.js" :type "text/javascript"}]
    [:script {:src "scripts/wow.min.js"}]
    [:script "new WOW().init();"]
    [:script "$('ul.slimmenu').slimmenu(\n{\n    resizeWidth: '1024',\n    collapserTitle: 'Main Menu',\n    animSpeed: '300',\n    easingEffect: null,\n    indentChildren: true,\n    childrenIndenter: '&nbsp;&nbsp;'\n});\n"]))


(defn html-filename [page]
  (str (pages (page-kw page "file")) ".html"))

(defn page-val [page s]
  (pages (page-kw page s)))

(def nav-links
  (for [p page-keys]
    (let [page-name (page-val p "name")
          filename (html-filename p)]
      [:li [:a {:href filename} page-name]])))


;[:li [:a {:href (r :home/file)} (r :home/name)]]
;[:li [:a {:href (r :consultation/file)} (r :services/name)]]
;;[:ul
;; [:li [:a {:href (r :consultation/file)} (r :consultation/name)]]
;; [:li [:a {:href (r :consultation/file)} (r :design/name)]]
;; [:li [:a {:href (r :florida-plants/file)} (r :pop-up-shop/name)]]]]
;[:li [:a {:href (r :florida-plants/file)} (r :florida-plants/name)]]
;;[:ul
;; [:li [:a {:href (r :florida-plants/file)} (r :florida-plants-411/name)]] [:li [:a {:href (r :ecosystems/file)} (r :ecosystems/name)]]]]
;[:li [:a {:href (r :about/file) } (r :about/name)]]
;[:li [:a {:href (r :contact/file)} (r :contact/name)]]


(def nav
  [:nav
   [:ul.slimmenu
    nav-links]])

(def masthead
  [:header.noborder
   [:div.container
    [:div.inside
     [:div.logo
      [:div.brand [:a {:href (pages :home/file)} [:img {:alt (c/images :logo/alt) :height "53" :src (u/img-path (c/images :logo/file)) :width "200"}]]]
      [:div.slogan c/slogan]]
     nav]
    [:hr.noshow]]])

(def footer-menu
  [:ul
   nav-links])

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

(def news
  [:section.contentBox4d
   [:h5 "News & Events:"]
   [:p.center
    [:script {:src "scripts/calendar02.js" :type "text/javascript"}]]
   [:p.center [:a.btn.btn-main {:href (pages :consultation/file)} "More Info ≫"]]])

;&raquo;


(def footer
  [:footer
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
      [:hr.fancy]
      [:h1 "Grounded Solutions, Inc"]
      [:p.copyright
       "© " ;"&copy;"
       [:script {:type "text/javascript"} "document.write(new Date().getFullYear());"] "All Rights Reserved"]
      [:p]]]]])

(def scroll-to-top
  [:div.scroll-to-top [:a {:href "#"} [:i.fa.fa-angle-double-up.fa-2x]]])

(defn page-hiccup [page body]
  [:html
   (head page)
   [:body body]])

(defn body [content-blocks]
  (->
    [scroll-to-top
     masthead]
    (concat
      content-blocks)
    (concat
      [footer
       script-files])))

(def build-root-path "build/")

(defn write-page [page body-blocks]
  (let [file-path
        (str build-root-path (html-filename page))]
    (spit file-path
      (rum/render-static-markup
        (page-hiccup page (body body-blocks))))))
