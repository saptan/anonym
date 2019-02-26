package ru.saptan.anonym.domain.repositories.post

import io.reactivex.Observable
import ru.saptan.anonym.app.Const
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.domain.model.rest.PostListRequestParams
import ru.saptan.anonym.domain.repositories.common.BaseRepository
import ru.saptan.anonym.domain.repositories.post.local.IPostLocalStorage
import ru.saptan.anonym.domain.repositories.post.rest.IPostRestApi

class PostRepository(private val localStorage: IPostLocalStorage,
                     private val restApi: IPostRestApi) : BaseRepository(), IPostRepository {


    override fun getPosts(requestParams: PostListRequestParams): Observable<List<Post>> {
        val localSource = getLocalPosts(requestParams.type)
        val remoteSource = getRemotePosts(requestParams)

        return when (requestParams.sourceData) {
            Const.TYPE_SOURCE_CACHE -> localSource
            Const.TYPE_SOURCE_NETWORK -> remoteSource
            else -> Observable.mergeDelayError(localSource, remoteSource)
        }
    }

    private fun getLocalPosts(typePost: Int): Observable<List<Post>> {
        return Observable.fromCallable { localStorage.getPostsFromCache(typePost) }
    }

    private fun getRemotePosts(requestParams: PostListRequestParams): Observable<List<Post>> {
        return restApi.getRemotePosts(requestParams)
                .map {
                    // Перед сохранением в бд нужно запомнить тип поста (новый или популярный)
                    // чтобы потом можно было быстренько из кеша подтянуть нужные данные
                    it.data.forEach { post -> post.type = requestParams.type }
                    return@map it.data
                }
                .doOnNext {
                    // кешировать все данные с сервера. При этом кеш должен сброситься только при
                    // загрузке первой порции постов
                    localStorage.savePosts(it, clearCache = requestParams.isFirstPage())
                }
    }

}