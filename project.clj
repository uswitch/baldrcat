(defproject baldrcat "0.1.0-SNAPSHOT"
  :description ""
  :url "http://github.com/uswitch/baldrcat"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [baldr "0.1.1"]
                 [clj-aws-s3 "0.3.9"]
                 [org.clojure/tools.cli "0.3.1"]]
  :main ^:skip-aot baldrcat.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :jvm-opts ["-Xmx2G"])
