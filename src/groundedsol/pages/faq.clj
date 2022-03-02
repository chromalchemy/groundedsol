(ns groundedsol.pages.faq
  (:require
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [groundedsol.content :as c]
    [groundedsol.common :as common]
    [groundedsol.airtable :as at]
    [groundedsol.util :as u]
    [rum.core :as rum]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]))


(def process
  [:div.contentLeft
   [:h2 "The Process"]
   [:h6 "Making the most of your consultation: Step One"]
   [:p.newsDate]
   [:p [:img.img-left-shadow.img-rotate-left {:alt "Florida Front Yard with Grass" :src "images/samples/yard.jpg"}] "Let’s walk your grounds together. Your first consultation provides a roadmap from a professional’s point of view.
				The first hour is about being able to see the property, understand the scope of the project, and decide what is possible, clarifying ideas as we go.
				We spend time identifying existing problems, future solutions, labor benefits and time drawbacks of each idea or dream.
				Are you looking to create a bird and butterfly foraging ground? Do you want to reduce the monthly or weekly mowing required?
				Is water conservation a concern for you? Looking to add some hardscape? Sometimes the best way to move forward is to cut things back.
				Remove fast growing shrubs and prepare the ground for new plantings.
				You may want to keep some of your existing plants.
				It’s all a discussion with suggestions and ideas at this stage.
				We offer on-site small impact designs, so you can incorporate native plants and build your landscape DIY style after the first meeting."]
   [:p]
   [:hr.fancy]
   [:hr.noshow]
   [:h6 "Making the most of your consultation: Step 2 Design"]
   [:p.newsDate]
   [:p [:img.img-right-shadow.img-rotate-right {:alt "Digital Layout Design for Yard" :src "images/samples/yard2.jpg"}] "Next, we work together to produce a design or viable plan to move forward.
			There are two types of design, a small impact design and a digital design.
			A small impact design is created on-site. An on-site design brings to paper the ideas suggested during the consult with color and detail.
			During the drawing I have an opportunity to look up individual plants with you, share which plants will work for each spot, and why they were selected for your landscape.
			 I leave you with a small design, labeled with botanical and common names so you can move forward at your own pace. The second is a digital design that showcases the full property.
			 A digital design allows me to take the property survey and create a drawing that is true to scale. Over the course of a few weeks I create a concept plan complete with outlines and ideas.
			 We meet again to review the concept, walk through the plant suggestions, discuss longer term maintenance requirements and seasonal flow of the landscape. We take notes, incorporate changes, and complete the planting details for each listed species for the final design.
			 If all you need is a direction, we can cover that with the initial consult and an on-site design. Looking for a long term plan? A digital design is the way to go. Wanting someone else to plant the design? We can help coordinate that for you.
			 Wanting a smaller landscape bed or just a re-fresh of color for spring or fall? We may be able to take care of that quickly by delivering plants, planting and mulching in a day. Getting a design is just a step towards the sustainable Florida landscape you want."]
   [:p]
   [:hr.fancy]
   [:hr.noshow]
   [:h6 "Implementing your design and installation"]
   [:p.newsDate]
   [:p [:img.img-left-shadow.img-rotate-left {:alt "Florida's sandy dry soils" :src "images/samples/yard3.jpg"}] "Design in hand, you are connected to established installation and irrigation contractors.
			 If you choose to have a professional do the digging, that is.
			 Small orders of plant material can be delivered and staged for the DIY crowd wanting to get their hands dirty.
			 A Pop-Up plant shop is in the works to bring a regular supply of native plants to Orlando and surrounding neighborhoods.
			 We recommend nurseries or garden centers stocking true Florida natives to shop at.
			 Keeping to the mission of restoring ecological diversity natural to this area we recommend species with high nectar content and quality pollen content that is nourishing to our pollinators.
			 Cultivated varieties and exotic species don't always provide the best nutrients, if any at all.
			 We enjoy working closely with Green Isle Gardens in Groveland for our plant material.
			 Green Isle Gardens consistently produces top quality plants that thrive in Florida's sandy dry soils better than exotic box store plants.
			 When selecting native plants to fill out your flower beds, you'll notice fast growth and bright blooms with our summertime rains, and without unnecessary fertilizer or pesticide treatments.
			 In-fact, a pesticide free lawn can help bring beneficial insects into the area, diversify the types of butterflies you see, increase dragonfly and damselfly populations, and creating a more interesting landscape for you and any little ones to watch."]
   [:p]
   [:hr.fancy]
   [:hr.noshow]
   [:h6 "the growing stage and plant material management"]
   [:p.newsDate " "]
   [:p [:img.img-right-shadow.img-rotate-right {:alt "Florida Native Plants" :src "images/samples/yard4.jpg"}] "Just like a new haircut, this stage is the one to get used to.
			Native plants thrive in our sunny, sandy landscapes.
			There are also a lot of species that thrive in shady, wet areas. And many plants are adapted for the areas in-between.
			Selecting the right plant for the right place will allow for each plant to grow to maturity with less attention.
			But they will need to be monitored, watered frequently and kept mulched in the first few months.
			After the plant has established it’s roots in the area, watering can drift towards seasonal rain supply and pruning
			can be addressed on a quarterly basis. It’s always a good idea to keep a 3 inch mulch barrier down to keep drifting
			weed seeds from taking root. We prefer to use pine needles and fine pine mulch over cypress mulch, so as not to add
			to the de-forestation of our cypress trees around sensitive wetland areas. It takes some time to get to know the
			growth habits of these native plant species, and we won’t leave you hanging, just call for a maintenance consult.
			Grounded Solutions wants your landscape to be successful. So, if for any reason you have questions or concerns,
			please email us. It will always be our goal to bring sustainable and beautiful landscapes to Florida, and
			have your property give you a sense of pride in giving back."]
   [:p]
   [:hr.fancy]])

(def events
  (list
    [:h2 "Events"]
    [:p.center
     [:script {:src "scripts/calendar02.js" :type "text/javascript"}]]))

(def faq
  [:dl#acc
   (list
     (for [f c/faq]
       (let [question (first f)
             answer (last f)]
         (list
           [:dt question]
           [:dd
            [:p.dropcap answer]
            [:p]])))
     [:hr.noshow])])

(def faq-block
  (list
    ;[:h4 "FREQUENTLY ASKED QUESTIONS"]
    [:ul.list2
     [:li "Below are a few frequently asked questions that
  typically come up during the consultation. Since the
  consult is based on an hourly fee, I thought I would
  address a few of these now so that your time is best
  spent on the consultation."]]
    faq))

(def faq
  [:aside.sidebarRight
   events
   [:br]
   faq-block])

(def divider
  [:hr.fancy])

(def page-title
  [:h1.center "Consultation & Design"])

(def divider
  [:hr.fancy])

(def content-blocks
  [
   [:div.container
    [:div.inside
     [:h1.center "Frequently Asked Questions"]
     divider
     faq-block]]])


(defn write-page []
  (common/write-page
    :faq
    content-blocks)
  (println "write faq page"))