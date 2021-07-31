(ns groundedsol.pages.index
  (:require
    ;[groundedsol.routes :as routes]
    ;[bidi.bidi :as b]
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [groundedsol.content :as c]
    [groundedsol.airtable :as at]
    [rum.core :as rum]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]))
    ;[goog.string :as gstring]))

;(defn html-entity [s]
;  (gstring/unescapeEntities s))

;(require 'goog.string)
;(s/transform [(s/walker string?)] goog.string.unescapeEntities my-hiccup-vector)

;markdown parse test with hickory + specter
(comment
  (def myvar
    (str (groundedsol.util/my-airtable-data))))

;(into [] (reverse [  "Hello World" :h1]))
      ;(setval [FIRST FIRST]
      ;  :h2
      ;  (map h/as-hiccup
      ;       (h/parse-fragment
      ;         (groundedsol.util/my-airtable-data)))))
;---------------------------
(comment
  [:div {:dangerouslySetInnerHTML {:__html myvar}}])

;markdown parse test with gonday
;(def md-hiccup
;  (cybermonday.core/parse-md
;    (:markdown-test c)))

(defn img-path [file]
  (str "images/" file))

(defn default-keymap [m]
  (zipmap
    (map symbol (keys m))
    (keys m)))

(default-keymap (c/intros :consult))

(def script-files
  (list
    ;[:script {:src "https://unpkg.com/htmx.org@1.4.1"}]
    [:script {:src "http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"}]
    [:script {:src "scripts/main.js" :type "text/javascript"}]
    [:script {:src "scripts/jquery.slimmenu.js" :type "text/javascript"}]
    [:script {:src "scripts/nivo-lightbox.js" :type "text/javascript"}]
    [:script {:src "scripts/accordionscript.js" :type "text/javascript"}]
    [:script {:src "scripts/wow.min.js"}]
    [:script "new WOW().init();"]
    [:script "$('ul.slimmenu').slimmenu(\n{\n    resizeWidth: '1024',\n    collapserTitle: 'Main Menu',\n    animSpeed: '300',\n    easingEffect: null,\n    indentChildren: true,\n    childrenIndenter: '&nbsp;&nbsp;'\n});\n"]))

(def page-description
  "We provide ecologically sound landscaping services focused on habitat enhancement using Florida native plant species")

(def css-files
  ["css/groundedsol.css"
   "css/animate.css"
   "css/default.css"
   "css/nivo-lightbox.css"
   "http://fonts.googleapis.com/css?family=Open+Sans|Poiret+One|Oswald:300"
   "https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"
   "images/lightbox/default.css"])
   ;"https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css"])

(def css-links
  (map
    (fn [css-url]
      [:link {:href css-url :rel "stylesheet" :type "text/css" :media "screen"}])
    css-files))

(def head
  [:head
   [:title "Grounded Solutions Landscape Consultation and Design Company"]
   [:meta {:charset "utf-8"}]
   [:meta {:content page-description :name "description"}]
   [:LINK {:REL "SHORTCUT ICON" :HREF "icongs.ico" :type "image/x-icon"}]
   [:meta {:content "width=device-width, initial-scale=1.0" :name "viewport"}]
   css-links])

(def mystuff
  ;(list)
  [:span
   {:style {:color "green"
            :font-size "2em"}}
   "hello hiccup man alive"])


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

(def intro-blocks
  [:div.row1
   [:div.container
    [:div.inside
     [:div.group
      (for [m (vals c/intros)]
        (intro-block m))
      [:div.clear]]]]])

(def nav
  (let [r c/routes]
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
  (let [m c/welcome]
    [:div.photoblock
     [:div.container
      [:div.inside
       [:div.photoblockInside
        [:h1.big (:title m)]
        [:p.lead (:body m)]
        [:p [:a.btn.btn-main {:href (:link m)} (:link-text m)]]]]]]))

(def hot-plants
  (list
    [:h3.alternate1 (c/hot-plant-gallery :title)]
    [:p.center
     (let [img-folder (:img-folder c/hot-plant-gallery)]
       (for [m (:images c/hot-plant-gallery)]
         [:a.lightbox.wow.fadeIn
          {:data-lightbox-gallery (:gallery-name c/hot-plant-gallery)
           :data-wow-delay (:delay m)
           :href (img-path (str "//" img-folder "//" (:img-file m)))
           :title (:title m)}
          [:img {:alt (m :title)
                 :src (img-path (str "//" img-folder "//" (:thumb-file m)))}]]))]))
(def hot-plant-gallery
  [:div.row2
   [:div.container
    [:div.inside
     hot-plants
     [:p [:a.btn.btn-color {:href "floridaplants.html"} "View More"]]]]])

(def masthead
  [:header.noborder
   [:div.container
    [:div.inside
     [:div.logo
      [:div.brand [:a {:href (c/routes :home)} [:img {:alt (c/images :logo/alt) :height "53" :src (img-path (c/images :logo/file)) :width "200"}]]]
      [:div.slogan c/slogan]]
     nav]
    [:hr.noshow]]])

(def scroll-to-top
  [:div.scroll-to-top [:a {:href "#"} [:i.fa.fa-angle-double-up.fa-2x]]])

(def about-us
  [:section.contentBox3a.wow.zoomIn {:data-wow-delay ".2s"}
   [:h4.alternate2 "About Us"]
   [:p [:img.img-left.img-round.img-small {:alt "Amanda" :src "images/samples/mandy.jpg"}] "A long time Winter Park resident, Amanda Martin is no stranger to the beauty that a
            planned landscape brings to our Florida neighborhoods. With expansive knowledge in horticulture,
            agricultural research, and landscape design, Amanda’s passion is
            bringing out the best in our sustainable landscapes."]
   [:p]])

(def discover
  [:section.contentBox3b.wow.zoomIn {:data-wow-delay ".6s"}
   [:h4.alternate2 "Discover"]
   [:div.callbox
    [:h6 "Why Buy Native"]
    [:img {:alt "" :height "111" :src "images/samples/GS%20Icon.png" :width "114"}]
    [:p "Residential landscapes are often filled with plants that grow well in our climate
                but offer little else to the surrounding environment. My goal is to bring a botanical
                garden-like atmosphere to the landscape while keeping valuable resource
                uses, like water, to a minimum."]
    [:p]]])

(def get-started
  [:section.contentBox3c.wow.zoomIn {:data-wow-delay "1.0s"}
   [:h4.alternate2 "Get Started"]
   [:p [:span.bignumber "1"] "Let’s walk your grounds together.
			Consultations take about an hour and involve identifying existing problems, future dreams or a whole new plan."]
   [:p [:span.bignumber "2"] "Next, we work together to review plants, producing a design or viable plan for the
            best solutions."]
   [:p [:span.bignumber "3"] "Design in hand, you are connected
to established installers or plant material is delivered for the DIY
crowd that wants to get their hands dirty."]])

(def reviews
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
         [:hr.noshow]]]]]]]])

(def social-media-links
  [:p.socialmedia
   (list
     [:a {:href "https://www.facebook.com/groundedsol/" :target "_blank"}
      [:img {:alt "Facebook" :height "32" :src "images/icons/fb.png" :title "Facebook" :width "32"}]])])

(def menu-links
  [:ul
   [:li [:a {:href "index.html"} "Home"]]
   [:li [:a {:href "consultationanddesign.html"} "Design Consultation"]]
   [:li [:a {:href "consultationanddesign.html"} "Services"]]
   [:li [:a {:href "floridaplants.html"} "Plants"]]
   [:li [:a {:href "about.html"} "About"]]
   [:li [:a {:href "contact.html"} "Contact"]]])

(def contact
  [:section.contentBox4b
   [:h5 "Contact:"]
   social-media-links
   [:p.home "1821 Amherst Ave." [:br] "Orlando, FL 32804"]
   [:p.email [:a {:href "mailto:groundedsolution@gmail.com"} "groundedsolution@gmail.com"] [:br]]
   [:p.phone "352-219-5381"]
   [:p.hours "By appointment only" [:br]]])

(def certifications
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
   [:br] [:br]])

(def news
  [:section.contentBox4d
   [:h5 "News & Events:"]
   [:p.center
    [:script {:src "javascripts/calendar02.js" :type "text/javascript"}]]
   [:p.center [:a.btn.btn-main {:href "consultationanddesign.html"} "More Info ≫"]]])
;&raquo;

(def footer
  [:footer
   [:div.container
    [:div.inside
     [:section.contentBox4a
      [:h5 "Menu:"]
      menu-links]
     contact
     certifications
     news
     [:hr.noshow]
     [:div.footerbottom
      [:hr.fancy]
      [:h1 "Grounded Solutions, Inc"]
      [:p.copyright
       "© " ;"&copy;"
       [:script {:type "text/javascript"} "document.write(new Date().getFullYear());"] "All Rights Reserved"]
      [:p]]]]])

(def body
  (list
    ;[:h1 md-hiccup]
    scroll-to-top
    masthead
    welcome
    intro-blocks
    hot-plant-gallery
    [:div.row1
     [:div.container
      [:div.inside
       about-us
       discover
       get-started
       [:div.clear]]]]
    reviews
    footer))

(def page
  [:html
   head
   [:body body]])

(def html-string
  (rum/render-static-markup page))

;(println html-string)

(spit "build/index.html" html-string)



