(ns gs.pages.fl-plants
  (:require
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [gs.content :as c]
    [gs.components :as common]
    [lambdaisland.ornament :refer [defstyled]]
    [gs.airtable :as at]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]
    [clojure.string :as string]
    [gs.meta])
  (:use [gs.util]))

;images/samples/1b.jpg

(def old-image-names
  (let [removed 3
        numbers
          (-> (range 1 15)
              set
              (disj removed)
              sort)]
    (map #(str "old/" % "b.jpg")
      numbers)))

(defn gallery-image-path [s]
  (str "images/gallery/" s))

(def old-image-local-paths
  (conj
    (vec old-image-names)
    (str "old/" (jpeg "milkweed2b"))))

(def image-local-paths
  (concat old-image-local-paths c/new-images-filenames))

(defn image-file-name->short-desc [filename]
  (-> filename
      (string/split #"-")
      drop-last
      (->> (interpose " ")
           (apply str))))

(def new-gallery-entries
  (->> c/new-images-filenames
    (map
      (fn [filename]
        [filename (image-file-name->short-desc filename)]))
    (into {})))

(def old-gallery-entries
  {"old/1b.jpg" "Deerberry, Vaccinium stamineum"
   "old/2b.jpg" "Firebush, Hamelia patens"
   "old/groundorchidsb.jpg" "Native Ground orchids! Also called terrestrial orchids. Scarlet Ladies’ Tresses, Sacoila lanceolata  #9412_Go"
   "old/4b.jpg" "A fresh cut flower arrangement"
   "old/5b.jpg" "Beach verbena, Glandularia maritima"
   "old/6bb.jpg" "Florida Paintbrush prior to setting bud, Carphephorus"
   "old/7b.jpg" "Indian-plantain with a beneficial insect getting nectar and pollen, Arnoglossum ovatum"
   "old/8b.jpg" "False pennyroyal, Piloblephus rigida"
   "old/9b.jpg" "Wild poinsettia, Poinsettia cyathophora"
   "old/10b.jpg" "Winter blooming vine Carolina jessamine, Gelsemium sempervirens"
   "old/11b.jpg" "Spring blooming plum tree, Prunus umbellata"
   "old/12b.jpg" "Gopher apple as a ground cover, Licania michauxii"
   "old/13b.jpg" "Pollinator attractor Spiderwort in purple, Tradescantia ohiensis"
   "old/14b.jpg" "Fruit producing Bigflower pawpaw, Asimina obovata"
   "old/milkweed2b.jpg" "Aquatic milkweed, also shown in orange. Asclepias perennis"})


(def gallery-entries
  (merge old-gallery-entries new-gallery-entries))

(defn image-tag [local-path description]
  [:a.lightbox
   {
    ;:class ["w-1/3"]
    :style                 {}
            ;:height "160px"
            :display "block"
            ;:margin "auto"}
    :data-lightbox-gallery "catalog1"
    :href                  (gallery-image-path local-path)
    :title                 description}
   [:img.img-portfolio
    {:src   (gallery-image-path (str "thumb/" local-path))
     :style {:height "160px"}
     :alt   description}]])

(def image-tags
  (map
    (fn [gallery-path]
      (let [description (gallery-entries gallery-path)]
        (image-tag gallery-path description)))
    image-local-paths))

;(def images)

(def gallery
  [:fieldset
   [:legend "Florida Faves"]
   [:p.center
    {:style {:display "flex"
             :flex-wrap "wrap"
             :justify-content "space-evenly"}}
    image-tags]
   [:p.center [:strong "Click on an image to see full view."]]])


(def gallery-intro
  [:div.group
   [:section.contentBox3a
    [:p
     [:img.img-left.img-curved
      {:alt "" :src "images/samples/green125.jpg"}]
     "Our native Florida plants are as versatile as they are colorful. Grounded Solutions specializes in ecosystem identification and assisting the property owner in re-developing for a native landscape. Many of our clients are interested in attracting birds, butterflies and other pollinators. "]
    [:p "With Grounded Solutions, you get the whole story. The proper Florida native name, its origin and how to best take care of your new plants in the landscape."]]

   [:section.contentBox3b
    [:p "Many homeowners are shifting towards a low water, no fertilizer, low maintenance landscape. Upland native plants can fit that bill. Methods of maintenance for these plants can vary from the subtropical, fast growing plants we commonly use in residential landscapes today."]
    [:p "Below are macro flower photos and some wide angle photos to show the beauty of individual flowers, pollinator interactions, and how the plant persists in the landscapes we find them in. Please enjoy the photos as we add to and edit this gallery over time. Email with any requests to use these photos."]]

   [:section.contentBox3c
    [:p "There are plenty of native plants to discover and we hope to see more planted. This gallery showcases some of our favorites that may be right for you too. Soon we will be open for business in our pop-up plant shop. We want to help spread the seeds of diversity in the landscape."]
    [:p "Please follow Grounded Solutions Inc on Facebook, Instagram and Youtube for individual plant stories, growing organically each season."]
    (common/social-icons "images/social-icons/")]])

(defstyled mytitle common/middle-of-line-title
  :text-red-500)

(def page-hiccup
  [common/container
   [common/page-title "What’s Hot in Florida Plants"]
   [common/fancy-divider]
   [mytitle "Flora & Fauna"]
   gallery-intro
   gallery])

    ;(comment)

    ; [:p "Shown above are a few examples of versatility you can find when choosing native plants.
    ;Grounded Solutions strives to over you the best quality plant material we possibly can.
    ;Pairing plant selection with the area you’d like to plant in is the first step towards habitat reconstruction.
    ; Want the wow factor? We can do that too."]]]])
     ;[:div.pagination
     ; [:span.disabled_pagination "Prev"]
     ; [:span.active_link "1"]
     ; [:a {:href "#0"} "Next"]]
     ;[:p.dropcap]]]])
