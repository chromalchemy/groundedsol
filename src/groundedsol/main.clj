(ns groundedsol.main
  (:require [nextjournal.beholder :as beholder]
            [groundedsol.pages.index]
            [groundedsol.pages.fl-plants]
            [groundedsol.pages.faq]
            [groundedsol.pages.services]
            [groundedsol.pages.contact]))


(defn build-site []
  (require 'groundedsol.pages.index :reload-all)
  (require 'groundedsol.pages.fl-plants :reload-all)
  (require 'groundedsol.pages.services :reload-all)
  (require 'groundedsol.pages.faq :reload-all)
  (require 'groundedsol.pages.contact :reload-all)
  (groundedsol.pages.index/write-page)
  (groundedsol.pages.fl-plants/write-page)
  (groundedsol.pages.services/write-page)
  (groundedsol.pages.faq/write-page)
  (groundedsol.pages.contact/write-page)
  (println "Site Built"))

(comment
  ;; watch function needs to take a map
  (def watcher
    (beholder/watch build-site "src")
    (beholder/stop watcher)))

