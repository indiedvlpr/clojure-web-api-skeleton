(ns clojure-web-api-skeleton.core
  (:require [clojure-web-api-skeleton.system.server :as server]
            [com.stuartsierra.component :as component]))

(defn api [port]
  "Set up API system map"
  (component/system-map :web-server (server/web-server port)))

(defn -main [port]
  (-> (component/start (api port))))
