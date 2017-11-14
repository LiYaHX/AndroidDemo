package com.robinlee.lee.widget;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.robinlee.androiddemo.R;

public class CustomDrawableTextViewActivity extends AppCompatActivity {

    private static final String LOG_TAG     = "CustomDrawableTextView";
    private static final float SCALE_RADIO  = 1.5f;

    private TextView mTextViewDrawableNative;
    private TextView mTextViewDrawableCustomSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_drawable_text_view);

        initWidget();
    }

    private void initWidget(){
        mTextViewDrawableNative         = (TextView) findViewById(R.id.mTextViewDrawableNative);
        mTextViewDrawableCustomSize     = (TextView) findViewById(R.id.mTextViewDrawableCustomSize);
        Drawable[] textViewNativeDrawables    = mTextViewDrawableNative.getCompoundDrawables();
        Drawable nativeDrawableTop = textViewNativeDrawables[1];
        if(nativeDrawableTop != null){

            Rect nativeTopRect = nativeDrawableTop.getBounds();
            Log.i(LOG_TAG, "native---left : " + nativeTopRect.left +
                    "; top : " + nativeTopRect.top +
                    "; right : " + nativeTopRect.right +
                    "; bottom : " + nativeTopRect.bottom);
            int left    = nativeTopRect.left;
            int top     = nativeTopRect.top;
            int right   = nativeTopRect.right;
            int bottom  = nativeTopRect.bottom;

            // Desc : Java code scale the drawable of TextView.
            Drawable customDrawableTop = getDrawable(R.drawable.ic_logo_apache);
            if(customDrawableTop != null) {

                customDrawableTop.setBounds(left, top, (int) (right *  SCALE_RADIO), (int) (bottom * SCALE_RADIO));
                mTextViewDrawableCustomSize.setCompoundDrawables(null, customDrawableTop, null, null);
                Rect customTopRect = customDrawableTop.getBounds();
                Log.i(LOG_TAG, "custom---left : " + customTopRect.left +
                        "; custom top : " + customTopRect.top +
                        "; custom right : " + customTopRect.right +
                        "; custom bottom : " + customTopRect.bottom);
            }
        }
    }

}
