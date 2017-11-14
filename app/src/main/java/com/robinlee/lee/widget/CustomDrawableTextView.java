package com.robinlee.lee.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import com.robinlee.androiddemo.R;

/**
 * Created by robinlee on 2017/11/10.
 */

public class CustomDrawableTextView extends AppCompatTextView {

    private static final String LOG_TAG = "CustomDrawableTextView";
    private static final float DEFAULT_DRAWABLE_RADIO = 0;

    private float leftDrawableRadio     = DEFAULT_DRAWABLE_RADIO;
    private float topDrawableRadio      = DEFAULT_DRAWABLE_RADIO;
    private float rightDrawableRadio    = DEFAULT_DRAWABLE_RADIO;
    private float bottomDrawableRadio   = DEFAULT_DRAWABLE_RADIO;

    public CustomDrawableTextView(Context context) {
        this(context, null, 0);
    }

    public CustomDrawableTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomDrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDrawableScaleRadioAttr(context, attrs, defStyleAttr, 0);
    }

    private void initDrawableScaleRadioAttr(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomDrawableTextView, defStyleAttr, defStyleRes);

        try {
            leftDrawableRadio = typedArray.getFloat(R.styleable.CustomDrawableTextView_left_drawable_scale_radio, DEFAULT_DRAWABLE_RADIO);
            topDrawableRadio = typedArray.getFloat(R.styleable.CustomDrawableTextView_top_drawable_scale_radio, DEFAULT_DRAWABLE_RADIO);
            rightDrawableRadio = typedArray.getFloat(R.styleable.CustomDrawableTextView_right_drawable_scale_radio, DEFAULT_DRAWABLE_RADIO);
            bottomDrawableRadio = typedArray.getFloat(R.styleable.CustomDrawableTextView_bottom_drawable_scale_radio, DEFAULT_DRAWABLE_RADIO);
        } finally {
            typedArray.recycle();
        }

        Drawable[] defDrawables = getCompoundDrawables();
        Drawable drawableLeft   = defDrawables[0];
        Drawable drawableTop    = defDrawables[1];
        Drawable drawableRight  = defDrawables[2];
        Drawable drawableBottom = defDrawables[3];

        if(leftDrawableRadio > 0){
            if(drawableLeft != null){
                Rect rectLeft = drawableLeft.getBounds();
                drawableLeft.setBounds(rectLeft.left, rectLeft.top, (int) (rectLeft.right * leftDrawableRadio), (int) (rectLeft.bottom * leftDrawableRadio));
            }
        }

        if(topDrawableRadio > 0){
            if(drawableTop != null){
                Rect rectTop = drawableTop.getBounds();
                Log.i(LOG_TAG, "pre_scale---left : " + rectTop.left +
                        "; top : " + rectTop.top +
                        "; right : " + rectTop.right +
                        "; bottom : " + rectTop.bottom);
                drawableTop.setBounds(rectTop.left, rectTop.top, (int) (rectTop.right * topDrawableRadio), (int) (rectTop.bottom * topDrawableRadio));
                Log.i(LOG_TAG, "after_scale---left : " + rectTop.left +
                        "; top : " + rectTop.top +
                        "; right : " + rectTop.right +
                        "; bottom : " + rectTop.bottom);
            }
        }

        if(rightDrawableRadio > 0){
            if(drawableRight != null){
                Rect rectRight = drawableRight.getBounds();
                drawableRight.setBounds(rectRight.left, rectRight.top, (int) (rectRight.right * rightDrawableRadio), (int) (rectRight.bottom * rightDrawableRadio));
            }
        }

        if(bottomDrawableRadio > 0){
            if(drawableBottom != null){
                Rect rectBottom = drawableBottom.getBounds();
                drawableBottom.setBounds(rectBottom.left, rectBottom.top, (int) (rectBottom.right * bottomDrawableRadio), (int) (rectBottom.bottom * bottomDrawableRadio));
            }
        }

        setCompoundDrawables(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }



      //    Override Handler writing function
//    private final ReloadWebViewHandler mReloadWebViewHandler = new ReloadWebViewHandler(NBProductDetailActivity.this);
//    class ReloadWebViewHandler extends Handler{
//
//        private WeakReference<NBProductDetailActivity> mWeakReference;
//
//        public ReloadWebViewHandler(NBProductDetailActivity nbProductDetailActivity) {
//            mWeakReference = new WeakReference<NBProductDetailActivity>(nbProductDetailActivity);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            NBProductDetailActivity nbProductDetailActivity = mWeakReference.get();
//            if(nbProductDetailActivity != null){
//                switch (msg.what){
//                    case MSG_SHOW_PROD_DETAUL:
//                        String prodId = (String) msg.obj;
//                        if(!TextUtils.isEmpty(prodId)){
//                            mProdId = prodId;
//                            getProdDetailInfo(prodId);
//                            mWebView.loadUrl(BaseNBangNetClient.getEnviroment() + NetConstant.WEB_PAGE_OF_PROD + prodId);
//                        }
//                        break;
//                    case MSG_CONTACT_MERCHANT:
//                        Merchant merchant = (Merchant) msg.obj;
//                        if(merchant != null){
//                            nbProductDetailActivity.onContactMerchant(merchant);
//                        }
//                        break;
//                    default:
//                        break;
//                }
//            }
//        }
//    }


}
