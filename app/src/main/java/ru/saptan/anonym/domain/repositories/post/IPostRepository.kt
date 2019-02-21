package ru.saptan.anonym.domain.repositories.post

import ru.saptan.anonym.domain.model.data.Post
import rx.Observable


interface IPostRepository {

    fun getPosts(): Observable<List<Post>>
}