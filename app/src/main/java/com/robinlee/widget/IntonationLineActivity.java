package com.robinlee.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.robinlee.androiddemo.R;

public class IntonationLineActivity extends AppCompatActivity {

    // 1. Timer 定时器； refresh / 200ms；
    // 2. 音准： screenHeight * radio(百分比——0至100随机数)，50% * screenHeight为标准；
    // 3. canvas 平移；



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intonation_line);
    }


}
