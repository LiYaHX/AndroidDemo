package com.robinlee.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.robinlee.androiddemo.R;

public class IntonationLineActivity extends AppCompatActivity {

    // 1. Timer 定时器； refresh / 200ms；
    // 2. 音准： screenHeight * radio(百分比——0至100随机数)，50% * screenHeight为标准；
    // 3. canvas 平移；

    private Button  mBtnStartDrawLine;
    private Button  mBtnStopDrawLine;
    private IntonationLine mIntonationLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intonation_line);
        initWidget();
    }

    private void initWidget(){
        mBtnStartDrawLine = (Button) findViewById(R.id.mBtnStartDrawLine);
        mBtnStartDrawLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntonationLine.startDrawLine();
            }
        });
        mBtnStopDrawLine = (Button) findViewById(R.id.mBtnStopDrawLine);
        mBtnStopDrawLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntonationLine.stopDrawLine();
            }
        });
        mIntonationLine = (IntonationLine) findViewById(R.id.mIntonationLine);
    }

}
