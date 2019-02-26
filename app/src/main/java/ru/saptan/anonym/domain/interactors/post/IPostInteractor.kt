package ru.saptan.anonym.domain.interactors.post

import io.reactivex.Observable
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.domain.model.rest.PostListRequestParams

interface IPostInteractor {

    fun getPosts(requestParams: PostListRequestParams): Observable<List<Post>>
}