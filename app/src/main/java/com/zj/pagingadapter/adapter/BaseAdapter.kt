package com.zj.pagingadapter.adapter

import androidx.recyclerview.widget.RecyclerView
import com.zj.pagingadapter.data.DifferData

abstract class BaseAdapter<T:DifferData,VH:RecyclerView.ViewHolder>:RecyclerView.Adapter<VH>() {
    protected var mDataList = mutableListOf<T>()

    fun setDataList(dataList:List<T>){
        mDataList = dataList.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }
}