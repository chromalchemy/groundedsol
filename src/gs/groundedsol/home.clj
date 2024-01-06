(ns gs.groundedsol.home
  (:require [clj-http.client :as http]
            [com.biffweb :as biff :refer [pprint]]
            [gs.groundedsol.middleware :as mid]
            [gs.groundedsol.ui :as ui]
            [gs.site]
            [gs.groundedsol.settings :as settings]
            [gs.pages.index]
            [gs.pages.fl-plants]
            [gs.pages.consultation]
            [lambdaisland.ornament :as o :refer [defstyled]]
            [gs.pages.services]
            [gs.pages.contact :as contact]
            [gs.pages.about]
            [gs.pages.notes]
            [gs.hiccup :as bhiccup]
            [com.biffweb.impl.util :as butil]
            [com.biffweb.impl.auth :as auth]
            [com.biffweb.impl.xtdb :as bxt]
            [gs.components :as common]
            [gs.build :as build]
            [lambdaisland.hiccup :as hiccup]
            [xtdb.api :as xt]
            [camel-snake-kebab.core :as csk]
            [camel-snake-kebab.extras :as cske]
            [clj-http.client :as http]
            [clojure.tools.logging :as log]))
            

(defstyled email-error-message :div
  ([error-text]
   [:<> error-text]))


(defn confirmation-page [{:keys [params] :as ctx}]
  (ui/page
   ctx
   [:h2.text-xl.font-bold "Check your inbox"]
   [:p "We've sent a sign-in link to " [:span.font-bold (:email params)] "."]))


(defn send-email! [{:keys [biff.auth/email-validator
                           biff/send-email
                           params]
                    :as ctx}]
  (let [email (butil/normalize-email (:email params))
        url ""
        user-id (atom nil)]
    (cond
      (not (passed-recaptcha? ctx))
      {:success false :error "recaptcha"}

      (not (email-validator ctx email))
      {:success false :error "invalid-email"}

      (not (send-email ctx
             {:template :signin-link
              :to email
              :url url
              :user-exists false}))
      {:success false :error "send-failed"}

      :else
      {:success true :email email :user-id @user-id})))



;; ___________________________________ .

(defn foo [request]
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "foo response"})


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


(def new-routes
  [#_["/foo" 
      {:det foo}]
   ["/send-contact" 
    {:post 
     gs.pages.contact/handle-form-submission}]
   ["" 
    {:middleware [mid/wrap-redirect-signed-in]}
    ["/" 
     {:get gs.pages.index/home-page}]]
   ["/contact-confirmed"  
    {:get confirmation-page}]
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
    

(def plugin
  {:routes all-routes})

