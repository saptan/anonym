package ru.saptan.anonym.presentation.post.list

import android.support.annotation.Nullable
import android.support.v7.util.DiffUtil
import ru.saptan.anonym.domain.model.data.Post


class PostListDiffUtilCallback(val oldList: List<Post>, val newList: List<Post>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPost = oldList[oldItemPosition]
        val newPost = newList[newItemPosition]
        return oldPost.id == newPost.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPost = oldList[oldItemPosition]
        val newPost = newList[newItemPosition]
        return oldPost.text.equals(newPost.text) &&
                oldPost.getPreviewPhotoUrl()?.equals(newPost.getPreviewPhotoUrl()) ?: false &&
                oldPost.getCountLikes() == newPost.getCountLikes() &&
                oldPost.getCountComments() == newPost.getCountComments() &&
                oldPost.getCountReposts() == newPost.getCountReposts()

    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val newPost = newList[newItemPosition]
        val oldPost = oldList[oldItemPosition]

        val diff: MutableList<String> = mutableListOf()
        if (!newPost.getPreviewPhotoUrl().equals(oldPost.getPreviewPhotoUrl())) {
            diff.add(PostListAdapter.CHANGED_IMAGE)
        }

        if (!newPost.text.equals(oldPost.text)) {
            diff.add(PostListAdapter.CHANGED_TEXT)
        }

        if (newPost.getCountLikes() != (oldPost.getCountLikes())) {
            diff.add(PostListAdapter.CHANGED_COUNT_LIKES)
        }

        if (newPost.getCountComments() != (oldPost.getCountComments())) {
            diff.add(PostListAdapter.CHANGED_COUNT_COMMENTS)
        }

        if (newPost.getCountReposts() != (oldPost.getCountReposts())) {
            diff.add(PostListAdapter.CHANGED_COUNT_REPOSTS)
        }

        return if (diff.size == 0) {
            null
        } else diff
    }
}