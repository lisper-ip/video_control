package app.lonzh.videocontrol.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 验证码倒计时
 */
public final class CountdownView extends AppCompatTextView implements Runnable {

    /**
     * 是否手动结束
     */
    private boolean handleStop = false;

    /** 倒计时秒数 */
    private int mTotalSecond = 60;
    /** 秒数单位文本 */
    private static final String TIME_UNIT = "S";

    /** 当前秒数 */
    private int mCurrentSecond;
    /** 记录原有的文本 */
    private CharSequence mRecordText;

    public CountdownView(Context context) {
        super(context);
    }

    public CountdownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CountdownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        getPaint().setFakeBoldText(true);
        super.onDraw(canvas);
    }

    /**
     * 设置倒计时总秒数
     */
    public void setTotalTime(int totalTime) {
        this.mTotalSecond = totalTime;
    }

    /**
     * 开始倒计时
     */
    public void start() {
        handleStop = false;
        mRecordText = getText();
        setEnabled(false);
        mCurrentSecond = mTotalSecond;
        post(this);
    }

    /**
     * 结束倒计时
     */
    public void autoStop() {
        setText(mRecordText);
        setEnabled(true);
    }

    /**
     * 手动结束
     */
    public void stop(){
        handleStop = true;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 设置点击的属性
        setClickable(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        // 移除延迟任务，避免内存泄露
        removeCallbacks(this);
        super.onDetachedFromWindow();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void run() {
        if (mCurrentSecond == 0 || handleStop) {
            autoStop();
        } else {
            mCurrentSecond--;
            setText(mCurrentSecond + " " + TIME_UNIT);
            postDelayed(this, 1000);
        }
    }
}