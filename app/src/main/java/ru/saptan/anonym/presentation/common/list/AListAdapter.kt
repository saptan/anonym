package ru.saptan.anonym.presentation.common.list

import android.support.v7.widget.RecyclerView
import android.view.View


abstract class AListAdapter<D, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    private var tag: String
    var onDataClickListener: ((item: D) -> Unit)? = null

    var dataSet: MutableList<D> = ArrayList<D>()
        @Synchronized
        set(value) {
            field = value
            isNeedShowEmptyView = dataSet.isEmpty()
            notifyDataSetChanged()
        }

    var isNeedShowEmptyView: Boolean = false
        private set

    init {
        this.dataSet = ArrayList()
        tag = this.javaClass.simpleName

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun add2DataSet(dataSet: MutableList<D>) {
        var needInvalidateList = false
        for (d in dataSet) {
            if (!this.dataSet.contains(d)) {
                this.dataSet.add(d)
                needInvalidateList = true
            }
        }
        if (needInvalidateList)
            notifyDataSetChanged()
    }

    fun add2End(data: MutableList<D>) {
        if (dataSet.isEmpty()) {
            add2DataSet(data)
        }

        for (d in data) {
            if (!this.dataSet.contains(d)) {
                this.dataSet.add(d)
                notifyItemInserted(this.dataSet.indexOf(d))
            }
        }
    }

    @Synchronized
    fun clearData() {
        dataSet = ArrayList()
        isNeedShowEmptyView = true
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: VH, position: Int) {
        bindDefaultViewHolder(holder as DefaultViewHolder<D>, position)
    }

    private fun bindDefaultViewHolder(holder: DefaultViewHolder<D>, position: Int) {
        if (position >= dataSet.size)
            return
        val d = dataSet[position]
        holder.view.bind(d)
    }

    fun onDestroy() {
        this.onDataClickListener = null
    }


    inner class DefaultViewHolder<D>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var view: IListItemView<D> = itemView as IListItemView<D>

        init {
            itemView.setOnClickListener {
                View.OnClickListener {
                    onDataClickListener?.invoke(dataSet[adapterPosition])
                }
            }
        }

    }
}
