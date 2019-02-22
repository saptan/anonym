package ru.saptan.anonym.domain.repositories.post.local

import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.domain.model.rest.PostListRequest

interface IPostLocalStorage {

    /**
     * @param type - тип поста (набирающий популярность или новый)
     * @return список  постов
     */
    fun getPostsFromCache(type: Int = PostListRequest.TYPE_POST_POPULAR): List<Post>

    fun savePosts(posts: List<Post>)

}