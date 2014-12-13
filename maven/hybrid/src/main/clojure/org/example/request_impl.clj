(ns org.example.request-impl
  (:use compojure.core
        [migae.memcache :as mc]
        [ring.middleware.params :only [wrap-params]]
        [ring.middleware.file-info :only [wrap-file-info]])
  (:refer-clojure :exclude [contains? get])
  (:require [compojure.route :as route]))

(defroutes request-routes
  (GET "/request/hello/:you" [you]
    (str (format "<h1>Howdy %s from org.example.request servlet!</h1>" you)
         "\n\n<a href='/'>home</a>"))
  (GET "/request/goodbye/:you" [you]
    (str (format "<h1>Adios %s from org.example.request servlet!</h1>" you)
         "\n\n<a href='/'>home</a>"))
  (route/not-found "<h1>Page not found</h1>"))

(def request-handler
  (-> #'request-routes
      wrap-params
      wrap-file-info
      ))

