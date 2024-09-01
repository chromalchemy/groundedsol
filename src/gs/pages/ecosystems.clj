(ns gs.pages.ecosystems
  (:require
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [gs.content :as c]
    [gs.components :as common]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]))


(def page-hiccup
  [common/container
   [:div.group
    [:section.contentBox3a
     [:h3 "FRAGILE FLORIDA"]
     [:div.heading-line]
     [:p.lead "Understanding an Ecosystem"]
     [:p "Each Ecosystem is a reflection of the past and an implication of the future. There are approximately 13 broad categories
			of Ecosystems present in Florida and as many as 69 uniquely distinguished communities, some of which are endangered."]
     [:p [:a.btn.btn-main {:href "about.html"} "Read More"]]]
    [:section.contentBox3b
     [:h3 "YOUR CONTRIBUTION"]
     [:div.heading-line]
     [:p.lead "Natural, organized and beautiful."]
     [:p "The goal of every GroundedSol landscape ultimately rewards our natural Florida.
			Every little bit helps. Striking changes in precipitation, saturation, soil nutrients,
			and the presence or absence of fire over short distances is what makes Florida unique.
			These subtle or contrasting features of the landscape bring an opportunity to try new texture patterns
			and sprinkle in seasonal color."]
     [:p [:a.btn.btn-main {:href "floridaplants.html"} "Read More"]]]
    [:section.contentBox3c
     [:h3 "THE FLORIDA WALTZ"]
     [:div.heading-line]
     [:p.lead "Florida, a fine balance of resources."]
     [:p "A little topography goes a long way towards influencing how things survive in Florida.
			From coast to coast, the highest point is only 345 feet (105 m).
			High ground supports scrub, high pine and sandhill species, and lower ground is littered with swamps,
			rivers and marshes. The dance between man and nature fires up when we showcase naturally occurring features
			in the landscape rather scraping away the uniqueness. Working with, instead of against nature, benefits everyone."]
     [:p [:a.btn.btn-main {:href "floridaplants.html"} "Read More"]]]
    [:div.clear]]])