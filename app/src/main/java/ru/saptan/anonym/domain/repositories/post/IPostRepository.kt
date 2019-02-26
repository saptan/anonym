package ru.saptan.anonym.domain.repositories.post

import io.reactivex.Observable
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.domain.model.rest.PostListRequestParams


interface IPostRepository {

    /**
     * Сначала загружает  все события из кеша, а потом с сервера (порциями). Каждый раз запрашивается
     * порция из постов в размере, указанном в Const.MAX_COUNT_POST_IN_REQUEST
     * @param requestParams - параметры запроса
     *
     */
    fun getPosts(requestParams: PostListRequestParams): Observable<List<Post>>
}