package ru.saptan.anonym.app.injections.global

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import ru.saptan.anonym.app.Const
import ru.saptan.anonym.domain.repositories.common.rest.retrofit.IRestClient
import ru.saptan.anonym.domain.repositories.common.rest.retrofit.RestClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule {

    companion object {
        const val API_URL = "http://dev.apianon.ru:3000/"
    }

    @Provides
    @Singleton
    fun provideOkHttpClientWithOutInterceptor(): OkHttpClient {
        val okClientBuilder = OkHttpClient.Builder()
        okClientBuilder.connectTimeout(Const.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Const.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Const.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        return okClientBuilder.build()
    }

    @Singleton
    @Provides
    internal fun provideGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .create()
    }

    @Provides
    @Singleton
    fun provideRestClient(okHttpClient: OkHttpClient, gson: Gson): IRestClient {
        return RestClient(okHttpClient, gson, API_URL)

    }
}