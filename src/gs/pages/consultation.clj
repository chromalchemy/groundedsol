(ns gs.pages.consultation
  (:require
    [gs.color :as color]
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [gs.content :as c]
    [gs.components :as common]
    [lambdaisland.ornament :refer [defstyled]]
    [gs.airtable :as at]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]])
  (:use
    [gs.util]))



(defstyled process-title :h2)


(def calendar-link
  [:a {:href "contact.html"}
   [:p.newsDate]])

(defstyled step-title :h6
  ;:border-none
  :text-center
  :mx-auto
  :self-center
  ;:w-75%

  ;:inline-block
  :text-2xl
  :mb-8
  :pb-0
  :border-none
  [:span
   :inline-block]
   ;{:border-bottom-style :solid
   ; :border-color "#bdaf64"
   ; :border-bottom-width (px 1)}]
  ([t]
   [:<> [:span t]]))

(defstyled step :div
  :mb-12
  [:h6 {:color color/gold-yellow}]
  ([{:keys [title alt src text image-side] :as m}]
   [:div
    ;{:style {:text-align "center"}}
    [step-title title]
    ;[calendar-link]
    ;calendar-link
    (case image-side
      :left
      [:img.img-left-shadow.img-rotate-left
       {:alt alt :src src}]
      :right
      [:img.img-right-shadow.img-rotate-right
       {:alt alt :src src}])

    text
    (common/fancy-divider)]))


(comment
  {:title
   :image-side :left
   :alt
   :src
   :text})


(def steps
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

(def process
  [:div
   (process-title "The Process")
   (map step steps)])

(def divider
  (common/fancy-divider))

(def page-title
  [:h1.center "Consultation & Design"])

(def page-hiccup
  [:div.container
   [:div.inside
    page-title
    divider
    process]])
