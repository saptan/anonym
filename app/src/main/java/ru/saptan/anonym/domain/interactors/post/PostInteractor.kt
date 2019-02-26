package ru.saptan.anonym.domain.interactors.post

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.saptan.anonym.domain.interactors.ABaseInteractor
import ru.saptan.anonym.domain.model.rest.PostListRequestParams
import ru.saptan.anonym.domain.repositories.post.IPostRepository

class PostInteractor(private val repository: IPostRepository) : ABaseInteractor(), IPostInteractor {

    override fun getPosts(requestParams: PostListRequestParams) = repository.getPosts(requestParams)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread(), true)


}