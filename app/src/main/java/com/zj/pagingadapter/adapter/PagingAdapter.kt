package com.zj.pagingadapter.adapter

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.zj.pagingadapter.data.DifferData
import kotlinx.coroutines.Dispatchers

abstract class PagingAdapter<T:DifferData,VH:RecyclerView.ViewHolder> : BaseAdapter<T,VH>() {
    private val differ = AsyncPagingDataDiffer<T>(
        diffCallback =DifferCallback(),
        updateCallback = AdapterListUpdateCallback(this),
        mainDispatcher = Dispatchers.Main,
        workerDispatcher = Dispatchers.Default
    )

    init {
        differ.addLoadStateListener {
            if (it.append is LoadState.NotLoading) {
                val items = differ.snapshot().items
                setDataList(items)
            }
        }
    }

    suspend fun submitList(pagingData: PagingData<T>) {
        differ.submitData(pagingData)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        differ.getItem(position)
    }
}