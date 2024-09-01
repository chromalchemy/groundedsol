(ns gs.groundedsol.middleware
  (:require [com.biffweb :as biff]
            [muuntaja.middleware :as muuntaja]
            [ring.middleware.anti-forgery :as csrf]
            [ring.middleware.defaults :as rd]
            [lambdaisland.hiccup :as hiccup]
            [ring.util.response :as resp]
            [ring.middleware.head :as head]))

(defn wrap-redirect-signed-in [handler]
  (fn [{:keys [session] :as ctx}]
    (if (some? (:uid session))
      {:status 303
       :headers {"location" "/app"}}
      (handler ctx))))

(defn wrap-signed-in [handler]
  (fn [{:keys [session] :as ctx}]
    (if (some? (:uid session))
      (handler ctx)
      {:status 303
       :headers {"location" "/signin?error=not-signed-in"}})))

;; Stick this function somewhere in your middleware stack below if you want to
;; inspect what things look like before/after certain middleware fns run.
(defn wrap-debug [handler]
  (fn [ctx]
    (let [response (handler ctx)]
      (println "REQUEST")
      (biff/pprint ctx)
      (def ctx* ctx)
      (println "RESPONSE")
      (biff/pprint response)
      (def response* response)
      response)))

(comment
  (hiccup/render [:div [:span]])
  )

(defn wrap-render-hiccup [handler]
  "If handler returns a vector, pass it to hiccup/render, and return a 200 response."
  (fn [ctx]
    (let [response (handler ctx)]
      (if (vector? response)
        {:status 200
         :headers {"content-type" "text/html"}
         :body (hiccup/render response)}
        response))))

(defn checksum [s]
  (let [crc (new java.util.zip.CRC32)]
    (.update crc (.getBytes s))
    (Long/toHexString (.getValue crc))))

;; for live.js
(defn wrap-etag [handler]
  (fn [ctx]
    (let [{:keys [body]:as response} 
          (handler ctx)]
      (resp/update-header
        response
        "ETag"
        (fn [etag]
          (or etag
            (when (string? body)
              (checksum body))))))))


(defn wrap-site-defaults [handler]
  (-> handler
      wrap-render-hiccup
      biff/wrap-anti-forgery-websockets
      csrf/wrap-anti-forgery
      biff/wrap-session
      muuntaja/wrap-params
      muuntaja/wrap-format
      (rd/wrap-defaults (-> rd/site-defaults
                            (assoc-in [:security :anti-forgery] false)
                            (assoc-in [:responses :absolute-redirects] true)
                            (assoc :session false)
                            (assoc :static false)))))

(defn wrap-api-defaults [handler]
  (-> handler
      muuntaja/wrap-params
      muuntaja/wrap-format
      (rd/wrap-defaults rd/api-defaults)))

(defn wrap-base-defaults [handler]
  (-> handler
    biff/wrap-https-scheme
    biff/wrap-resource
    biff/wrap-internal-error
    biff/wrap-ssl
    biff/wrap-log-requests
    #_wrap-etag ;; for live.js
    head/wrap-head
    ))
