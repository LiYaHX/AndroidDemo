package com.robinlee.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by robinlee on 2017/11/17.
 */

public class IntonationLine extends View {

    private static final String TAG = "IntonationLine";
    private static final int RADIO_OF_INTONATION_LINE   = 10;
    private int mWidthOfIntonationLine;
    private int mHeightOfIntonationLine;
    private int              mLineCount = 0;
    private static final int MIN_RANDOM = 50;
    private static final int MAX_RANDOM = 150;
    private Random           mRandom    = new Random();
    private int mRandomNum;

    private long mDelayMillis   = 200;    // ms
    private Handler mHandler    = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private Runnable mRunnable  = new Runnable() {
        @Override
        public void run() {
            invalidate();
            mHandler.postDelayed(this, mDelayMillis);
            mLineCount += 1;
        }
    };

    private Paint mNormalPaint = new Paint();
    private Paint mPaint = new Paint();
    static int[] colors = new int[]{
            Color.BLACK,
            Color.DKGRAY,
            Color.GRAY,
            Color.LTGRAY,
            Color.MAGENTA,
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.GRAY,
            Color.CYAN
    };

    public IntonationLine(Context context) {
        super(context);
        setScreenSize(getScreenWith(), getScreenHight());
        initPaint(mPaint);
    }

    public IntonationLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setScreenSize(getScreenWith(), getScreenHight());
        initPaint(mPaint);
    }

    public IntonationLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setScreenSize(getScreenWith(), getScreenHight());
        initPaint(mPaint);
    }

    public IntonationLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setScreenSize(getScreenWith(), getScreenHight());
        initPaint(mPaint);
    }

    private void initPaint(Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);                   // 抗锯齿
        paint.setStrokeWidth(5.0f);                 // 线宽度
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    public void startDrawLine() {
        mHandler.postDelayed(mRunnable, mDelayMillis);
    }

    public void stopDrawLine(){
        mHandler.removeCallbacks(mRunnable);
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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawLine(canvas, mPaint, mLineCount);
    }

    PointF pointInit = new PointF(0.0f, 0.0f);
    PointF pointStart = new PointF(0.0f, 0.0f);
    PointF pointStop = new PointF(0.0f, mHeightOfIntonationLine);
    float offset = 0.0f;
    ArrayList<Integer> historyRadom = new ArrayList<>();

    private void drawLine(Canvas canvas, Paint paint, int count) {

        mNormalPaint.setColor(colors[1]);
        pointStart.set(0.0f, 0.0f);
        pointStop.set(0.0f, mHeightOfIntonationLine);

        for (int i = 0; i <= count; i++) {
            paint.setColor(colors[i % RADIO_OF_INTONATION_LINE]);

            if (i < count) {
                mRandomNum = historyRadom.get(i);
            } else {
                mRandomNum = mRandom.nextInt(MAX_RANDOM - MIN_RANDOM) + MIN_RANDOM;
                historyRadom.add(mRandomNum);
            }

            pointStart.set(pointStop.x, pointStop.y);
            offset = pointStart.y - mHeightOfIntonationLine;
            if (offset >= 0) {
                pointStop.set(pointInit.x + (i + 1) * mWidthOfIntonationLine, mHeightOfIntonationLine - historyRadom.get(i));
            } else {
                pointStop.set(pointInit.x + (i + 1) * mWidthOfIntonationLine, mHeightOfIntonationLine + historyRadom.get(i));
            }
            canvas.drawLine(pointStart.x - count * 20, pointStart.y, pointStop.x - count * 20, pointStop.y, mPaint);
            canvas.drawLine(0.0f, mHeightOfIntonationLine, getScreenWith(), mHeightOfIntonationLine, mNormalPaint);
        }
    }

    public void setScreenSize(int screenWidth, int screenHeight) {

        mWidthOfIntonationLine = screenWidth / RADIO_OF_INTONATION_LINE;
        mHeightOfIntonationLine = screenHeight / 2;

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
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

}
