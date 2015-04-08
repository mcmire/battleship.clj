(ns battleship.properties
  (:require [battleship.util :as util]))

;(def columns (util/character-range \a \j))
(def columns ["A" "B" "C" "D" "E" "F" "G" "H" "I" "J"])

(def rows (range 1 10))

(def ships
  [{:name :carrier :length 5}
   {:name :battleship :length 4}
   {:name :submarine :length 3}
   {:name :destroyer :length 3}
   {:name :patrol_boat :length 2}])

(def orientations [:horizontal :vertical])
