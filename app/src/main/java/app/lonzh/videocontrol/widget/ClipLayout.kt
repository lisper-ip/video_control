package app.lonzh.videocontrol.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import app.lonzh.videocontrol.R
import kotlin.math.pow

/**
 *
 * @ProjectName:    CustomView
 * @Description:    描述
 * @Author:         Lisper
 * @CreateDate:     2021/7/25 6:37 下午
 * @UpdateUser:     Lisper：
 * @UpdateDate:     2021/7/25 6:37 下午
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class ClipLayout : ConstraintLayout {
    private var clipStartColor = 0
    private var clipEndColor = 0
    private var clipHeight = 0f

    private var paint = Paint()

    constructor(context: Context) : super(context) {
        initialize(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleInt: Int) : super(
        context,
        attrs,
        defStyleInt
    ){
        initialize(context, attrs, defStyleInt)
    }

    private fun initialize(context: Context, attrs: AttributeSet?, defStyleInt: Int){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClipLayout)
        clipHeight = typedArray.getDimension(
            R.styleable.ClipLayout_clip_height,
        30f)

        clipStartColor = typedArray.getColor(
            R.styleable.ClipLayout_clip_start_color,
            ContextCompat.getColor(context, R.color.purple_700))

        clipStartColor = typedArray.getColor(
            R.styleable.ClipLayout_clip_start_color,
            ContextCompat.getColor(context, R.color.purple_700))

        typedArray.recycle()

        setWillNotDraw(false)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        canvas.save()
        paint.shader = LinearGradient((width/2).toFloat(), 0f, (width/2).toFloat(), height.toFloat(), Color.parseColor("#86F1C8"), Color.parseColor("#10D0A5"), Shader.TileMode.CLAMP)
        paint.isAntiAlias = true
        val radius = (width.toDouble().pow(2) + 4 * (clipHeight.toDouble().pow(2))) / (8 * clipHeight)
        canvas.translate((width / 2).toFloat(), (height - radius).toFloat())
        val rectF = RectF((-radius).toFloat(), (-radius).toFloat(), radius.toFloat(),
            radius.toFloat()
        )
        canvas.drawArc(rectF, 0f, 180f, true, paint)
        canvas.restore()
        super.onDraw(canvas)
    }
}