;; Copyright 2013 the original author or authors.
;;
;; Licensed under the Apache License, Version 2.0 (the "License");
;; you may not use this file except in compliance with the License.
;; You may obtain a copy of the License at
;;
;;      http://www.apache.org/licenses/LICENSE-2.0
;;
;; Unless required by applicable law or agreed to in writing, software
;; distributed under the License is distributed on an "AS IS" BASIS,
;; WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
;; See the License for the specific language governing permissions and
;; limitations under the License.

(ns example.ssl.server
  (:require [vertx.net :as net]
            [vertx.stream :as stream]))


(-> (net/server {:SSL true
                 :key-store-path "server-keystore.jks"
                 :key-store-password "wibble"})
    (net/on-connect
     (fn [sock]
       (stream/on-data sock
                       (partial stream/write sock))))
    (net/listen 1234 "localhost"
      (fn [ex _]
        (if ex
          (println ex)
          (println "Started Net server with SSL on localhost:1234")))))
