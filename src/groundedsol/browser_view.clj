(ns groundedsol.browser-view
  (:require [clojure.java.shell :as sh]
            [donut.system :as ds]
            [etaoin.api :as e]
            [nextjournal.beholder :as beholder]
            [lambdaisland.hiccup :as h]))
;[vlaaad.reveal :as reveal]
;[vlaaad.reveal.ext :as reveal-ext]

;todo: run etain reload directly from repl, instead of watcher fn?
;todo: assume file name is same as var, to simplify
;todo: figure out more ergonomic way to reload hiccup, without having to explictly wrap in do block
;  maybe use watch -> hiccup defined in atom)

;(sh/sh "ls" "-la")

(def html-render-path-abs
  "/Users/ryan/dev/gbo/html-render/")

(defn file-url [filename]
  (str "file://" html-render-path-abs filename ".html"))

(def default-browser-params
  {:download-dir "./test"})

(defn new-browser! []
  (e/chrome default-browser-params))

(defn view-html [driver filename]
  (e/go driver (file-url filename)))

(defn load-html-cb [driver filename reload-response-map]
  (view-html driver filename)
  (println "Reload HTML"))
;(println reload-response-map)

;(def myfile "night-hunt")

(comment)

(defn watch-html-rendered-files [driver filename]
  (beholder/watch
    (partial load-html-cb driver filename)
    "html-render/"))

(def web-repl-system
  {::ds/defs
   {:services
    {:webview
     {:start
      (fn [{:keys [filename]} _ _]
        (def browser-window (new-browser!))
        (view-html browser-window filename)
        (println "Started browser-window")
        (def file-watcher
          (watch-html-rendered-files browser-window filename))
        (println "Started file-watcher"))
      :stop
      (fn [_ instance _]
        (e/close-window browser-window)
        (println "Closed browser-window")
        (beholder/stop file-watcher)
        (println "Stoped file-watcher"))
      :conf {:filename "default-file"}}}}})

(defn start-live-browser-view! [filename]
  (-> web-repl-system
    (assoc-in
      [::ds/defs :services :webview :conf :filename] (name filename))
    (ds/signal :start)))

(defn stop-system! [s]
  (ds/signal s :stop))
;--------------------------------

(defn write-html-to-file [filename html-string]
  (spit (str "html-render/" (name filename) ".html")
    html-string))

(defn write-hiccup-to-html-file [filename file-hiccup]
  (write-html-to-file filename (h/render-html (h/html file-hiccup))))

(comment
  (def running-system
    (start-live-browser-view! "night-hunt"))
  (stop-system! running-system))
