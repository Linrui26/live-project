package com.seven.setest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.MarkerOptions
import com.amap.api.maps.model.MyLocationStyle
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    private var index:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        map_view.onCreate(savedInstanceState)
        index = intent.getIntExtra("index",0)
        fab_back.setOnClickListener {
            onBackPressed()
        }

        tv_name.text = Data.dianpus[index].name

        tv_detail.text = "人均消费：90元"

        ratingBar2.rating = Data.dianpus[1].rating.toFloat()

        tv_location.text = "地址:" + Data.dianpus[index].address

        tv_contact.text = "联系电话:" + Data.dianpus[index].tel.split(';')[0]

        showMyLocation()

    }

    fun showMyLocation() {
        val aMap = map_view.map
        aMap.apply {
            myLocationStyle = MyLocationStyle().apply {
                myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE)
                interval(5000L)
            }
            setOnMyLocationChangeListener {
                if (myLocationStyle.myLocationType == MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE) {
                    myLocationStyle = MyLocationStyle().apply {
                        myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER)
                        interval(5000L)
                    }
                    moveCamera(CameraUpdateFactory.zoomTo(17f))
                }
            }
            uiSettings?.apply {
                isMyLocationButtonEnabled = false
                isZoomControlsEnabled = false
                isRotateGesturesEnabled = true
            }
            isMyLocationEnabled = true
            addMarker(MarkerOptions().position(LatLng(Data.dianpus[index].lng,Data.dianpus[index].lat)))
            moveCamera(CameraUpdateFactory.changeLatLng(LatLng(Data.dianpus[index].lng,Data.dianpus[index].lat)))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        map_view.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        map_view.onResume()
    }

    override fun onPause() {
        super.onPause()
        map_view.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        map_view.onSaveInstanceState(outState)
    }


}
