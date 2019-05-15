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

open class tMarketUpdate : RealmObject() {
    var id: String = UUID.randomUUID().toString()
    var mu_title: String = ""
    var mu_date: Date? = null
    var created_by: String = ""
    var mu_body: String = ""
}

class tMarketUpdateManager {
    var realm: Realm = Realm.getDefaultInstance()

    fun find(): tMarketUpdate? = realm.where<tMarketUpdate>().findFirst()

    fun findAll(): List<tMarketUpdate> = realm.where<tMarketUpdate>().findAll()

    fun insertFromJson(j: JSONObject) =
        realm.executeTransaction { realm -> realm.createObjectFromJson(tMarketUpdate::class.java, j) }

    fun insertFromJsonList(j: JSONArray) {
        try {
            // Open a transaction to store items into the realm
            realm.beginTransaction()
            realm.createAllFromJson(tMarketUpdate::class.java, j)
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
                val results = realm.where(tMarketUpdate::class.java).findAll().deleteAllFromRealm()
            }
        } finally {
            realm?.close()
        }
    }

}