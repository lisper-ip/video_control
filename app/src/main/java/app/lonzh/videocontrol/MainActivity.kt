package app.lonzh.videocontrol

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import app.lonzh.baselibrary.manage.net.NetState
import app.lonzh.commonlibrary.activity.BaseVmDbActivity
import app.lonzh.commonlibrary.vm.BaseViewModel
import app.lonzh.videocontrol.databinding.ActivityMainBinding
import app.lonzh.videocontrol.fragment.MainFragment
import app.lonzh.videocontrol.fragment.base.LisperFragment
import app.lonzh.videocontrol.vm.AppDataViewModel
import com.blankj.utilcode.util.KeyboardUtils
import com.drake.logcat.LogCat
import me.jessyan.autosize.AutoSizeCompat

class MainActivity : BaseVmDbActivity<BaseViewModel, ActivityMainBinding>() {
    private val appDataViewModel: AppDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.app)
        super.onCreate(savedInstanceState)
    }
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun onBackPressed() {
        hideSoftInput()
        //获取hostFragment
        val mainNavFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        //获取当前fragment
        val fragment = mainNavFragment?.childFragmentManager?.primaryNavigationFragment
        if (fragment is MainFragment) {
            moveTaskToBack(true)
        } else {
            (fragment as? LisperFragment<*, *>)?.onBackPressed()
            super.onBackPressed()
        }
    }

    private fun hideSoftInput() {
        post {
            KeyboardUtils.hideSoftInput(this)
        }
    }

    override fun onNetworkStateChanged(netState: NetState) {
        LogCat.e("网络变化: ${netState.networkType.desc}")
    }

    override fun getResources(): Resources {
        AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources())
        return super.getResources()
    }
}