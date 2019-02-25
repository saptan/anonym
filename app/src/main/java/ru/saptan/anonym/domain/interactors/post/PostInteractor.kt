package ru.saptan.anonym.domain.interactors.post

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.saptan.anonym.domain.interactors.ABaseInteractor
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.domain.repositories.post.IPostRepository

class PostInteractor(private val repository: IPostRepository) : ABaseInteractor(), IPostInteractor {

    override fun getPosts(): Observable<List<Post>> = repository.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread(), true)
}