package ru.saptan.anonym.domain.repositories.common.rest

import ru.saptan.anonym.domain.repositories.common.rest.retrofit.IRestClient


abstract class ABaseRestApi(private val restClient: IRestClient) : IRestApi {

    override fun cancelAllRequests() {
        restClient.cancelAllRequests()
    }
}
