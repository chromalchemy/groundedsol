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
    [flow-storm.api :as fs-api]
    [garden.compiler :as gc]
    [lambdaisland.hiccup :as hiccup]
    :reload))


(repl/set-refresh-dirs "src")
(comment
  repl/refresh-dirs)

(comment
  (fs-api/local-connect))

(defn write-page [page-key page-hiccup]
  (let [file-path (str gs.site/build-path (gs.site/html-filename page-key))]
    (->> page-hiccup
      (gs.components/html-el page-key)
      hiccup/html
      hiccup/render-html
      (spit file-path))
    (println (str "Wrote " (name page-key)))))

(comment
  (let [page-key :florida-plants
        page-hiccup gs.pages.fl-plants/page-hiccup
        file-path
        (str gs.site/build-path
          (gs.site/html-filename page-key))]
    (->> page-hiccup
      (gs.components/html-el page-key)
      hiccup/html)))
      ;hiccup/render-html)))

(defn write-page-styles! []
  (->>
    [(slurp "resources/css/default.css")
     (o/defined-styles #_{:preflight? true})
     (gc/compile-css gs.garden.page/rules)
     (interpose \n)]
    (apply str)
    (spit "build/css/compiled.css"))
  ;(->>
  ;  (spit "build/css/default.css"))
  (println "Wrote compiled.css"))


;todo: generate require from page kw
(defn build-site! []
  ;(refresh {:refresh-dirs ["src"]})
  (write-page-styles!)
  (->>
    {:home gs.pages.index/page-hiccup
     :new gs.pages.new/page-hiccup
     :florida-plants gs.pages.fl-plants/page-hiccup
     :services gs.pages.services/page-hiccup
     :consultation gs.pages.consultation/page-hiccup
     :contact gs.pages.contact/page-hiccup
     :about gs.pages.about/page-hiccup}
    (mapv
      (fn [[page-key page-hiccup]]
        (write-page page-key page-hiccup))))
  (println "Site Built"))

(comment
  (build-site!)
  (bench
    (build-site!)
    :calls 1))
