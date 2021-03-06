package ru.saptan.anonym.domain.repositories.post.rest

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import ru.saptan.anonym.domain.model.data.ListPostsResponse
import ru.saptan.anonym.domain.model.rest.PostListRequestParams

interface IPostRestService {

    @POST("posts/get")
    fun getRemotePosts(@Body requestParams: PostListRequestParams): Observable<ListPostsResponse>
}