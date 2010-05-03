(ns simple.webapp
  (:use ring.util.servlet
        compojure.core
        compojure.response)
  (:gen-class :extends javax.servlet.http.HttpServlet))

;; http://en.wikibooks.org/wiki/Compojure/Getting_Started

(def r-prefix "/simple.webapp")

(defroutes my-routes
  (GET (str r-prefix "/test/:id") [id & other-attrs]
       (render (apply str "id=" id ", attrs=" other-attrs))
    )
  (GET (str r-prefix "/*") [& updated-attrs]
       (render (apply str "params= " updated-attrs)))
  )

(defservice my-routes)
