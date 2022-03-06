(ns gs.build
  (:require
    [nextjournal.beholder :as beholder]
    [gs.pages.index]
    [gs.site]
    [gs.components]
    [lambdaisland.hiccup :as hiccup]
    [criterium.core :refer [bench]]
    [gs.pages.fl-plants]))
    ;[gs.pages.faq]
    ;[gs.pages.services]
    ;[gs.pages.contact]))

(defn write-page [page-key content]
  (let [file-path (str gs.site/build-path (gs.site/html-filename page-key))]
    (->> content
      (gs.components/page page-key)
      hiccup/html
      hiccup/render-html
      (spit file-path))
    (println (str "Wrote " (name page-key)))))

;todo: generate require from page kw
(defn build-site! []
  (require 'gs.pages.index :reload)
  (require 'gs.pages.fl-plants :reload)
  ;(require 'gs.pages.services :reload-all)
  ;(require 'gs.pages.faq :reload-all)
  ;(require 'gs.pages.contact :reload-all)
  (write-page :home gs.pages.index/page-hiccup)
  (write-page :florida-plants gs.pages.fl-plants/content)
  ;(gs.pages.services/write-page)
  ;(gs.pages.faq/write-page)
  ;(gs.pages.contact/write-page)
  (println "Site Built"))

#_
(build-site!)

(comment
  (build-site!)
  (bench
    (build-site!)
    :calls 1))
