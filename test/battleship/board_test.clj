(ns battleship.board-test
  (:use expectations)
  (:require [battleship.board :as board]
            [battleship.random :as random]))

(comment
  (expect {:width 10
           :height 10
           :ships [{:name "carrier"
                    :positions [{:x "B" :y 9}
                                {:x "C" :y 9}
                                {:x "D" :y 9}
                                {:x "E" :y 9}
                                {:x "F" :y 9}]}
                   {:name "battleship"
                    :positions [{:x "H" :y 2}
                                {:x "H" :y 3}
                                {:x "H" :y 4}
                                {:x "H" :y 5}]}
                   {:name "submarine"
                    :positions [{:x "I" :y 3}
                                {:x "I" :y 4}
                                {:x "I" :y 5}
                                {:x "I" :y 6}]}
                   {:name "destroyer"
                    :positions [{:x "A" :y 7}
                                {:x "B" :y 7}
                                {:x "C" :y 7}]}
                   {:name "patrol boat"
                    :positions [{:x "F" :y 1}
                                {:x "G" :y 2}]}]}
          (board/generate)))

(comment
  (with-redefs [random/choose-location (fn [] {:x "B" :y 9})
                random/choose-orientation (fn [] :horizontal)]
    (expect {:width 10
             :height 10
             :ships [{:name "carrier"
                      :positions [{:x "B" :y 9}
                                  {:x "C" :y 9}
                                  {:x "D" :y 9}
                                  {:x "E" :y 9}
                                  {:x "F" :y 9}]}]}
            (place-ship {:width 10
                         :height 10
                         :ships []}
                        {:name "carrier"
                         :length 5}))))
