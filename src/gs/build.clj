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
    [flow-storm.api :as fs-api]
    [garden.compiler :as gc]
    [lambdaisland.hiccup :as hiccup]
    :reload))


(repl/set-refresh-dirs "src")
(comment
  repl/refresh-dirs)

(comment
  (fs-api/local-connect))

#_
(do
  (defn write-page [page-key page-hiccup]
    (let [file-path (str gs.site/build-path (gs.site/html-filename page-key))]
      [page-key page-hiccup]
      (->>
        [gs.components/html-elem page-key page-hiccup]
        (#(hiccup/render % {:doctype? true}))
        (spit file-path))
      (println (str "Wrote " (name page-key)))))

;(comment
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
    [(slurp "resources/css/default.css")
     (o/defined-styles #_{:preflight? true})
     (gc/compile-css gs.garden.page/rules)
     (interpose \n)]
    (apply str)
    (spit "build/css/main.css"))
  (println "Wrote Compiled CSS to main.css"))


;todo: generate require from page kw
(defn build-site! []
  ;(refresh {:refresh-dirs ["src"]})
  (write-page-css!)
  #_
  (->>
    {:home gs.pages.index/page-hiccup
     :notes gs.pages.notes/page-hiccup
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
  #_(bench
      (build-site!)
      :calls 1))