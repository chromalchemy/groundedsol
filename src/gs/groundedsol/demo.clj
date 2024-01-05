(ns gs.groundedsol.demo
    (:require
      [gs.components :as components]
      [com.biffweb :as biff :refer [q pprint]]
      [gs.groundedsol.middleware :as mid]
      [lambdaisland.hiccup :as hiccup]
      [gs.groundedsol.ui :as ui]
      [gs.hiccup :as bhiccup]))


#_"setTimeout(() => {
  document.getElementById('demo-form').submit();
}, '1000');"

(def callback-js-fn-str 
  "<script> 
    function myCallback() { 
   
      console.log('ran callback');
    };
   </script>")

(defn bar-form [v]
  (let [v (if v v "no value set")]
    [:div
     [::hiccup/unsafe-html
      callback-js-fn-str]
     (biff/form
       {:id "demo-form"
        ;; :hx-target "this"
        ;; :action "/demo/set-bar"
        :onsubmit "myCallback()"
      ;; :hx-post "/demo/set-bar"
        ;; :hx-swap "outerHTML"
        }
       
      
       [:label {:for "bar"} "Bar: "
        (pr-str v)]
       [:input#bar
        {:type "text"
         :name "bar"
         :value "bar"}]
       [:button
        {:type "submit"
        ;;  :data-callback "myCallback"
         }
        "Update"]
       [:div
        "This demonstrates updating a value with HTMX."])]))

(defn set-bar [{:keys [params] :as ctx}]
  (println "params")
  (pprint params)
  (println "ran set-bar")
  (bhiccup/render 
    (bar-form (:bar params))))


(defn set-foo [{:keys [params] :as ctx}]
  (println)
  (pprint params)
  (println "ran set-foo")
  {:status 303
   :headers {"location" "/demo"}})


(defn demo-page [{:keys [params] :as ctx}]
  (let [{:keys [foo]
         :or {foo "no value set"}} 
        params]
    #_(println (str "foo: " foo))
    (ui/page
     {}
     #_(biff/form
        {:action "/demo/set-foo"}
      
        [:label
         {:for "foo"} 
         "Foo: " (pr-str foo)]
        
      
        [:div.flex
       
         [:input#foo
          {:type "text"
           :name "foo"
           :value "foo"}]
       
         [:button
          {:type "submit"}
          "Update"]]
      
        [:div
         "This demonstrates updating a value with a plain old form."])
     
     #_[:hr]
     (bar-form (:bar params)))))
     


(def plugin
  {:routes ["/demo" 
            ["" {:get demo-page}]
            ["/set-foo" {:post set-foo}]
            ["/set-bar" {:post set-bar}]]})
   
