(ns gs.content
  (:require
    [clojure.string :as string]))


(def consultion-steps
  [{:title "Step one: Making the most out of your consultation"
    :image-side :left
    :alt "Florida Front Yard with Grass"
    :src "images/samples/yard.jpg"
    :text
    [:<>
     [:p
      "Let’s walk your grounds together. Your first consultation provides a roadmap from a professional’s point of view. The first hour is all about being able to see the property and understand the scope of the projected change. We discuss what is possible, clarifying ideas and keeping a budget in mind as we go. During the site analysis consultation we spend time identifying existing problems, discuss future solutions, labor benefits and time drawbacks for each idea or dream. "
      [:p
       "Are you looking to create a bird and butterfly foraging ground? Do you want to reduce the monthly or weekly mowing maintenance required? Is water conservation a concern for you? Just need and area to sit and enjoy the view?  Sometimes the best way to move forward is to cut things back. Remove fast growing shrubs and prepare the ground for new plantings. You may want to keep some of your existing plants and only change a few areas. It’s all a discussion with suggestions and ideas at this stage."]]]}
   {:title "Step two: The Concept Design, Hand rendered or Full Digital"
    :image-side :right
    :alt "Digital Layout Design for Yard"
    :src "images/samples/yard2.jpg"
    :text
    [:<>
     [:p "By the second hour, we are on our way, working together to produce a hand drawn design or detailing a viable plan to move forward. There are two types of design, a small impact design and a digital design."]
     [:p "A small impact design is created on-site. An on-site design brings to paper the ideas suggested during the consult with color and detail. During the drawing I have an opportunity to look up individual plants with you, share which plants will work for each spot, and why they were selected for your landscape. I leave you with a small design, labeled with botanical and common names so you can move forward at your own pace. The second is a digital design that showcases the full property."]
     [:p "A digital design allows me to take home the property survey and create a drawing that is true to scale. Over the course of a few weeks I create a concept plan complete with distinct icons indicating plant size and bloom color. Outlined thoughts and intentions for each area is listed in the margins to communicate the details of the concept design. We then meet again to review the concept, walk through the plant suggestions, discuss longer term maintenance requirements and seasonal flow of the landscape. We take notes, incorporate changes, and I return home to complete and finalize planting details for each listed species. Billing for the final design takes place in 2 stages. At the time of the initial consult and at the concept meeting. The final design is emailed as a high resolution PDF for printing on any size paper."]
     [:p
      "If all you need is a direction, we can cover that with the initial consult and an on-site design. Looking for a long term plan? A digital design is the way to go. Wanting someone else to plant the design? Using the final design details, we can help coordinate that for you. Getting a design is the first steps towards the sustainable Florida landscape you want."]]}

   {:title "Step three: Implementing your design And Phased installation"
    :image-side :left
    :alt "Florida's sandy dry soils"
    :src "images/samples/yard3.jpg"
    :text
    [:<>
     [:p "With your design in hand, you are connected to established installation, irrigation and lighting contractors for a full yard installation. If you choose to have a professional do the digging, that is. Small orders of plant material can be delivered and staged for the DIY crowd wanting to get their hands dirty and keep costs low."]
     [:p "Phased installation works best when the homeowner has a few years to fully implement the design. To begin planting with trees and shrubs, or a specific area can make a big difference in the landscape without the cost and disruption of doing the entire yard at once. Longterm maintenance in wildflower areas is dependent on quality site preparation and elimination of undesirable weedy annuals. Taking a full year to groom these areas leads to easier maintenance later on. When the days are hot and rainy, you’ll be glad to see nothing but flowers growing in your garden. A phased installation approach can span 1 -3 years, depending on the speed and budget the homeowner wishes to work with."]
     [:p "A Pop-Up plant shop is in the works to bring a regular supply of native plants to Orlando and surrounding neighborhoods. Grounded solutions recommends nurseries or garden centers stocking true Florida natives to shop at. Keeping to the mission of restoring ecological diversity natural to this area we recommend species with high nectar content and quality pollen content that is nourishing to our pollinators. Cultivated varieties and exotic species don't always provide the best nutrients, if any at all. We enjoy working closely with the Florida Association of Native Nurseries to find our plant material."
      [:p "We encourage involving kids in the planting process. We hope they pick the flowers, collect the seeds, and become familiar with native plants and all the insects they attract. Developing some math and science exercises in the garden can help any young homeschooler find an interest in the natural world."]]]}
   {:title "Step four: The Growing Stage And Plant Material Management"
    :image-side :right
    :alt "Florida Native Plants"
    :src "images/samples/yard4.jpg"
    :text
    [:<>
     [:p "Just like a new haircut, this stage is the one to get used to. Many native plants thrive in our hot, sunny, sandy landscapes. Quite a few thrive in hot, shady, wet areas. Some are adapted for areas in-between. Selecting the right plant for the right place will allow for each plant to grow to maturity with less attention as it grows."]
     [:p "All plants will need to be monitored, watered frequently and kept mulched in the first few months to a year. After the plant has established roots in the area, watering can drift towards seasonal rain supply and pruning can be addressed on a quarterly basis. It’s usually a good idea to keep a 3 inch mulch barrier down to keep drifting weed seeds from taking root. We prefer to use pine needles and fine pine mulch over dyed mulches or hardwood mulch. Recently, we have begun to mulch with sand and crushed shell for more pollinator habitat opportunities. Over time the type of mulch used can change as weedy populations are brought under control."]
     [:p "It takes some time to get to know the growth habits of these native plant species, and we won’t leave you hanging, just call for a maintenance consult. Grounded Solutions wants your landscape to be successful. So, if for any reason you have questions or concerns, please email or call. It will always be our goal to bring sustainable and beautiful landscapes to Florida, and have your property give you a sense of pride in giving back."]]}
   #_
   {:title
    :image-side :left
    :alt
    :src
    :text}])

(def ajm-resume-experience
  [{:role "Biologist"
    :company "AJM Environmental Services"
    :experiences
    [{:img-alt-text "About Amanda"
      :img-path "samples/grass.jpg"
      :text "My first company! I became a certified wetland delineator, able to identify hydric soils, wetland plant indicators and understand the laws affecting wetland boundary identification."}
     {:img-path "samples/grassbed.jpg"
      :text "I used this knowledge to enhance residential lakefront properties, acquiring the necessary DEP permits for each site. Upland native plants were used in residential landscape design projects. I managed installation crews and designed management plans per site."}
     {:img-path "samples/flowerbed.jpg"
      :text "My expertise in native plant material was sought out for consultation and maintenance for LEED certified green-roof systems. I was able to install selected material on three Central Florida roof-tops with great success."}]}
   {:role "Research Scientist"
    :company "Eurofins"
    :experiences
    [{:img-path "samples/euro1.jpg"
      :text "I worked as a principle investigator for field trials on agricultural crops, As a research scientist I performed applications of registered and experimental compounds on food crops, then collected time sensitive samples, environmental data, and crop assessment/evaluation figures."}
     {:img-path "samples/euro2.jpg"
      :text "In many instances, a larger crew of people assemble to assist with trial activities. Most trials performed were RAC trials and Efficacy trials. Magnitude of Residue (RAC) trials were comprised of an application then sampling for analysis of residues. Efficacy trials tested the effectiveness of newly formulated compounds against pest and weed populations."}
     {:img-path "samples/euro3.jpg"
      :text "Other trials I performed included environmental fate studies for dissipation in sandy soils, bee toxicity studies around citrus tree blossoms and best practices for hand pollination in corn. I became a Fl registered beekeeper and a commercial pesticide applicator during this time."}]}
   {:role "Horticulturist"
    :company "Evolving Landscapes"
    :experiences
    [{:img-path "samples/evolve1.jpg"
      :text "While serving as a horticulturist with Evolving Landscapes, I provided consulting for design and installation of plants, selected for the Florida Landscape. We specialized in ecosystem identification and assisting the property owner in re-developing for a native landscape."}
     {:img-path "samples/evolve2.jpg"
      :text "Many of our clients were interested in attracting birds, butterflies and other pollinators while enjoying a low resource intensive landscape. Concept designs, plant selection, budget development, project timelines and client coordination were vital components of each job."}
     {:img-path "samples/evolve3.jpg"
      :text "Presentations on the topics of Native landscapes and Permaculture principles were provided to garden societies for the promotion of environmentally sustainable landscapes."}]}
   {:role "Research Assistant"
    :company "Florida Pesticide Research"
    :experiences
    [{:img-path "samples/fpr1.jpg"
      :text "FPR is an agricultural research company, operating for almost 30 years out of Oviedo, FL. FPR evaluates the effectiveness of cultural practices, organic and synthetic compounds on various crops, diseases and pests."}
     {:img-path "samples/fpr2.jpg"
      :text "Through the sampling of leaf tissues, root systems, whole fruit, soil and water they are able to provide analytic labs with the material they need for more accurate detection of residues. No trial is complete without detailed documentation."}
     {:img-path "samples/fpr3.jpg"
      :text "Through paper records and evaluations, statistical analysis of numbers, and database input per trial. It takes time and energy to search and research, but I thoroughly enjoyed figuring these things out."}]}
   {:role "Horticulturist"
    :company "Community Garden Organizer"
    :experiences
    [{:img-path "samples/cg1.jpg"
      :text "I was given the opportunity to lead a community supported agriculture garden. The community garden had 12 paying members that gathered twice a week for two hours. On each Thursdays I would gather with the gardeners and collectively learn about and tend to the garden needs. Most gardens allow you to rent one plot for what you'd like to grow."}
     {:img-path "samples/cg2.jpg"
      :text "This garden allowed everyone to work together to harvest from the entire garden. This helped ensure plenty of produce and greens were available for the taking each week. As each seasonal vegetable lived it's life, we would pull and replant the expired areas and trim back and harvest the producing areas."}
     {:img-path "samples/cg3.jpg"
      :text "I had fun coordinating a raw chef to come in and share a few recipes strait from the garden to the plate in just a few hours. I was able to create a small cucumber trial organic fertilizer trial to see which blends worked best. A few of the gardeners enjoyed learning the process. Counting and weighing the cucumbers as they grew."}]}
   {:role "President"
    :company "Tarflower Native Plant Society"
    :experiences
    [{:img-path "resume/tarflower-board-sq.jpg"
      :text "Helped host meetings and arrange for speakers to present to our local chapter"}
     {:img-path "resume/tarflower-talk-sq.jpg"
      :text "Facilitated meetings for the general public and coordinated speakers."}
     {:img-path "resume/tarflower-sale-sq.jpg"
      :text "Organized, arranged, and sold plants as a chapter fundraiser for Native plant community projects and educational grants."}]}])


(def horticulturalist-description
  [:<>
   [:a {:href "https://en.wikipedia.org/wiki/Horticulture" :target "_blank"} "Wikipedia definition"]
   ": A Horticulturist is someone who applies their knowledge, skills, and technologies used to grow intensively produced plants for human food and non-food uses and for personal or social needs. Their work involves plant propagation and cultivation with the aim of improving plant growth, yields, quality, nutritional value, and resistance to insects, diseases, and environmental stresses. They work as gardeners, growers, therapists, designers, and technical advisors in the food and non-food sectors of horticulture. Horticulture even refers to the growing of plants in a field or garden."])

(def in-the-works-text
  " is a pop-up plant shop on wheels! I hope to bring retail native plants, delivery and suggested placement options to homeowners and their neighborhood at large. ")

(def about-me-intro-text
  "I love anything to do with being outside in this wonderful state. Growing up in Winter Park gave me the visual appreciation of the immense value that flora and fauna added to any landscape. Becoming aware of the impact it made on our natural resources, gave the curiosity and ecological consciousness to pursue a lifetime passion and career in the field of sustainable ecosytems. To explore how all living things affect one another, six years ago I became a certified Florida Beekeeper and now have four hives and produce small batch raw wildflower honey.")

(def about-me-text
  ["I began my adventure in horticulture by getting a B.S. in Environmental Horticulture from the University of Florida. I enjoyed learning all about plant physiology, growth condition requirements, identification markers, along with disease, insect and nutrient strategies. Prior to my degree I always loved flowers and took a job taking care of interior plants. Interior plant-scaping brought me to more philosphical conversations about plants and plant care. Bringing plants out of nature and into offices, both high-rises and small offices brought various constraints to what kind of plant material can be used, how it needed to be grown in low light conditions, and most importantly, how to keep plants alive for those who tend to kill plants in their personal experiences. I was driven to learn what plants like and how to accomodate for their needs. See below for both a definition of what a horticulturist does and some of my work/project history."
   "In Decemebr of 2013, I began Grounded Solutions Inc. Having found the genera of Native plants and their importance in our landscapes I was able to focus on this wide range of plant material and begin understanding the relationships they have with our soils, our rainfall, our seasonal temperature and our precious pollinators. Taking this education and awareness to my clients, I help educate others about individual plants and how they attract beneficial wildlife through an aesthetically pleasing design. My goal is to increase biodiversity in the landscape and help my clients notice and appreciate the diversity they are witnessing. Maintenance habits can be built in to weekly walks through the landscape and with observation it becomes easier to know when and how to prune these plants. Grounded Solutions Inc has provided me with endless opportunities to see native plants growing in all kinds of conditions and I grow more aware of how to make a sod-free landscape look manicured and functional for the homeowner."
   "As climate change continues to accelerate, native lanscapes are becoming an obvious way to reduce fertilizer and water use. Noise pollution and CO2 pollution from lawn mowers, edgers and trimmers are greatly reduced. And with food sources for our pollinators and birds, you will see a dramatic change in the natural activity of your front and/or back yard."])

(def page-description
  "We provide ecologically sound landscaping services focused on habitat enhancement using Florida native plant species")

(def images
  {:logo/file "logo200.png"
   :logo/alt "Grounded Solutions"})

(def intros
  {:consult
   {:title "Personal Consultation"
    :subtitle "The Landscape that gives back."
    :body "Grounded Solutions gives you a Florida yard that gives back with pollinator friendly
            plant material, less water, and eventually less maintenance. Good for you, good for Florida,
            and good for your checkbook."
    :link-text "About our Process"
    :link :consultation}
   :design
   {:title "Landscape Design"
    :subtitle "Natural, organized and beautiful."
    :body "No two properties are the same. Each requires it’s own unique focus. We can sketch out some ideas... Let’s start by walking your property together."
    :link-text "Design Services"
    :link :services}
   :flora-and-fauna
   {:title "Native Flora & Fauna"
    :subtitle "Florida, a fine balance of resources."
    :body "Drought tolerant Florida plants, offering seasonal flowers and berries for many
            other species is the basis for Grounded Solutions landscape designs. A pop-up plant shop is now germinating to help spread the seeds of diversity in your landscape."
    :link-text "Plant Gallery"
    :link :florida-plants}})

;(def markdown-test my-airtable-data)

(def slogan "Ecological Landscape Design & Consulting")

(def hot-plant-gallery
  {:title "What's Hot"
   :img-folder "samples"
   :gallery-name "catalog1"
   :link :florida-plants
   :link-text "View More"
   :images
   [{:delay "1.0s" :img-file "plant1b.jpg" :thumb-file "plant1s.jpg" :title "Deerberry, Vaccinium stamineum"}
    {:delay ".6s" :img-file "plant2b.jpg" :thumb-file "plant2s.jpg" :title "Firebush, Hamelia patens"}
    {:delay ".2s" :img-file "plant3b.jpg" :thumb-file "plant3s.jpg" :title "Redroot, Lachnanthes caroliana and a Swallowtail getting nectar"}
    {:delay ".6s" :img-file "plant4b.jpg" :thumb-file "plant4s.jpg" :title "A fresh cut flower arrangement"}
    {:delay "1.0s" :img-file "plant5b.jpg" :thumb-file "plant5s.jpg" :title "Beach verbena, Glandularia maritima"}]})

(def welcome
  {:title "Welcome"
   :body "Grounded Solutions is a Florida native focused landscape consultation and design
company. We provide ecologically informed landscaping ideas and services to enhance the natural habitat of developing Florida."
   :link "about.html"
   :link-text "Learn More"})

(def reviews
  [{:img "Kelly-t-portrait-sq.jpg"
    :name "Kelly T."
    :review
    [:<>
     [:p
      "We would highly recommend Grounded Solutions! Amanda came to our home and did an On-Site hand drawn design. It was perfect for what we wanted to accomplish. We wanted a guide on how to slowly transition our space into a more Florida Friendly landscape, using as many Florida native species as possible. We wanted to do the work in phases and be hands on in the process. This is why Amanda’s on site hand drawn option was perfect for us."]
     [:p
      "Amanda is clearly passionate and knowledgeable in her field. She walked the yard with us listening to our goals and vision. She shared her knowledge to guide us to what was possible and suggested options we had not even considered. When she was finished with the drawing... which is lovely on its own, we had a good plan to get started. We had names of plant species, best placement, where to shop and what to expect. We learned a lot!"]
     [:p "So far our we are pleased with the results and will continue to work the plan outlined by Amanda. I highly recommend Amanda with Grounded Solutions. You will not regret it and you will be making your yard and the ecosystem beautiful and balanced!"]]}
   {:img "Adrian2-sq.jpg"
    :name "Adrian M."
    :review
    [:<>
     [:p
      "Amanda has done an awesome job designing, installing, and maintaining a beautiful native landscape for us as part of a new build. Her design works really well with a modern home - giving a clean, open  look with definite structure in places and being a bit more relaxed in others."]
     [:p
      "Regular reapplication of pine straw, especially when the landscape was getting established,  has build a strong weed barrier. Weekly maintenance takes about 10 minutes, mostly blowing off the driveway and sidewalk plus removing with a few weeds. We are delighted with the results"]]}
   {:img "reviews3.jpg"
    :name "Patty R."
    :review
    [:<>
     [:p
      "My husband and I had been interested in a landscape of Florida native plants since the house was purchased in July 2013, but we didn’t have a clue how to do start or which plants to incorporate. Fortunately we were lead to Amanda Martin."
      [:p
       "This young woman has a plethora of knowledge involving not only Florida native plants, but landscaping with them, and also bee keeping. We are so grateful for her expertise and continued dedication to the yard she created. Thanks, Amanda!"]]]}
   {:img "reviews2.jpg"
    :name "Gina S."
    :review
    "Amanda created a beautiful native landscape plan for our new home. She knew what grew here before the orange groves and subdivisions and included the natives that will easily thrive and support the butterflies, birds and other insects and critters that will visit and live in our yard. We are so enjoying our new yard!"}])

(def faq
  [["What is a low maintenance landscape?"
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
 pesticides to the landscape."]
   ["What is the best time of year to plant?"
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
    "Site prep is the first step of the long term maintenance strategy. When you look at the bed you'd like to re-vamp, or the sodded yard you'd like to reduce, you are looking at your site. Preparation is the process of getting it ready for planting. A short sighted view is to slash down weeds or shrubs, till the ground, then plant. This will disturb the natural seed bank and encourage rapid germination of undesired weeds. Mulch or landscape fabrics won't be able to keep them all down. Good preparation is giving time for that seed bank to become exhausted. Knowing what will grow back after you plant is a big part of the maintenance strategy."]
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
      [:a {:href "samples/Monarch%20egg-0044%20resized.jpg" :target "_blank"} [:img.img-shadow {:alt "Monarch Larvae" :height "252" :src "samples/monarchegg.jpg" :width "373"}]])]
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
 small builds or paver-stone pathways."]])



(def steps
  ["Let’s walk your landscape! Schedule an appointment for a site analysis to look at what’s growing, identify existing problems and begin to dream up a new plan."
   "We meet up again to review a new concept for the property, look up each native plant and discuss longterm maintenance strategies."
   "Finished design in hand, we can assist in delivering, staging and planting native plants with any homeowner. We love the DIY type!"])

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
   "Landscape-Adrian-Backyard-0131.jpg"
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