(ns groundedsol.core
  (:require
    [airtable-clj.core :as airtable]))
    ;[clojure.java.io :as io]))
    ;[environ.core :refer [env]]))

;(defn copy-file [uri file]
;  (with-open [in (io/input-stream uri)
;              out (io/output-stream file)]
;    (io/copy in out)))

;(copy-file
;  "https://dl.airtable.com/.attachments/c800071442f6589160bf68ec4ef51cc9/af845f97/webcam-5292021102643PM.png"
;  "resources/images/myimage.jpg")

(def api-key "keyoyjKNMHK4XUP7h")
  ;(:airtable-api-key env))
(def base "app2480ik2bNPMyti")
  ;(:airtable-base-id env))

(def table "mytable")

(def sample-query
  (airtable/select
    {:api-key api-key
     :base base
     :table table
     :max-records 5}))

(def mytest "hello world")

(def my-airtable-data
  (-> sample-query
       :records
       first
       :fields
       (get "Notes")))
    ;(get "Attachments"))
    ;count)
    ;:attachments)


;(def just-one-record
;  (airtable/retrieve {:api-key api-key
;                      :base base
;                      :table table
;                      :record-id "rec123"}))
;
;(airtable/create {:api-key api-key
;                  :base base
;                  :table table
;                  :fields {"Foo" "boo"
;                           "Bing" "bong"}
;                  :typecast? false})

;
;(comment
;  (def data
;    #?(:cljs
;       (t/read (t/reader :json)
;              (rc/inline "unfufilled-items.json"))
;       :clj
;       (with-open [in (io/input-stream "src/unfufilled-items.json")]
;         (t/read (t/reader in :json))))))
