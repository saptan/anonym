package ru.saptan.anonym.domain.repositories.post

import io.reactivex.Observable
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.domain.model.rest.PostListRequest
import ru.saptan.anonym.domain.repositories.common.BaseRepository
import ru.saptan.anonym.domain.repositories.post.local.IPostLocalStorage
import ru.saptan.anonym.domain.repositories.post.rest.IPostRestApi

class PostRepository(private val localStorage: IPostLocalStorage,
                     private val restApi: IPostRestApi) : BaseRepository(), IPostRepository {

    var request = PostListRequest()

    override fun getPosts(): Observable<List<Post>> {
        val localSource = Observable.fromCallable { localStorage.getPostsFromCache(request.type) }
        val remoteSource = getRemotePosts()
        return Observable.mergeDelayError(localSource, remoteSource)
    }

    private fun getRemotePosts() = restApi.getRemotePosts(request)
            .map {
                it.data.forEach { post -> post.type =  request.type}
                it.data
            }
            .doOnNext { localStorage.savePosts(it) }
}