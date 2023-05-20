(ns gs.garden.styles)

(defn border
  ([]
   (border nil))
  ([color]
   {:border
    {:color color
     :style "solid"
     :width "2px"}}))