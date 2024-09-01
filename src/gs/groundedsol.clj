(ns gs.groundedsol
  (:require [com.biffweb :as biff]
            [gs.groundedsol.email :as email]
            [gs.groundedsol.app :as app]
            [gs.groundedsol.demo]
            [gs.groundedsol.home :as home]
            [gs.groundedsol.middleware :as mid]
            [gs.groundedsol.ui :as ui]
            #_[gs.groundedsol.worker :as worker]
            [gs.groundedsol.schema :as schema]
            [clojure.test :as test]
            [clojure.tools.logging :as log]
            [clojure.tools.namespace.repl :as tn-repl]
            [malli.core :as malc]
            [gs.hiccup :refer [export-hiccup]]
            [malli.registry :as malr]
            [nrepl.cmdline :as nrepl-cmd]
            [gs.build])
  (:gen-class))

(def modules
  [gs.groundedsol.demo/module
   #_app/module
   #_(biff/authentication-module {})
   home/module
   schema/module])

(def routes [["" {:middleware [mid/wrap-site-defaults]}
              (keep :routes modules)]
             ["" {:middleware [mid/wrap-api-defaults]}
              (keep :api-routes modules)]])

(def handler 
  (-> (biff/reitit-handler {:routes routes})
    mid/wrap-base-defaults))

(def static-pages (apply biff/safe-merge (map :static modules)))

(defn generate-assets! [ctx]
  (gs.build/write-page-css!)
  (export-hiccup static-pages "target/resources/public")
  (biff/delete-old-files {:dir "target/resources/public"
                          :exts [".html"]}))

(defn on-save [ctx]
  (biff/add-libs)
  (biff/eval-files! ctx)
  (generate-assets! ctx)
  (biff/catchall (require 'gs.example-test))
  (test/run-all-tests #"gs.groundedsol.*-test"))

(def malli-opts
  {:registry (malr/composite-registry
              malc/default-registry
              (apply biff/safe-merge
                     (keep :schema modules)))})

(def initial-system
  {:biff/modules #'modules
   :biff/send-email #'email/send-email
   :biff/handler #'handler
   :biff/malli-opts #'malli-opts
   :biff.beholder/on-save #'on-save
   :biff.middleware/on-error #'ui/on-error
   :biff.xtdb/tx-fns biff/tx-fns
   #_#_ :gs.groundedsol/chat-clients (atom #{})
   }
  )

(defonce system (atom {}))

(def components
  [biff/use-aero-config
   biff/use-xt
   biff/use-queues
   biff/use-tx-listener
   biff/use-htmx-refresh
   biff/use-jetty
   biff/use-chime
   biff/use-beholder])

(defn start []
  (let [new-system (reduce (fn [system component]
                             (log/info "starting:" (str component))
                             (component system))
                           initial-system
                           components)]
    (reset! system new-system)
    (generate-assets! new-system)
    (log/info "System started.")
    (log/info "Go to" (:biff/base-url new-system))
    new-system))

(defn -main []
  (let [{:keys [biff.nrepl/args]} (start)]
    (apply nrepl-cmd/-main args)))

(defn refresh []
  (doseq [f (:biff/stop @system)]
    (log/info "stopping:" (str f))
    (f))
  (tn-repl/refresh :after `start))
