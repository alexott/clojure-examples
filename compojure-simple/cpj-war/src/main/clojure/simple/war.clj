(ns simple.war
  (:use ring.util.servlet)
  (:require [simple.core :as core])
  (:gen-class :extends javax.servlet.http.HttpServlet))

(defservice core/example)

