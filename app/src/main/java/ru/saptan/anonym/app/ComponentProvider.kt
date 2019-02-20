package ru.saptan.anonym.app

import android.content.Context
import ru.saptan.anonym.app.injections.global.AppComponent
import ru.saptan.anonym.app.injections.global.AppModule

object ComponentProvider {

    lateinit var appComponent: AppComponent

    fun init(appContext: Context) {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(appContext))
                .build()
    }

    fun provideGson() = appComponent.gson()
}