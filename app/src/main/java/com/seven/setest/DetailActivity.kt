package com.seven.setest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.model.MyLocationStyle
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        map_view.onCreate(savedInstanceState)
        fab_back.setOnClickListener {
            onBackPressed()
        }

        tv_name.text = "某店名"

        tv_detail.text = "4641条评论 人均:108元"

        ratingBar2.rating = 4f

        tv_location.text = "福建省福州市福州大学学生公寓"

        tv_contact.text = "130000000"

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
