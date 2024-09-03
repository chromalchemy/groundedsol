(ns gs.pages.fl-plants
  (:require
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [gs.content :as content]
    [gs.components :as c]
   [gs.content :as content]
    [lambdaisland.ornament :refer [defstyled]]
    [gs.util :as u]
    [lambdaisland.hiccup :as hiccup]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE selected? before-index pred=] :as s]
    [clojure.string :as string]
    [gs.meta])
  (:use [gs.util]))

;img/samples/1b.jpg

(defn set-elem-at-beginning-of-first-child-tag 
  [{:keys [target-tag hiccup]} h]
  (setval
    [s/INDEXED-VALS 
     (selected? s/LAST vector?
       FIRST (pred= target-tag))
     (selected? FIRST (pred= 1))
     s/LAST
     (before-index 1)]
    hiccup
    h)
  )

(comment
  (set-elem-at-beginning-of-first-child-tag
    {:target-tag :p
     :hiccup :inserted}
    [:<>
     [:p "first"]
     [:p "second"]]))

(do 
  (defn set-elem-at-end-of-last-child-tag
    [{:keys [target-tag hiccup]} h]
    (let [last-elem 
          (last h)]
      (setval
        [s/INDEXED-VALS
         (selected? s/LAST vector?
           FIRST (pred= target-tag))
         (selected? s/LAST 
           (pred= last-elem))
       s/LAST
       s/AFTER-ELEM]
      hiccup
        h)))
  (set-elem-at-end-of-last-child-tag
    {:target-tag :p
     :hiccup :inserted}
    [:<>
     [:p "first"]
     [:p "second"]]))



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
  (str "https://groundedsol.com/img/gallery/" s))

(def old-image-local-paths
  (conj
    (vec old-image-names)
    (str "old/" (jpeg "milkweed2b"))))

(def gallery-image-local-paths
  (concat old-image-local-paths content/new-images-filenames))

(defn image-file-name->short-desc [filename]
  (-> filename
      (string/split #"-")
      drop-last
      (->> (interpose " ")
           (apply str))))

(def new-gallery-entries
  (->> content/new-images-filenames
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


(defstyled thumbnail-img :img.img-portfolio.thumb)

(defstyled gallery-link :a.lightbox 
  :block
  [:.thumb :h-160px]
  ([local-path description]
   (let [full-img-path
         (gallery-image-path local-path)
         thumb-img-path
         (gallery-image-path
           (str "thumb/" local-path))]
     [:<>
      {:href full-img-path
       :data-lightbox-gallery "catalog1"
       :title description}
      [thumbnail-img
       {:src thumb-img-path
        :alt description}]])))

(defstyled gallery :fieldset
  [:.inner :flex :flex-wrap :justify-evenly]
  [:.directions :text-center]
  ([]
   [:<>
    [:legend "Florida Faves"]
    [:div.inner
     (for [gallery-path gallery-image-local-paths]
       (let [description
             (gallery-entries gallery-path)]
         (gallery-link gallery-path description)))]
    [:p.directions
     "Click on an image to see full view."]]))

(defstyled gallery-intro :div
  [:.capsule :rounded-2xl]
  [:at-media 
   {:min-width "768px"}
   {:column
    {:count 3
     :gap (px 30)}}]
  [:p {:margin-block-start "auto"}]
  ([]
    (->> content/gallery-intro-text
      (set-elem-at-beginning-of-first-child-tag
        {:target-tag :p
         :hiccup
         [c/left-img
          {:alt "" 
           :src "img/samples/green125.jpg"}]})
      (setval s/AFTER-ELEM 
        (c/inline-social-icons 
          "img/social-icons/")))))

(defstyled centered-title c/middle-of-line-title
  :text-gray-700 :mb-4)

(def page-hiccup
  [c/container
   [c/page-title "What’s Hot in Florida Plants"]
   [c/fancy-divider]
   [centered-title "Flora & Fauna"]
   [gallery-intro]
   [gallery]])