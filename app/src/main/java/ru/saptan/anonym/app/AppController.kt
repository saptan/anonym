package ru.saptan.anonym.app

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import io.realm.Realm
import io.realm.RealmConfiguration

class AppController: MultiDexApplication() {

    private val tag: String = this.javaClass.simpleName

    override fun onCreate() {
        super.onCreate()
        ComponentProvider.init(applicationContext)
        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


}