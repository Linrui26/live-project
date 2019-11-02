package com.seven.setest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.amap.api.maps.model.MyLocationStyle
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.amap.api.maps.CameraUpdateFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        map_view.onCreate(savedInstanceState)
        showMyLocation()
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            0,0
        ).run {
            drawer_layout.addDrawerListener(this)
            syncState()
        }

        navigation_view.setNavigationItemSelectedListener {
            when(it.itemId) {

            }
        }

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
                isMyLocationButtonEnabled = true
                isZoomControlsEnabled = true
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
