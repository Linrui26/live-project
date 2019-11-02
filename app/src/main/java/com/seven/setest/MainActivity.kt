package com.seven.setest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val imgUrl = "https://img.meituan.net/msmerchant/28304fa9884ba4baa5058b0502673a56115678.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.title = "福州市"
        val adapter = GoodsAdapter(applicationContext, GoodListener{
            startActivity(Intent(this,DetailActivity::class.java))
        })
        rv_fushi.adapter = adapter
        rv_shangquan.adapter = adapter
        adapter.submitList(getGoods())

        Glide.with(this).load(imgUrl).into(meishi_1)
        Glide.with(this).load(imgUrl).into(meishi_2)
        Glide.with(this).load(imgUrl).into(meishi_3)

        more_shangquan.setOnClickListener {
            startActivity(Intent(this,RankActivity::class.java))

        }

        more_meishi.setOnClickListener {

        }

        more_fushi.setOnClickListener {

        }



    }

    fun getGoods():List<Good> {
        val list = mutableListOf<Good>()
        for(i in 1..10) {
            list.add(Shangquan("商圈名",imgUrl))
        }
        return list
    }
}
