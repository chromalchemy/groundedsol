(ns groundedsol.pages.fl-plants
  (:require
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [groundedsol.content :as c]
    [groundedsol.common :as common]
    [groundedsol.airtable :as at]
    [groundedsol.util :as u]
    [rum.core :as rum]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]))

(def divider
  [:hr.fancy])

(def page-title
  [:h1.center "What’s Hot in Florida Plants"])

(def raw-page
  [:div.container
   [:div.inside
    [:h1.center "What’s Hot in Florida Plants"]
    [:hr.fancy]
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
     [:p.center [:a.lightbox {:data-lightbox-gallery "catalog1" :href "images/samples/1b.jpg" :title "Deerberry, Vaccinium stamineum"}
                 [:img.img-portfolio {:alt "Deerberry, Vaccinium stamineum" :src "images/samples/1s.jpg"}]]
      [:a.lightbox {:data-lightbox-gallery "catalog1" :href "images/samples/2b.jpg" :title "Firebush, Hamelia patens"}
       [:img.img-portfolio {:alt "Firebush, Hamelia patens" :src "images/samples/2s.jpg"}]]
      [:a.lightbox {:data-lightbox-gallery "catalog1" :href "images/samples/groundorchidsb.jpg" :title "Native Ground orchids! Also called terrestrial orchids. Scarlet Ladies’ Tresses, Sacoila lanceolata  #9412_Go"}
       [:img.img-portfolio {:alt "Native Ground orchids! Also called terrestrial orchids. Scarlet Ladies’ Tresses, Sacoila lanceolata #9412_Go" :src "images/samples/groundorchids.jpg"}]]
      [:a.lightbox {:data-lightbox-gallery "catalog1" :href "images/samples/4b.jpg" :title "A fresh cut flower arrangement"}
       [:img.img-portfolio {:alt "A fresh cut flower arrangement" :src "images/samples/4s.jpg"}]]
      [:a.lightbox {:data-lightbox-gallery "catalog1" :href "images/samples/5b.jpg" :title "Beach verbena, Glandularia maritima"}
       [:img.img-portfolio {:alt "Beach verbena, Glandularia maritima" :src "images/samples/5s.jpg"}]]
      [:a.lightbox {:data-lightbox-gallery "catalog1" :href "images/samples/6bb.jpg" :title "Florida Paintbrush prior to setting bud, Carphephorus"}
       [:img.img-portfolio {:alt "Florida Paintbrush prior to setting bud, Carphephorus" :src "images/samples/6s.jpg"}]]
      [:a.lightbox {:data-lightbox-gallery "catalog1" :href "images/samples/7b.jpg" :title "Indian-plantain with a beneficial insect getting nectar and pollen, Arnoglossum ovatum"}
       [:img.img-portfolio {:alt "Indian-plantain with a beneficial insect getting nectar and pollen, Arnoglossum ovatum" :src "images/samples/7s.jpg"}]]
      [:a.lightbox {:data-lightbox-gallery "catalog1" :href "images/samples/8b.jpg" :title "False pennyroyal, Piloblephus rigida"}
       [:img.img-portfolio {:alt "False pennyroyal, Piloblephus rigida" :src "images/samples/8s.jpg"}]]
      [:a.lightbox {:data-lightbox-gallery "catalog1" :href "images/samples/9b.jpg" :title "Wild poinsettia, Poinsettia cyathophora"}
       [:img.img-portfolio {:alt "Wild poinsettia, Poinsettia cyathophora" :src "images/samples/9s.jpg"}]]
      [:a.lightbox {:data-lightbox-gallery "catalog1" :href "images/samples/10b.jpg" :title "Winter blooming vine Carolina jessamine, Gelsemium sempervirens"}
       [:img.img-portfolio {:alt "Winter blooming vine Carolina jessamine, Gelsemium sempervirens" :src "images/samples/10s.jpg"}]]
      [:a.lightbox {:data-lightbox-gallery "catalog1" :href "images/samples/11b.jpg" :title "Spring blooming plum tree, Prunus umbellata"}
       [:img.img-portfolio {:alt "Spring blooming plum tree, Prunus umbellata" :src "images/samples/11s.jpg"}]]
      [:a.lightbox {:data-lightbox-gallery "catalog1" :href "images/samples/12b.jpg" :title "Gopher apple as a ground cover, Licania michauxii"}
       [:img.img-portfolio {:alt "Gopher apple as a ground cover, Licania michauxii" :src "images/samples/12s.jpg"}]]
      [:a.lightbox {:data-lightbox-gallery "catalog1" :href "images/samples/13b.jpg" :title "Pollinator attractor Spiderwort in purple, Tradescantia ohiensis"}
       [:img.img-portfolio {:alt "Pollinator attractor Spiderwort in purple, Tradescantia ohiensis" :src "images/samples/13s.jpg"}]]
      [:a.lightbox {:data-lightbox-gallery "catalog1" :href "images/samples/14b.jpg" :title "Fruit producing Bigflower pawpaw, Asimina obovata"}
       [:img.img-portfolio {:alt "Fruit producing Bigflower pawpaw, Asimina obovata" :src "images/samples/14s.jpg"}]]
      [:a.lightbox {:data-lightbox-gallery "catalog1" :href "images/samples/milkweed2b.jpg" :title "Aquatic milkweed, also shown in orange. Asclepias perennis  "}
       [:img.img-portfolio {:alt "Aquatic milkweed, also shown in orange. Asclepias perennis" :src "images/samples/milkweed2s.jpg"}]]]
     [:p.center [:strong "Click on an image to see a larger view."]]
     [:p "Shown above are a few examples of versatility you can find when choosing native plants.
		Grounded Solutions strives to over you the best quality plant material we possibly can.
		Pairing plant selection with the area you’d like to plant in is the first step towards habitat reconstruction.
		 Want the wow factor? We can do that too."]
     [:div.pagination
      [:span.disabled_pagination "Prev"]
      [:span.active_link "1"]
      [:a {:href "#0"} "Next"]]
     [:p.dropcap]]]])

(def content-blocks
  [raw-page])

(common/write-page
  :florida-plants
  content-blocks)