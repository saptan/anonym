package ru.saptan.anonym.domain.repositories.common.rest.retrofit

interface IRestClient {

    fun <S> createService(serviceClass: Class<S>): S

    fun cancelAllRequests()
}