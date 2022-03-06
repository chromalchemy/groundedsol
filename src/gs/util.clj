(ns gs.util
  (:require
    [lambdaisland.hiccup :as hiccup]))

(defn default-keymap [m]
  (zipmap
    (map symbol (keys m))
    (keys m)))

(defn jpeg [s]
  (str s ".jpg"))

(defn px [s]
  (str s "px"))

(defn v
  ([x]
   (str x))
  ([x y]
   (str x " " y))
  ([x y a b]
   (str x " " y " " b " " b)))

(defn pd
  ([x]
   (str (px x)))
  ([x y]
   (str (px x) " " (px y))))

(defn percent [n]
  (str n "%"))

(def nline "\n")

(def double-quote-mark "\"")

(def zero "0")

(defn render-hiccup [h]
  (hiccup/render-html* h))


;(defn html-entity [s]
;  (gstring/unescapeEntities s))

;(require 'goog.string)
;(s/transform [(s/walker string?)] goog.string.unescapeEntities my-hiccup-vector)


