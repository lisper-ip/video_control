package app.lonzh.videocontrol.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import app.lonzh.videocontrol.R
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ScreenUtils
import com.drake.logcat.LogCat

/**
 * 柱状图item
 */
class BarGraphItem : View {
    private var barWith = 0f

    private var ratio: Float = 0.2f

    private var tip: String = "120"

    private lateinit var barPaint: Paint

    private lateinit var textPaint: TextPaint

    constructor(context: Context) : super(context){
        initArgs(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        initArgs(context)
    }

    private fun initArgs(context: Context) {
        barWith = context.resources.getDimension(R.dimen.dp_40)

        barPaint = Paint().apply {
            color = ContextCompat.getColor(context, R.color.color_bar)
            isAntiAlias = true
            isDither = true
        }

        textPaint = TextPaint().apply {
            color = ContextCompat.getColor(context, R.color.white)
            isAntiAlias = true
            isDither = true
            textSize = ConvertUtils.dp2px(12f).toFloat()
        }
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //画柱状
        if(ratio > 0){
            val rect = Rect()
            if(tip.isNotEmpty()){
                textPaint.getTextBounds(tip, 0, tip.length, rect)
            }
            val ratioHeight = (height - (rect.bottom - rect.top) * 2) * ratio
            canvas.save()
            canvas.translate(0f,height - ratioHeight)
            val rectF = RectF((width - barWith)/2, 0f, (width + barWith)/2, ratioHeight)
            canvas.drawRoundRect(rectF, 10f, 10f, barPaint)

            if(tip.isNotEmpty()){
                canvas.drawText(tip, ((width - (rect.right - rect.left)) / 2).toFloat(),
                    0f - (rect.bottom - rect.top), textPaint)
            }
            canvas.restore()
        }
    }

    fun setRatioAndTip(ratio: Float, tip: String){
        this.ratio = ratio
        this.tip = tip
        invalidate()
    }

    fun setRatio(ratio: Float){
        this.ratio = ratio
        invalidate()
    }

    fun getRatio(): Float{
        return ratio
    }

}