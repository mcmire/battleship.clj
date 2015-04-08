(ns battleship.util
  (:require [clojure.string :refer [upper-case]]))

(defn upper-case-character [char]
  (upper-case (str char)))

(defn character-range [start end]
  (map
    upper-case-character
    (range (int start) (inc (int end)))))
