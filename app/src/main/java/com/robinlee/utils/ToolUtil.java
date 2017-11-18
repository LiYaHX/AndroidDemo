package com.robinlee.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by robinlee on 2017/11/18.
 */

public class ToolUtil {

    private Context mContext;

    public ToolUtil(Context context) {
        this.mContext = context;
    }

    /**
     * Get display metrics.
     *
     * @return
     */
    public DisplayMetrics getDisplayMetrics(){

        if(mContext == null){
            return null;
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

}
