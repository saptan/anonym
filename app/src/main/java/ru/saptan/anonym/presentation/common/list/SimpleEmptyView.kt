package ru.saptan.anonym.presentation.common.list

import android.content.Context
import android.util.AttributeSet
import kotlinx.android.synthetic.main.simple_empty_view.view.*
import ru.saptan.anonym.R
import ru.saptan.anonym.presentation.common.ABaseView
import ru.saptan.anonym.presentation.common.Layout

@Layout(id = R.layout.simple_empty_view)
open class SimpleEmptyView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ABaseView(context, attrs, defStyleAttr), IEmptyView {

    override fun setText(text: Int) {
        tvNotDataFoundMessage.setText(text)
    }

}