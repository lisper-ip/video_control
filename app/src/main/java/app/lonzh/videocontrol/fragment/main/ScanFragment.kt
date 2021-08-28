package app.lonzh.videocontrol.fragment.main

import android.os.Bundle
import app.lonzh.baselibrary.util.RELAY_LOAD
import app.lonzh.commonlibrary.vm.BaseViewModel
import app.lonzh.travel.ext.back
import app.lonzh.videocontrol.R
import app.lonzh.videocontrol.databinding.FragmentScanBinding
import app.lonzh.videocontrol.fragment.base.LisperFragment
import cn.bingoogolapple.qrcode.core.QRCodeView


/**
 * 溯源
 */
class ScanFragment : LisperFragment<BaseViewModel, FragmentScanBinding>(), QRCodeView.Delegate {

    override val layoutId: Int
        get() = R.layout.fragment_scan

    override fun initView(savedInstanceState: Bundle?) {
        binding.zbarView.setDelegate(this)
    }

    override fun onStop() {
        super.onStop()
        binding.zbarView.stopCamera()
    }

    override fun onResume() {
        super.onResume()
        postDelayed({
            binding.zbarView.startSpot()
        }, RELAY_LOAD)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding.zbarView.onDestroy()
    }

    override fun onScanQRCodeSuccess(result: String?) {
        result?.let {
            toast(it)
        }
        binding.zbarView.startSpot()
        back()
    }

    override fun onCameraAmbientBrightnessChanged(isDark: Boolean) {

    }

    override fun onScanQRCodeOpenCameraError() {
        toast(getString(R.string.open_camera_failed))
    }
}