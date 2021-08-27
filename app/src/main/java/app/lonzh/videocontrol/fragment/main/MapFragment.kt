package app.lonzh.videocontrol.fragment.main

import android.os.Bundle
import app.lonzh.commonlibrary.vm.BaseViewModel
import app.lonzh.videocontrol.R
import app.lonzh.videocontrol.databinding.FragmentMapBinding
import app.lonzh.videocontrol.fragment.base.LisperFragment

/**
 * 地图
 */
class MapFragment : LisperFragment<BaseViewModel, FragmentMapBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_map

    override fun initView(savedInstanceState: Bundle?) {

    }
}