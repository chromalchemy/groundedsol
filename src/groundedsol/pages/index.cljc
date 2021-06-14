(ns groundedsol.pages.index
  (:require
    [groundedsol.routes :as routes]
    [bidi.bidi :as b]
    ;[hickory.core :as h]
    ;[cybermonday.core :as cm]
    #?(:clj [groundedsol.util]
       :cljs [groundedsol.util]
       :include-macros true)))
    ;[com.rpl.specter :refer [select ALL FIRST setval transform NONE]]))




(comment
  ;markdown parse test with hickory + specter
  (def myvar
    ;(into [] (reverse [  "Hello World" :h1]))
    (setval [FIRST FIRST]
      :h2
      (map h/as-hiccup
           (h/parse-fragment
             (groundedsol.util/my-airtable-data))))))
;---------------------------
(comment
  [:div {:dangerouslySetInnerHTML {:__html myvar}}])

(def c (groundedsol.util/content))

;markdown parse test with cybermonday
;(def md-hiccup
;  (cybermonday.core/parse-md
;    (:markdown-test c)))

(comment
 (defn img-path [file]
   (str "images/" file))

 (def routes (:routes c))
 (def images (:images c))

 (defn default-keymap [m]
   (zipmap
     (map symbol (keys m))
     (keys m)))

 (default-keymap (:consult (:intros c)))

 (defn intro-block [m]
   (let [{title :title,
          subtitle :subtitle,
          body :body,
          link-text :link-text,
          link :link} m]
     [:section.contentBox3a
      [:h3 title]
      [:div.heading-line]
      [:p.lead subtitle]
      [:p body]
      [:p [:a.btn.btn-main {:href link} link-text]]]))

 (def nav
   (let [r routes]
     [:nav
      [:ul.slimmenu
       [:li [:a {:href (r :home/file)} (r :home/name)]]
       [:li [:a {:href (r :consultation/file)} (r :services/name)]
        [:ul
         [:li [:a {:href (r :consultation/file)} (r :consultation/name)]]
         [:li [:a {:href (r :consultation/file)} (r :design/name)]]
         [:li [:a {:href (r :florida-plants/file)} (r :pop-up-shop/name)]]]]
       [:li [:a {:href (r :florida-plants/file)} (r :florida-plants/name)]
        [:ul
         [:li [:a {:href (r :florida-plants/file)} (r :florida-plants-411/name)]] [:li [:a {:href (r :ecosystems/file)} (r :ecosystems/name)]]]]
       [:li [:a {:href (r :about/file) } (r :about/name)]]
       [:li [:a {:href (r :contact/file)} (r :contact/name)]]]]))

 (def welcome
   (let [m (:welcome c)]
     [:div.photoblock
      [:div.container
       [:div.inside
        [:div.photoblockInside
         [:h1.big (:title m)]
         [:p.lead (:body m)]
         [:p [:a.btn.btn-main {:href (:link m)} (:link-text m)]]]]]]))

 (def hot-plant-gallery
   (let [gallery (c :hot-plant-gallery)]
     (list
       [:h3.alternate1 (gallery :title)]
       [:p.center
        (for [m (:images gallery)]
          [:a.lightbox.wow.fadeIn
           {:data-lightbox-gallery (gallery :gallery-name)
            :data-wow-delay (m :delay)
            :href (img-path (str "//" (gallery :img-folder) "//" (m :img-file)))
            :title (m :title)}
           [:img {:alt (m :title)
                  :src (img-path (str "//" (gallery :img-folder) "//" (m :thumb-file)))}]])]))))

(defn hiccup []
  (list
    [:h1 {:style {:color "green"}}
     (str
       (:markdown-test c))]))


(comment
  (list
    ;[:h1 md-hiccup]
    [:div.scroll-to-top [:a {:href "#"} [:i.fa.fa-angle-double-up.fa-2x]]]
    [:header.noborder
     [:div.container
      [:div.inside
       [:div.logo
        [:div.brand [:a {:href (routes :home)} [:img {:alt (images :logo/alt) :height "53" :src (img-path (images :logo/file)) :width "200"}]]]
        [:div.slogan (c :slogan)]]
       nav]
      [:hr.noshow]]]
    welcome
    [:div.row1
     [:div.container
      [:div.inside
       [:div.group
        (for [m (vals (c :intros))]
          (intro-block m))
        [:div.clear]]]]]
    [:div.row2
     [:div.container
      [:div.inside
       hot-plant-gallery
       [:p [:a.btn.btn-color {:href "floridaplants.html"} "View More"]]]]]
    [:div.row1
     [:div.container
      [:div.inside
       [:section.contentBox3a.wow.zoomIn {:data-wow-delay ".2s"}
        [:h4.alternate2 "About Us"]
        [:p [:img.img-left.img-round.img-small {:alt "Amanda" :src "images/samples/mandy.jpg"}] "A long time Winter Park resident, Amanda Martin is no stranger to the beauty that a
            planned landscape brings to our Florida neighborhoods. With expansive knowledge in horticulture,
            agricultural research, and landscape design, Amanda’s passion is
            bringing out the best in our sustainable landscapes."]
        [:p]]
       [:section.contentBox3b.wow.zoomIn {:data-wow-delay ".6s"}
        [:h4.alternate2 "Discover"]
        [:div.callbox
         [:h6 "Why Buy Native"]
         [:img {:alt "" :height "111" :src "images/samples/GS%20Icon.png" :width "114"}]
         [:p "Residential landscapes are often filled with plants that grow well in our climate
                but offer little else to the surrounding environment. My goal is to bring a botanical
                garden-like atmosphere to the landscape while keeping valuable resource
                uses, like water, to a minimum."]
         [:p]]]
       [:section.contentBox3c.wow.zoomIn {:data-wow-delay "1.0s"}
        [:h4.alternate2 "Get Started"]
        [:p [:span.bignumber "1"] "Let’s walk your grounds together.
			Consultations take about an hour and involve identifying existing problems, future dreams or a whole new plan."]
        [:p [:span.bignumber "2"] "Next, we work together to review plants, producing a design or viable plan for the
            best solutions."]
        [:p [:span.bignumber "3"] "Design in hand, you are connected
to established installers or plant material is delivered for the DIY
crowd that wants to get their hands dirty."]]
       [:div.clear]]]]
    [:div.row3
     [:div.container
      [:div.inside
       [:h1.alternate1 "Reviews"]
       [:div.reviews
        [:ul#ticker
         [:li
          [:section.content
           [:p [:img.img-round-border {:alt "Patty R., March 2, 2018" :src "images/samples/reviews3.jpg"}]]
           [:p "My husband and I had been interested in a landscape of Florida native plants since the house was purchased in July 2013, but we didn’t have
a clue how to do start or which plants to incorporate. Fortunately we were lead to Amanda Martin. This young woman has a plethora of
knowledge involving not only Florida native plants, but landscaping with them, and also bee keeping. We are so grateful for her expertise and
continued dedication to the yard she created. Thanks, Amanda!"]
           [:p.small "~ Patty R., March 2, 2018"]
           [:hr.noshow]]]
         [:li
          [:section.content
           [:p [:img.img-round-border {:alt "Gina S., June 20, 2017" :src "images/samples/reviews2.jpg"}]]
           [:p "Amanda created a beautiful native landscape plan for our new home.
				        She knew what grew here before the orange groves and subdivisions and included the natives that will easily
				        thrive and support the butterflies, birds and other insects and critters that will visit and live in our yard.
				        We are so enjoying our new yard!"]
           [:p]
           [:p.small "~ Gina S.,  June 20, 2017"]
           [:hr.noshow]]]
         [:li
          [:section.content
           [:p [:img.img-round-border {:alt "Carla N., May 13, 2017" :src "images/samples/reviews1.jpg"}]]
           [:p "Just stopped into a local nursery, met Amanda, bought some great plants, and supported local business. You should do the same. Support this local lady making it happen."]
           [:p]
           [:p.small "~ Carla N., May 13, 2017"]
           [:hr.noshow]]]]]]]]
    [:footer
     [:div.container
      [:div.inside
       [:section.contentBox4a
        [:h5 "Menu:"]
        [:ul
         [:li [:a {:href "index.html"} "Home"]]
         [:li [:a {:href "consultationanddesign.html"} "Design Consultation"]]
         [:li [:a {:href "consultationanddesign.html"} "Services"]]
         [:li [:a {:href "floridaplants.html"} "Plants"]]
         [:li [:a {:href "about.html"} "About"]]
         [:li [:a {:href "contact.html"} "Contact"]]]]
       [:section.contentBox4b
        [:h5 "Contact:"]
        [:p.socialmedia "&nbsp;" [:a {:href "https://www.facebook.com/groundedsol/" :target "_blank"} [:img {:alt "Facebook" :height "32" :src "images/icons/fb.png" :title "Facebook" :width "32"}]]]
        [:p.home "1821 Amherst Ave." [:br] "Orlando, FL 32804"]
        [:p.email [:a {:href "mailto:groundedsolution@gmail.com"} "groundedsolution@gmail.com"] [:br]]
        [:p.phone "352-219-5381"]
        [:p.hours "By appointment only" [:br]]]
       [:section.contentBox4c
        [:h5 "Certifications / Affiliations"]
        [:i.fa.fa-trophy.fa-3x.img-left.color3.icon-shadow] "B.S. Environmental Science"
        [:br] "Certified Wetland Delineator"
        [:br] "Commercial Applicator License"
        [:br] [:br] "Member of Sierra Club"
        [:i.fa.fa-leaf.fa-3x.img-left.color3.icon-shadow]
        [:br] "Florida Native Plant Society"
        [:br] "Orange Audubon Society"
        [:br] [:br]
        [:i.fa.fa-camera.fa-3x.img-left.color3.icon-shadow] "Photography by Amanda Martin. All rights reserved"
        [:br] [:br]]
       [:section.contentBox4d
        [:h5 "News &amp; Events:"]
        [:p.center
         [:script {:src "javascripts/calendar02.js" :type "text/javascript"}]]
        [:p.center [:a.btn.btn-main {:href "consultationanddesign.html"} "More Info &raquo;"]]]
       [:hr.noshow]
       [:div.footerbottom
        [:hr.fancy]
        [:h1 "Grounded Solutions, Inc"]
        [:p.copyright "&copy;"
         [:script {:type "text/javascript"} "document.write(new Date().getFullYear());"] "All Rights Reserved"]
        [:p]]]]]))


(comment
  (list
    ;[:h1.myclass myvar]
    [:p "This really is an index page and its content is up to you! Also visit "
     [:a {:href (b/path-for routes/routes :page/contact)} "contact page"] "."]
    [:p {:style {:color            "navy"
                 :background-color "lightblue"
                 :padding          5}}
     "You can use any hiccup you want:"]
    [:ul (for [index (range 10)]
           [:li "The element " (inc index)])]))






