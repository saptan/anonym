package ru.saptan.anonym.domain.repositories.post.local

import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.domain.model.rest.PostListRequestParams

interface IPostLocalStorage {

    /**
     * @param type - тип поста (набирающий популярность или новый)
     * @return список  постов
     */
    fun getPostsFromCache(type: Int = PostListRequestParams.POPULAR): List<Post>

    fun savePosts(posts: List<Post>, clearCache: Boolean)

    fun getPostById(postId: Int): Post?


}