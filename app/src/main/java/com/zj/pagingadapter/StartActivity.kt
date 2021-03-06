package com.zj.pagingadapter

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class StartActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }

    fun funtcion1(view:View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun function2(view:View){
        val intent = Intent(this,Main2Activity::class.java)
        startActivity(intent)
    }
}