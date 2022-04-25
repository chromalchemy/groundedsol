(ns gs.color
  (:require
    [garden.color :as gc]))

(defn hsl->hex [h s l]
  (gc/hsl->hex (gc/hsl h s l)))

(defn hsl [h s l]
  (str " hsl(" h ", " s "%, " l "%)"))

(defn hsla [h s l a]
  (str "hsla(" h ", " s "%, " l "%, " (* 0.1 a) ")"))

(defn hsl-k [k h s l]
  (keyword (str (name k) "-" (hsl->hex h s l))))

(comment
  (hsl-k :hello 20 20 20))

(def gold-yellow
  "#d1c583")