package com.seven.setest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_rank.*

class RankActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)

        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val adapter = DianpuAdapter(applicationContext, GoodListener {

        })
        rv_rank.adapter = adapter
        adapter.submitList(getGoods())


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home->onBackPressed()
        }
        return true
    }

    fun getGoods():List<Good> {
        val imgUrl = "https://img.meituan.net/msmerchant/28304fa9884ba4baa5058b0502673a56115678.jpg"
        val list = mutableListOf<Good>()
        for(i in 1..10) {
            list.add(Dianpu("商圈名",imgUrl).apply {
                location = "五一广场|八一中七路55号"
                prize = 108
            })
        }
        return list
    }
}
