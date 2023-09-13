(ns gs.meta
  (:require
    [lambdaisland.ornament :as o :refer [defstyled]]
    [gs.content :as c]
    [gs.garden.page]
    [gs.site :as site :refer [pages html-filename]]
    [garden.compiler :as gc]
    [nano-id.core :refer [nano-id]])
  (:use
    [gs.util]
    [gs.site]
    [gs.garden.page]))

(def css-files
  ["https://fonts.googleapis.com/css?family=Open+Sans|Poiret+One|Oswald:300"
   "https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"
   ;"css/default.css"
   ;"css/groundedsol.css"
   "css/animate.css"
   "css/nivo-lightbox.css"
   "img/lightbox/default.css"])
   

(def css-links
  (for [css-url css-files]
    [:link {:href css-url :rel "stylesheet" :type "text/css" :media "screen"}]))

(defn head-elem [page-key]
  [:head
   [:title
    (let [custom-page-title (:title (pages page-key))]
      (if custom-page-title
        custom-page-title
        (-> pages :home :title)))]
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
   [:meta {:property "og:type" :content "website"}]
   [:meta {:content c/page-description :name "description"}]
   [:link {:REL "SHORTCUT ICON" :HREF "icongs.ico" :type "image/x-icon"}]
   [:link
    {:rel "stylesheet"
     :href (str "css/compiled.css" "?" (nano-id))
     :type "text/css"
     :title "default"}]
   [:meta {:content "width=device-width, initial-scale=1.0" :name "viewport"}]
   #_[:link {:href "http://fonts.googleapis.com/css?family=Open+Sans|Poiret+One|Oswald:300" :rel "stylesheet" :type "text/css"}]
   css-links])


(def script-files
  (list
    ;[:script {:src "https://unpkg.com/htmx.org@1.4.1"}]
    ;[:script {:src "https://www.hCaptcha.com/1/api.js" :async "true" :defer "true"}]

    [:script {:src "js/live.js" :type "text/javascript"}]
    [:script {:src "https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"}]
    [:script {:src "js/jquery.slimmenu.js" :type "text/javascript"}]
    [:script {:src "js/nivo-lightbox.js" :type "text/javascript"}]
    [:script {:src "js/accordionscript.js" :type "text/javascript"}]
    [:script {:src "js/wow.min.js" :type "text/javascript"}]
    [:script {:src "https://www.google.com/recaptcha/api.js"}]
    [:script {:src "js/main.js" :type "text/javascript"}]))

