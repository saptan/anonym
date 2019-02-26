package ru.saptan.anonym.domain.repositories.post.rest

import io.reactivex.Observable
import ru.saptan.anonym.domain.model.data.ListPostsResponse
import ru.saptan.anonym.domain.model.rest.PostListRequestParams
import ru.saptan.anonym.domain.repositories.common.rest.IRestApi

interface IPostRestApi: IRestApi {
    fun getRemotePosts(requestParams: PostListRequestParams): Observable<ListPostsResponse>
}