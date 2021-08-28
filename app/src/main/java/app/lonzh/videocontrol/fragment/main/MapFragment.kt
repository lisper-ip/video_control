package app.lonzh.videocontrol.fragment.main

import android.os.Bundle
import app.lonzh.commonlibrary.vm.BaseViewModel
import app.lonzh.videocontrol.R
import app.lonzh.videocontrol.databinding.FragmentMapBinding
import app.lonzh.videocontrol.fragment.base.LisperFragment
import com.amap.api.maps.AMap
import com.amap.api.maps.UiSettings
import com.amap.api.maps.model.Marker

/**
 * 地图
 */
class MapFragment : LisperFragment<BaseViewModel, FragmentMapBinding>() {

    private var mMap: AMap? = null
    private var uiSettings: UiSettings? = null
    private val markers = mutableListOf<Marker>()

    private var saveState: Bundle? = null

    override val layoutId: Int
        get() = R.layout.fragment_map

    override fun initView(savedInstanceState: Bundle?) {
        saveState = savedInstanceState
    }

    override fun lazyLoad() {
        binding.mapView.apply {
            onCreate(saveState)
            mMap = map
            uiSettings = mMap!!.uiSettings
            uiSettings?.apply {
                isZoomControlsEnabled = false
                isScaleControlsEnabled = false
                isMyLocationButtonEnabled = false
                isCompassEnabled = false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }
}