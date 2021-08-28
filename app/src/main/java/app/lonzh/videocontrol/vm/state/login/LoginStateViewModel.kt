package app.lonzh.videocontrol.vm.state.login

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class LoginStateViewModel : ViewModel() {

    val mobile = ObservableField("")

    val code = ObservableField("")
}