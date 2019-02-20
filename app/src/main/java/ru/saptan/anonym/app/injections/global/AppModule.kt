package ru.saptan.anonym.app.injections.global

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.saptan.anonym.routing.ActivitiesRouter
import ru.saptan.anonym.routing.IActivitiesRouter
import ru.saptan.anonym.routing.IIntentCreator
import ru.saptan.anonym.routing.IntentCreator
import javax.inject.Singleton

@Module
class AppModule(private val appContext: Context) {

    private val packageName: String = appContext.applicationContext.packageName

    @Singleton
    @Provides
    internal fun context(): Context {
        return appContext
    }

    @Singleton
    @Provides
    internal fun activitiesRouter(iIntentCreator: IIntentCreator): IActivitiesRouter {
        return ActivitiesRouter(iIntentCreator)

    }

    @Singleton
    @Provides
    internal fun intentCreator(): IIntentCreator {
        return IntentCreator(packageName)
    }

}
