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
  [:<>
    [:br]
    [:img.img-left {:alt "About Amanda" :height "300" :src "images/samples/amanda2.jpg" :width "300"}]
    [:blockquote.rightside
     [:p [:strong "In the works"]
      " is a pop-up plant shop on wheels! I hope to bring retail native plants, delivery and suggested placement options to homeowners and their neighborhood at large. "]]
    "I began my adventure in horticulture by getting a B.S. in Environmental Horticulture from the University of Florida. I enjoyed learning all about plant physiology, growth condition requirements, identification markers, along with disease, insect and nutrient strategies. Prior to my degree I always loved flowers and took a job taking care of interior plants. Interior plant-scaping brought me to more philosphical conversations about plants and plant care. Bringing plants out of nature and into offices, both high-rises and small offices brought various constraints to what kind of plant material can be used, how it needed to be grown in low light conditions, and most importantly, how to keep plants alive for those who tend to kill plants in their personal experiences. I was driven to learn what plants like and how to accomodate for their needs. See below for both a definition of what a horticulturist does and some of my work/project history."
    [:br] [:br]
    "In Decemebr of 2013, I began Grounded Solutions Inc. Having found the genera of Native plants and their importance in our landscapes I was able to focus on this wide range of plant material and begin understanding the relationships they have with our soils, our rainfall, our seasonal temperature and our precious pollinators. Taking this education and awareness to my clients, I help educate others about individual plants and how they attract beneficial wildlife through an aesthetically pleasing design. My goal is to increase biodiversity in the landscape and help my clients notice and appreciate the diversity they are witnessing. Maintenance habits can be built in to weekly walks through the landscape and with observation it becomes easier to know when and how to prune these plants. Grounded Solutions Inc has provided me with endless opportunities to see native plants growing in all kinds of conditions and I grow more aware of how to make a sod-free landscape look manicured and functional for the homeowner."
    [:br] [:br]
    "As climate change continues to accelerate, native lanscapes are becoming an obvious way to reduce fertilizer and water use. Noise pollution and CO2 pollution from lawn mowers, edgers and trimmers are greatly reduced. And with food sources for our pollinators and birds, you will see a dramatic change in the natural activity of your front and/or back yard."])

(def other-blocks
  (list
    [:section
     [:h3 "What Is A Horticulturist?"]
     [:div.heading-line]
     [:p
      [:a {:href "https://en.wikipedia.org/wiki/Horticulture" :target "_blank"} "Wikipedia definition"] ": A Horticulturist is someone who applies their knowledge, skills, and technologies used to grow intensively produced plants for human food and non-food uses and for personal or social needs. Their work involves plant propagation and cultivation with the aim of improving plant growth, yields, quality, nutritional value, and resistance to insects, diseases, and environmental stresses. They work as gardeners, growers, therapists, designers, and technical advisors in the food and non-food sectors of horticulture. Horticulture even refers to the growing of plants in a field or garden."]]))


(defn resume-item [item items]
  (let [[img-path text] item
        class
        (str "contentBox3"
          (case (inc (.indexOf items item))
            1 "a" 2 "b" 3 "c"))]
    [:section {:class class}
     [:p [:img.img-left {:alt "About Amanda" :height "125" :src (str "images/" img-path) :width "125"}] text]
     [:p]]))

(def ajm-resume-items
  [["samples/grass.jpg"
    "My first company! I became a certified wetland delineator, able to identify hydric soils, wetland plant indicators and understand the laws affecting wetland boundary identification."]
   ["samples/grassbed.jpg"
    "I used this knowledge to enhance residential lakefront properties, acquiring the necessary DEP permits for each site. Upland native plants were used in residential landscape design projects. I managed installation crews and designed management plans per site."]
   ["samples/flowerbed.jpg"
    "My expertise in native plant material was sought out for consultation and maintenance for LEED certified green-roof systems. I was able to install selected material on three Central Florida roof-tops with great success."]])

(def resume
  (list
    [:h3 "Biologist:" [:br] "AJM Environmental Services"]
    [:div.group
     (map #(resume-item % ajm-resume-items) ajm-resume-items)]
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
      [:p]]]
    [:h3 "President:" [:br] "Tarflower Native Plant Society"]
    [:div.group
     [:section.contentBox3a
      [:p [:img.img-left {:alt "About Amanda" :height "125" :src "images/resume/tarflower-board-sq.jpg" :width "125"}] "Helped host meetings and arrange for speakers to present to our local chapter"]
      [:p]]
     [:section.contentBox3c
      [:p
       [:img.img-left {:alt "About Amanda" :height "125" :src "images/resume/tarflower-talk-sq.jpg" :width "125"}] "Facilitated meetings for the general public and coordinated speakers."]
      [:p]]
     [:section.contentBox3b
      [:p
       [:img.img-left {:alt "About Amanda" :height "125" :src "images/resume/tarflower-sale-sq.jpg" :width "125"}] "Organized, arranged, and sold plants as a chapter fundraiser for Native plant community projects and educational grants."]
      [:p]]]))

(def page-hiccup
  [:<>
   [:div.container
    [:div.inside
     page-title
     divider
     intro
     ;[:br] [:br]
     [:h2 "Getting Growing"]
     [:div.heading-line]
     about-me-article
     [:br] [:br]
     other-blocks
     (common/fancy-divider)
     [:br]
     resume
     [:p [:br]]
     [:p]]]])
