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

/**
 * 首页百分比View
 */
class HomePercentView : ConstraintLayout {
    private var homeStateViewModel: HomeStateViewModel? = null

    private lateinit var ivOut: ImageView
    private lateinit var ivIn: ImageView
    private lateinit var ivProgressView: CircleProgressView
    private lateinit var tvPercent: TextView

    constructor(context: Context) : super(context) {
        initArgs(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initArgs(context)
    }

    private fun initArgs(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.layout_home_percent, this, true)

        ivOut = findViewById(R.id.iv_out)
        ivIn = findViewById(R.id.iv_in)
        ivProgressView = findViewById(R.id.iv_progress)
        tvPercent = findViewById(R.id.tv_percent)
    }

    fun setHost(fragment: HomeFragment) {
        homeStateViewModel = ViewModelProvider(fragment).get(HomeStateViewModel::class.java)
        startAnim()
    }

    private fun startAnim(){
        var loadAnimation1 = AnimationUtils.loadAnimation(context, R.anim.anim_circle_z_b)
        ivOut.startAnimation(loadAnimation1)
        var loadAnimation2 = AnimationUtils.loadAnimation(context, R.anim.anim_circle_f_b)
        ivIn.startAnimation(loadAnimation2)
    }
}
