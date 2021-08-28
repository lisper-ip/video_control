package app.lonzh.videocontrol.fragment.main

import android.os.Bundle
import androidx.core.content.ContextCompat
import app.lonzh.commonlibrary.ext.appContext
import app.lonzh.commonlibrary.vm.BaseViewModel
import app.lonzh.travel.ext.nav
import app.lonzh.videocontrol.R
import app.lonzh.videocontrol.data.bean.HomeMenu
import app.lonzh.videocontrol.databinding.FragmentHomeBinding
import app.lonzh.videocontrol.fragment.base.LisperFragment
import com.drake.brv.utils.grid
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup

/**
 * 首页
 */
class HomeFragment : LisperFragment<BaseViewModel, FragmentHomeBinding>() {

    private val menus = mutableListOf<HomeMenu>()

    companion object {
        @JvmStatic
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun initView(savedInstanceState: Bundle?) {
        menus.apply {
            add(HomeMenu(R.mipmap.ic_home_msg, getString(R.string.dining_info)))
            add(HomeMenu(R.mipmap.ic_home_bn, getString(R.string.dcba)))
            add(HomeMenu(R.mipmap.ic_home_sy, getString(R.string.spsy)))
            add(HomeMenu(R.mipmap.ic_home_ts, getString(R.string.tscl)))
            add(HomeMenu(R.mipmap.ic_home_xc, getString(R.string.ycxc)))
            add(HomeMenu(R.mipmap.ic_home_xcjc, getString(R.string.xcjc)))
            add(HomeMenu(R.mipmap.ic_home_ctsh, getString(R.string.ctsh)))
            add(HomeMenu(R.mipmap.ic_home_sbsh, getString(R.string.sbsh)))
        }

        titleBar?.let {
            it.background = null
            it.leftIcon = null
            it.setLineVisible(false)
            it.title = getString(R.string.app_name)
            it.setTitleColor(ContextCompat.getColor(appContext, R.color.white))
        }

        binding.menuRecycle.grid(spanCount = 4).setup {
            addType<HomeMenu>(R.layout.item_home_menu)

            onClick(R.id.item){
                when(modelPosition){
                    0 -> nav(R.id.action_mainFragment_to_diningListFragment)
                    1 ->{}
                    2 ->{}
                    3 ->{}
                    4 ->{}
                    5 ->{}
                    6 ->{}
                    7 ->{}
                    else -> {}
                }
            }
        }.models = menus

        binding.newsRecycle.linear().setup {
            addType<String>(R.layout.item_home_new)

            onClick(R.id.item){

            }
        }.models = mutableListOf<String>().apply {
            add("资讯1")
            add("资讯2")
            add("资讯3")
        }
    }
}