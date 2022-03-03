(ns gs.page)

(def pages
  {:email-confirmation/file "thankyou"
   :email-confirmation/title "Thank You"
   :home/file               "index"
   :home/name               "Home"
   :home/title              "Grounded Solutions Landscape Consultation and Design Company"
   :services/name           "Services"
   :services/file           "consultationanddesign"
   :services/title          "Consultation and Design Eco-Logical Landscapes"
   :consultation/name       "CONSULTATION"
   :design/name             "DESIGN"
   :pop-up-shop/name        "POP UP Shop"
   :consultation/file       "consultationanddesign"
   :florida-plants/name     "Plants"
   :florida-plants/file     "floridaplants"
   :ecosystems/file         "ecosystems"
   :ecosystems/name         "Ecosytems"
   :about/file              "about"
   :about/name              "About"
   :florida-plants-411/name "FL Plants 411"
   :contact/file            "contact"
   :contact/name            "Contact"
   :faq/name "FAQ"
   :faq/file "faq"})

(defn page-kw [page-kw tail-str]
  (keyword (str (name page-kw) "/" tail-str)))

(def page-keys [:home :services :florida-plants :faq :about :contact])

(defn html-filename [page]
  (str (pages (page-kw page "file")) ".html"))

(comment
  (html-filename :home))

(defn page-val [page s]
  (pages (page-kw page s)))

(def page-description
  "We provide ecologically sound landscaping services focused on habitat enhancement using Florida native plant species")