(ns gs.pages.about
  (:require
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [gs.content :as c]
    [gs.components :as common]
    [gs.airtable :as at]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]))

(def divider
  (common/fancy-divider))

(def page-title
  [:h1.center "Meet Amanda Martin"])

(def intro
  [:p.dropcap "I love anything to do with being outside in this wonderful state. Growing up in Winter Park gave me the visual appreciation of the immense value that flora and fauna added to any landscape. Becoming aware of the impact it made on our natural resources, gave the curiosity and ecological consciousness to pursue a lifetime passion and career in the field of sustainable ecosytems. To explore how all living things affect one another, six years ago I became a certified Florida Beekeeper and now have four hives and produce small batch raw wildflower honey."])

(def about-me-article
  (list
    [:h2 "From the beginning"]
    [:br]
    [:img.img-left {:alt "About Amanda" :height "300" :src "images/samples/amanda2.jpg" :width "300"}]
    [:blockquote.rightside
     [:p [:strong "I would like to "]
      "continue creating pop-up plant shop opportunities to bring hard to find native plants and ecological education to neighborhoods around Orlando."]]
    "I began my adventure in horticulture by receiving a B.S. in Environmental Studies from the University of Florida, specializing in horticultural operations. Selecting my internship in Portland, Oregon, I took about two weeks to drive cross country, visiting national parks and experiencing the immense transition of the landscape from the Mississippi River to the Rocky Mountains. Reaching my destination at a large tree nursery, Mount Hood in front of me, I was greeted by a double rainbow, a sign that I was where I was supposed to be. (Photo below, left.)"
    [:br] [:br]
    "Grounded Solutions Inc. began its journey in December of 2013. My company was set up to be a wholesale organic fertilizer company and I parted ways with Evolving Landscapes. I rented a small warehouse space during 2014 to store both liquid and granular organic fertilizers. My first clients were a local hydroponics grower and two farm style grocery stores supplying fresh produce from nearby farms. I began to solicit entrepreneurial business advice from the UCF incubator and worked to grow my client list. Due to slow sales in 2014, I returned to agricultural research work. I was able to keep Grounded solutions open on a limited basis during this time and returned to working full time with Grounded solutions in 2015"
    [:br] [:br]
    [:img.img-left {:alt "Double Rainbow" :height "300" :src "images/samples/rainbowfla.jpg" :width "300"}]
    "As a volunteer with the Orange	county chapter of the Florida Native Plant Society, I began to shift my focus from permaculture, or fruiting plant landscapes, to native plant landscapes. Both demographics prefer organic fertilizers to synthetic fertilizers, but the native plant group often opted for no fertilizer at all. Florida native plants are naturally adapted to nutrient poor soils often requiring few inputs to grow a successful landscape."
    [:br] [:br]
    "My enthusiasm for native plants continued to grow. I was elected president of the Tarflower chapter (Orange county chapter) and requests for native plant landscape designs continued. I shifted my focus of consultation and designs toward ecological functioning landscapes and placed the mission of wholesale fertilizer suppler on hold."
    [:br] [:br]
    "Throughout 2015 and 2016 I was able to grow Grounded Solutions into a full service consultation, design and maintenance company. In 2017, Grounded Solutions opened it’s first pop-up retail plant shop. The pop-up plant shop was an retail opportunity in a small, 60’s styled strip mall in North Orlando. The shop was opened for two months and supplied local residents with access to sought after native plant material. The shop not only supplied hard to find plant material, but it created an educational space for new clients who wanted private consultations and design. Previous inventory of organic fertilizers were stocked and sold as retail products during this time. Sales were high and Grounded solutions was able to grow administratively."
    [:br] [:br]
    "In 2018, the goal is to continue to grow Grounded Solutions reach in the community. I would like to continue creating pop-up plant shop opportunities to bring hard to find native plants and ecological education to neighborhoods around Orlando. It is my goal to eventually have a brick and mortar, retail and educational classroom space where the general public can go to a trusted source for plants that can bring back pollinator health, water filtration services, migratory bird food, and a host of ways to make each landscape a little more sustainable for the future."))

(def other-blocks
  (list
    [:section.contentBox2a
     [:h3 "What Is A Horticulturist?"]
     [:div.heading-line]
     [:p
      [:a {:href "https://en.wikipedia.org/wiki/Horticulture" :target "_blank"} "Wikipedia definition"] ": A Horticulturist is someone who applies their knowledge, skills, and technologies used to grow intensively produced plants for human food and non-food uses and for personal or social needs. Their work involves plant propagation and cultivation with the aim of improving plant growth, yields, quality, nutritional value, and resistance to insects, diseases, and environmental stresses. They work as gardeners, growers, therapists, designers, and technical advisors in the food and non-food sectors of horticulture. Horticulture even refers to the growing of plants in a field or garden."]]
    [:section.contentBox2b
     [:h3 {:style {:color "white"}} "."]
     [:div.heading-line]
     [:p "Drought tolerant, low maintenance plants, offering food and habitat for many other species are the basis for Grounded Solutions landscape designs. Our goal is to bring a botanical garden-like atmosphere to the landscape while keeping valuable resource inputs to a minimum. I am excited to serve the greater Central Florida area in the Eco-Logical landscape design field and enjoy the diversity of clients interested in making this transition."]]))

(def resume
  (list
    [:h3 "Biologist:" [:br] "AJM Environmental Services"]
    [:div.group
     [:section.contentBox3a
      [:p [:img.img-left {:alt "About Amanda" :height "125" :src "images/samples/grass.jpg" :width "125"}] "My first company! I became a certified wetland delineator, able to identify hydric soils, wetland plant indicators and understand the laws affecting wetland boundary identification."]
      [:p]]
     [:section.contentBox3b
      [:p
       [:img.img-left {:alt "About Amanda" :height "125" :src "images/samples/grassbed.jpg" :width "125"}] "I used this knowledge to enhance residential lakefront properties, acquiring the necessary DEP permits for each site. Upland native plants were used in residential landscape design projects. I managed installation crews and designed management plans per site."]
      [:p]]
     [:section.contentBox3c
      [:p
       [:img.img-left {:alt "About Amanda" :height "125" :src "images/samples/flowerbed.jpg" :width "125"}] "My expertise in native plant material was sought out for consultation and maintenance for LEED certified green-roof systems. I was able to install selected material on three Central Florida roof-tops with great success."]
      [:br]]]
    [:h3 "Research Scientist:" [:br] "Eurofins"]
    [:div.group
     [:section.contentBox3a
      [:p [:img.img-left {:alt "About Amanda" :height "125" :src "images/samples/euro1.jpg" :width "125"}] "I worked as a principle investigator for field trials on agricultural crops, As a research scientist I performed applications of registered and experimental compounds on food crops, then collected time sensitive samples, environmental data, and crop assessment/evaluation figures."]
      [:p]]
     [:section.contentBox3b
      [:p
       [:img.img-left {:alt "About Amanda" :height "125" :src "images/samples/euro2.jpg" :width "125"}] "In many instances, a larger crew of people assemble to assist with trial activities. Most trials performed were RAC trials and Efficacy trials. Magnitude of Residue (RAC) trials were comprised of an application then sampling for analysis of residues. Efficacy trials tested the effectiveness of newly formulated compounds against pest and weed populations."]
      [:p]]
     [:section.contentBox3c
      [:p
       [:img.img-left {:alt "About Amanda" :height "125" :src "images/samples/euro3.jpg" :width "125"}] "Other trials I performed included environmental fate studies for dissipation in sandy soils, bee toxicity studies around citrus tree blossoms and best practices for hand pollination in corn. I became a Fl registered beekeeper and a commercial pesticide applicator during this time."]]]
    [:h3 "Horticulturist:" [:br] "Evolving Landscapes"]
    [:div.group
     [:section.contentBox3a
      [:p [:img.img-left {:alt "About Amanda" :height "125" :src "images/samples/evolve1.jpg" :width "125"}] "While serving as a horticulturist with Evolving Landscapes, I provided consulting for design and installation of plants, selected for the Florida Landscape. We specialized in ecosystem identification and assisting the property owner in re-developing for a native landscape."]
      [:p]]
     [:section.contentBox3b
      [:p
       [:img.img-left {:alt "About Amanda" :height "125" :src "images/samples/evolve2.jpg" :width "125"}] "Many of our clients were interested in attracting birds, butterflies and other pollinators while enjoying a low resource intensive landscape. Concept designs, plant selection, budget development, project timelines and client coordination were vital components of each job."]
      [:p]]
     [:section.contentBox3c
      [:p
       [:img.img-left {:alt "About Amanda" :height "125" :src "images/samples/evolve3.jpg" :width "125"}] "Presentations on the topics of Native landscapes and Permaculture principles were provided to garden societies for the promotion of environmentally sustainable landscapes."]
      [:br]]]
    [:h3 "Research Assistant:" [:br] "Florida Pesticide Research"]
    [:div.group
     [:section.contentBox3a
      [:p [:img.img-left {:alt "About Amanda" :height "125" :src "images/samples/fpr1.jpg" :width "125"}] "FPR is an agricultural research company, operating for almost 30 years out of Oviedo, FL. FPR evaluates the effectiveness of cultural practices, organic and synthetic compounds on various crops, diseases and pests."]
      [:p]]
     [:section.contentBox3b
      [:p
       [:img.img-left {:alt "About Amanda" :height "125" :src "images/samples/fpr2.jpg" :width "125"}] "Through the sampling of leaf tissues, root systems, whole fruit, soil and water they are able to provide analytic labs with the material they need for more accurate detection of residues. No trial is complete without detailed documentation."]
      [:p]]
     [:section.contentBox3c
      [:p
       [:img.img-left {:alt "About Amanda" :height "125" :src "images/samples/fpr3.jpg" :width "125"}] "Through paper records and evaluations, statistical analysis of numbers, and database input per trial. It takes time and energy to search and research, but I thoroughly enjoyed figuring these things out."]
      [:br]]]
    [:h3 "Horticulturist:" [:br] "Community Garden Organizer"]
    [:div.group
     [:section.contentBox3a
      [:p [:img.img-left {:alt "About Amanda" :height "125" :src "images/samples/cg1.jpg" :width "125"}] "I was given the opportunity to lead a community supported agriculture garden. The community garden had 12 paying members that gathered twice a week for two hours. On each Thursdays I would gather with the gardeners and collectively learn about and tend to the garden needs. Most gardens allow you to rent one plot for what you'd like to grow."]
      [:p]]
     [:section.contentBox3b
      [:p
       [:img.img-left {:alt "About Amanda" :height "125" :src "images/samples/cg2.jpg" :width "125"}] "This garden allowed everyone to work together to harvest from the entire garden. This helped ensure plenty of produce and greens were available for the taking each week. As each seasonal vegetable lived it's life, we would pull and replant the expired areas and trim back and harvest the producing areas."]
      [:p]]
     [:section.contentBox3c
      [:p
       [:img.img-left {:alt "About Amanda" :height "125" :src "images/samples/cg3.jpg" :width "125"}] "I had fun coordinating a raw chef to come in and share a few recipes strait from the garden to the plate in just a few hours. I was able to create a small cucumber trial organic fertilizer trial to see which blends worked best. A few of the gardeners enjoyed learning the process. Counting and weighing the cucumbers as they grew."]
      [:p]]]))

(def page-hiccup
  [:<>
   [:div.container
    [:div.inside
     page-title
     divider
     intro
     ;[:br] [:br]
     about-me-article
     [:br] [:br]
     other-blocks
     (common/fancy-divider)
     [:br]
     resume
     [:p [:br]]
     [:p]]]])
