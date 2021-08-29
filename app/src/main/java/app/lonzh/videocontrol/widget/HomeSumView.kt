package app.lonzh.videocontrol.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import app.lonzh.videocontrol.R
import app.lonzh.videocontrol.fragment.main.HomeFragment
import app.lonzh.videocontrol.vm.state.home.HomeStateViewModel

class HomeSumView : ConstraintLayout {
    private var homeStateViewModel: HomeStateViewModel? = null

    private lateinit var ivOut: ImageView
    private lateinit var ivIn: ImageView
    private lateinit var ivMid: ImageView
    private lateinit var tvSum: TextView

    constructor(context: Context) : super(context) {
        initArgs(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initArgs(context)
    }

    private fun initArgs(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.layout_home_sum, this, true)

        ivOut = findViewById(R.id.iv_out)
        ivIn = findViewById(R.id.iv_in)
        ivMid = findViewById(R.id.iv_mid)
        tvSum = findViewById(R.id.tv_sum)
    }

    fun setHost(fragment: HomeFragment) {
        homeStateViewModel = ViewModelProvider(fragment).get(HomeStateViewModel::class.java)
        startAnim()
    }

    private fun startAnim(){
        var loadAnimation1 = AnimationUtils.loadAnimation(context, R.anim.anim_circle_z)
        ivOut.startAnimation(loadAnimation1)
        var loadAnimation2 = AnimationUtils.loadAnimation(context, R.anim.anim_circle_f)
        ivIn.startAnimation(loadAnimation2)
    }
}