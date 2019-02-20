package ru.saptan.anonym.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout

abstract class ABaseView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        val layout = javaClass.getAnnotation(Layout::class.java) as Layout
        inflate(context, layout.id, this)

    }
}