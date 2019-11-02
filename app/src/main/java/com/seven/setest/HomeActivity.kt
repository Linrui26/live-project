package com.seven.setest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        toolbar.title = "福州市"
        val adapter = GoodsAdapter(applicationContext, GoodListener{
            // TODO : 跳转详情页面
        })
        rv_fushi.adapter = adapter
        adapter.submitList(getGoods())

    }

    fun getGoods():List<Good> {
        val list = mutableListOf<Good>()
        for(i in 1..10) {
            list.add(Shangquan("商圈名","https://www.meituan.com/meishi/833626/"))
        }
        return list
    }
}
