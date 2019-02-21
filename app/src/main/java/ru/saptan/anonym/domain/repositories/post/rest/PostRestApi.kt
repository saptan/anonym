package ru.saptan.anonym.domain.repositories.post.rest

import ru.saptan.anonym.domain.model.rest.PostListRequest
import ru.saptan.anonym.domain.repositories.common.rest.ABaseRestApi
import ru.saptan.anonym.domain.repositories.common.rest.retrofit.IRestClient

class PostRestApi(restClient: IRestClient) : ABaseRestApi(restClient), IPostRestApi {

    private var service: IPostRestService = restClient.createService(IPostRestService::class.java)

    override fun getRemotePosts(request: PostListRequest) = service.getRemotePosts(request)
}