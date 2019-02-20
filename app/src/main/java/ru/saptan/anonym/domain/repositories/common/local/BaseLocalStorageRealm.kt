package ru.saptan.anonym.domain.repositories.common.local

import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults

abstract class BaseLocalStorageRealm {
    protected var tag: String = this.javaClass.simpleName

    fun <T : RealmModel> saveChanges(realmObject: T, realm: Realm): T {
        realm.beginTransaction()
        val result = realm.copyFromRealm(realm.copyToRealmOrUpdate(realmObject))
        realm.commitTransaction()
        return result
    }


    fun <T : RealmModel> saveChanges(realmObjects: List<T>, realm: Realm): List<T> {
        realm.beginTransaction()
        val result = realm.copyFromRealm(realm.copyToRealmOrUpdate(realmObjects))
        realm.commitTransaction()
        return result
    }

    fun <T : RealmModel> delete(realmClass: Class<T>, realm: Realm) {
        realm.beginTransaction()
        realm.delete(realmClass)
        realm.commitTransaction()
    }

    fun <T : RealmModel> delete(realmObjects: RealmResults<T>, realm: Realm) {
        realm.beginTransaction()
        realmObjects.deleteAllFromRealm()
        realm.commitTransaction()
    }

    fun deleteAllRealmData() {
        Realm.getDefaultInstance().use {
            it.beginTransaction()
            it.deleteAll()
            it.commitTransaction()
        }
    }

}
