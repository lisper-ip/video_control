package app.lonzh.videocontrol.fragment.main

import android.os.Bundle
import app.lonzh.commonlibrary.vm.BaseViewModel
import app.lonzh.videocontrol.R
import app.lonzh.videocontrol.databinding.FragmentHomeBinding
import app.lonzh.videocontrol.fragment.base.LisperFragment

/**
 * 首页
 */
class HomeFragment : LisperFragment<BaseViewModel, FragmentHomeBinding>() {

    companion object {
        @JvmStatic
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun initView(savedInstanceState: Bundle?) {

    }
}