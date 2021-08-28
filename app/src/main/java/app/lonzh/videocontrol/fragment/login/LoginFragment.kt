package app.lonzh.videocontrol.fragment.login

import android.os.Bundle
import androidx.fragment.app.viewModels
import app.lonzh.travel.ext.back
import app.lonzh.travel.ext.nav
import app.lonzh.videocontrol.R
import app.lonzh.videocontrol.databinding.FragmentLoginBinding
import app.lonzh.videocontrol.fragment.base.LisperFragment
import app.lonzh.videocontrol.vm.request.login.LoginRequestViewModel
import app.lonzh.videocontrol.vm.state.login.LoginStateViewModel
import com.blankj.utilcode.util.ClickUtils

/**
 * 登录
 */
class LoginFragment : LisperFragment<LoginRequestViewModel, FragmentLoginBinding>() {

    private val loginStateViewModel: LoginStateViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.fragment_login

    override fun initView(savedInstanceState: Bundle?) {
        binding.vm = loginStateViewModel

        ClickUtils.applySingleDebouncing(arrayOf(binding.tvLogin, binding.tvSend)){
            when(it){
                binding.tvLogin -> {
                    nav(R.id.action_loginFragment_to_mainFragment)
                }
                binding.tvSend -> {

                }
                else ->{}
            }
        }
    }

    override fun createObserver() {
        viewModel.loginResultLiveData.observe(viewLifecycleOwner){
            back()
        }
    }
}