package app.lonzh.videocontrol.fragment.home

import android.os.Bundle
import app.lonzh.commonlibrary.vm.BaseViewModel
import app.lonzh.videocontrol.R
import app.lonzh.videocontrol.databinding.FragmentDiningListBinding
import app.lonzh.videocontrol.fragment.base.LisperFragment

/**
 * 餐厅列表
 */
class DiningListFragment : LisperFragment<BaseViewModel, FragmentDiningListBinding>(){

    override val layoutId: Int
        get() = R.layout.fragment_dining_list

    override fun initView(savedInstanceState: Bundle?) {
        setTitle(getString(R.string.dining_info))
    }
}