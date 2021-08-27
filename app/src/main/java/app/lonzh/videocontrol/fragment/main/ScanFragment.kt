package app.lonzh.videocontrol.fragment.main

import android.os.Bundle
import app.lonzh.commonlibrary.vm.BaseViewModel
import app.lonzh.videocontrol.R
import app.lonzh.videocontrol.databinding.FragmentScanBinding
import app.lonzh.videocontrol.fragment.base.LisperFragment


/**
 * 溯源
 */
class ScanFragment : LisperFragment<BaseViewModel, FragmentScanBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_scan

    override fun initView(savedInstanceState: Bundle?) {

    }
}