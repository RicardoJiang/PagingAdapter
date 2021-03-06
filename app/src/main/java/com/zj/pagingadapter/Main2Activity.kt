package com.zj.pagingadapter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zj.pagingadapter.adapter.Demo2Adapter
import com.zj.pagingadapter.adapter.DemoAdapter
import com.zj.pagingadapter.adapter.PagingWrapAdapter
import com.zj.pagingadapter.data.NewsBean
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class Main2Activity : AppCompatActivity() {
    private val viewModel: MyViewModel by viewModels()
    private val mAdapter by lazy {
        val readAdapter = Demo2Adapter()
        PagingWrapAdapter<NewsBean.StoriesBean, Demo2Adapter.ViewHolder>(readAdapter) {
            readAdapter.setDataList(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initData()
    }

    private fun initViews() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = mAdapter
    }

    private fun initData() {
        viewModel.pager.getScope().launch {
            viewModel.pager.getData().collectLatest {
                mAdapter.submitList(it)
            }
        }
    }
}