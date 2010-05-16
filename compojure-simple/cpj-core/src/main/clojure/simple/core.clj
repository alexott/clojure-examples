(ns simple.core
  (:use compojure.core)
  (:require [compojure.route :as route]))

(def r-prefix "/cpj")

(defroutes example
  (GET (str r-prefix "/") [] "<h1>Hello World Wide Web!</h1>")
  (GET (str r-prefix "/t/:id") [id]
       (str "<h1>Path with param: '" id "'</h1>"))
  (route/not-found "Page not found"))
