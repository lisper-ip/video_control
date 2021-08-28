package app.lonzh.travel.startup

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import app.lonzh.commonlibrary.ext.appContext
import app.lonzh.netlibrary.RxHttpManager
import app.lonzh.videocontrol.BuildConfig
import app.lonzh.videocontrol.R
import app.lonzh.videocontrol.data.StateData
import com.drake.brv.BR
import com.drake.brv.PageRefreshLayout
import com.drake.brv.utils.BRV
import com.drake.logcat.LogCat
import com.drake.statelayout.StateConfig
import com.hjq.bar.TitleBar
import com.hjq.bar.style.LightBarStyle
import com.hjq.toast.ToastUtils
import com.rousetime.android_startup.AndroidStartup
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.mmkv.MMKV


/**
 *
 * @ProjectName:    lisper
 * @Description:    主线程初始化
 * @Author:         Lisper
 * @CreateDate:     5/19/21 2:18 PM
 * @UpdateUser:     Lisper：
 * @UpdateDate:     5/19/21 2:18 PM
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class CommonMainStartUp : AndroidStartup<String>(){
    /**
     * Return true call the create function on main thread otherwise false.
     */
    override fun callCreateOnMainThread(): Boolean = true

    /**
     * Contains all of the necessary operations to initialize the component.
     * and returns an instance of `T`
     *
     * @param [context]
     */
    override fun create(context: Context): String? {
        //CrashReport.initCrashReport(context, "f8ebcaff63", BuildConfig.DEBUG)
        MMKV.initialize(context)
        ToastUtils.init(context as Application)
        BRV.modelId = BR.bean
        RxHttpManager.init(context)
        PageRefreshLayout.startIndex = 1
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { mContext, _ ->
            MaterialHeader(mContext)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { mContext, _ ->
            ClassicsFooter(mContext)
        }
        TitleBar.setDefaultStyle(object : LightBarStyle() {
            override fun createBackIcon(context: Context): Drawable {
                return getDrawableResources(context, R.drawable.ic_white_back)
            }
        })

        StateConfig.apply {
            loadingLayout = R.layout.layout_view_loading
            emptyLayout = R.layout.layout_view_state
            errorLayout = R.layout.layout_view_state

            onLoading {
                when(it){
                    is StateData -> {
                        findViewById<TextView>(R.id.tv_msg).text = it.msg
                    }
                    else -> {
                        findViewById<TextView>(R.id.tv_msg).text = appContext.getString(R.string.default_loading)
                    }
                }
            }

            onEmpty {
                when(it){
                    is StateData -> {
                        findViewById<ImageView>(R.id.iv_anim).setImageResource(it.resId)
                        findViewById<TextView>(R.id.tv_msg).text = it.msg
                    }
                    else -> {
                        findViewById<ImageView>(R.id.iv_anim).setImageResource(R.drawable.ic_empty)
                        findViewById<TextView>(R.id.tv_msg).text = ""
                    }
                }
            }

            onError {
                when(it){
                    is StateData -> {
                        findViewById<ImageView>(R.id.iv_anim).setImageResource(it.resId)
                        findViewById<TextView>(R.id.tv_msg).text = it.msg
                    }
                    else -> {
                        findViewById<ImageView>(R.id.iv_anim).setImageResource(R.drawable.ic_error)
                        findViewById<TextView>(R.id.tv_msg).text = appContext.getString(R.string.unknown_mistake)
                    }
                }
            }
            setRetryIds(R.id.iv_anim, R.id.tv_msg)
        }
        LogCat.config {
            defaultTag = "lisper"
            enabled = BuildConfig.DEBUG
        }
        return javaClass.simpleName
    }

    /**
     * Return true block the main thread until the startup completed otherwise false.
     *
     * Note: If the function [callCreateOnMainThread] return true, main thread default block.
     */
    override fun waitOnMainThread(): Boolean = true
}