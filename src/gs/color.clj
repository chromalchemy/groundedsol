(ns gs.color
  (:require
    [garden.color :as gc]))

(defn hsl->hex [h s l]
  (gc/hsl->hex (gc/hsl h s l)))

(defn hsl [h s l]
  (str " hsl(" h ", " s "%, " l "%)"))

(defn hsla [h s l a]
  (str "hsla(" h ", " s "%, " l "%, " (* 0.1 a) ")"))
