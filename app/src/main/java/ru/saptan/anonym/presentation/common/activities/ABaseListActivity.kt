package ru.saptan.anonym.presentation.common.activities

import android.support.annotation.CallSuper
import android.support.annotation.StringRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import ru.saptan.anonym.R
import ru.saptan.anonym.presentation.common.list.AListAdapter
import ru.saptan.anonym.presentation.common.list.EmptyRecyclerView
import ru.saptan.anonym.presentation.common.list.IEmptyView


abstract class ABaseListActivity<D, VH : RecyclerView.ViewHolder> : ABaseActivity() {
    lateinit var recyclerView: EmptyRecyclerView
    var emptyView: IEmptyView? = null
    protected lateinit var adapter: AListAdapter<D, VH>
    private lateinit var layoutManager: RecyclerView.LayoutManager
    protected var isDoScrollDown = true
    @get:StringRes
    protected abstract val emptyViewText: Int


    override fun initViews() {
        super.initViews()
        recyclerView = findViewById(R.id.rvList)
        emptyView = findViewById<View>(R.id.emptyView) as? IEmptyView
        recyclerView.setEmptyView(emptyView, emptyViewText)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(initItemDecorator())
        layoutManager = initLayoutManager()
        recyclerView.layoutManager = layoutManager
        adapter = initAdapter()
        recyclerView.adapter = adapter
    }

    fun clearData() {
        adapter.clearData()
    }

    open fun setData(dataSet: List<D>) {
        adapter.dataSet = dataSet.toMutableList()
    }

    open fun addData(dataSet: List<D>) {
        adapter.add2End(dataSet)
        releaseScroll()
    }

    protected abstract fun initAdapter(): AListAdapter<D, VH>

    protected abstract fun initLayoutManager(): RecyclerView.LayoutManager

    abstract fun initItemDecorator(): RecyclerView.ItemDecoration

    override fun initListeners() {
        super.initListeners()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val first = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val data = adapter.dataSet
                if (data.size > 0)
                    onFirstVisible(data[first])

                if (isDoScrollDown && dy > 0) {
                    if (layoutManager.childCount + first >= layoutManager.itemCount - 2) {
                        onScrollDown()
                    }
                }
            }
        })
    }

    protected fun onFirstVisible(item: D?) {

    }



    @CallSuper
    protected fun releaseScroll() {
        isDoScrollDown = true
    }

    @CallSuper
    protected open fun onScrollDown() {
        isDoScrollDown = false
    }

}