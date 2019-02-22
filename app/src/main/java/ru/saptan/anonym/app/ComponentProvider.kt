package ru.saptan.anonym.app

import android.content.Context
import ru.saptan.anonym.app.injections.global.AppComponent
import ru.saptan.anonym.app.injections.global.AppModule
import ru.saptan.anonym.app.injections.global.DaggerAppComponent
import ru.saptan.anonym.app.injections.post.IPostComponent

object ComponentProvider {

    lateinit var appComponent: AppComponent
    private var payComponent: IPostComponent? = null

    fun init(appContext: Context) {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(appContext))
                .build()
    }

    fun provideGson() = appComponent.gson()

    fun providePostComponent(): IPostComponent {
        if (payComponent == null)
            payComponent = appComponent.postList()

        return payComponent as IPostComponent
    }
}