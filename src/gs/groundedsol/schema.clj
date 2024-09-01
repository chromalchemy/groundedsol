(ns gs.groundedsol.schema)

(def schema
  {:user/id :uuid
   :user [:map {:closed true}
          [:xt/id                     :user/id]
          [:user/email                :string]
          [:user/joined-at            inst?]
          #_ [:user/foo {:optional true} :string]
          #_ [:user/bar {:optional true} :string]]

  ;;  :msg/id :uuid
  ;;  :msg [:map {:closed true}
  ;;        [:xt/id       :msg/id]
  ;;        [:msg/user    :user/id]
  ;;        [:msg/text    :string]
  ;;        [:msg/sent-at inst?]]
   })

(def module
  {:schema schema})
