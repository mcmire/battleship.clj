(ns battleship.properties)


(def columns (character-range \a \j))

(def rows (range 1 10))

(def ships
  [{:name :carrier :length 5}
   {:name :battleship :length 4}
   {:name :submarine :length 3}
   {:name :destroyer :length 3}
   {:name :patrol_boat :length 2}])

(def orientations [:horizontal :vertical])
