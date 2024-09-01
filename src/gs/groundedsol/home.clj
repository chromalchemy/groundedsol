(ns gs.groundedsol.home
  (:require
   [com.biffweb :as biff :refer [pprint]]
   [gs.groundedsol.middleware :as mid]
   [gs.groundedsol.ui :as ui]
   [gs.site]
   [gs.pages.index]
   [gs.pages.fl-plants]
   [gs.pages.consultation]
   [gs.pages.services]
   [gs.pages.contact :as contact]
   [gs.pages.about]
   [gs.pages.notes]))

(defn add-html-route [[path-str m]]
  [[(str path-str ".html") m]
   [path-str m]])

(defn route-fn [page-hiccup]
  (fn 
    [{:keys [recaptcha/site-key params] :as ctx}]
    (ui/page
      (assoc ctx ::ui/recaptcha false)
      page-hiccup)))

(defn make-routes [[page-key page-hiccup]]
  (let [route-base-str 
        (str "/" (gs.site/html-filename page-key))]
    (add-html-route 
      [route-base-str {:get (route-fn page-hiccup)}])))

(defn demo-handler [request]
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "foo response"})

(def new-routes
  [#_["/demo" 
      {:get demo-handler}]
   ["/send-contact" 
    {:post 
     gs.pages.contact/handle-form-submission}]
   ["" 
    {:middleware [mid/wrap-redirect-signed-in]}
    ["/" 
     {:get gs.pages.index/home-page}]]
   ["/contact" 
    {:get gs.pages.contact/page}]])

(def original-page-routes
  (->>
    {:notes gs.pages.notes/page-hiccup
     :florida-plants gs.pages.fl-plants/page-hiccup
     :services gs.pages.services/page-hiccup
     :consultation gs.pages.consultation/page-hiccup
    ;;  :contact gs.pages.contact/page-hiccup
     :about gs.pages.about/page-hiccup}
    (mapv make-routes)
    (apply concat)))


(def all-routes
  (vec  
    (concat
         new-routes
         original-page-routes)))
    

(def module
  {:routes all-routes})

