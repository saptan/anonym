package ru.saptan.anonym.presentation.post.list

import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.presentation.common.list.AListAdapter


class PostListAdapter : AListAdapter<Post, AListAdapter.DefaultViewHolder<Post>>() {

    companion object Payload {
        const val CHANGED_IMAGE = "image"
        const val CHANGED_TEXT = "text"
        const val CHANGED_COUNT_LIKES = "likes"
        const val CHANGED_COUNT_COMMENTS = "comments"
        const val CHANGED_COUNT_REPOSTS = "reposts"
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
        val view = holder.view as PostItemView

        val diff = payloads[0] as MutableList<*>
        diff.forEach { key ->
            when (key) {
                CHANGED_IMAGE -> view.loadImage(post)
                CHANGED_TEXT -> view.setText(post.text)
                CHANGED_COUNT_LIKES -> view.setLikesCount(post.getCountLikes())
                CHANGED_COUNT_COMMENTS -> view.setCommentsCount(post.getCountLikes())
                CHANGED_COUNT_REPOSTS -> view.setReportsCount(post.getCountLikes())
            }
        }
    }

    fun setData(posts: List<Post>) {
        val productDiffUtilCallback = PostListDiffUtilCallback(dataSet, posts)
        val productDiffResult = DiffUtil.calculateDiff(productDiffUtilCallback)

        dataSet = posts.toMutableList()
        productDiffResult.dispatchUpdatesTo(this)
    }
}