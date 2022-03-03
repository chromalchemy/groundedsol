(ns gs.main
  (:require
    [nextjournal.beholder :as beholder]
    [gs.pages.index]
    [gs.common :as common]))
    ;[gs.pages.fl-plants]
    ;[gs.pages.faq]
    ;[gs.pages.services]
    ;[gs.pages.contact]))

(defn build-site []
  ;(require 'gs.pages.index :reload-all)
  ;(require 'gs.pages.fl-plants :reload-all)
  ;(require 'gs.pages.services :reload-all)
  ;(require 'gs.pages.faq :reload-all)
  ;(require 'gs.pages.contact :reload-all)
  (common/write-page :home gs.pages.index/content)
  ;(gs.pages.fl-plants/write-page)
  ;(gs.pages.services/write-page)
  ;(gs.pages.faq/write-page)
  ;(gs.pages.contact/write-page)
  (println "Site Built"))

(comment
  (build-site)
  (common/page :home gs.pages.index/content))

(comment
  ;; watch function needs to take a map
  (def watcher
    (beholder/watch
      (fn [x]
        (build-site))
      "src"))
  (beholder/stop watcher))

