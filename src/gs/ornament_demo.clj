(ns gs.ornament-demo
  (:require [nextjournal.clerk :as clerk]))

(comment
  (clerk/serve! {:watch-paths ["notebooks"]
                 :browse? true})

  (clerk/show! "notebooks/demo.clj")
  (clerk/show! "notebooks/template.clj"))