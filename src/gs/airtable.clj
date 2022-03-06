(ns gs.airtable
  (:require
  ;  ;[gs.core]
  ;  ;[markdown.core :as md]
    [cybermonday.core :as cm]
    [airtable-clj.core :as airtable]))
  ;  [gs.mycontent]
  ;  [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]))

(comment)

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

(def my-airtable-data
  (-> sample-query
      :records
      first
      :fields
      (get "Notes")
      cm/parse-md
      :body))
      ;cm/md-to-ir))
      ;:body))

    ;md/md-to-html-string))

;(def my-airtable-data
;  (-> sample-query
;      :records
;      first
;      :fields
;      (get "Notes")))
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


;-------


;(into [] (reverse [  "Hello World" :h1]))
;(setval [FIRST FIRST]
;  :h2
;  (map hickory/as-hiccup
;       (hickory/parse-fragment
;         (gs.util/my-airtable-data)))))
;---------------------------
(comment
  [:div {:dangerouslySetInnerHTML {:__html myvar}}])



;markdown parse test with hickory + specter
(comment
  (def myvar
    (str (gs.util/my-airtable-data))))

;markdown parse test with gonday
;(def md-hiccup
;  (cybermonday.core/parse-md
