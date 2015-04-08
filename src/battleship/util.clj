(ns battleship.util
  (:require [clojure.string :refer [upper-case]]))

(defn upper-case-character [char-value]
  (upper-case (str (char char-value))))

(defn character-range [start-char end-char]
  (map
    upper-case-character
    (range (int start-char) (inc (int end-char)))))
