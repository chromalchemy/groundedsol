(ns gs.site
  (:require
    [clojure.string :as string]
    [lambdaisland.ornament :as o :refer [defstyled]]
    [garden.compiler :as gc]
    [gs.garden.page]))

(def base-url "https://groundedsol.com")

(def build-path "build/")

(def pages
  {:home
     {:filename "index"
      :title "Grounded Solutions Landscape Consultation and Design Company"}
   :florida-plants
     {:filename "floridaplants"
      :name "Plants"}
   :email-confirmation
     {:filename "thankyou"
      :name "Thank You"}
   ;:ecosystems {}
   :services
     {:filename "consultationanddesign"
      :name "Plants"
      :title "Consultation and Design Eco-Logical Landscapes"}
   :about {}
   :consultation
     {:name (string/upper-case "Consultation")
      :filename "consultationanddesign"}
   ;:design {:name (string/upper-case "Design")}
   :contact {}
   :faq {:name "FAQ"}})
   ;:florida-plants-411 "FL Plants 411"
   ;:pop-up-shop {:name "POP UP Shop"}})

(defn html-filename [page-key]
  (let [page (pages page-key)
        specified-name (:filename page)]
    (-> (if specified-name
          specified-name
          (name page-key))
      (str ".html"))))

(comment
  (html-filename :home))

(def image-root
  (str "images/"))

(defn img-path
  ([filename]
   (str image-root filename))
  ([filename folder]
   (str image-root folder "/" filename)))


