(ns gs.photo-gallery
  (:require
    [clojure.string :as string]
    [exif-processor.core :as exif]
    [camel-snake-kebab.core :as csk]
    [clojure.java.io :as io])
  (:use [com.rpl.specter])
  (:import
    ;[java.io]
    [com.drew.imaging ImageMetadataReader]))

(def exif-directory-regex
  (re-pattern
    (str "(?i)("
      (string/join "|"
        ["Exif" "JPEG" "JFIF"
         "Agfa" "Canon" "Casio" "Epson"
         "Fujifilm" "Kodak" "Kyocera"
         "Leica" "Minolta" "Nikon" "Olympus"
         "Panasonic" "Pentax" "QuickTime" "Sanyo"
         "Sigma/Foveon" "Sony"]) ")")))

(defn- extract-from-tag
  [tag]
  (into {} (map #(hash-map (.getTagName %) (.getDescription %)) tag)))

(comment
  (do
    (defn dng-data
      "Takes an image file (as a java.io.InputStream or java.io.File) and extracts exif information into a map"
      [file]
      (let [metadata (ImageMetadataReader/readMetadata file)
            directories (.getDirectories metadata)
            xmp-directory
            (->> directories)]
            ;(filter #(string/includes? (str %) "XMP"))
            ;(first))]
        
      ;metadata
        (->> directories
          (map bean)
          ((fn [x]
             (let
               [exif-data
                (-> (nth x 7)
                  :tags
                  seq
                  (->>
                    (map
                      (fn [exif-kv-str]
                        (as-> exif-kv-str x
                          (str x)
                          (string/split x #" - ")
                          (let [[k v] x]
                            {(-> k
                               (string/replace "[Exif SubIFD] " "")
                               csk/->kebab-case-string
                               (string/replace "/" "")
                               (#(keyword (str "exif/" %))))
                             v}))))
                    (apply merge)))
                lr-data
                (->> (nth x 6)
                  :xmpProperties
                  (map
                    (fn [[x v]]
                      {(as-> x lr-data-kv-str
                         (let
                           [[n k]
                            (string/split x #":")]
                           (keyword
                             (str
                               (-> n
                                 (string/replace "MM" ""))
                               "/"
                               (-> k
                                 (string/replace "/" "|")
                                 (string/replace "[" "-")
                                 (string/replace "]" "-")
                                 csk/->kebab-case-string
                                 (string/replace "-|" "|"))))))
                       v}))
                  (apply merge))]
               (->>
                 (merge
                   exif-data
                   lr-data)
               ;; conj lr keywords into set
                 ((fn [x]
                    (let
                      [lr-kws
                       (->> x
                         (filter
                           (fn [[k v]]
                             (and
                               (= (namespace k) "dc")
                               (string/includes? (name k) "subject"))))
                         vals
                         set)]
                      (assoc x :lr/keywords lr-kws)))))))))))
        ;tags (map #(.getTags %) exif-directories)]
    (->
    ;"resources/img/What's hot photo drop/cropped/random-xmp-example.xmp"
    ;"resources/img/What's hot photo drop/cropped/DSC_0008.xmp"
      "resources/img/What's hot photo drop/cropped/DSC_0008.dng"
      (io/file)
      (dng-data)))
  )


(def mystring "hello")
(str "world " mystring)