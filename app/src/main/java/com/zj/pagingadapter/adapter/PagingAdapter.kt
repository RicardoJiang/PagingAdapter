package com.zj.pagingadapter.adapter

import android.util.Log
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.DiffUtil
import com.zj.pagingadapter.data.NewsBean
import kotlinx.coroutines.Dispatchers

class PagingAdapter:DemoAdapter() {
    private val differ = AsyncPagingDataDiffer(
        diffCallback =itemCallback<NewsBean.StoriesBean>(
            areItemsTheSame = { old, new ->
                old.areItemsTheSame(new)
            },
            areContentsTheSame = { old, new ->
                old.areContentsTheSame(new)
            },
            getChangePayload = { old, new ->
                old.getChangePayload(new)
            }
        ),
        updateCallback = AdapterListUpdateCallback(this),
        mainDispatcher = Dispatchers.Main,
        workerDispatcher = Dispatchers.Default
    )

    private fun <T> itemCallback(
        areItemsTheSame: (T, T) -> Boolean = { o, n -> o == n },
        areContentsTheSame: (T, T) -> Boolean = { o, n -> o == n },
        getChangePayload: (T, T) -> Any? = { _, _ -> null }
    ): DiffUtil.ItemCallback<T> {
        return object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
                return areItemsTheSame(oldItem, newItem)
            }

            override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                return areContentsTheSame(oldItem, newItem)
            }

            override fun getChangePayload(oldItem: T, newItem: T): Any? {
                return getChangePayload(oldItem, newItem)
            }
        }
    }

     suspend fun submitList(pagingData: PagingData<NewsBean.StoriesBean>){
         Log.i("tiaoshi","submit list")
        differ.submitData(pagingData)
         Log.i("tiaoshi","differItems:"+differ.snapshot().items.size)
//        setDataList(differ.snapshot().items)
    }

    override fun getItem(position: Int): NewsBean.StoriesBean {
        Log.i("tiaoshi","here:"+position)
        return differ.getItem(position)!!
    }

    override fun getItemCount(): Int {
        val size = differ.itemCount
        Log.i("tiaoshi","getItemCount:"+size)
        return size
    }
}