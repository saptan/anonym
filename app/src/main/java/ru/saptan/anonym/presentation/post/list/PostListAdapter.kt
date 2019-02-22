package ru.saptan.anonym.presentation.post.list

import android.view.ViewGroup
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.presentation.common.list.AListAdapter
import android.support.v7.util.DiffUtil



class PostListAdapter : AListAdapter<Post, AListAdapter.DefaultViewHolder<Post>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder<Post> {
        val context = parent.context
        val view = PostItemView(context)
        return createDefaultViewHolder(view)
    }

    fun setData(posts: List<Post>) {
        val productDiffUtilCallback = PostListDiffUtilCallback(dataSet, posts)
        val productDiffResult = DiffUtil.calculateDiff(productDiffUtilCallback)

        dataSet = posts.toMutableList()
        productDiffResult.dispatchUpdatesTo(this)
    }
}