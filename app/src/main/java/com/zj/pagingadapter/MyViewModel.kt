package com.zj.pagingadapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingSource
import cn.leo.paging_adapter.net.http.OkHttp3Creator
import cn.leo.paging_adapter.net.http.ServiceCreator
import cn.leo.paging_adapter.net.interceptor.LoggerInterceptor
import com.zj.pagingadapter.api.Apis
import com.zj.pagingadapter.data.NewsBean
import java.text.SimpleDateFormat
import java.util.*

class MyViewModel : ViewModel() {
    companion object {
        const val baseUrlZhiHu = "https://news-at.zhihu.com/api/4/news/"
    }

    val api by lazy {
        ServiceCreator.create(Apis::class.java) {
            baseUrl = baseUrlZhiHu
            httpClient = OkHttp3Creator.build {
                //缓存文件夹
                //网络请求日志打印拦截器
                addInterceptor(LoggerInterceptor())
            }
        }
    }
    private val mDate = Calendar.getInstance().apply {
        add(Calendar.DATE, 1)
    }

    private val initialKey = SimpleDateFormat("yyyyMMdd", Locale.CHINA)
        .format(mDate.time)
        .toLong()

    val pager = SimplePager<Long, NewsBean.StoriesBean>(
        viewModelScope,
        enablePlaceholders = true
    ) {
        val date = it.key ?: initialKey
        try {
            //从网络获取数据
            val data = api.getNewsAsync(date)
            //添加title
            val list: MutableList<NewsBean.StoriesBean> = data.stories.toMutableList()
            //返回数据
            PagingSource.LoadResult.Page(
                list,
                null,
                data.date?.toLongOrNull(),
                0,  //前面剩余多少未加载数量，
                100  //后面剩余多少未加载数量，配合 enablePlaceholders 在滑动过快的时候显示占位；
            )
        } catch (e: Exception) {
            //请求失败
            PagingSource.LoadResult.Error(e)
        }
    }
}