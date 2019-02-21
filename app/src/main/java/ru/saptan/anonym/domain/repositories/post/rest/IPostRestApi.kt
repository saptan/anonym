package ru.saptan.anonym.domain.repositories.post.rest

import io.reactivex.Observable
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.domain.model.rest.PostListRequest

interface IPostRestApi {
    fun getRemotePosts(request: PostListRequest): Observable<List<Post>>
}