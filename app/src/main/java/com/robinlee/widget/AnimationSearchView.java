package com.robinlee.widget;

import android.content.Context;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by RobinLee on 4/9/16.
 */
public class AnimationSearchView extends SearchView{

    private static final String TAG = "AnimationSearchView";

    public AnimationSearchView(Context context) {
        super(context);
    }

    public AnimationSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimationSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        Log.i(TAG, "changedView : " + changedView.toString() + "; visibility : " + visibility);
    }

    @Override
    public void onActionViewCollapsed() {
        super.onActionViewCollapsed();
    }
}
