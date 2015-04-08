(ns battleship.core)

; board is 10x10
;
; carrier is 5 pieces
; battleship is 4 pieces
; sub is 3
; destroyer is 3
; patrol boat is 2
;
; set up two sides
; place ships in random positions on both sides
; a ship sinks if all of its positions are hit
; the game ends if all boats are sunk

; http://dev.clojure.org/jira/secure/attachment/13134/CLJ-1468-deep-merge-01.patch
(defn deep-merge
  "Like merge, but merges maps recursively."
  {:added "1.7"}
  [& maps]
  (if (every? map? maps)
    (apply merge-with deep-merge maps)
    (last maps)))

(defn deep-merge-with
  "Like merge-with, but merges maps recursively, applying the given fn
  only when there's a non-map at a particular level."
  {:added "1.7"}
  [f & maps]
  (apply
    (fn m [& maps]
      (if (every? map? maps)
        (apply merge-with m maps)
        (apply f maps)))
    maps))

(def num-players 2)

(def columns (range 1 10))

(def rows (range "A" "J"))

(def ships
  [{:name :carrier :length 5}
   {:name :battleship :length 4}
   {:name :submarine :length 3}
   {:name :destroyer :length 3}
   {:name :patrol_boat :length 2}])

(def orientations [:horizontal :vertical])

(defn choose-orientation (rand-nth orientations))

(def columns-for-start-of-location [ship]
  (take (- (length columns) (:length ship)) columns))

(def rows-for-start-of-location [ship]
  (take (- (length rows) (:length ship)) rows))

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
        coords))))

(defn with-placed-ship [ship board]
  (choose-location ship board (choose-orientation)))

(defn with-placed-ships [board]
  (let [ships (:ships board)]
    (with-placed-ship (:patrol-boat ships)
      (with-placed-ship (:destroyer ships)
        (with-placed-ship (:submarine ships)
          (with-placed-ship (:battleship ships)
            (with-placed-ship (:carrier ships) board)))))))

(defn make-board (with-placed-ships {:width 10 :height 10 :ships []}))

(def boards (reduce #(assoc %1 %2 (make-board)) {} [0 1]))

(def game {:boards boards :turn 0})

(defn build-result [coords board]
  {:coords coords :board board})

(defn hit-at [coords board ship]
  (merge (build-result coords board) {:hit true :ship ship}))

(defn miss-at [coords board]
  (merge (build-result coords board) {:hit false}))

(defn ship-located-at [board coords]
  (let [ships (:ships board)]
    (filter #(eq (:coords %1) coords) ships)))

(defn fire [board coords]
  (if-let [ship (ship-located-at board coords)]
    (hit-at coords board ship)
    (miss-at coords board))

; this needs to handle unknown input
(defn parse-input [input]
  (split input))

(defn format-coords [coords]
  (str ((:x coords) columns) ((:y coords) rows)))

(defn hit-or-miss-text [result]
  (case (:hit result)
    true (str "HIT at" (format-coords (:coords result)))
    false "That's a MISS!"))

(defn ship-sunk? [ship]
  (all? #(:hit %1) (:coords ship)))

(defn ship-sunk-text [result]
  (let [ship (:ship result)]
    (if (ship-sunk? ship)
      (str "The " (:name ship) " has been sunk!")
      "")))

(defn winner [game]
  (let [ships (get-in game [:boards (:turn game) :ships])]
    (all? ship-sunk? ships)

(defn game-over-text [game]
  (if (game-over game) "The game is over! The winner is: " ))

; this needs to pass the game to the game-over-text
(def result-interpretations
  [hit-or-miss-text ship-sunk-text game-over-text])

(defn result-interpretation-parts [game result]
  [(hit-or-miss-text result)
   (ship-sunk-text result)
   (game-over-text game)])

(defn interpret-result [game result]
  (join "\n" (remove blank? (result-interpretation-parts game result))))

(defn valid-input [input]
  (not (nil? (re-find #"^[A-La-l][1-10]$" input))))

(defn prompt-for-coords
  (println "Please enter a coordinate:")
  (read-line))

(def read-coords [] (read-coords false))

(defn read-coords
  (let [input (prompt-for-coords)]
    (if (valid-input input)
      input
      (do
        (println "Invalid coordinates. Try something like: B2")
        (recur true)))))

(defn game-with-flipped-turn [game turn]
  (merge game {:turn (bit-flip turn 0)}))

(defn update-game [game turn result]
  (let [boards (:boards game)
        board (turn board)
        ships (:ships board)
        ship-index (.indexOf ships ship)
        hit (:hit result)]
    (assoc-in game [:boards turn :ships ship-index :hit] hit))

(defn play [game]
  (let [turn (:turn game)
        board (turn (:boards game))]
    (display-game-state game)
    (let [result (fire board (read-coords))
          game (update-game game turn result)]
      (println (interpret-result game result))
      (if-not (game-over game)
        (recur (game-with-flipped-turn game turn))))))
