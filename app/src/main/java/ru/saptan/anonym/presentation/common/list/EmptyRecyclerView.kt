package ru.saptan.anonym.presentation.common.list

import android.content.Context
import android.support.annotation.StringRes
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View


open class EmptyRecyclerView : RecyclerView {

    private var emptyView: IEmptyView? = null

    private val observer = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            checkIfEmpty()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }
    }

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        val oldAdapter = getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(observer)
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(observer)
        checkIfEmpty()
    }

    fun setEmptyView(emptyView: IEmptyView?, @StringRes text: Int) {
        this.emptyView = emptyView
        if (text != 0 && emptyView != null)
            emptyView.setText(text)
        checkIfEmpty()
    }

    internal fun checkIfEmpty() {
        if (emptyView == null || adapter == null)
            return

        var emptyViewVisible = adapter?.itemCount == 0

        if (adapter is AListAdapter<*, *>) {
            if (emptyViewVisible) {
                emptyViewVisible = (adapter as AListAdapter<*, *>).isNeedShowEmptyView
            }


        }
        if (emptyViewVisible) {
            emptyView?.setVisibility(View.VISIBLE)
            visibility = View.GONE
        } else {
            emptyView?.setVisibility(View.GONE)
            visibility = View.VISIBLE

        }
    }

}