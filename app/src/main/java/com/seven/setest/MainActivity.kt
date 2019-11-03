package com.seven.setest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.android.synthetic.main.activity_main.*
import okio.Buffer

class MainActivity : AppCompatActivity() {

    val imgUrl = "https://img.meituan.net/msmerchant/28304fa9884ba4baa5058b0502673a56115678.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.title = "福州市"

        getDianpu()

        val adapter = GoodsAdapter(applicationContext, GoodListener{
        })

        rv_fushi.adapter = adapter
        rv_shangquan.adapter = adapter
        adapter.submitList(getShangquan())

        Glide.with(this).load(Data.dianpus[0].imageUrl).into(meishi_1)
        meishi_1.setOnClickListener {
            startActivity(Intent(this,DetailActivity::class.java).apply {
                putExtra("index",0)
            })
        }
        Glide.with(this).load(Data.dianpus[1].imageUrl).into(meishi_2)
        meishi_2.setOnClickListener {
            startActivity(Intent(this,DetailActivity::class.java).apply {
                putExtra("index",1)
            })
        }
        Glide.with(this).load(Data.dianpus[2].imageUrl).into(meishi_3)
        meishi_3.setOnClickListener {
            startActivity(Intent(this,DetailActivity::class.java).apply {
                putExtra("index",2)
            })
        }

        more_shangquan.setOnClickListener {
            startActivity(Intent(this,RankActivity::class.java).putExtra(
                "type","shangquan"
            ))
        }

        more_meishi.setOnClickListener {
            startActivity(Intent(this,RankActivity::class.java).putExtra(
                "type","meishi"
            ))
        }

        more_fushi.setOnClickListener {
            startActivity(Intent(this,RankActivity::class.java).putExtra(
                "type","fushi"
            ))
        }
    }

    private fun getShangquan(): List<Good> {
        val list = mutableListOf<Shangquan>()
        list.add(Shangquan("东街口商圈","http://img1.imgtn.bdimg.com/it/u=2089786815,3526773374&fm=26&gp=0.jpg").apply {
            detail = "东街口百货、三坊七巷、达明美食街"
            rating = "4.54"
        })
        list.add(Shangquan("浦上商圈","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572712485271&di=82b7ed8cc23e4dc5aa2071e6d3ba44a3&imgtype=0&src=http%3A%2F%2Fwww.h0591.com%2FMyNet%2FApp_Upload%2FStorageForVirtual%2FCMS_Information%2F2018-11-20%2F21d72bea-f59f-4e9a-8d56-5b87ea3fd335.jpg").apply {
            detail = "仓山万达广场、红星美凯龙、爱琴海购物广场"
            rating = "4.21"

        })
        list.add(Shangquan("东二环泰禾广场","https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1828173237,2149035217&fm=26&gp=0.jpg").apply {
            detail = "东二环泰禾广场"
            rating = "4.09"

        })
        list.add(Shangquan("万宝商圈","https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=811522459,3740932004&fm=26&gp=0.jpg").apply {
            detail = "万象城、宝龙广场"
            rating = "3.82"

        })
        list.add(Shangquan("海峡金融街商圈","https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=373788340,726314174&fm=26&gp=0.jpg").apply {
            detail = "金融街万达广场"
            rating = "3.60"

        })
        Data.shangquans.addAll(list)
        return Data.shangquans
    }

    fun getDianpu():List<Good> {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, PoiData::class.java)
        val adaper = moshi.adapter<List<PoiData>>(type)
        val data = adaper.fromJson(Buffer().readFrom(resources.openRawResource(R.raw.top20)))
        data?.forEach {
            Data.dianpus.add(Dianpu(it.name,it.photos[0].url).apply {
                address = it.address
                val latlng = it.location.split(',')
                lat = latlng[0].toDouble()
                lng = latlng[1].toDouble()
                tel = it.tel
                rating = it.biz_ext.rating.toDouble()
            })
        }
        return Data.dianpus
    }
}
