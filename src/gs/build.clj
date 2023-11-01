(ns gs.build
  (:require
    [clojure.tools.namespace.repl :refer [refresh refresh] :as repl]
    [nextjournal.beholder :as beholder]
    [gs.pages.index]
    [gs.site]
    [gs.garden.page]
    [gs.components]
    [lambdaisland.ornament :as o]
    [lambdaisland.hiccup :as hiccup]
    ;[criterium.core :refer [bench]]
    [gs.pages.fl-plants]
    [gs.pages.consultation]
    [gs.pages.services]
    [gs.pages.contact]
    [gs.pages.about]
    [gs.pages.notes]
    #_[flow-storm.api :as fs-api]
    [garden.compiler :as gc]
    [lambdaisland.hiccup :as hiccup]
   [gs.groundedsol.ui :as ui]
    :reload))


(repl/set-refresh-dirs "src")
(comment
  repl/refresh-dirs)

(comment
  (fs-api/local-connect))

(defn write-page [page-key page-hiccup]
  (let [file-path (str gs.site/build-path (gs.site/html-filename page-key))]
    [page-key page-hiccup]
    (->>
      [gs.components/html-elem page-key page-hiccup]
      (#(hiccup/render % {:doctype? true}))
      (spit file-path))
    (println (str "Wrote " (name page-key)))))
  

(comment
  (let [page-key :florida-plants
        page-hiccup gs.pages.fl-plants/page-hiccup]
    (write-page page-key page-hiccup)))

(comment
  (let [page-key :florida-plants
        page-hiccup gs.pages.fl-plants/page-hiccup
        file-path
        (str gs.site/build-path
          (gs.site/html-filename page-key))]
    (->> page-hiccup
      (gs.components/html-elem page-key)
      (#(hiccup/render % {:doctype? true})))))

(defn write-page-css! []
  (->>
    [(slurp "resources/default.css")
     (o/defined-styles #_{:preflight? true})
     (gc/compile-css gs.garden.page/rules)
     (interpose \n)]
    (apply str)
    (spit "resources/public/css/main.css"))
  (println "Wrote Compiled CSS to main.css"))

(defn load-pages-hiccup []
  {:notes gs.pages.notes/page-hiccup
   :florida-plants gs.pages.fl-plants/page-hiccup
   :services gs.pages.services/page-hiccup
   :consultation gs.pages.consultation/page-hiccup
   :contact gs.pages.contact/page-hiccup
   :about gs.pages.about/page-hiccup})

;; ----------------- ssg build

(defn build-site! []
  ;(refresh {:refresh-dirs ["src"]})
  (write-page-css!)
  (->> (load-pages-hiccup)
      (mapv
        (fn [[page-key page-hiccup]]
          (write-page page-key page-hiccup))))
  (println "Site Built"))


(comment
  (build-site!)
  #_(bench
      (build-site!)
      :calls 1))

;;;---------------- biff version

;todo: generate require from page kw
(defn pages []
  (->> (load-pages-hiccup)
    (mapv
      (fn [[page-key page-hiccup]]
        [(str "/" (gs.site/html-filename page-key))
         {:get
          (fn [{:keys [recaptcha/site-key params] :as ctx}]
            (ui/page
              (assoc ctx ::ui/recaptcha false)
              page-hiccup))}]))))
