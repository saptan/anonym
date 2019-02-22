package ru.saptan.anonym.app.injections.global

import dagger.Module
import dagger.Provides
import ru.saptan.anonym.domain.repositories.common.rest.retrofit.IRestClient
import ru.saptan.anonym.domain.repositories.post.rest.IPostRestApi
import ru.saptan.anonym.domain.repositories.post.rest.PostRestApi
import javax.inject.Singleton

@Module
class RestModule {

    @Provides
    @Singleton
    fun postRestApi(restClient: IRestClient): IPostRestApi {
        return PostRestApi(restClient)
    }
}