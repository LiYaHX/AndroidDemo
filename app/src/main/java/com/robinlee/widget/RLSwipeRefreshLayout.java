package com.robinlee.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by RobinLee on 3/26/16.
 *
 * @author Lior Iluz
 * @see "http://stackoverflow.com/questions/23989910/horizontalscrollview-inside-swiperefreshlayout/23989911#23989911"
 *
 */
public class RLSwipeRefreshLayout extends SwipeRefreshLayout {

    private static final String TAG = RLSwipeRefreshLayout.class.getSimpleName();

    private int mTouchSlop;
    private float mPrevX;

    public RLSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public RLSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                mPrevX = MotionEvent.obtain(ev).getX();
                break;
            case MotionEvent.ACTION_MOVE:
                final float eventX = ev.getX();
                float xDiff = Math.abs(eventX - mPrevX);
                if(xDiff > mTouchSlop){
                    return false;
                }
                break;
            default:
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

}
