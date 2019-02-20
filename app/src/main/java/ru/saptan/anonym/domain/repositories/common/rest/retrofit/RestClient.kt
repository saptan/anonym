package ru.saptan.anonym.domain.repositories.common.rest.retrofit

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RestClient(private val okHttpClient: OkHttpClient, gson: Gson, baseurl: String) : IRestClient {
    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()


    override fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    override fun cancelAllRequests() {
        okHttpClient.dispatcher().cancelAll()
    }

}