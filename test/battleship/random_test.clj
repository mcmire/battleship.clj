(ns battleship.random-test
  (:use expectations)
  (:require [battleship.random :as random]
             [battleship.util :as util]))

(expect :foo
        (with-redefs [rand-nth (constantly :foo)]
          (random/choose-orientation)))

(expect (util/character-range  "A" "G")
        (random/available-starting-columns {:length 5}))
