(ns battleship.random-test
  (:use expectations)
  (:require [battleship.random :as random]
             [battleship.util :as util]))

(expect :foo
        (with-redefs [rand-nth (constantly :foo)]
          (random/choose-orientation)))

(expect ["A" "B" "C" "D" "E"]
        (random/available-starting-columns {:length 5}))
