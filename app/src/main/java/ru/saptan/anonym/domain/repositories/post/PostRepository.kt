package ru.saptan.anonym.domain.repositories.post

import io.reactivex.Observable
import io.reactivex.Single
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
                    var index = 0
                    // Пока непонятно за что отвечает поле type, приходящее с бэкенда, т.к. при
                    // запросе постов с типом 1 (Новые), могут прийти посты с типов 2 (Популярные)
                    // и наоборот. Поэтому здесь выполняется дополнительный маппинг,
                    // чтобы потом можно было быстренько из кеша подтянуть нужные данные

                    // Также неизвестен принцип сортировки постов, набирующих популярность. Поэтому
                    // чтобы лента постов полученная из кеша отображала посты в нужном порядки, добавлено
                    // поле createdAt, именно по этому полю буду отсортированы все посты
                    it.data.forEach { post ->
                        post.type = requestParams.type
                        post.createdAt = System.currentTimeMillis() + index
                        index++
                    }
                    return@map it.data
                }
                .doOnNext {
                    // кешировать все данные с сервера. При этом кеш должен сброситься только при
                    // загрузке первой порции постов
                    localStorage.savePosts(it, clearCache = requestParams.isFirstPage())
                }
    }

    override fun getPostById(postId: Int): Single<Post?> {
        return Single.fromCallable { localStorage.getPostById(postId) }
    }
}