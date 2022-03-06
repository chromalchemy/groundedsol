(ns gs.site
  (:require
    [clojure.string :as string]
    [lambdaisland.ornament :as o :refer [defstyled]]
    [garden.compiler :as gc]))


(def base-url "https://groundedsol.com")

(def build-path "build/")

(def pages
  {:email-confirmation/file "thankyou"
   :email-confirmation/title "Thank You"
   :home/file "index"
   :home/name "Home"
   :home/title "Grounded Solutions Landscape Consultation and Design Company"
   :services/name "Services"
   :services/file "consultationanddesign"
   :services/title "Consultation and Design Eco-Logical Landscapes"
   :consultation/name (string/upper-case "Consultation")
   :design/name (string/upper-case "Design")
   :pop-up-shop/name "POP UP Shop"
   :consultation/file "consultationanddesign"
   :florida-plants/name "Plants"
   :florida-plants/file "floridaplants"
   :ecosystems/file "ecosystems"
   :ecosystems/name "Ecosytems"
   :about/file "about"
   :about/name "About"
   :florida-plants-411/name "FL Plants 411"
   :contact/file "contact"
   :contact/name "Contact"
   :faq/name "FAQ"
   :faq/file "faq"})

(defn page-kw [page-kw tail-str]
  (keyword (str (name page-kw) "/" tail-str)))

(def page-keys [:home :services :florida-plants :faq :about :contact])

(defn page-val [page s]
  (pages (page-kw page s)))

(defn html-filename [page]
  (str (pages (page-kw page "file")) ".html"))

(def image-root
  (str "images/"))

(defn img-path
  ([filename]
   (str image-root filename))
  ([filename folder]
   (str image-root folder "/" filename)))

(comment
  (html-filename :home))

(comment
  (page page-key content))

(def global-garden-style-rules
  [])

(comment
  (->> global-garden-style-rules
    gc/compile-css))

(defn local-styles []
  (list
    [:style (o/defined-styles)]
    [:style (gc/compile-css global-garden-style-rules)]))

(defn concatenated-styles-string []
  (apply str
    (interpose "\n"
      [(o/defined-styles)
       (gc/compile-css global-garden-style-rules)])))



