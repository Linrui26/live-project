package com.seven.setest

import android.content.Intent
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


        if(intent.getStringExtra("type")=="shangquan") {
            val adapter = DianpuAdapter(this, GoodListener {

            })
            adapter.submitList(Data.shangquans as List<Good>)
            rv_rank.adapter = adapter
        } else if(intent.getStringExtra("type")=="meishi") {
            val adapter = DianpuAdapter(this, GoodListener {

            })
            adapter.submitList(Data.dianpus as List<Good>)
            rv_rank.adapter = adapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home->onBackPressed()
        }
        return true
    }

}
