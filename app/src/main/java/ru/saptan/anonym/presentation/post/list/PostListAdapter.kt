package ru.saptan.anonym.presentation.post.list

import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import ru.saptan.anonym.domain.model.data.Post
import ru.saptan.anonym.presentation.common.ABaseView
import ru.saptan.anonym.presentation.common.list.AListAdapter

class PostListAdapter : AListAdapter<Post, AListAdapter.DefaultViewHolder<Post>>() {

    private var isLoadingAdded = false

    companion object {
        const val CHANGED_IMAGE = "image"
        const val CHANGED_TEXT = "text"
        const val CHANGED_COUNT_LIKES = "likes"
        const val CHANGED_COUNT_COMMENTS = "comments"
        const val CHANGED_COUNT_REPOSTS = "reposts"

        const val ITEM_TYPE_POST = 0
        const val ITEM_TYPE_LOADING = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder<Post> {
        val context = parent.context

        val view: ABaseView = when (viewType) {
            ITEM_TYPE_LOADING -> LoadingItemView(context)
            else -> PostItemView(context) //ITEM_TYPE_POST
        }

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

    override fun getItemViewType(position: Int): Int {
        return if (position == dataSet.lastIndex && isLoadingAdded) ITEM_TYPE_LOADING else ITEM_TYPE_POST
    }

    fun showLoadingFooter() {
        isLoadingAdded = true
        dataSet.add(Post())
        notifyItemInserted(dataSet.lastIndex)
    }

    fun hideLoadingFooter() {
        isLoadingAdded = false
        val itemLoader = dataSet.lastOrNull()

        val footerPosition = dataSet.lastIndex
        if (itemLoader != null) {
            dataSet.removeAt(footerPosition)
            notifyItemRemoved(footerPosition)
        }
    }


}