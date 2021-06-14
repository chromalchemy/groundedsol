(ns groundedsol.util
  (:require
  ;  ;[groundedsol.core]
  ;  ;[markdown.core :as md]
    [cybermonday.core :as cm]
    [airtable-clj.core :as airtable]))
  ;  [groundedsol.mycontent]
  ;  [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]))

(comment)

(def api-key "keyoyjKNMHK4XUP7h")
;(:airtable-api-key env))
(def base "app2480ik2bNPMyti")
;(:airtable-base-id env))

(def table "mytable")

(def sample-query
  (airtable/select
    {:api-key api-key
     :base base
     :table table
     :max-records 5}))

(def my-airtable-data
  (-> sample-query
      :records
      first
      :fields
      (get "Notes")))
    ;md/md-to-html-string))

(def images
  {:logo/file "logo200.png"
   :logo/alt "Grounded Solutions"})

(def routes
  {:home/file                   "index.html"
   :home/name                   "Home"
   :services/name               "Services"
   :consultation/name           "CONSULTATION"
   :design/name                 "DESIGN"
   :pop-up-shop/name            "POP UP Shop"
   :consultation/file           "consultationanddesign.html"
   :florida-plants/name         "Plants"
   :florida-plants/file         "floridaplants.html"
   :ecosystems/file             "ecosystems.html"
   :ecosystems/name "Ecosytems"
   :about/file                  "about.html"
   :about/name "About"
   :florida-plants-411/name "FL Plants 411"
   :contact/file "contact.html"
   :contact/name "Contact"})

(def intros
  {:consult
   {:title "Consultation"
    :subtitle "The Landscape that gives back."
    :body "Grounded Solutions gives you a Florida yard that gives back with pollinator friendly
            plant material, less water, and eventually less maintenance. Good for you, good for Florida,
            and good for your checkbook."
    :link-text "Read More"
    :link (routes :consultation/file)}
   :design
   {:title "Design"
    :subtitle "Natural, organized and beautiful."
    :body "No two properties are the same. Each requires it’s own unique focus. Providing a
            hand-drawn sketch or digital design is the fun part. Let’s start by walking your property together."
    :link-text "Contact for a Consult"
    :link (routes :consultation/file)}
   :flora-and-fauna
   {:title "FLORA &amp; FAUNA"
    :subtitle "Florida, a fine balance of resources."
    :body "Drought tolerant Florida plants, offering seasonal flowers and berries for many
            other species is the basis for Grounded Solutions landscape designs. But we don’t
            stop there. A pop-up plant shop is now germinating to help spread the seeds of diversity in your landscape."
    :link-text "Gallery"
    :link (routes :florida-plants/file)}})

(defmacro content [& body]
  {:markdown-test my-airtable-data
   :images images
   :slogan "Ecological Landscape Design &amp; Consulting"
   :routes routes
   :intros intros
   :hot-plant-gallery
   {:title "What's Hot"
    :img-folder "samples"
    :gallery-name "catalog1"
    :link (routes :florida-plants/file)
    :link-text "View More"
    :images
      [{:delay "1.0s" :img-file "plant1b.jpg" :thumb-file "plant1s.jpg" :title "Deerberry, Vaccinium stamineum"}
       {:delay ".6s" :img-file "plant2b.jpg" :thumb-file "plant2s.jpg" :title "Firebush, Hamelia patens"}
       {:delay ".2s" :img-file "plant3b.jpg" :thumb-file "plant3s.jpg" :title "Redroot, Lachnanthes caroliana and a Swallowtail getting nectar"}
       {:delay ".6s" :img-file "plant4b.jpg" :thumb-file "plant4s.jpg" :title "A fresh cut flower arrangement"}
       {:delay "1.0s" :img-file "plant5b.jpg" :thumb-file "plant5s.jpg" :title "Beach verbena, Glandularia maritima"}]}
   :welcome
   {:title "Welcome"
    :body "Grounded Solutions is a Florida native focused landscape consultation and design
company. We provide ecologically informed landscaping ideas and services to enhance the natural habitat of developing Florida."
    :link "about.html"
    :link-text "Learn More"}})

