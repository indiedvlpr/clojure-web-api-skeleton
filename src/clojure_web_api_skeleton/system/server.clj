(ns clojure-web-api-skeleton.system.server
  (:require [ring.adapter.jetty :as jetty]
            [com.stuartsierra.component :as component]))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello Clojure, Hello Ring!"})

(defn start-server [port]
  "Helper function to start the server when the component's start function is called"
  (let [server (jetty/run-jetty handler {:port (Integer. port)})]
    server))

(defn stop-server [server]
  "Helper function to stop the server when the component's stop function is called"
  (when server
    (dissoc server :server)))

(defrecord WebServer [port]
  component/Lifecycle
  (start [this]
    (assoc this :server (start-server port)))
  (stop [this]
    (stop-server (:server this))))

(defn web-server [port]
  "Map web server to component"
  (map->WebServer {:port port}))