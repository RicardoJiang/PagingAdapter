package com.zj.pagingadapter.adapter

import androidx.recyclerview.widget.DiffUtil
import com.zj.pagingadapter.data.DifferData

class DifferCallback<T: DifferData>: DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }

    override fun getChangePayload(oldItem: T, newItem: T): Any? {
        return oldItem.getChangePayload(newItem)
    }
}