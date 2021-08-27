package app.lonzh.videocontrol.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


/**
 *
 * @ProjectName:    kmc_travel
 * @Description:    描述
 * @Author:         Lisper
 * @CreateDate:     2021/7/26 5:39 下午
 * @UpdateUser:     Lisper：
 * @UpdateDate:     2021/7/26 5:39 下午
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class NoScrollViewPager : ViewPager {
    private var noScroll = true

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context) : super(context)

    fun setNoScroll(noScroll: Boolean) {
        this.noScroll = noScroll
    }

    override fun scrollTo(x: Int, y: Int) {
        super.scrollTo(x, y)
    }

    override fun onTouchEvent(arg0: MotionEvent?): Boolean {
        return if (!noScroll) false else super.onTouchEvent(arg0)
    }

    override fun onInterceptTouchEvent(arg0: MotionEvent?): Boolean {
        return if (!noScroll) false else super.onInterceptTouchEvent(arg0)
    }

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        super.setCurrentItem(item, smoothScroll)
    }

    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item)
    }
}