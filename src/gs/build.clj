(ns gs.build
  (:require
    [nextjournal.beholder :as beholder]
    [gs.pages.index]
    [gs.common]
    [gs.page]
    [lambdaisland.hiccup :as hiccup]

    [gs.pages.fl-plants]))
    ;[gs.pages.faq]
    ;[gs.pages.services]
    ;[gs.pages.contact]))

(def build-path "build/")

(defn write-page [page-key content]
  (let [file-path (str build-path (gs.page/html-filename page-key))]
    (->> content
      (gs.common/page page-key)
      hiccup/html
      hiccup/render-html
      (spit file-path))
    (println (str "Wrote " (name page-key)))))

(defn build-site! []
  (require 'gs.pages.index :reload-all)
  (require 'gs.pages.fl-plants :reload-all)
  ;(require 'gs.pages.services :reload-all)
  ;(require 'gs.pages.faq :reload-all)
  ;(require 'gs.pages.contact :reload-all)
  (write-page :home gs.pages.index/content)
  (write-page :florida-plants gs.pages.fl-plants/content)
  ;(gs.pages.services/write-page)
  ;(gs.pages.faq/write-page)
  ;(gs.pages.contact/write-page)
  (println "Site Built"))

(comment
  (build-site!))
  ;(common/page :home gs.pages.index/content))
