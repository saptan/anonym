package ru.saptan.anonym.domain.interactors.post

import io.reactivex.Observable
import ru.saptan.anonym.domain.model.data.Post

interface IPostInteractor {
    fun getPosts(): Observable<List<Post>>
}