(ns simple.webapp
  (:use ring.util.servlet
        compojure.core
        compojure.response)
  (:gen-class :extends javax.servlet.http.HttpServlet))

;; http://en.wikibooks.org/wiki/Compojure/Getting_Started

(defroutes my-routes
  (GET (str "/test/:id") [id & updated-attrs]
       (render (str "Called with param id='" id "'. other params=" updated-attrs)))
  (GET "/*" []
       (render "Without params"))
  )

(defservice my-routes)
