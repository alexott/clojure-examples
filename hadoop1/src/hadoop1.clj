(ns hadoop1
    (:require [clojure-hadoop.wrap :as wrap]
              [clojure-hadoop.defjob :as defjob]
              [clojure-hadoop.imports :as imp])
    (:use clojure.contrib.seq-utils)
    (:require [clojure.contrib.str-utils2 :as str2])
    (:import (java.util StringTokenizer)
))

(imp/import-io)
(imp/import-mapred)

(def delimiters "0123456789[ \t\n\r!\"#$%&'()*+,./:;<=>?@\\^`{|}~-]+")

(defn gen-n-grams [#^String s #^Integer n]
  (when (> (.length s) 0)
      (let [str (str " " s (String. ) (str2/repeat " " (- n 1)))]
        (reduce (fn [val elem]
                  (conj val (.substring str elem (+ elem n))))
                []
                (range 0 (+ 1 (.length s)))))))

(defn my-map [key #^String value]
  (map (fn [token] [token 1])
       (flatten (map #(gen-n-grams %1 3)
                     (enumeration-seq (StringTokenizer. value delimiters))))))

(defn my-reduce [key values-fn]
  [[key (reduce + (values-fn))]])

(defn string-long-writer [#^OutputCollector output
                          #^String key value]
  (.collect output (Text. key) (LongWritable. value)))

(defn string-long-reduce-reader [#^Text key wvalues]
  [(.toString key)
   (fn [] (map (fn [#^LongWritable v] (.get v))
               (iterator-seq wvalues)))])

(defjob/defjob job
  :map my-map
  :map-reader wrap/int-string-map-reader
  :map-writer string-long-writer
  :reduce my-reduce
  :reduce-reader string-long-reduce-reader
  :reduce-writer string-long-writer
  :output-key Text
  :output-value LongWritable
  :input-format :text
  :output-format :text
  :compress-output false)