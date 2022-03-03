(ns gs.util
  (:require
    ;[girouette]
    [garden.color :as gc]
    [clojure.string :as str]))

(defn default-keymap [m]
  (zipmap
    (map symbol (keys m))
    (keys m)))

(defn hsl->hex [h s l]
  (gc/hsl->hex (gc/hsl h s l)))

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

(def site-root "https://getbitoutdoors.com/")

;todo: make this pure, and injext state downstream, with DI or vars

(def image-root
  ;"images/"
  (str site-root "content/newsletter/"))

(defn img-path
  ([filename]
   (str image-root filename))
  ([filename folder]
   (str image-root folder "/" filename)))

(defn style [s]
  {:style
   (str/join ";"
     (map
       #(str (name %) ":" ((keyword %) s))
       (keys s)))})

(def nline "\n")

(def double-quote-mark "\"")

(defn hsl [h s l]
  (str " hsl(" h ", " s "%, " l "%)"))

(defn hsla [h s l a]
  (str "hsla(" h ", " s "%, " l "%, " (* 0.1 a) ")"))

(def zero "0")




