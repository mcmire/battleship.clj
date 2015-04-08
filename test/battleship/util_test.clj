(ns battleship.util-test
  (:use expectations)
  (:require [battleship.util :as util]))

(expect "D" (util/upper-case-character 100))

(expect ["A" "B" "C"] (util/character-range \a \c))
