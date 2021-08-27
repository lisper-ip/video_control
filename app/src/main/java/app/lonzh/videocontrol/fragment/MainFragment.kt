package app.lonzh.videocontrol.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import app.lonzh.commonlibrary.vm.BaseViewModel
import app.lonzh.travel.ext.doSelected
import app.lonzh.travel.ext.initFragment
import app.lonzh.travel.ext.nav
import app.lonzh.videocontrol.R
import app.lonzh.videocontrol.databinding.FragmentMainBinding
import app.lonzh.videocontrol.fragment.base.LisperFragment
import app.lonzh.videocontrol.fragment.main.HomeFragment
import app.lonzh.videocontrol.fragment.main.MineFragment
import com.blankj.utilcode.util.ClickUtils

class MainFragment : LisperFragment<BaseViewModel, FragmentMainBinding>(){

    override val layoutId: Int
        get() = R.layout.fragment_main

    private val fragmentList = arrayListOf<Fragment>()

    init {
        fragmentList.apply {
            add(HomeFragment.getInstance())
            add(Fragment())
            add(Fragment())
            add(Fragment())
            add(MineFragment.getInstance())
        }
    }
    /**
     * 初始化view
     */
    override fun initView(savedInstanceState: Bundle?) {
        binding.mainViewpager.apply {
            initFragment(childFragmentManager, fragmentList).run {
                offscreenPageLimit = fragmentList.size
            }
            doSelected {
                binding.bottomNav.menu.getItem(it).isChecked = true
            }
            setNoScroll(false)
        }
        binding.bottomNav.run {
            itemIconTintList = null
            setOnNavigationItemSelectedListener { item ->
                when(item.itemId){
                    R.id.menu_home -> binding.mainViewpager.setCurrentItem(0, false)
                    R.id.menu_look -> nav(R.id.action_mainFragment_to_scanFragment)
                    R.id.menu_select -> toast(context.getString(R.string.waiting_for))
                    R.id.menu_mine ->  binding.mainViewpager.setCurrentItem(4, false)
                    else ->{}
                }
                false
            }
        }
        ClickUtils.applySingleDebouncing(binding.ivMidTab){
            nav(R.id.action_mainFragment_to_mapFragment)
        }
    }


}