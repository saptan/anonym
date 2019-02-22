package ru.saptan.anonym.domain.repositories.post

import io.reactivex.Observable
import ru.saptan.anonym.domain.model.data.Post


interface IPostRepository {

    fun getPosts(): Observable<List<Post>>
}