(ns gs.meta
  (:require
    [clojure.java.io :as io]
    [clojure.string :as str])
  (:use
    [gs.util]
    ))

;; ------------------ js

(def js-path  "/js/")

(defn main-js-path []
  (if-some [f (io/file (io/resource "public/js/main.js"))]
    (str "main.js?t=" (.lastModified f))
    "main.js"))

(def htmx-main-lib-script-tag
  [:script
   {:src "https://unpkg.com/htmx.org@1.9.12"}
  ;; latest 
   #_{:src "https://unpkg.com/htmx.org@2.0.2"
    :integrity "sha384-Y7hw+L/jvKeWIRRkqWYfPcvVxHzVzn5REgzbawhxAuQGwX1XWe70vji+VSeHOThJ"
    :crossorigin "anonymous"}])

(def recaptcha-script-tag
  [:script {:src "https://www.google.com/recaptcha/api.js"
            :async "async" :defer "defer"}])

(def local-js-file-names 
  ["jquery.slimmenu.js"
   "nivo-lightbox.js"
   "wow.min.js"
   "accordionscript.js"
   (main-js-path)])

(def js-cdn-include-urls
  ["https://unpkg.com/htmx.org@1.9.12/dist/ext/ws.js"
   "https://unpkg.com/hyperscript.org@0.9.8"
   #_"https://unpkg.com/hyperscript.org@0.9.12"
   "https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"
   ]) 

(defn script-tag [js-source-str]
  [:script
   {:src js-source-str
    :type "text/javascript"}])

(defn js-script-links [recaptcha]
  (let [local-js-links 
        (->> local-js-file-names
          (map #(str js-path %))
          (map script-tag))
        
        cdn-sj-links
        (-> (map script-tag js-cdn-include-urls)
          (conj htmx-main-lib-script-tag)
          (cond->  recaptcha
            (conj recaptcha-script-tag)))]
    (concat
      local-js-links
      cdn-sj-links)))

(comment 
  (js-script-links nil))

;; ----------- css

(defn main-css-path []
  (if-some [f (io/file (io/resource "public/css/main.css"))]
    (str "/css/main.css?t=" (.lastModified f))
    "/css/main.css"))

(def css-urls
  ["https://fonts.googleapis.com/css?family=Open+Sans|Poiret+One|Oswald:300"
   "https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"
   "/css/animate.css"
   "/css/nivo-lightbox.css"
   "/img/lightbox/default.css"
   (main-css-path)])
              
(def css-links
  (for [css-url css-urls]
    [:link {:href css-url 
            :rel "stylesheet" 
            :type "text/css" 
            :media "screen"
            }]))

;;;-------------------

(def icon-meta
  [[:link {:href "/apple-touch-icon.png", :sizes "180x180", :rel "apple-touch-icon"}]
   [:link {:href "/favicon-32x32.png", :sizes "32x32", :type "image/png", :rel "icon"}]
   [:link {:href "/favicon-16x16.png", :sizes "16x16", :type "image/png", :rel "icon"}]
   [:link {:color "#5bbad5", :href "/safari-pinned-tab.svg", :rel "mask-icon"}]
   [:meta {:content "#da532c", :name "msapplication-TileColor"}]
   [:meta {:content "#0d9488", :name "theme-color"}]
   ;; old stuff
   #_[:link {:REL "SHORTCUT ICON" :HREF "icongs.ico" :type "image/x-icon"}]])

(def old-meta 
  [[:meta {:charset "utf-8"}]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
   [:meta {:property "og:type" :content "website"}]
   #_[:meta {:content c/page-description :name "description"}]
   [:meta {:content "width=device-width, initial-scale=1.0" :name "viewport"}]])


(defn include-livejs? [ctx]
  (:gs.groundedsol/include-livejs ctx))

(defn head-stuff  [ctx head recaptcha]
  (concat
    [[:link {:href "/site.webmanifest", :rel "manifest"}]]
    icon-meta
    (js-script-links recaptcha)
    #_(when (include-livejs? ctx)
      [[:script {:src "/js/live.js" :title "default"}]])
    css-links
    head))

(comment
  (head-stuff [] true))


