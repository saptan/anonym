package ru.saptan.anonym.presentation.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.view_counter.view.*
import ru.saptan.anonym.R
import ru.saptan.anonym.domain.model.extensions.formarThousands


@Layout(R.layout.view_counter)
class CounterSocialActionsView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ABaseView(context, attrs, defStyleAttr) {

    var count: Int = 0
    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CounterSocialActionsView)

        setIcon(attributes.getDrawable(R.styleable.CounterSocialActionsView_icon))
        setCountActions(attributes.getInt(R.styleable.CounterSocialActionsView_count, 0))
        setTitle(attributes.getString(R.styleable.CounterSocialActionsView_title))

        try {
            val align = attributes.getInt(R.styleable.CounterSocialActionsView_content_align, ContentAlign.LEFT.type)
            alignContent(ContentAlign.fromType(align))

        } finally {
            attributes.recycle()
        }
    }

    private fun alignContent(alignValue: ContentAlign) {
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        params.gravity = when (alignValue) {
            ContentAlign.CENTER -> Gravity.CENTER_HORIZONTAL
            ContentAlign.RIGHT -> Gravity.END
            else -> Gravity.START
        }

        llCountInfo.layoutParams = params
    }

    fun setIcon(drawable: Drawable?) {
        ivIcon.setImageDrawable(drawable)
    }

    fun setCountActions(count: Int) {
        this.count = count
        // количество лайков и комментов не может быть отрицательным. А если оно равно 0, то не показывать
        tvCount.setVisibility(count > 0)

        val formattedNumber = count.formarThousands()
        tvCount.text = formattedNumber
    }

    fun setTitle(title: String?) {
        tvTitle.setVisibility(title != null)
        tvTitle.text = title


    }
}