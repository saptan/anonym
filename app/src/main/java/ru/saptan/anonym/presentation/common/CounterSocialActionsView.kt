package ru.saptan.anonym.presentation.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import kotlinx.android.synthetic.main.view_counter.view.*
import ru.saptan.anonym.R

@Layout(R.layout.view_counter)
class CounterSocialActionsView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ABaseView(context, attrs, defStyleAttr) {

    var count: Int = 0

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CounterSocialActionsView)

        setIcon(attributes.getDrawable(R.styleable.CounterSocialActionsView_icon))
        setCountActions(attributes.getInt(R.styleable.CounterSocialActionsView_count, 0))
        attributes.recycle()
    }

    fun setIcon(drawable: Drawable?) {
        ivIcon.setImageDrawable(drawable)
    }

    fun setCountActions(count: Int) {
        this.count = count
        // количество лайков и комментов не может быть отрицательным. А если оно равно 0, то не показывать
        tvCount.setVisibility(count > 0)
        tvCount.text = "$count"
    }


}