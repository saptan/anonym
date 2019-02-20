package ru.saptan.anonym.presentation.common

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.saptan.anonym.presentation.common.actions.Action

@StateStrategyType(SkipStrategy::class)
interface IView : MvpView {

    fun showLoadingProgress(show: Boolean)

    fun showToast(message: String?)

    fun showToast(messageId: Int)

    fun showSnack(message: String?, action: Action)

    fun showErrorActionView(message: String?, action: () -> Unit)

    fun showSnack(messageId: Int, action: Action)

    fun hideKeyboard()
}