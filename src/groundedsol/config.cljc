(ns groundedsol.config
  (:require [groundedsol.routes :as routes]
            [groundedsol.pages.index :as index]
            [groundedsol.pages.contact :as contact]))

(def css-files
  (list
    [:link {:href "css/groundedsol.css" :rel "stylesheet" :type "text/css" :media "screen"}]
    [:link {:href "css/animate.css" :rel "stylesheet" :type "text/css" :media "screen"}]
    [:link {:href "css/default.css" :rel "stylesheet" :type "text/css" :media "screen"}]
    [:link {:href "css/nivo-lightbox.css" :rel "stylesheet" :type "text/css" :media "screen"}]
    [:link {:href "http://fonts.googleapis.com/css?family=Open+Sans|Poiret+One|Oswald:300" :rel "stylesheet" :type "text/css"}]
    [:link {:href "https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" :rel "stylesheet"}]
    [:link {:href "images/lightbox/default.css" :media "screen" :rel "stylesheet" :type "text/css"}]))
    ;[:link {:href "https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" :rel "stylesheet"}]))

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

(def page-description
  "We provide ecologically sound landscaping services focused on habitat enhancement using Florida native plant species")

(defn config []
  {:resource-dir     "resources"
   :target-dir       "build"
   :pages            {:page/index   {:hiccups (index/hiccup)}
                      :page/contact {:hiccups (contact/hiccup)}}
   :routes           routes/routes
   :default-route    :page/index
   :default-template
   [:html
    [:head
     [:title "Grounded Solutions Landscape Consultation and Design Company"]
     [:meta {:charset "utf-8"}]
     [:meta {:content page-description :name "description"}]
     [:LINK {:REL "SHORTCUT ICON" :HREF "icongs.ico" :type "image/x-icon"}]
     [:meta {:content "width=device-width, initial-scale=1.0" :name "viewport"}]
     css-files]
    [:body
     :volcano/hiccups
     script-files]]

   :exclude-files    #{"index.html"}
   :exclude-dirs     #{"js"}})

