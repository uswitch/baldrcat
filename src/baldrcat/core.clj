(ns baldrcat.core
  (:require [clojure.tools.cli :refer (parse-opts)]
            [baldr.core :refer (baldr-seq)]
            [aws.sdk.s3 :refer (get-object bucket-exists? object-exists?)])
  (:import [java.util.zip GZIPInputStream])
  (:gen-class))

(def cli-options
  ;; An option with a required argument
  [["-a" "--access-key ACCESS_KEY" "AWS Access Key" :validate [string?] :default (System/getenv "AWS_ACCESS_KEY")]
   ["-s" "--secret-key SECRET_KEY" "AWS Secret Key" :validate [string?] :default (System/getenv "AWS_SECRET_KEY")]
   ["-h" "--help"]])

(defn parse-s3-object-url [s]
  (let [[_ bucket object]   (re-matches #"s3[n]{0,1}\://([\d_\w-]+)\/(.*)" s)]
    {:bucket bucket :object object}))

(defn -main
  [& args]
  (let [{:keys [options summary arguments]} (parse-opts args cli-options)]
    (when (or (:help options) (empty? options))
      (println "Usage: baldrcat OPTIONS urls ...")
      (println summary)
      (System/exit 0))
    (let [creds                   (assoc (select-keys options [:access-key :secret-key])
                                    :endpoint "s3-eu-west-1.amazonaws.com")]
      (let [s3-objs (map parse-s3-object-url arguments)]
        (doseq [{:keys [bucket object]} s3-objs]
          (let [obj      (get-object creds bucket object)
                instream (GZIPInputStream. (:content obj))
                records  (baldr-seq instream)]
            (println "Object:" obj)
            (doseq [r records]
              (println (String. r)))))))))
