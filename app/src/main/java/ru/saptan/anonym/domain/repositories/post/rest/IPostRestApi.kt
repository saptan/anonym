package ru.saptan.anonym.domain.repositories.post.rest

import io.reactivex.Observable
import ru.saptan.anonym.domain.model.data.ListPostsResponse
import ru.saptan.anonym.domain.model.rest.PostListRequestParams

interface IPostRestApi {
    fun getRemotePosts(requestParams: PostListRequestParams): Observable<ListPostsResponse>
}