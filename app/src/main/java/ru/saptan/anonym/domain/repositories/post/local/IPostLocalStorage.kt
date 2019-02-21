package ru.saptan.anonym.domain.repositories.post.local

import io.reactivex.Observable
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.domain.model.rest.PostListRequest

interface IPostLocalStorage {

    /**
     * @
     * @return список  постов
     */
    fun getPostsFromCache(type: Int = PostListRequest.TYPE_POST_NEW): Observable<List<Post>>
}