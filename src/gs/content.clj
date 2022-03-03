(ns gs.content
  (:require [gs.page :refer [pages html-filename]]
            [clojure.string :as string]))

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
    :link (html-filename :consultation)}
   :design
   {:title "Design"
    :subtitle "Natural, organized and beautiful."
    :body "No two properties are the same. Each requires it’s own unique focus. Providing a
            hand-drawn sketch or digital design is the fun part. Let’s start by walking your property together."
    :link-text "Contact for a Consult"
    :link (html-filename :consultation)}
   :flora-and-fauna
   {:title "FLORA & FAUNA"
    :subtitle "Florida, a fine balance of resources."
    :body "Drought tolerant Florida plants, offering seasonal flowers and berries for many
            other species is the basis for Grounded Solutions landscape designs. But we don’t
            stop there. A pop-up plant shop is now germinating to help spread the seeds of diversity in your landscape."
    :link-text "Gallery"
    :link (html-filename :florida-plants)}})

;(def markdown-test my-airtable-data)

(def slogan "Ecological Landscape Design & Consulting")

(def hot-plant-gallery
  {:title "What's Hot"
   :img-folder "samples"
   :gallery-name "catalog1"
   :link (html-filename :florida-plants)
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


(def new-images-filenames
  ["Bees-defending-flowers-0123.jpg"
   "Bees-guarding-flower-0124.jpg"
   "Bees-defending-flowers-0123.jpg"
   "Blossom-Eryngium-yuccafolium-0270.jpg"
   "Bug-Ant-and-flower-passiflora-suberosa-0108.jpg"
   "Bug-assasin-nymph-0007.jpg"
   "Bug-Bee-on-Coreopsis-0018.jpg"
   "Bug-Bee-on-Coreopsis-0044.jpg"
   "Bug-Bee-on-Elephantopus-elatus-0071.jpg"
   "Bug-Bee-on-Elephantopus-elatus-0075.jpg"
   "Bug-Bee-on-Elephantopus-elatus-0078.jpg"
   "Bug-Bee-on-Gaillardia--0042.jpg"
   "Bug-Bee-on-gaillardia-profile-0086.jpg"
   "Bug-Bee-on-gaillardia-profile-0099.jpg"
   "Bug-Bee-on-Salvia-involucrata-0099.jpg"
   "Bug-Biocontrol-on-Arial-potato-20140905_120659.jpg"
   "Bug-Bumblebee-on-Monarda-0126.jpg"
   "Bug-Butterfly-Cassias-blue-on-toadflax-0116.jpg"
   "Bug-Butterfly-cloudless-sulfur-on-Hamelia-0083.jpg"
   "Bug-butterfly-swallowtail-emerging-on-dutchmans-pipe-0165.jpg"
   "Bug-Butterfly-Zebra-longwings-on-Hamelia-0096.jpg"
   "Bug-Butterfly-Zebra-longwings-pair-to-mate-0093.jpg"
   "Bug-Butterfly-Zebra-longwings-pair-to-mate-0145.jpg"
   "Bug-Butterfly-Zebra-longwings-pair-to-mate-0212.jpg"
   "Bug-Carpenter-bee-female-on-Monarda-0156.jpg"
   "Bug-carpenter-bee-on-Monarda-0051.jpg"
   "Bug-Carpenter-bee-on-Monarda-0199.jpg"
   "Bug-dragonfly-blue-0142.jpg"
   "Bug-Dragonfly-small-0057.jpg"
   "Bug-Gopher-tortoise-Ms-vanderbuilt-0127.jpg"
   "Bug-green-bee-on-Amorpha-fruticosa-bloom-0419.jpg"
   "Bug-honeybee-on-Saw-palmetto-0272.jpg"
   "Bug-Leaf-cutter-bee-in-high-def-0068.jpg"
   "Bug-Leaf-footed-bugs-on-milkwort-0103.jpg"
   "Bug-Leaf-shape-and-ladybug-0159.jpg"
   "Bug-Lizard-in-the-wild-0112.jpg"
   "Bug-Monarch-caterpillar-exiting-egg-on-A.incarnata-0070.jpg"
   "Bug-monarch-caterpillar-on-tropical-milkweed-0032.jpg"
   "Bug-Monarch-egg-on-milkweed-0044.jpg"
   "Bug-monarch-on-stokes-aster-0353.jpg"
   "Bug-Orchid-bee-after-Hamelia-0104.jpg"
   "Bug-Orchid-bee-on-Hamelia-0108.jpg"
   "Bug-parasitic-wasp-on-Monarda-0159.jpg"
   "Bug-Sawfly-on-Chickasaw-plum-bloom--0080.jpg"
   "Bug-small-bee-collecting-pollen-from-gaillardia-0143.jpg"
   "Bug-Snake-in-Facahatchee-slough-0449.jpg"
   "Bug-Spider-in-web-0048.jpg"
   "Bug-spider-on-A.-incarnata-0272.jpg"
   "Bug-spider-on-A.-incarnata-0273.jpg"
   "Bug-spider-on-hypericum-0014.jpg"
   "Bug-Spider-on-Senna--0031.jpg"
   "Bug-spider-on-Yucca-0259.jpg"
   "Bug-Threadwaisted-wasp-on-Monarda-0174.jpg"
   "Bug-Tortoise-in-burrow-Ms-Vanderbuilt-0067.jpg"
   "Bug-Wasp-on-FL-poinsettia-red-0087.jpg"
   "Bug-Wasp-on-Scorpiontail-0085.jpg"
   "Butterflies-nectaring-0051.jpg"
   "Butterfly-caterpillar-senna-on-senna-0019.jpg"
   "Butterfly-Crescent-blue-Cassius-blue-0106.jpg"
   "Butterfly-egg-on-Passiflora-suberosa-0112.jpg"
   "Butterfly-egg-on-senna-0014.jpg"
   "Butterfly-Giant-swallowtail-on-redroot-0055.jpg"
   "Butterfly-Giant-swallowtail-on-redroot-0056.jpg"
   "Butterfly-Heliconia-caterpillar-on-P.-suberosa-0013.jpg"
   "Butterfly-Monarch-chrysalis-0015.jpg"
   "Butterfly-Monarch-0090.jpg"
   "Butterfly-White-peacock-0226.jpg"
   "Flower-aesclipias-incarnata-half-open-half-closed-0018.jpg"
   "Flower-aesclipias-incarnata-half-open-half-closed-0028.jpg"
   "Flower-Aesclipias-tuberosa-0068.jpg"
   "Flower-Aesclipias-tuberosa-0070.jpg"
   "Flower-Aesclipius-perennis-0140.jpg"
   "Flower-Amorpha-fruiticosa-0402.jpg"
   "Flower-Amorpha-fruiticosa-0439.jpg"
   "Flower-Ant-Campsis-radicans-0101.jpg"
   "Flower-arrangement-Monarda-punctata-0127.jpg"
   "Flower-arrangement-Monarda-punctata-0142.jpg"
   "Flower-arrangement-San-Diego-0004.jpg"
   "Flower-arrangement-San-Diego-0006.jpg"
   "Flower-Asclepias-linaria-branch-0187.jpg"
   "Flower-Asclepias-linaria-macro-0295.jpg"
   "Flower-Asclepius--Wekiva-sandhill-0003.jpg"
   "Flower-Asclepius-incarnata-macro-0007.jpg"
   "Flower-Asclepius-incarnata-0099.jpg"
   "Flower-Asclipias-incarnata-0017.jpg"
   "Flower-blue-eyed-grass-cup-receptical-0248.jpg"
   "Flower-Blueeyed-grass-shiny-flower-nectar-appearance-0299.jpg"
   "Flower-brittons-beargrass-nectar-pool-0315.jpg"
   "Flower-bug-bee-on-Ruellia-caorlinianus-0053.jpg"
   "Flower-Butterfly-pea-0170.jpg"
   "Flower-Cephelanthus-occidentalis-0046.jpg"
   "Flower-Chamechrista-fasciculata-0084.jpg"
   "Flower-Chickasaw-plum-branch-0097.jpg"
   "Flower-Chickasaw-plum-branch-0103.jpg"
   "Flower-Chickasaw-plum-branch-0105.jpg"
   "Flower-chrynum-lily-0123.jpg"
   "Flower-Chrysopsis-spike-0194.jpg"
   "Flower-Chrysopsis-spike-0201.jpg"
   "Flower-Chrysopsis-0192.jpg"
   "Flower-Clematis-in-sandhill-0233.jpg"
   "Flower-cluster-CA-0401.jpg"
   "Flower-Conradina-grandiflora-little-bee-0040.jpg"
   "Flower-coreopsis-0149.jpg"
   "Flower-Elephantopus-elatus-0064.jpg"
   "Flower-eryngium-yuccafolium-0068.jpg"
   "Flower-Erythrina-herbacea-0042.jpg"
   "Flower-Firebush-and-monarda-0000.jpg"
   "Flower-Fleabane-0061.jpg"
   "Flower-gaillardia-red-with-honeybee-0080.jpg"
   "Flower-gaillardia-red-with-honeybee-0123.jpg"
   "Flower-gaillardia-red-with-honeybee-0124.jpg"
   "Flower-gaillardia-yellow--0258.jpg"
   "Flower-gaillardia-yellow-and-red-0250.jpg"
   "Flower-Gaillardia-yellow-with-tiny-bee-in-middle-0255.jpg"
   "Flower-glandularia-martitima-0078.jpg"
   "Flower-gopher-apple-0056.jpg"
   "Flower-green-eyes-Berlandaria-subacalis-0083.jpg"
   "Flower-Hamelia-patens-0205.jpg"
   "Flower-Hypericum-hypercoides-branch-0117.jpg"
   "Flower-Hypericum-hypercoides-0111.jpg"
   "Flower-Hypericum-reductum-branch-0109.jpg"
   "Flower-Hypericum-0077.jpg"
   "Flower-leaf-paw-paw-in-shade-0173.jpg"
   "Flower-lonicera-sempervirens-0295.jpg"
   "Flower-Lonicera-sempervirens-0298.jpg"
   "Flower-mimosa-striggilosa-0103.jpg"
   "Flower-Monarda-punctata-0210.jpg"
   "Flower-Muhlenbergia-capillaris-Muhly-grass-0180.jpg"
   "Flower-Palafoxia-feayi-0135.jpg"
   "Flower-Passiflora-incarnata-0127.jpg"
   "Flower-pathiopetilum-0434.jpg"
   "Flower-Polygala-rugelii-milkwort-0015.jpg"
   "Flower-Polygala-rugelii-yellow-milkwort-0070.jpg"
   "Flower-Rhexia-0016.jpg"
   "Flower-Rivina-humilis-0024.jpg"
   "Flower-Rivina-humilis-0026.jpg"
   "Flower-Rose-deep-pink-0161.jpg"
   "Flower-Rudbeckia-hirta-0333.jpg"
   "Flower-Rudbeckia-lacinata-panoramic-0328.jpg"
   "Flower-salvia-lyrata-0043.jpg"
   "Flower-Salvia-white-0070.jpg"
   "Flower-sandspur-fuzzy-hairs-0286.jpg"
   "Flower-Scuttelaria--0057.jpg"
   "Flower-Scuttelaria--0058.jpg"
   "Flower-Sebatia-anthers-0414.jpg"
   "Flower-Sebatia-munched-petals-0104.jpg"
   "Flower-Sebatia-white-0145.jpg"
   "Flower-Sebatia-0147.jpg"
   "Flower-Sedge-chromophytum--0053.jpg"
   "Flower-Sedge-wetland-0009.jpg"
   "Flower-Stoksia-leavis-0351.jpg"
   "Flower-Tarflower-Bejaria-racemose-0020.jpg"
   "Flower-Tetons-Mountain-side-20140815_105230.jpg"
   "Flower-Toadflax-Linaria-canadensis-0046.jpg"
   "Flower-Tradescantia-purple-parts-0057.jpg"
   "Flower-Tradescantia-purple-parts-0070.jpg"
   "Flower-tradescantia-purple-0023.jpg"
   "Flower-water-lily-Facahatchee-slough-0517.jpg"
   "Flower-Y-Scorpion-tail-0147.jpg"
   "Flower-Yucca-white-0259.jpg"
   "Fruit-Amorpha-fruiticosa-0115.jpg"
   "Fruit-Blueberry-2015-04-02-14.57.14.jpg"
   "Fruit-Blueberry-0101.jpg"
   "Fruit-Cabbage-Farm-rows-20141023_100806.jpg"
   "Fruit-Callicarpa-americana-Beautyberry-0073.jpg"
   "Fruit-Cretagus-tree-0220.jpg"
   "Fruit-Grapes-Mexico-0066.jpg"
   "Fruit-Grapes-Mexico-0067.jpg"
   "Fruit-passiflora-suberosa-0111.jpg"
   "Fruit-Wild-coffee-matte-0477.jpg"
   "Landscape-Adrian-Marshall-Backyard-0131.jpg"
   "Landscape-Amherst-Ave-Bird-Dirt-Bath-0092.jpg"
   "Landscape-Amherst-ave-street-view-Muhly-0176.jpg"
   "Landscape-Architecture-inspire-0040.jpg"
   "Landscape-Architecture-inspire-0044.jpg"
   "Landscape-Bill-Garmany-Street-view-0011.jpg"
   "Landscape-black-and-white-south-FL-0148.jpg"
   "Landscape-containers-with-bromeliads-0427.jpg"
   "Landscape-Everglades-Grassland-upclose-0526.jpg"
   "Landscape-Everglades-longrange-0533.jpg"
   "Landscape-Everglades-longrange-0535-2.jpg"
   "Landscape-Everglades-longrange-0535.jpg"
   "Landscape-Everglades-Tillandsia-hammock-0518.jpg"
   "Landscape-gaillardia-front-yard-0245.jpg"
   "Landscape-Green-Bill-Garmany-0057.jpg"
   "Landscape-Ilex-vomitoria-hedge-20140920_151220.jpg"
   "Landscape-Lake-taho-20150121_141352.jpg"
   "Landscape-Lake-Tahoe-20150121_130240.jpg"
   "Landscape-Mexican-coast-0089.jpg"
   "Landscape-Mexico-vineyard-valley-0023.jpg"
   "Landscape-mulch-and-Cypress-tree-0051.jpg"
   "Landscape-Palms-Miami-20141126_173457.jpg"
   "Landscape-path-used-by-tortoise-0239.jpg"
   "Landscape-Pine-flatwoods-needs-to-be-burned-0167.jpg"
   "Landscape-Pine-Flatwoods-Pinellis-county-0072.jpg"
   "Landscape-rocks-in-wet-areas-0170.jpg"
   "Landscape-San-diego-beach-cliff-0024.jpg"
   "Landscape-Savannas-preserve-state-park-0182.jpg"
   "Landscape-scrub-old-burn-south-FL-0064.jpg"
   "Landscape-scrub-south-FL-0015.jpg"
   "Landscape-scrub-south-FL-0140.jpg"
   "Landscape-Viola-sp.-0440.jpg"
   "Leaf-Aesclipias-tuberosa-0069.jpg"
   "Leaf-bug-Swallowtail-on-dutchmans-pipe-0120.jpg"
   "Leaf-cactus-CA-0313.jpg"
   "Leaf-Drocera-carnivorus-0130.jpg"
   "Leaf-Fern-in-Facahatchee-0486.jpg"
   "Leaf-Fern-Osmunda-regalis-0216.jpg"
   "Leaf-Flower-attachment-Sedge-0193.jpg"
   "Leaf-Galls-on-Lyonia-0061.jpg"
   "Leaf-grape-vine-cling-0390.jpg"
   "Leaf-Helianthus-radula-Rayless-sunflower-0180.jpg"
   "Leaf-Ilex-vomitoria--0166.jpg"
   "Leaf-Juniperus-solicicola-0187.jpg"
   "Leaf-Nutrient-deficiency-Nitrogen-Butterfly-pea-0196.jpg"
   "Leaf-palmately-compound-Schefflera-0111.jpg"
   "Leaf-Passiflora-suberos-and-butterfly-egg-0074.jpg"
   "Leaf-pinnate-Amorpha-0106.jpg"
   "Leaf-Psychotria-nervosa-Wild-coffee-0131.jpg"
   "Leaf-Punus-umbellata-Flatwoods-plum-0165.jpg"
   "Leaf-ressurection-fern-and-lichen-0469.jpg"
   "Leaf-shape-salvia-lyrata-0053.jpg"
   "Leaf-sheath-Ponytail-palm-0212.jpg"
   "Leaf-texture-Silver-saw-palmetto-and-coontie-0053.jpg"
   "Leaf-thorns-Toothache-tree-small-0177.jpg"
   "Leaf-thorns-Toothache-tree-small-0178.jpg"
   "Leaf-thorns-Toothache-tree-small-0179.jpg"
   "Leaf-tomentosa-2015-08-22-17.03.43.jpg"
   "Leaf-Tomentosa-hairs-0069.jpg"
   "Leaf-Trifoliate-blackberry-0214.jpg"
   "Leaf-Whorled-attachment-Hamelia-0202.jpg"
   "Leaf-Wild-grape--0215.jpg"
   "Plant-Asclepius-Wekiva-sandhill-0011.jpg"
   "Plant-Berlandiera-subacalus-0235.jpg"
   "Plant-carphephorus-bloom-stalk-texture-0027.jpg"
   "Plant-carphephorus-0016.jpg"
   "Plant-Chrysopsis-mariana-0014.jpg"
   "Plant-Conradina-grandiflora-full-bloom-0168.jpg"
   "Plant-Conradina-grandiflora-full-bloom-0170.jpg"
   "Plant-cypress-tree-in-everglades-0350.jpg"
   "Plant-deer-moss-20140208_110231.jpg"
   "Plant-Drocera-carnivorus-0128.jpg"
   "Plant-Drocera-carnivorus-0130.jpg"
   "Plant-duckweed-0036.jpg"
   "Plant-Elephantopus-elatus-foliage-0003.jpg"
   "Plant-Elephantopus-elatus-in-bloom-0119.jpg"
   "Plant-Elephantopus-elatus-single-0177.jpg"
   "Plant-Erythrina-herbacea-0003.jpg"
   "Plant-frogfruit-phyla-nodiflora-0100.jpg"
   "Plant-Gaillardia-pulchella-UF-garden-0093.jpg"
   "Plant-glandularia-maritima-creeping-0067.jpg"
   "Plant-gopher-apple-on-side-of-road-0003.jpg"
   "Plant-gopher-apple-on-side-of-road-0052.jpg"
   "Plant-gopher-apple-0023.jpg"
   "Plant-Grass-St-augustine-0044.jpg"
   "Plant-green-eyes-berlandiara-subacaulis-0048.jpg"
   "Plant-Kalenchoe-Flapjack-backlit-0162.jpg"
   "Plant-liatris-spicata-0290.jpg"
   "Plant-lonicera-sempervirens-0303.jpg"
   "Plant-mimosa-striggilosa-0084.jpg"
   "Plant-mimosa-striggilosa-0110.jpg"
   "Plant-Myrcianthes-fragrans-in-3-gal-pot-0037.jpg"
   "Plant-Osmunda-cinnemomea-0023.jpg"
   "Plant-peperomia-Facahatchee-slough-0490.jpg"
   "Plant-Pityopsis-graminifolia-0280.jpg"
   "Plant-Ruellia-caroliniana-0051.jpg"
   "Plant-salvia-lyrata-0047.jpg"
   "Plant-Sebatia-in-bloom-0144.jpg"
   "Plant-tillandsia-in-bloom-0005.jpg"
   "Plant-Toadflax-Linaria-canadensis-0001.jpg"
   "Plant-toothache-tree-small-0177.jpg"
   "Plant-Twinflower-in-scrub-after-burn-0020.jpg"
   "Plant-viola-sp.--0150.jpg"
   "Plant-Yucca-filimentosa-20130302_092728.jpg"
   "Seeds-butterfly-pea-0009.jpg"
   "Seeds-Coreopsis-0137.jpg"
   "Seeds-coreopsis-0168.jpg"
   "Seeds-Gaillardia-0159.jpg"
   "Seeds-Gaillardia-0164.jpg"
   "Seeds-Pityopsis-graminifolia-0140.jpg"
   "Seeds-tropical-milkweed-0108.jpg"
   "Tea-harvest-greens-0129.jpg"
   "Tea-tones-0113.jpg"
   "Tree-cretagus--0040.jpg"
   "Tree-cypress-tree-small-0175.jpg"
   "Tree-pine-0035.jpg"])

(map
  #(-> %
     (string/replace #" " "-"))
  new-images-filenames)