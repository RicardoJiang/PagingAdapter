package com.zj.pagingadapter.data

/**
 * @author : leo
 * @date : 2020/11/10
 * @description : 数据比较接口
 */
interface DifferData {

    fun areItemsTheSame(data: DifferData): Boolean {
        return this == data
    }

    fun areContentsTheSame(data: DifferData): Boolean {
        return this == data
    }

    fun getChangePayload(data: DifferData): Any? {
        return null
    }

}