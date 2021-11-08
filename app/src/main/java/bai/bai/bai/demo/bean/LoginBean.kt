package bai.bai.bai.demo.bean

class LoginBean {
    var id: String? = ""
    var merchant_name: String? = ""
    var merchant_id: String? = ""
    var merchant_address: String? = ""
    var merchant_zipcode: Int? = -1
    var tid: String? = ""
    var mid: String? = ""
    var merchant_mcc: String? = ""
    var is_first_login: Boolean = false
    var is_key_exchange_completed: Boolean = false
    var aggregator: String? = ""
    var txn_sets: TransactionSetBean? = null
    var bank_profile: BankProfileBean? = null

    override fun toString(): String {
        return "LoginBean(id=$id, merchant_name=$merchant_name, merchant_id=$merchant_id, merchant_address=$merchant_address, merchant_zipcode=$merchant_zipcode, tid=$tid, mid=$mid, merchant_mcc=$merchant_mcc, is_first_login=$is_first_login, is_key_exchange_completed=$is_key_exchange_completed, aggregator=$aggregator, txn_sets=$txn_sets, bank_profile=$bank_profile)"
    }

}