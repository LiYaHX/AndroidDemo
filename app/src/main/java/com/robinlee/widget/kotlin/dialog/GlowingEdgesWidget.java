package com.robinlee.widget.kotlin.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.robinlee.androiddemo.R;

public class GlowingEdgesWidget extends AppCompatRadioButton {

    public GlowingEdgesWidget(Context context) {
        super(context);
    }

    public GlowingEdgesWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GlowingEdgesWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
//            setGlowBackground();
        }
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(background);
//        setGlowBackground();
    }

}
