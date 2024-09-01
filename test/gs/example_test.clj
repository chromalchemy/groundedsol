(ns gs.example-test
  (:require [clojure.test :refer [deftest is]]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

(deftest example-test
  (is (= 4 (+ 2 2))))
