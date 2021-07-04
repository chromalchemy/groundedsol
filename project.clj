(defproject groundedsol "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  ;:dependencies [[org.clojure/clojure "1.10.1"]]
  :repl-options {:init-ns groundedsol.core}
  :plugins [[lein-garden "0.3.0"]]
  :main groundedsol.build
  :source-paths ["src"]
  :garden {:builds [{:id           "groundedsol"
                     :source-paths ["src"]
                     :stylesheet   groundedsol.css.core/styles
                     :compiler     {:output-to     "resources/css/groundedsol.css"
                                    :pretty-print? true}}]}

  :clean-targets ^{:protect false} ["resources/js" "target""resources/css"]
  :profiles {:dev {:prep-tasks [["garden" "once"]]}}
  :dependencies [;[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.742"]
                 [thheller/shadow-cljs "2.8.109"]
                 [bidi "2.1.6"]
                 [binaryage/devtools "0.9.10"]
                 [reagent "0.10.0"]
                 [venantius/accountant "0.2.5"]
                 [orgpad/volcano "0.1.2"]

                 [garden "1.3.9"]


                 ;[girouette "0.0.3"]
                 [com.rpl/specter "1.1.3"]
                 [markdown-clj "1.10.5"]
                 [hickory "0.7.1"]
                 [airtable-clj "0.1.1-SNAPSHOT"]
                 [com.kiranshila/cybermonday "0.2.0"]])

  ;               [clj-http "3.8.0"]
  ;               [cheshire "5.8.0"]
  ;               [environ "1.1.0"]]
  ;:profiles {:dev {:dependencies [[com.squareup.okhttp3/mockwebserver "3.10.0"]]
  ;                 :plugins [[lein-cljfmt "0.5.7"]]}})









