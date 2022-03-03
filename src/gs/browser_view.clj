(ns gs.browser-view
  (:require
    [clojure.java.shell :as sh]
    [donut.system :as ds]
    [etaoin.api :as e]
    [nextjournal.beholder :as beholder]
    [lambdaisland.hiccup :as h]
    [gs.common]
    [gs.build]))
;[vlaaad.reveal :as reveal]
;[vlaaad.reveal.ext :as reveal-ext]

;todo: run etain reload directly from repl, instead of watcher fn?
;todo: assume file name is same as var, to simplify
;todo: figure out more ergonomic way to reload hiccup, without having to explictly wrap in do block
;  maybe use watch -> hiccup defined in atom)

;(sh/sh "ls" "-la")


(def project-path
  "/Users/ryan/dev/groundedsol/")

(defn file-url [filename]
  (str "file://" project-path gs.build/build-path filename ".html"))

(comment
  (file-url "myname"))

(def default-browser-params
  {:download-dir "./test"})

(defn new-browser! []
  (e/chrome default-browser-params))

(defn view-html! [driver filename]
  (e/go driver (file-url filename)))

(defn reload-browser-callback! [driver reload-response-map]
  (e/refresh driver)
  (println "Reload HTML"))
;(println reload-response-map)

(defn live-reload-html [driver]
  (beholder/watch
    (partial reload-browser-callback! driver)
    gs.build/build-path))

(defn build-on-save-src []
  (beholder/watch
    (fn [callback-map]
      (gs.build/build-site!))
    "src"))

(def web-repl-system
  {::ds/defs
   {:services
    {:webview
     {:start
      (fn [{:keys [filename]} _ _]
        (def browser-window (new-browser!))
        (view-html! browser-window filename)
        (println "Started Browser View")
        (def live-reload-instance
          (live-reload-html browser-window))
        (println "Started HTML Live Reload")
        (def build-on-save-instance
          (build-on-save-src))
        (println "Started Rebuild on Save src"))
      :stop
      (fn [_ instance _]
        (beholder/stop live-reload-instance)
        (println "Stoped Live Reload")
        (e/close-window browser-window)
        (println "Closed Browser View")
        (beholder/stop build-on-save-instance)
        (println "Closed Rebuild on Save src"))
      :conf {:filename "index"}}}}})

(defn start-live-browser-view! [filename]
  (-> web-repl-system
    (assoc-in
      [::ds/defs :services :webview :conf :filename] (name filename))
    (ds/signal :start)))

(defn stop-system! [s]
  (ds/signal s :stop))

;--------------------

;; ala carte watcher + builds   dsdfdfddddfdf sdfdfdf
(comment
  ;; watch function needs to take a map
  (def watcher
    (beholder/watch
      (fn [x]
        (build-site))
      "src"))
  (beholder/stop watcher))

(comment
  (def running-system
    (start-live-browser-view! "index"))
  (stop-system! running-system))

