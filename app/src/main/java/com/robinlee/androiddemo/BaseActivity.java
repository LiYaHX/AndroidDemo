package com.robinlee.androiddemo;

import java.io.File;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.support.v4.app.FragmentActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class BaseActivity extends FragmentActivity {
    private static final boolean DEBUG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheDir = getExternalCacheDir();
        if (cacheDir == null || !cacheDir.canWrite()) {
            final AlertDialog exitDialog = new AlertDialog.Builder(this)
                    .setTitle("sd卡不存在，应用将退出！")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.dismiss();
                                    finish();
                                }
                            }).create();
            exitDialog.show();
            return;
        }

    }

    /**
     * @usedBy Easemob
     */
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    /**
     * 返回
     *
     * @param view
     * @usedBy Easemob
     */
    public void back(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left_unbounded, R.anim.slide_out_right_unbounded);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
        overridePendingTransition(R.anim.slide_in_right_unbounded, R.anim.slide_out_left_unbounded);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        overridePendingTransition(R.anim.slide_in_right_unbounded, R.anim.slide_out_left_unbounded);
    }

}
