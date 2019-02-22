package ru.saptan.anonym.app.injections.global

import dagger.Module
import dagger.Provides
import ru.saptan.anonym.domain.repositories.post.IPostRepository
import ru.saptan.anonym.domain.repositories.post.PostRepository
import ru.saptan.anonym.domain.repositories.post.local.IPostLocalStorage
import ru.saptan.anonym.domain.repositories.post.rest.IPostRestApi
import javax.inject.Singleton

@Module(includes = [(LocalStorageModule::class), (RestModule::class)])
class RepoModule {

    @Provides
    @Singleton
    fun profileRepository(postLocalStorage: IPostLocalStorage, restApi: IPostRestApi): IPostRepository {
        return PostRepository(postLocalStorage, restApi)
    }
}