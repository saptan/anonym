package ru.saptan.anonym.app.injections.global

import dagger.Module
import dagger.Provides
import ru.saptan.anonym.domain.repositories.post.local.IPostLocalStorage
import ru.saptan.anonym.domain.repositories.post.local.PostLocalStorage
import javax.inject.Singleton

@Module
class LocalStorageModule {

    @Provides
    @Singleton
    fun postLocalStorage(): IPostLocalStorage {
        return PostLocalStorage()
    }
}