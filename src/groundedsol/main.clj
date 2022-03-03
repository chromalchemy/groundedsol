(ns groundedsol.main
  (:require
    [nextjournal.beholder :as beholder]
    [groundedsol.pages.index]
    [groundedsol.common :as common]))
    ;[groundedsol.pages.fl-plants]
    ;[groundedsol.pages.faq]
    ;[groundedsol.pages.services]
    ;[groundedsol.pages.contact]))


(defn build-site []
  ;(require 'groundedsol.pages.index :reload-all)
  ;(require 'groundedsol.pages.fl-plants :reload-all)
  ;(require 'groundedsol.pages.services :reload-all)
  ;(require 'groundedsol.pages.faq :reload-all)
  ;(require 'groundedsol.pages.contact :reload-all)
  (common/write-page :home groundedsol.pages.index/content)
  ;(groundedsol.pages.fl-plants/write-page)
  ;(groundedsol.pages.services/write-page)
  ;(groundedsol.pages.faq/write-page)
  ;(groundedsol.pages.contact/write-page)
  (println "Site Built"))

(comment
  (build-site)
  (common/page :home groundedsol.pages.index/content))

(comment
  ;; watch function needs to take a map
  (def watcher
    (beholder/watch
      (fn [x]
        (build-site))
      "src"))
  (beholder/stop watcher))

