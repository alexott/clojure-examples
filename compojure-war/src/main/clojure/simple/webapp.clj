(ns simple.webapp
  (:use ring.util.servlet
        compojure.core
        compojure.response)
  (:gen-class :extends javax.servlet.http.HttpServlet))

;; http://en.wikibooks.org/wiki/Compojure/Getting_Started

(defroutes simple
  (GET "/" []
    (render "Without params"))
  (GET (str "/:id") [id & updated-attrs]
       (render (str "Called with param id='" id "'. other params=" updated-attrs)))
  )

(defservice simple)
