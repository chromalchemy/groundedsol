(ns gs.hiccup
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [com.biffweb.impl.util :as util]
            [ring.middleware.anti-forgery :as anti-forgery]
            [lambdaisland.hiccup :as hiccup]
            [lambdaisland.ornament :refer [defstyled]]
            [gs.components :as components]
            ;; move rum stuff over from here
            #_[com.biffweb :as biff]
            
          ))

;; clone of com.biffweb.impl.rum

(defn render-static-markup [body]
  (hiccup/render body {:doctype? false}))

(comment
  (render-static-markup [:div])
  )

(defn render [body]
  {:status 200
   :headers {"content-type" "text/html; charset=utf-8"}
   :body (hiccup/render body)})

(defn unsafe [html]
  [::hiccup/unsafe-html html])

(def emdash1 [:span [::hiccup/unsafe-html "&mdash;"]])

(def endash [:span [::hiccup/unsafe-html "&#8211;"]])

(def nbsp [:span [::hiccup/unsafe-html "&nbsp;"]])

(defn g-fonts
  [families]
  [:link {:href (apply str "https://fonts.googleapis.com/css2?display=swap"
                  (for [f families]
                    (str "&family=" f)))
          :rel "stylesheet"}])

(defstyled main-elem :section
  :flex :flex-grow :flex-shrink-0 :w-auto
  [:.inner :w-full]
  ([content]
   [:<>
    [:div.inner
     content]]))

(defstyled body-elem :body
  :h-full :flex :flex-col
  ;; biff ex body styles
  ;; {:position "absolute"
  ;;  :width "100%"
  ;;  :min-height "100%"
  ;;  :display "flex"
  ;;  :flex-direction "column"}
  ([content]
   [:<>
    [components/scroll-to-top]
    [components/masthead]
    [main-elem content]
    [components/footer]]))



(defn base-html
  "Wraps contents in an :html and :body element with various metadata set.
    font-families:  A collection of families to request from Google fonts (see g-fonts).
    head:           Additional Rum elements to include inside the head."
  [{:base/keys [title
                description
                lang
                image
                icon
                url
                canonical
                font-families
                head]
    :as opts}
   & contents]
  [:html
   {:lang lang
    :style {:min-height "100%"
            :height "auto"}}
   [:head
    [:title title]
    [:meta {:name "description" :content description}]
    [:meta {:content title :property "og:title"}]
    [:meta {:content description :property "og:description"}]
    (when image
      [:<>
       [:meta {:content image :property "og:image"}]
       [:meta {:content "summary_large_image" :name "twitter:card"}]])
    (when-some [url (or url canonical)]
      [:meta {:content url :property "og:url"}])
    (when-some [url (or canonical url)]
      [:link {:ref "canonical" :href url}])
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
    (when icon
      [:link {:rel "icon"
              :type "image/png"
              :sizes "16x16"
              :href icon}])
    [:meta {:charset "utf-8"}]
    (when (not-empty font-families)
      [:<>
       [:link {:href "https://fonts.googleapis.com", :rel "preconnect"}]
       [:link {:crossorigin "crossorigin",
               :href "https://fonts.gstatic.com",
               :rel "preconnect"}]
       (g-fonts font-families)])
    (into [:<>] head)]
   [body-elem contents]])

(defn form
  "Returns a [:form ...] element.
  
    hidden:  A map from names to values, which will be converted to
             [:input {:type \"hidden\" ...}] fields.
    opts:    Options for the :form element (with hidden removed).
  
    Sets :method to \"post\" by default, and includes a CSRF token (via
    ring.middleware.anti-forgery/*anti-forgery-token*)."
  [{:keys [hidden] :as opts} & body]
  [:form (-> (merge {:method "post"} opts)
           (dissoc :hidden)
           (assoc-in [:style :margin-bottom] 0))
   (for [[k v] (util/assoc-some hidden "__anti-forgery-token" anti-forgery/*anti-forgery-token*)]
     [:input {:type "hidden"
              :name k
              :value v}])
   body])

;; you could say that hiccup is one of our main exports
(defn export-hiccup
  "Generate HTML files and write them to a directory.
  
    pages:  A map from paths to hiccup data structures, e.g.
            {\"/\" [:div \"hello\"]}. Paths that end in / will have index.html
            appended to them.
    dir:    A path to the root directory where the files should be saved, e.g.
            \"target/resources/public\"."
  [pages dir]
  (doseq [[path hicc] pages
          :let [full-path (cond-> (str dir path)
                            (str/ends-with? path "/") (str "index.html"))]]
    (io/make-parents full-path)
    (spit full-path (hiccup/render hicc))))

