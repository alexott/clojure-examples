(ns simple.main
  (:require [simple.core :as core])
  (:use ring.adapter.jetty)
  (:gen-class :main true)
  )

(defn -main [& args]
  (run-jetty core/example {:port 8080}))
