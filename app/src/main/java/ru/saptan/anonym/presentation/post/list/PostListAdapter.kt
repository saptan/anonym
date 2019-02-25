package ru.saptan.anonym.presentation.post.list

import android.view.ViewGroup
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.presentation.common.list.AListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView


class PostListAdapter : AListAdapter<Post, AListAdapter.DefaultViewHolder<Post>>() {

    companion object Payload {
          const val CHANGED_COUNT_LIKES = 0
          const val CHANGED_COUNT_COMMENTS = 0
          const val CHANGED_COUNT_REPOSTS = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder<Post> {
        val context = parent.context
        val view = PostItemView(context)
        return createDefaultViewHolder(view)
    }

    override fun onBindViewHolder(holder: DefaultViewHolder<Post>, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }

        val post = dataSet[position]
        if (holder.view is PostItemView) {
            // обновить данные конкретных вьюх
        }
    }


    fun setData(posts: List<Post>) {
        val productDiffUtilCallback = PostListDiffUtilCallback(dataSet, posts)
        val productDiffResult = DiffUtil.calculateDiff(productDiffUtilCallback)

        dataSet = posts.toMutableList()
        productDiffResult.dispatchUpdatesTo(this)
    }
}