package ru.saptan.anonym.presentation.post.list

import ru.saptan.anonym.presentation.common.list.IListView

interface IPostListView<D> : IListView<D> {
    fun showLoadingFooter()

    fun hideLoadingFooter()

    fun setWidgetTypePost(type: Int)

    fun showChoosingTypeDialog(onItemSelected: (selectedType: Int) -> Unit )
}