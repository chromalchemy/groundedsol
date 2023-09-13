(ns gs.groundedsol.ui
  (:require [cheshire.core :as cheshire]
            [clojure.java.io :as io]
            [gs.meta]
            [gs.groundedsol.settings :as settings]
            [com.biffweb :as biff]
            [ring.middleware.anti-forgery :as csrf]
            [rum.core :as rum]))


(defn base [{:keys [::recaptcha] :as ctx} & body]
  (apply
   biff/base-html
   (-> ctx
     (merge
       #:base{:title settings/app-name
              #_ (let [custom-page-title (:title (pages page-key))]
                   (if custom-page-title
                     custom-page-title
                     (-> pages :home :title)))
              :lang "en-US"
              #_#_:icon "/img/glider.png"
              :description "Ecological Landscape Design & Consulting"
              :image "/img/logo.png"})
     (update :base/head
       #(gs.meta/head-stuff % recaptcha)))
   body))

(defn page [ctx & body]
  (base
   ctx
   [:.bg-orange-50.flex.flex-col.flex-grow
    [:.flex-grow]
    [:.p-3.mx-auto.max-w-screen-sm.w-full
     (when (bound? #'csrf/*anti-forgery-token*)
       {:hx-headers (cheshire/generate-string
                      {:x-csrf-token csrf/*anti-forgery-token*})})
     body]
    [:.flex-grow]
    [:.flex-grow]]))

(defn on-error [{:keys [status ex] :as ctx}]
  {:status status
   :headers {"content-type" "text/html"}
   :body (rum/render-static-markup
          (page
           ctx
           [:h1.text-lg.font-bold
            (if (= status 404)
              "Page not found."
              "Something went wrong.")]))})
