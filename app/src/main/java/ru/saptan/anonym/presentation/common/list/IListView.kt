package ru.saptan.anonym.presentation.common.list

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.saptan.anonym.presentation.common.IView

interface IListView<D> : IView {

    @StateStrategyType(SingleStateStrategy::class)
    fun setData(dataSet: List<D>)

    @StateStrategyType(SingleStateStrategy::class)
    fun addData(dataSet: List<D>)

    @StateStrategyType(SingleStateStrategy::class)
    fun clearData()

    fun stopRefreshing()
}