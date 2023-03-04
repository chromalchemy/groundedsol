(ns gs.util
  (:require
    [lambdaisland.hiccup :as hiccup]
    [clojure.string :as string]))

(defn default-keymap [m]
  (zipmap
    (map symbol (keys m))
    (keys m)))

(defn jpeg [s]
  (str s ".jpg"))

(defn px [s]
  (str s "px"))

(defn em [s]
  (str s "em"))

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

(defn html [h]
  (hiccup/render h {:doctype? false}))


(defn remove-first-semicolon [s]
  (if (string/starts-with? s ":")
    (->> s (drop 1) (apply str))
    s))

(defn pred-k [& ks]
  (keyword
    (remove-first-semicolon
      (->> ks (apply str)))))

(defn sentences [& strs]
  (->> strs (interpose " ")))

;(defn html-entity [s]
;  (gstring/unescapeEntities s))

;(require 'goog.string)
;(s/transform [(s/walker string?)] goog.string.unescapeEntities my-hiccup-vector)

(defn css-url [file]
  (str "url(\"../" file "\")"))
