(ns groundedsol.util
  (:require [girouette.]))

(defn img-path [file]
  (str "images/" file))

(defn default-keymap [m]
  (zipmap
    (map symbol (keys m))
    (keys m)))

(+ 1 1)