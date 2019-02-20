package ru.saptan.anonym.presentation.common.actions

import android.content.Context
import android.util.AttributeSet
import kotlinx.android.synthetic.main.network_error_action_view.view.*
import ru.saptan.anonym.R
import ru.saptan.anonym.presentation.common.ABaseView
import ru.saptan.anonym.presentation.common.Layout

@Layout(id = R.layout.network_error_action_view)
class NetworkErrorActionView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : ABaseView(context, attrs, defStyleAttr) {

    fun bind(message: String?, action: () -> Unit) {
        tvErrorMessage.text = message
        btnErrorRetry.setOnClickListener { action.invoke() }
    }

}