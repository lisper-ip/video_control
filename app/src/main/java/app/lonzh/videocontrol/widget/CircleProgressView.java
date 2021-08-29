package app.lonzh.videocontrol.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * author : Lee
 * date   : 2020/4/22 10:01
 * desc   :
 */
public class CircleProgressView extends View {
    private Context mContext;
    private int mWidth, mHeight, centerX, centerY, maxRadius;
    private float angle = 9;
    private int lineWidth = 30;
    private int mPaintWidth = 6;
    private Paint mPaint;
    private String colors[];
    private int progress;
    private int maxNum;
    private static final String TAG = "CircleProgressView";

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        measure();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        measure();
    }

    void measure() {
        centerX = mWidth / 2;
        centerY = mHeight / 2;
        maxRadius = Math.min(mWidth, mHeight) / 2;
    }

    private void init(Context mContext) {
        this.mContext = mContext;
        if (centerX == 0 || centerY == 0) {

        }
        colors = new String[]{"#50000000", "#29A3C2"};
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor(colors[0]));
        mPaint.setStrokeWidth(mPaintWidth);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        Log.e(TAG, "setProgress: " + progress);
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (centerX == 0 || centerY == 0) {
            return;
        }
        for (int i = 0; i < ((int)360 / angle); i++) {
            if (i  <= (progress)) {
                mPaint.setColor(Color.parseColor(colors[1]));
            } else {
                mPaint.setColor(Color.parseColor(colors[0]));
            }
            canvas.drawLine(centerX, centerY - maxRadius, centerX, centerY - maxRadius + lineWidth, mPaint);
            canvas.rotate(angle, centerX, centerY);
        }
    }

    public void setMax(int num) {
        maxNum=num;
        postInvalidate();
    }
}
