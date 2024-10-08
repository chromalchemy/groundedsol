(ns gs.groundedsol.app
  (:require [com.biffweb :as biff :refer [q]]
            [gs.groundedsol.middleware :as mid]
            [gs.groundedsol.ui :as ui]
            #_[gs.groundedsol.settings :as settings]
            [xtdb.api :as xt]
            [gs.hiccup :as bhiccup]
            #_[ring.adapter.jetty9 :as jetty]
            #_[cheshire.core :as cheshire]))


(defn sign-in-form [email]
  [:div "Signed in as " email ". "
   (bhiccup/form
     {:action "/auth/signout"
      :class "inline"}
     [:button.text-blue-500.hover:text-blue-800 {:type "submit"}
      "Sign out"])
   "."]
  [:div.h-6]
  [:div "thanks for joining"])

(defn app [{:keys [session biff/db] :as ctx}]
  (let [{:user/keys [email]} (xt/entity db (:uid session))]
    (ui/page
      {}
      [:h1 "APP"])))


(def module
  {:routes ["/app" {:middleware [mid/wrap-signed-in]}
            ["" {:get app}]]})
