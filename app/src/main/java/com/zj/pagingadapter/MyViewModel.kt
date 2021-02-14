package com.zj.pagingadapter

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn

class MyViewModel : ViewModel(){

    fun getPage(){
        val flow = Pager(config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        ), pagingSourceFactory = {
            object : PagingSource<Long,Long>(){
                override suspend fun load(params: LoadParams<Long>): LoadResult<Long, Long> {

                }

            }
        }).flow
    }
}