package com.example.simplefund2.model

import io.realm.Realm
import io.realm.RealmObject
import io.realm.kotlin.where
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.*

/**
 * Created by antho.firuze@gmail.com on 14/05/2019.
 */

open class tPorfolio : RealmObject() {
    var id: Int = 0
    var name: String = ""
    var ccy: String = ""
    var asset_type: String = ""
    var portfolio_type: String = ""
    var risk_profile: String = ""
    var inception_date: Date? = null
    var timestamp: Date? = null
    var custodian_bank: String = ""
    var status: String = ""
}

class tPortfolioManager {
    var realm: Realm = Realm.getDefaultInstance()

    fun find(): tPorfolio? = realm.where<tPorfolio>().findFirst()

    fun findAll(): List<tPorfolio> = realm.where<tPorfolio>().findAll()

    fun insertFromJson(j: JSONObject) =
        realm.executeTransaction { realm -> realm.createObjectFromJson(tPorfolio::class.java, j) }

    fun insertFromJsonList(j: JSONArray) {
        try {
            // Open a transaction to store items into the realm
            realm.beginTransaction()
            realm.createAllFromJson(tPorfolio::class.java, j)
            realm.commitTransaction()
        } catch (e: IOException) {
            // Remember to cancel the transaction if anything goes wrong.
            if (realm.isInTransaction) {
                realm.cancelTransaction()
            }
            throw RuntimeException(e)
        } finally {
            realm.close()
        }
    }

    fun deleteAll() {
        try {
            realm.executeTransaction { realm ->
                val results = realm.where(tPorfolio::class.java).findAll().deleteAllFromRealm()
            }
        } finally {
            realm?.close()
        }
    }

}