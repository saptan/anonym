package ru.saptan.anonym.presentation.common.list

import android.support.annotation.StringRes

interface IEmptyView {
    fun setText(@StringRes text: Int)

    fun setVisibility(i: Int)
}