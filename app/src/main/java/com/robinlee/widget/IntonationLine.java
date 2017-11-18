package com.robinlee.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by robinlee on 2017/11/17.
 */

public class IntonationLine extends View {

    private static final String TAG = "IntonationLine";
    private static final int INIT_INTONATION_LINE = 100;
    private static final int RADIO_OF_INTONATION_LINE = 10;
    private int widthOfLine = INIT_INTONATION_LINE;
    private int heightOfIntonationLine;
    private Context mContext;
    private int count = 0;
    private CountDownTimer mCountDownTimer = new CountDownTimer(2000, 200) {

        @Override
        public void onTick(long millisUntilFinished) {

            Log.i(TAG, "millisUntilFinished : " + millisUntilFinished + "; count : " + count);
            invalidate();
            count += 1;
        }

        @Override
        public void onFinish() {

        }
    };

    private Paint mPaint = new Paint();
    static int[] colors = new int[]{
            Color.BLACK,
            Color.DKGRAY,
            Color.GRAY,
            Color.LTGRAY,
            Color.WHITE,
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.YELLOW,
            Color.CYAN
    };
//    static float[] pts      = new float[]{
//
//            10.0f, 200.0f, 110.0f, 202.0f,
//            110.0f, 200.0f, 310.0f, 202.0f,
//            310.0f, 200.0f, 410.0f, 202.0f,
//            410.0f, 200.0f, 510.0f, 202.0f
//    };

    public IntonationLine(Context context) {
        super(context);
        mContext = context;
        setScreenSize(getScreenWith(), getScreenHight());
        mCountDownTimer.start();
    }

    public IntonationLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setScreenSize(getScreenWith(), getScreenHight());
        mCountDownTimer.start();
    }

    public IntonationLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setScreenSize(getScreenWith(), getScreenHight());
        mCountDownTimer.start();
    }

    public IntonationLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        setScreenSize(getScreenWith(), getScreenHight());
        mCountDownTimer.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawLine(canvas, mPaint, count);
        Log.i(TAG, "draw() " + count);
//        mPaint.setColor(colors[0]);
//        canvas.drawLine(widthOfLine * 0, heightOfIntonationLine, widthOfLine * 1, heightOfIntonationLine, mPaint);
//        mPaint.setColor(colors[1]);
//        canvas.drawLine(widthOfLine * 1, heightOfIntonationLine, widthOfLine * 2, heightOfIntonationLine, mPaint);
//        mPaint.setColor(colors[2]);
//        canvas.drawLine(widthOfLine * 2, heightOfIntonationLine, widthOfLine * 3, heightOfIntonationLine, mPaint);
//        mPaint.setColor(colors[3]);
//        canvas.drawLine(widthOfLine * 3, heightOfIntonationLine, widthOfLine * 4, heightOfIntonationLine, mPaint);
//        mPaint.setColor(colors[4]);
//        canvas.drawLine(widthOfLine * 4, heightOfIntonationLine, widthOfLine * 5, heightOfIntonationLine, mPaint);
//        mPaint.setColor(colors[5]);
//        canvas.drawLine(widthOfLine * 5, heightOfIntonationLine, widthOfLine * 6, heightOfIntonationLine, mPaint);
//        mPaint.setColor(colors[6]);
//        canvas.drawLine(widthOfLine * 6, heightOfIntonationLine, widthOfLine * 7, heightOfIntonationLine, mPaint);
//        mPaint.setColor(colors[7]);
//        canvas.drawLine(widthOfLine * 7, heightOfIntonationLine, widthOfLine * 8, heightOfIntonationLine, mPaint);
//        mPaint.setColor(colors[8]);
//        canvas.drawLine(widthOfLine * 8, heightOfIntonationLine, widthOfLine * 9, heightOfIntonationLine, mPaint);
//        mPaint.setColor(colors[9]);
//        canvas.drawLine(widthOfLine * 9, heightOfIntonationLine, widthOfLine * 10, heightOfIntonationLine, mPaint);
//          canvas.drawLines(pts, mPaint);
    }

    private void drawLine(Canvas canvas, Paint paint, int count) {

        for (int i = 0; i <= count; i++) {
            paint.setColor(colors[i]);
            canvas.drawLine(widthOfLine * i, heightOfIntonationLine, widthOfLine * (i + 1), heightOfIntonationLine, mPaint);
        }
    }

    public void setScreenSize(int screenWidth, int screenHeight) {

        widthOfLine = screenWidth / RADIO_OF_INTONATION_LINE;
        heightOfIntonationLine = screenHeight / 2;

        invalidate();
    }

    private int getScreenWith() {
        return getDisplayMetrics().widthPixels;
    }

    private int getScreenHight() {
        return getDisplayMetrics().heightPixels;
    }

    /**
     * Get display metrics.
     *
     * @return
     */
    private DisplayMetrics getDisplayMetrics() {

        if (mContext == null) {
            return null;
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

}
