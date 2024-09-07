(ns gs.groundedsol.ui
  (:require [cheshire.core :as cheshire]
            [clojure.java.io :as io]
            [gs.meta]
            [gs.groundedsol.settings :as settings]
            [com.biffweb :as biff]
            [ring.middleware.anti-forgery :as csrf]
            [lambdaisland.hiccup :as hiccup]
            [gs.hiccup :refer [base-html] :as bhiccup]
    ))

(defn base [{:keys [::recaptcha] :as ctx} & body]
  (let [custom-title 
        (:base/title ctx)
        #_settings/app-name
        title 
        (cond 
          custom-title custom-title
          :else
          "Grounded Solutions Native Landscape Consultation & Design")]
    (apply
      base-html
      (-> ctx
        (merge
          #:base{:title title
                 #_ (let [custom-page-title (:title (pages page-key))]
                      (if custom-page-title
                        custom-page-title
                        (-> pages :home :title)))
                 :lang "en-US"
                 #_#_:icon "/img/glider.png"
                 :description "Ecological Landscape Design & Consulting"
                 :image "/img/logo400.png"})
        (update :base/head
          (fn [head]
            (gs.meta/head-stuff ctx head recaptcha))))
      body)))

(defn page [ctx & body]
  (base
   ctx
   [:div
    (when (bound? #'csrf/*anti-forgery-token*)
      {:hx-headers (cheshire/generate-string
                     {:x-csrf-token csrf/*anti-forgery-token*})})
    body]))

(defn on-error [{:keys [status ex] :as ctx}]
  {:status status
   :headers {"content-type" "text/html"}
   :body 
   (bhiccup/render-static-markup
     (page
       ctx
       [:h1.text-lg.font-bold
        (if (= status 404)
          "Page not found."
          "Something went wrong.")]))
   
   })

