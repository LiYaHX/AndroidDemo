package com.robinlee.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.robinlee.androiddemo.R;

/**
 *
 * @author RobinLee
 * @since  3/16/17
 */
public class ShimmerActivity extends Activity {

    public static final int ANIMATION_DIRECTION_LTR = 0;
    public static final int ANIMATION_DIRECTION_RTL = 1;

    private static final int DEFAULT_REPEAT_COUNT = ValueAnimator.INFINITE;
    private static final int DEFAULT_DURATION     = 1000;
    private static final int DEFAULT_START_DELAY        = 0;
    private static final int DEFAULT_DETRECTION   = ANIMATION_DIRECTION_LTR;

    private ShimmerTextView mTextViewShimmer;
    private Shimmer shimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shimmer);
        initWidget();
    }

    private void initWidget() {
        mTextViewShimmer = (ShimmerTextView) this.findViewById(R.id.mTextViewShimmer);
        mTextViewShimmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shimmerAnimation();
            }
        });
    }

    private void shimmerAnimation() {

        if (shimmer != null && shimmer.isAnimating()) {
            shimmer.cancel();
        } else {
            shimmer = new Shimmer();
            shimmer.start(mTextViewShimmer);
        }
    }

}
