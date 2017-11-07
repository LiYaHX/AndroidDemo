package com.robinlee.androiddemo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    private static final String TAG = "StartActivity";

    private Button mBtnSkip;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initData();
        initWidget();
    }

    private void initData() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        startMainActivity();
                        break;
                    default:
                        break;
                }
            }
        };
        mHandler.sendEmptyMessageDelayed(0, 3000);
    }

    private void initWidget() {
        mBtnSkip = (Button) findViewById(R.id.mBtnSkip);
        mBtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });
    }

    private void startMainActivity() {
        mHandler.removeMessages(0);
        finish();
        startActivity(new Intent(StartActivity.this, MainActivity.class));
    }

}
