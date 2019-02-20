package ru.saptan.anonym.presentation.common.activities

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView

abstract class ABaseSwipeListActivity<D, VH : RecyclerView.ViewHolder> : ABaseListActivity<D, VH>(), SwipeRefreshLayout.OnRefreshListener {

    lateinit var swipeToRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        swipeToRefreshLayout = findViewById(R.id.swipeToRefresh)
        swipeToRefreshLayout.setOnRefreshListener(this)
    }

    open fun stopRefreshing() {
        swipeToRefreshLayout.isRefreshing = false
    }
}