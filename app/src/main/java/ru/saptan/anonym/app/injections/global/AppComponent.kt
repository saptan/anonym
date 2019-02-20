package ru.saptan.anonym.app.injections.global

import com.google.gson.Gson
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
    NetModule::class,
    RepoModule::class,
    LocalStorageModule::class,
    RestModule::class])
interface AppComponent {

    fun gson(): Gson
}