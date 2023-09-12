(ns gs.groundedsol.app
  (:require [com.biffweb :as biff :refer [q]]
            [gs.groundedsol.middleware :as mid]
            [gs.groundedsol.ui :as ui]
            #_[gs.groundedsol.settings :as settings]
            #_[rum.core :as rum]
            [xtdb.api :as xt]
            #_[ring.adapter.jetty9 :as jetty]
            #_[cheshire.core :as cheshire]))


(defn app [{:keys [session biff/db] :as ctx}]
  (let [{:user/keys [email]} (xt/entity db (:uid session))]
    (ui/page
     {}
     [:div "Signed in as " email ". "
      (biff/form
       {:action "/auth/signout"
        :class "inline"}
       [:button.text-blue-500.hover:text-blue-800 {:type "submit"}
        "Sign out"])
      "."]
     [:.h-6]
     [:div "thanks for joining"]
     )))





(def plugin
  {:routes ["/app" {:middleware [mid/wrap-signed-in]}
            ["" {:get app}]]})
