(require
  '[clojure.java.shell :refer [sh]])

;(sh "ls" "foo")
(sh "browser-sync start --server \"build\" --files \"build/*.html, build/css/*.css\"")