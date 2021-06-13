(ns groundedsol.build
  (:require [volcano.build :as build]
            [groundedsol.config :as config]))

(defn -main [& args]
  (build/build-web! (config/config)))