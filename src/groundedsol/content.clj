(ns groundedsol.content)

(def images
  {:logo/file "logo200.png"
   :logo/alt "Grounded Solutions"})

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
   {:title "FLORA & FAUNA"
    :subtitle "Florida, a fine balance of resources."
    :body "Drought tolerant Florida plants, offering seasonal flowers and berries for many
            other species is the basis for Grounded Solutions landscape designs. But we don’t
            stop there. A pop-up plant shop is now germinating to help spread the seeds of diversity in your landscape."
    :link-text "Gallery"
    :link (routes :florida-plants/file)}})

;(def markdown-test my-airtable-data)

(def slogan "Ecological Landscape Design & Consulting")

(def hot-plant-gallery
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
    {:delay "1.0s" :img-file "plant5b.jpg" :thumb-file "plant5s.jpg" :title "Beach verbena, Glandularia maritima"}]})


(def welcome
  {:title "Welcome World"
   :body "Grounded Solutions is a Florida native focused landscape consultation and design
company. We provide ecologically informed landscaping ideas and services to enhance the natural habitat of developing Florida."
   :link "about.html"
   :link-text "Learn More"})

(def reviews
  [{:img "reviews3.jpg"
    :date "March 2, 2018"
    :name "Patty R."
    :review
    "My husband and I had been interested in a landscape of Florida native plants since the house was purchased in July 2013, but we didn’t have
a clue how to do start or which plants to incorporate. Fortunately we were lead to Amanda Martin. This young woman has a plethora of
knowledge involving not only Florida native plants, but landscaping with them, and also bee keeping. We are so grateful for her expertise and
continued dedication to the yard she created. Thanks, Amanda!"}
   {:img "reviews2.jpg"
    :date "June 20, 2017"
    :name "Gina S."
    :review
    "Amanda created a beautiful native landscape plan for our new home.
    She knew what grew here before the orange groves and subdivisions and included the natives that will easily
    thrive and support the butterflies, birds and other insects and critters that will visit and live in our yard.
    We are so enjoying our new yard!"}
   {:img "reviews1.jpg"
    :date "May 13, 2017"
    :name "Carla N."
    :review
    "Just stopped into a local nursery, met Amanda, bought some great plants, and supported local business. You should do the same. Support this local lady making it happen."}])



