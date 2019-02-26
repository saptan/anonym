package ru.saptan.anonym.presentation.post.list

import ru.saptan.anonym.presentation.common.list.IListView

interface IPostListView<D> : IListView<D> {
    fun showLoadingFooter()
    fun hideLoadingFooter()
}