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
   :consultation
     {:filename "consultationanddesign"
      :name "Consultation"
      :title "Consultation and Design Eco-Logical Landscapes"}
   :about
     {:name "About"
      :filename "about"}
   ;:design {:name (string/upper-case "Design")}
   :contact {}
   :services {:filename "services"
              :name "Services"}})
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


