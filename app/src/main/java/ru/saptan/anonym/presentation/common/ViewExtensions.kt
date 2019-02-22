package ru.saptan.anonym.presentation.common

import android.content.Context
import android.view.View
import android.widget.Toast
import ru.saptan.anonym.presentation.common.list.AListAdapter


fun Context.showToast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.showToast(message: CharSequence, duration: Int) =
        Toast.makeText(this, message, duration).show()


fun View.setVisibility(enable: Boolean) {
    this.visibility = if (enable) View.VISIBLE else View.GONE
}

fun <D> AListAdapter.DefaultViewHolder<D>.onClickListen(event: (position: Int) -> Unit): AListAdapter.DefaultViewHolder<D> {
    itemView.setOnClickListener {
        event.invoke(adapterPosition)
    }
    return this
}