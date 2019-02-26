package ru.saptan.anonym.presentation.post.detail

import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.presentation.common.IView

interface IPostDetailView : IView {
    fun showInfo(post: Post)
}