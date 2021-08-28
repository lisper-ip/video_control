package app.lonzh.videocontrol.vm.request.login

import androidx.lifecycle.MutableLiveData
import app.lonzh.commonlibrary.ext.launch
import app.lonzh.commonlibrary.vm.BaseViewModel
import app.lonzh.netlibrary.config.RequestConfig
import app.lonzh.videocontrol.vm.state.login.LoginStateViewModel
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toLpResponse

class LoginRequestViewModel : BaseViewModel() {
    val loginResultLiveData by lazy { MutableLiveData<Any>() }

    fun login(loginStateViewModel: LoginStateViewModel) = launch({
        val result = RxHttp.postForm("").toLpResponse<Any>().await()
        loginResultLiveData.value = result
    }, RequestConfig().isShowLoading(true).loadingMessage("登录中"))
}