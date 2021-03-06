package com.zj.pagingadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.paging.flatMap
import androidx.recyclerview.widget.LinearLayoutManager
import com.zj.pagingadapter.adapter.DemoAdapter
import com.zj.pagingadapter.adapter.PagingAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel:MyViewModel by viewModels()
    private val mAdapter by lazy {
        PagingAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initData()
    }

    private fun initViews(){
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = mAdapter
    }

    private fun initData(){
        viewModel.pager.getScope().launch {
            viewModel.pager.getData().collectLatest {
                mAdapter.submitList(it)
            }
        }
    }
}