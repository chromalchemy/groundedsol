(ns groundedsol.pages.services
  (:require
    [hickory.core :as h]
    [cybermonday.core :as cm]
    [groundedsol.content :as c]
    [groundedsol.common :as common]
    [groundedsol.airtable :as at]
    [groundedsol.util :as u]
    [rum.core :as rum]
    [com.rpl.specter :refer [select ALL FIRST setval transform NONE]]))


(def content-blocks
  [])


(common/write-page
  :services
  content-blocks)