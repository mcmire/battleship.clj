(defproject battleship "0.1.0-SNAPSHOT"
  :description "Battleship, in Clojure"
  :url "http://github.com/mcmire/battleship.clj"
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :profiles {:dev {:dependencies [[expectations "2.1.0"]]}}
  :plugins [[lein-expectations "0.0.8"]
            [lein-autoexpect "1.4.2"]
            [com.jakemccrary/lein-test-refresh "0.7.0"]]
  :main battleship.game
  :test-refresh {:notify-command ["terminal-notifier" "-title" "Tests" "-message"]})
