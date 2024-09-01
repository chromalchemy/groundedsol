(ns gs.example-test
  (:require 
   [cheshire.core :as cheshire]
   [clojure.string :as str]
   [clojure.test :refer [deftest is]]
   [com.biffweb :as biff :refer [test-xtdb-node]]
   [gs.groundedsol :as main]
   [gs.groundedsol :as home]
   [malli.generator :as mg]
   #_[rum.core :as rum]
   [xtdb.api :as xt]))

(deftest example-test
  (is (= 4 (+ 2 2))))

(defn get-context [node]
  {:biff.xtdb/node  node
   :biff/db         (xt/db node)
   :biff/malli-opts #'main/malli-opts})

#_(deftest send-message-test
  (with-open [node (test-xtdb-node [])]
    (let [message (mg/generate :string)
          user    (mg/generate :user main/malli-opts)
          ctx     (assoc (get-context node) :session {:uid (:xt/id user)})
          _       (app/send-message ctx {:text (cheshire/generate-string {:text message})})
          db      (xt/db node) ; get a fresh db value so it contains any transactions
                               ; that send-message submitted.
          doc     (biff/lookup db :msg/text message)]
      (is (some? doc))
      (is (= (:msg/user doc) (:xt/id user))))))