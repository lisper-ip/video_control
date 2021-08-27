package app.lonzh.videocontrol.fragment.main

import android.os.Bundle
import app.lonzh.commonlibrary.vm.BaseViewModel
import app.lonzh.videocontrol.R
import app.lonzh.videocontrol.databinding.FragmentMineBinding
import app.lonzh.videocontrol.fragment.base.LisperFragment

/**
 * 我的
 */
class MineFragment : LisperFragment<BaseViewModel, FragmentMineBinding>() {

    companion object {
        @JvmStatic
        fun getInstance(): MineFragment {
            return MineFragment()
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_mine

    override fun initView(savedInstanceState: Bundle?) {

    }
}