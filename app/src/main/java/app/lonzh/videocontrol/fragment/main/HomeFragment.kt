package app.lonzh.videocontrol.fragment.main

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.lonzh.commonlibrary.ext.appContext
import app.lonzh.commonlibrary.vm.BaseViewModel
import app.lonzh.travel.ext.nav
import app.lonzh.videocontrol.R
import app.lonzh.videocontrol.data.bean.HomeMenu
import app.lonzh.videocontrol.data.bean.LineData
import app.lonzh.videocontrol.databinding.FragmentHomeBinding
import app.lonzh.videocontrol.fragment.base.LisperFragment
import app.lonzh.videocontrol.widget.BarGraphItem
import com.drake.brv.utils.grid
import com.drake.brv.utils.linear
import com.drake.brv.utils.models
import com.drake.brv.utils.setup
import kotlin.random.Random

/**
 * 首页
 */
class HomeFragment : LisperFragment<BaseViewModel, FragmentHomeBinding>() {

    private val menus = mutableListOf<HomeMenu>().apply {
        add(HomeMenu(R.mipmap.ic_home_msg, R.string.dining_info))
        add(HomeMenu(R.mipmap.ic_home_bn, R.string.dcba))
        add(HomeMenu(R.mipmap.ic_home_sy, R.string.spsy))
        add(HomeMenu(R.mipmap.ic_home_ts, R.string.tscl))
        add(HomeMenu(R.mipmap.ic_home_xc, R.string.ycxc))
        add(HomeMenu(R.mipmap.ic_home_xcjc, R.string.xcjc))
        add(HomeMenu(R.mipmap.ic_home_ctsh, R.string.ctsh))
        add(HomeMenu(R.mipmap.ic_home_sbsh, R.string.sbsh))
    }

    companion object {
        @JvmStatic
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun initView(savedInstanceState: Bundle?) {
        titleBar?.let {
            it.background = null
            it.leftIcon = null
            it.setLineVisible(false)
            it.title = getString(R.string.app_name)
            setRightIcon(R.drawable.ic_white_camera)
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
        }

        binding.newsRecycle.linear().setup {
            addType<String>(R.layout.item_home_new)

            onClick(R.id.item){

            }
        }

        binding.lineRecycle.grid(spanCount = 4).setup {
            addType<LineData>(R.layout.item_home_line)

            onBind {
                findView<BarGraphItem>(R.id.bar_view).setRatio(getModel<LineData>().ratio)
                val animation = AnimationUtils.loadAnimation(context, R.anim.line_scale)
                findView<BarGraphItem>(R.id.bar_view).startAnimation(animation)
            }
        }
    }

    override fun onRightClick(v: View) {

    }

    override fun lazyLoad() {
        binding.menuRecycle.models = menus
        binding.newsRecycle.models = mutableListOf<String>().apply {
            add("资讯1")
            add("资讯2")
            add("资讯3")
        }
        binding.lineRecycle.models = mutableListOf<LineData>().apply {
            add(LineData(0.4f))
            add(LineData(0.2f))
            add(LineData(0.7f))
            add(LineData(0.99f))
        }
    }
}