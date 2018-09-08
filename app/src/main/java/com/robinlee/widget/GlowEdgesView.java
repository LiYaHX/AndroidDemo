package com.robinlee.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.robinlee.androiddemo.R;

public class GlowEdgesView extends View{

    private Paint mPaint;
    private Rect mSrcRect;
    private Rect mDestRect;
    private Bitmap bmp;
    private Bitmap shadowBitmap;
    private int width;
    private int height;

    public GlowEdgesView(Context context) {
        super(context);
        init(context, null);
    }

    public GlowEdgesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GlowEdgesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public GlowEdgesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        // 关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GlowEdgesView, 0, 0);
        int n = a.getIndexCount();
        Drawable src_resource;
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.GlowEdgesView_light_color:
                    mPaint.setColor(a.getColor(attr, Color.WHITE));
                    break;
                case R.styleable.GlowEdgesView_light_radius:
                    mPaint.setMaskFilter(new BlurMaskFilter(a.getFloat(attr, 5f), BlurMaskFilter.Blur.OUTER));
                    mPaint.setAlpha(180);   // 可根据实际情况调整
                    break;
                case R.styleable.GlowEdgesView_light_src:
                    src_resource = a.getDrawable(attr);
                    bmp = drawableToBitmap(src_resource);
                    break;
            }
        }

    }


    @SuppressLint("DrawAllocation")
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = this.getMeasuredWidth();
        height = this.getMeasuredHeight();
        // 获取位图的Alpha通道图
        shadowBitmap = Bitmap.createScaledBitmap(bmp.extractAlpha(), width, height, true);
        bmp = Bitmap.createScaledBitmap(bmp, width, height, true);
        mSrcRect = new Rect(0, 0, shadowBitmap.getWidth(), shadowBitmap.getHeight());
        mDestRect = new Rect(0, 0, shadowBitmap.getWidth(), shadowBitmap.getHeight());
        canvas.drawBitmap(shadowBitmap, mSrcRect, mDestRect, mPaint);
        canvas.drawBitmap(bmp, mSrcRect, mSrcRect, null);
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888: Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }
    /**
     * 重写onMeasure，解决在wrap_content下与match_parent效果一样的问题
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = width;
        int width = height;
        final int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        final int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        final int heightSpecSize = MeasureSpec.getMode(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width,height);
        }else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width,heightSpecSize);
        }else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize,height);
        }
    }
}