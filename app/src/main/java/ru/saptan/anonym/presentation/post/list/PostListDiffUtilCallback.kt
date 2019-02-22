package ru.saptan.anonym.presentation.post.list

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
        return oldPost.text.equals(newPost.text) && oldPost.getMediumPhotoUrl()?.equals(newPost.getMediumPhotoUrl()) ?: false
    }
}