package com.zj.pagingadapter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zj.pagingadapter.R
import com.zj.pagingadapter.data.NewsBean
import kotlinx.android.synthetic.main.item_news.view.*

class DemoAdapter:PagingAdapter<NewsBean.StoriesBean,DemoAdapter.ViewHolder>() {
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.run {
            val data = mDataList[position]
            tv_title.text = data.title
            Glide.with(context).load(data.images?.get(0)).into(iv_cover)
        }
    }
}