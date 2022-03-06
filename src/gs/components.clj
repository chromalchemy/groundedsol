(ns gs.components
  (:require
    [lambdaisland.ornament :as o :refer [defstyled]]
    [gs.content :as c]
    [gs.site :as site :refer [pages page-kw page-keys html-filename page-val]])
  (:use [gs.util]))

(def css-files
  ["https://fonts.googleapis.com/css?family=Open+Sans|Poiret+One|Oswald:300"
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

(defn head [page-key]
  [:head
   [:title
    (let [custom-page-title (page-val page-key "title")]
      (if custom-page-title
        custom-page-title
        (:home/title pages)))]
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
   [:meta {:property "og:type" :content "website"}]
   [:meta {:content c/page-description :name "description"}]
   [:LINK {:REL "SHORTCUT ICON" :HREF "icongs.ico" :type "image/x-icon"}]
   [:meta {:content "width=device-width, initial-scale=1.0" :name "viewport"}]
   ;[:link {:href "http://fonts.googleapis.com/css?family=Open+Sans|Poiret+One|Oswald:300" :rel "stylesheet" :type "text/css"}]
   css-links])

;<script src='https://www.hCaptcha.com/1/api.js' async defer></script>

(def script-files
  (list
    ;[:script {:src "https://unpkg.com/htmx.org@1.4.1"}]
    ;[:script {:src "https://www.hCaptcha.com/1/api.js" :async "true" :defer "true"}]
    [:script {:src "//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"}]
    [:script {:src "scripts/main.js" :type "text/javascript"}]
    [:script {:src "scripts/jquery.slimmenu.js" :type "text/javascript"}]
    [:script {:src "scripts/nivo-lightbox.js" :type "text/javascript"}]
    [:script {:src "scripts/accordionscript.js" :type "text/javascript"}]
    [:script {:src "scripts/wow.min.js"}]
    [:script "new WOW().init();"]))
    ;[:script "$('ul.slimmenu').slimmenu(\n{\n    resizeWidth: '1024',\n    collapserTitle: 'Main Menu',\n    animSpeed: '300',\n    easingEffect: null,\n    indentChildren: true,\n    childrenIndenter: '&nbsp;&nbsp;'\n});\n"]))

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
      [:div.brand [:a {:href (site/html-filename :home)} [:img {:alt (c/images :logo/alt) :height "53" :src (gs.site/img-path (c/images :logo/file)) :width "200"}]]]
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
    (into '() content)
    (footer)
    script-files]))

(defn page [page-key content]
  [:html {:lang "en"}
   (head page-key)
   (body content)])