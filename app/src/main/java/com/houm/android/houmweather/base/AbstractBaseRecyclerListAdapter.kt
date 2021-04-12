package com.houm.android.houmweather.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.houm.android.houmweather.intf.ItemClickListener

abstract class AbstractBaseRecyclerListAdapter<ViewHolder : AbstractBaseViewHolder>(protected var context: Context? = null) :
    RecyclerView.Adapter<ViewHolder>(), View.OnClickListener {


    private var itemClickListener: ItemClickListener? = null

    companion object ViewType {
        const val ITEM = 1
        const val FOOTER = 2
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): ViewHolder

    abstract fun updateList(list: List<Any?>?)

    abstract fun getItem(position: Int): Any?

    abstract override fun getItemCount(): Int

    abstract override fun getItemViewType(position: Int): Int

    abstract override fun onBindViewHolder(viewHolder: ViewHolder, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return getViewHolder(parent, viewType)
    }

    override fun onClick(view: View?) {
        itemClickListener?.onItemClick(view, view?.tag)
    }


    fun setOnItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }


}