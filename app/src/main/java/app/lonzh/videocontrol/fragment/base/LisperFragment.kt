package app.lonzh.videocontrol.fragment.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import app.lonzh.baselibrary.dialog.BaseDialog
import app.lonzh.baselibrary.util.RELAY_KEYBOARD
import app.lonzh.commonlibrary.fragment.BaseVmDbFragment
import app.lonzh.commonlibrary.vm.BaseViewModel
import app.lonzh.netlibrary.constant.MMKVUtil
import app.lonzh.videocontrol.vm.AppDataViewModel
import app.lonzh.videocontrol.widget.dialog.LoadingDialog
import com.blankj.utilcode.util.KeyboardUtils

/**
 *
 * @ProjectName:    lisper
 * @Description:    描述
 * @Author:         Lisper
 * @CreateDate:     2021/6/6 1:21 下午
 * @UpdateUser:     Lisper：
 * @UpdateDate:     2021/6/6 1:21 下午
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
abstract class LisperFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbFragment<VM, DB>(){
    protected val appDataViewModel: AppDataViewModel by activityViewModels()
    private var loadingDialog: BaseDialog? = null

    override fun showLoading(msg: String){
        activity?.let {
            if (!it.isFinishing) {
                if (loadingDialog == null) {
                    loadingDialog = LoadingDialog.Builder(it)
                        .setMessage(msg)
                        .create()
                }
                loadingDialog?.show()
            }
        }
    }

    override fun dismissLoading(){
        loadingDialog?.dismiss()
        loadingDialog = null
    }

    fun showSoftInput(view: View){
        postDelayed({
            KeyboardUtils.showSoftInput(view)
        }, RELAY_KEYBOARD)
    }

    private fun hideSoftInput() {
        post {
            activity?.let { KeyboardUtils.hideSoftInput(it) }
        }
    }

    fun hideSoftInputAfter(block: () -> Unit){
        post {
            activity?.let { KeyboardUtils.hideSoftInput(it) }
            block.invoke()
        }
    }

    override fun onDestroyView() {
        hideSoftInput()
        super.onDestroyView()
    }

    override fun showErrorMsg(msg: String){
        toast(msg)
    }

    fun isLogin() : Boolean {
        return MMKVUtil.getToken() != null
    }

    open fun onBackPressed(){

    }
}