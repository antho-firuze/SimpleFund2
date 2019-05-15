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

open class tPorfolioDashboard : RealmObject() {
    var id: Int = 0
    var navperunit: Double = 0.0
    var ytd: Double = 0.0
}

class tPortfolioDashboardManager {
    var realm: Realm = Realm.getDefaultInstance()

    fun find(): tPorfolioDashboard? = realm.where<tPorfolioDashboard>().findFirst()

    fun findAll(): List<tPorfolioDashboard> = realm.where<tPorfolioDashboard>().findAll()

    fun insertFromJson(j: JSONObject) =
        realm.executeTransaction { realm -> realm.createObjectFromJson(tPorfolioDashboard::class.java, j) }

    fun insertFromJsonList(j: JSONArray) {
        try {
            // Open a transaction to store items into the realm
            realm.beginTransaction()
            realm.createAllFromJson(tPorfolioDashboard::class.java, j)
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
                val results = realm.where(tPorfolioDashboard::class.java).findAll().deleteAllFromRealm()
            }
        } finally {
            realm?.close()
        }
    }

}