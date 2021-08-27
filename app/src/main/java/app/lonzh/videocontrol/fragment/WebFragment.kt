package app.lonzh.videocontrol.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.*
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import app.lonzh.baselibrary.manage.net.NetworkUtil
import app.lonzh.commonlibrary.ext.appContext
import app.lonzh.commonlibrary.vm.BaseViewModel
import app.lonzh.travel.ext.back
import app.lonzh.videocontrol.R
import app.lonzh.videocontrol.databinding.FragmentWebBinding
import app.lonzh.videocontrol.fragment.base.LisperFragment
import com.just.agentweb.AgentWeb
import com.just.agentweb.WebChromeClient

/**
 *
 * @ProjectName:    lisper
 * @Description:    描述
 * @Author:         Lisper
 * @CreateDate:     2021/6/28 10:33 上午
 * @UpdateUser:     Lisper：
 * @UpdateDate:     2021/6/28 10:33 上午
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class WebFragment : LisperFragment<BaseViewModel, FragmentWebBinding>() {

    private var agentWeb: AgentWeb? = null

    override val layoutId: Int = R.layout.fragment_web


    @SuppressLint("InflateParams")
    override fun initView(savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (!agentWeb!!.back()) {
                        back()
                    }
                }
            })
    }

    override fun lazyLoad() {
        arguments?.run {
            val view = LayoutInflater.from(activity).inflate(R.layout.layout_view_state, null)
            view?.run {
                findViewById<ImageView>(R.id.iv_anim).setImageResource(R.drawable.ic_error)
                if (!NetworkUtil.isNetworkAvailable(activity)) {
                    findViewById<TextView>(R.id.tv_msg).text = getString(R.string.network_error)
                } else {
                    findViewById<TextView>(R.id.tv_msg).text = context.getString(R.string.page_error)
                }
            }
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                setTitle(
                    Html.fromHtml(
                        WebFragmentArgs.fromBundle(this).title,
                        Html.FROM_HTML_MODE_COMPACT
                    )
                )
            } else {
                setTitle(
                    Html.fromHtml(
                        WebFragmentArgs.fromBundle(this).title
                    )
                )
            }

            val url = WebFragmentArgs.fromBundle(this).url
            agentWeb = AgentWeb.with(this@WebFragment)
                .setAgentWebParent(
                    binding.webCon,
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                )
                .useDefaultIndicator(ContextCompat.getColor(appContext, R.color.purple_700))
                .setMainFrameErrorView(view)
                .setWebChromeClient(object : WebChromeClient() {
                    override fun onReceivedTitle(view: WebView?, title: String?) {
                        super.onReceivedTitle(view, title)
                        if (titleBar?.titleView?.text.isNullOrEmpty()) {
                            setTitle(title)
                        }
                    }
                })
                .interceptUnkownUrl()
                .createAgentWeb()
                .ready()
                .go(url)
        }
    }

    override fun onPause() {
        agentWeb?.webLifeCycle?.onPause()
        super.onPause()
    }


    override fun onResume() {
        agentWeb?.webLifeCycle?.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        agentWeb?.webLifeCycle?.onDestroy()
        super.onDestroy()
    }
}