(ns battleship.random
  (:require [battleship.properties :as props]))

(defn available-starting-columns [ship]
  (take (- (count props/columns) (:length ship)) props/columns))

(comment
  (defn rows-for-start-of-location [ship]
    (take (- (length props/rows) (:length ship)) rows))

  (defn coords-for-start-of-location [ship board orientation]
    (case orientation
      :horizontal
        {:x (rand-nth columns-for-start-of-location)
         :y (rand-nth rows)}
      :vertical
        {:x (rand-nth columns)
         :y (rand-nth rows-for-start-of-location)}))

  (defn choose-location [ship board orientation]
    (loop
      (let [coords (coords-for-start-of-location)]
        (if (ship-located-at board coords)
          (recur)
          coords)))))

(defn choose-orientation [] (rand-nth props/orientations))
