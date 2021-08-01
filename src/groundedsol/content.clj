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

(def faq
  [["What is the best time of year to plant?"
    "In Florida you can plant any time of year. In winter, colder temperatures reduce growth rate in most plants,
           therefore plants require less water to get established than in summer months where the growth rate is high and temperatures
           can be stressful. When planting in spring and summer, supplemental water will help the new root system stay hydrated in naturally hot,
           dry soils. If late June, July and August bring our normal rainy seasons, nature will take care of extra watering in non-irrigated sites.
           Spring and fall have bright, shiny, cool days where working in the garden is a pleasure, so it's easy to be outside tending to new plants.
           Helping them get established."]
   ["Do I need irrigation for Natives?"
    "Irrigation is not necessarily needed long term, but each plant will need water until it is fully established.
           Each type of plant will have a different establishment interval. Seasonal annuals offer a quick growing life cycle and
            a relatively short establishment period. Anywhere from 1 - 2 months of attention is sufficient to give annuals their start.
             Perennials or shrubs with woody stems will need a little bit longer, in the range of 3 - 6 months.
              Trees are often planted and left to do their thing, but this can give the tree a rough start and stunt long term growth. Trees enjoy regular watering for approximately a year. Regular watering can be through targeted pooling around the base with a hose, 2 - 3 times a week for the first 3 months, and expanding the intervals (but not quantity) over time. Trees should continue to receive water at least 1 time a week for up to, and beyond a year depending on how the tree acclimates to its new soil and light conditions."]
   ["What is site prep?"
    "Site prep is the first step of the long term maintenance strategy.
           When you look at the bed you'd like to re-vamp, or the sodded yard you'd like to reduce,
            you are looking at your site. Preparation is the process of getting it ready for planting.
             A short sighted view is to slash down weeds or shrubs, till the ground, then plant.
             This will disturb the natural seed bank and encourage rapid germination of undesired weeds.
             Mulch or landscape fabrics won't be able to keep them all down. Good preparation is giving time
             for that seed bank to become exhausted. Knowing what will grow back after you plant is a big part
             of the maintenance strategy."]
   ["What is your rate?"
    "My rate is $150/hr. As I work with various clients it becomes clear that each project has a unique
           scope of attention. Sometimes the homeowner just needs a site review so they can begin to make informed decisions.
           Sometimes instructions are needed to show which plants will add to the space, and where they should be added or moved.
           Sometimes there are deeper problems like tree health, soil fertility or erosion issues and I'm needed to work alongside
           my clients to find real solutions. Keeping a flat rate allows me to take each problem and give it the attention it needs
           rather than setting price packages for each set of services."]
   ["Do you install?"
    "Yes, small projects are my specialty. For large projects I work with native nurseries skilled
           in installing full native plant landscapes. I'm happy to work along side you if you like to get your hands dirty,
           or simply place the plants where they should be planted for those who have a maintenance person they like to work with."]
   ["Do you deliver plants?"
    "Yes, small or large deliveries can be scheduled."]
   ["What is an annual vs perennial?"
    "An annual is a vegetative plant that fulfills it's lifecycle in one season.
           Which means it germinates from seed, grows into a plant, blooms for several weeks and then sets new seeds
           to be scattered around for the next time the season is right. Annuals frequently occur once a year
            (like blanket flower, Gaillardia pulchella).  Perennials have a few distinctions.
            The classic perennial is a plant that lasts 2 years. The first season it grows vegetatively, sets a flower,
            then sets seed. As the bloom and foliage die, energy is put back into the roots and they don't die. The second year,
            just when you thought it was gone, it re-grows from the roots, flowers and sets seed, then it dies completely,
             (like spotted beebalm, Monarda punctata or tropical sage, Salvia coccinea).
              New seeds pop up and start the cycle all over again.
               Perennial can also refer to something that doesn't die back completely but can be severely cut back or frozen
               back between seasons (like firebush, Hamelia patens)."]
   ["What is this plant?"
    "Take a look at my gallery for images and information.
           I should have a few links to my favorite plant databases. Remember to use the botanical name when looking up
            the plant for more accurate information."]
   ["Do you sell weed?"
    "No, but there are a host of native medicinals out there. Let's keep the research going."]
   ["What is a larval host plant?"
    (list
      "It's a plant that has a specialized relationship with a specific species of butterfly."
      [:a {:href "https://www.floridamuseum.ufl.edu/wildflower/searchAllButterflyImages.asp?offset=0" :target "_blank"} "Florida Museum.ufl.edu"]
      ".
           The butterfly is a beautiful creature who will drink from any cup, but will only lay their eggs on a particular
            type of plant. That's because the larvae, when it hatches will be so small it has to start eating right away.
            The worst type of raw vegan possible. They will only eat the freshest foliage on a particular genus,
            or species of plant. So, this is what we mean by habitat, grouping plants together that exist in concert
            with insects to form the base of the food web."
      [:a {:href "images/samples/Monarch%20egg-0044%20resized.jpg" :target "_blank"} [:img.img-shadow {:alt "Monarch Larvae" :height "252" :src "images/samples/monarchegg.jpg" :width "373"}]])]
   ["How do I attract butterflies?"
    "Flowers that produce nectar and have a tubular shape to them can be planted.
 Larval host plants will bring them around to mate and lay eggs, which provides regular
 flight patterns to enjoy from a window or from the porch."]
   ["Do native bees sting?"
    "Honey bees, wasps or yellow jackets are what we think of when we imagine being stung.
 Honey bees sting to protect the large quantity of honey they collect.
 Wasps and yellow jackets are fine until the nest is disturbed, and their stinger are large so they hurt a lot.
 Native bees tend to be small. Many of them are no larger than a dime.
 Native bees are also solitary bees, nesting in 3/8 inch diameter holes or buried in the ground.
 Each hole is home to just a few larval eggs. Each egg is left with enough nectar and pollen to grow into a
 strong young bee. Since native bees don't make a large quantity of honey, they don't have that much to protect.
 Often they go about their business right under our noses and we don't even notice. Yes, they can sting,
 but they are likely to just fly by."]
   ["Why should I use natives in my landscape?"
    "Native plants are the base of the food web for your region.
 It starts with the smallest insects eating specific plants.
 Slightly larger insects and animals eat those and so on, until you get to the top predators.
 Studies show habitat loss as the top reason that the bottom of the food chain is disappearing."]
   ["Will my plants look perfect all year long?"
    "Probably not. Each season brings different temperatures, rainfall, intensity of light and length
 of evening. Native plants respond to these changes and showcase true seasonality for Florida.
 Spring and Summer bring a lot of green growth. Fall brings a lot of color through grasses and flowers.
 Winter brings leaf change and dormancy. Cutting away the brown in the Spring will invigorate new growth as
 temperatures warm and the sun rises in the sky."]
   ["Do you know small project contractors?"
    "Yes. I have a few contacts that I've been referring to clients to help with tree work,
 small builds or paver-stone pathways."]
   ["What is a low maintenance landscape?"
    "All landscapes require some maintenance, but there are degrees to which maintenance
 can be minimized. For example, a sod landscape with viburnum or podocarpus hedge may need to be trimmed
 weekly during the summer and monthly during the winter. Native shrubs and trees tend to have a slower
 growth rate which reduces the trimming schedule to once a year. Shrubs and small perennial bushes may
 need trimming twice a year to keep shape in the summer and the dead wood cut back in the Spring.
 Annuals can get away with weeding and deadheading four times a year between each season.
 So a 12 visit cycle, or 18 visit maintenance cycle per year turns into a 4 visit cycle with the summer
 and spring visit lasting longer than the others. This cuts down on gas emissions and noise pollution from
 hand held equipment. Reduced water requirements save money and water as we watch our pristine resources
 grow scarce. And the biological diversity that occurs with native landscapes can eliminate the need to apply
 pesticides to the landscape."]])
