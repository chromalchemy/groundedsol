^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns template
  (:require
    [lambdaisland.ornament :as o]
    [lambdaisland.ornament.clerk-util :refer [inline-styles render]]
    [nextjournal.clerk :as clerk]
    [lambdaisland.hiccup :as hiccup]
    [girouette.core :as g]
    [garden.compiler :as gc]
    [girouette.tw.preflight :as girouette-preflight]
    [nextjournal.clerk.viewer :as v]))


;; # Ornament with Girouette bump
;; Latest version.
;; Had to fix broken code (from bad formatting).

(defn k-s [k s]
  (keyword (str (name k) "-" s)))


(defn hsl-k
  ([k h s l]
   (k-s k (str "hsl-" (apply str (interpose "-" [h s l])))))
  ([k h s l a]
   (k-s k (str "hsla-" (apply str (interpose "-" [h s l a]))))))


(defn hex-k [k hex-str]
  (k-s k hex-str))

(comment
  (hex-k :text "#64AF54FF"))


;;###  Define Hiccup Styled Component
;; `:underline-5` is a TW-3 style

(o/defstyled strong-link :a
  (hsl-k :bg 10 20 25) :px-6 :py-4  :rounded (hex-k :text "#ff0000c5")  :border-3 :inline-block :text-center :underline :underline-5  :text-xl
  {:font-weight 1000})

;;###  Render Component
(render
  [strong-link {:href ""} "My Link"])

^{::clerk/visibility {:code :hide}}
(-> [strong-link {:href ""} "My Link"]
  (hiccup/render {:doctype? false}))

;-----------------------------

;; #### Set clerk/visiblity default as a file breakpoint
;; `^{::clerk/visibility {:code :show :result :show}}`

;-----------------------------

;; # Ornament Page Render

;; ### Custom Styles

^{::clerk/visibility {:code :hide}}
(clerk/example
  @o/registry
  (o/defined-styles))

;; ### Tailwind 3 tokens
^{::clerk/visibility {:code :hide}}
(clerk/example
  o/default-tokens-v3)

v/default-viewers

;; ### Named viewers
^{::clerk/visibility {:code :hide}}
(filter some?
  (map :name v/default-viewers))

;::clerk/viewer :code-folded

;;## TW-3 # Grammar
^{::clerk/visibility {:code :hide}
  ::clerk/viewer :code-folded}
(:grammar
  (o/set-tokens!
    {:tw-version 3}))

(o/set-tokens!
  {:tw-version 3})


;; ### Default Tailwind styles
^{::clerk/visibility {:code :hide}}
(clerk/example
  o/default-tokens-v3
  girouette-preflight/preflight-v3_0_24
  (o/defined-styles {:preflight? true}))


^{::clerk/no-cache true
  ::clerk/visibility :hide}
;; Inline our styles last, so that this happens after all `defstyled`s are defined
(render [:style (o/defined-styles {:preflight? true})])


^{::clerk/visibility {:result :hide :code :hide}}
(comment
  (clerk/show! "notebooks/template.clj")
  (clerk/serve! {:browse? true})
  (clerk/serve! {:watch-paths ["notebooks"]
                 :browse? true}))

