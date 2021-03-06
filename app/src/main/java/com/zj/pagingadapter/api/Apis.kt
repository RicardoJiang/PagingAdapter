package com.zj.pagingadapter.api

import com.zj.pagingadapter.data.NewsBean
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author : leo
 * @date : 2020/5/12
 */
interface Apis {

    /**
     * 知乎日报历史记录
     */
    @GET("before/{time}")
    suspend fun getNewsAsync(@Path("time") time: Long): NewsBean
}