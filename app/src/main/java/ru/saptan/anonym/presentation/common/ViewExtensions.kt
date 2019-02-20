package ru.saptan.anonym.presentation.common

import android.content.Context
import android.view.View
import android.widget.Toast


fun Context.showToast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.showToast(message: CharSequence, duration: Int) =
        Toast.makeText(this, message, duration).show()


fun View.setVisibility(enable: Boolean) {
    this.visibility = if (enable)  View.VISIBLE else View.GONE
}