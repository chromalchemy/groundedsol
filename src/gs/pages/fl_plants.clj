(ns gs.pages.fl-plants
  (:require
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [gs.content :as c]
    [gs.common :as common]
    [gs.airtable :as at]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]
    [clojure.string :as string]))

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

(defn image-path [s]
  (str "images/gallery/" s))

(defn jpeg [s]
  (str s ".jpg"))

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
    :href                  (image-path local-path)
    :title                 description}
   [:img.img-portfolio
    {:src   (image-path (str "thumb/" local-path))
     :style {:height "160px"}
     :alt   description}]])

(def image-tags
  (map
    (fn [gallery-path]
      (let [description (gallery-entries gallery-path)]
        (image-tag gallery-path description)))
    image-local-paths))

;(def images)

(def page-title
  [:h1.center "What’s Hot in Florida Plants"])




(def raw-page
  [:div.container
   [:div.inside
    [:h1.center "What’s Hot in Florida Plants"]
    (common/fancy-divider)
    [:h2.alternate1 "Flora & Fauna"]
    [:div.group
     [:section.contentBox3a
      [:p [:img.img-left.img-curved {:alt "" :src "images/samples/green125.jpg"}] "Our native Florida plants are as versatile as they are colorful. Grounded Solutions specializes in ecosystem
        identification and assisting the property owner in re-developing for a native landscape.
        Many of our clients are interested in attracting birds, butterflies and other pollinators."]]
     [:section.contentBox3b
      [:p "Whenever we discover a wonderful native plant we want to see it everywhere.
        This gallery showcases some of our favorites that may be right for you too.
        Soon we will be open for business in our pop-up plant shop. We want to help spread the seeds of diversity in the landscape."]
      [:p "With Grounded Solutions, you get the whole story.
The proper Florida native name, it’s origin and how to best take care of your new plants in the landscape."]]
     [:section.contentBox3c
      [:p "We also tell you where you can purchase your plant material yourself if you so choose,
        from a verified Florida Native Plant nursery. Half the fun of our consultations is discovering Florida native treasures
        that you did not even know you had. So, welcome to the Florida friendly landscape family.
        Help us keep our bird, butterfly and bees happy by reconnecting the environmental food web."]]]
    [:fieldset
     [:legend "Florida Faves"]
     [:p.center
      {:style {:display "flex"
               :flex-wrap "wrap"
               :justify-content "space-evenly"}}
      image-tags]
     [:p.center [:strong "Click on an image to see full view."]]
     [:p "Shown above are a few examples of versatility you can find when choosing native plants.
		Grounded Solutions strives to over you the best quality plant material we possibly can.
		Pairing plant selection with the area you’d like to plant in is the first step towards habitat reconstruction.
		 Want the wow factor? We can do that too."]]]])
     ;[:div.pagination
     ; [:span.disabled_pagination "Prev"]
     ; [:span.active_link "1"]
     ; [:a {:href "#0"} "Next"]]
     ;[:p.dropcap]]]])

(def content-blocks
  [raw-page])

(defn write-page []
  (common/write-page
    :florida-plants
    content-blocks)
  (println "Flora Fauna Page Written"))