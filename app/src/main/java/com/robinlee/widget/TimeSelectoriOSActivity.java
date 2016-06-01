package com.robinlee.widget;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.robinlee.androiddemo.BaseActivity;
import com.robinlee.androiddemo.R;
import org.feezu.liuli.timeselector.TimeSelector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by RobinLee on 6/1/16.
 */
public class TimeSelectoriOSActivity extends BaseActivity{

    private static final String DEF_START_TIME = "1970-01-01 20:47";
    private static final String DEF_END_TIME = "2099-12-30 00:00";
    private String mStartTime = DEF_START_TIME;
    private String mEndTime = DEF_END_TIME;

    private TimeSelector mTimeSelector;
    private Button mBtnStartTime;
    private Button mBtnEndTime;
    private boolean mIsStartTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_selector);
        initView();
    }

    private void initView(){
        mBtnStartTime = (Button) this.findViewById(R.id.mBtnStartTime);
        mBtnStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsStartTime = true;
                mTimeSelector.show();
            }
        });
        mBtnEndTime = (Button) this.findViewById(R.id.mBtnEndTime);
        mBtnEndTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mIsStartTime = false;
                mTimeSelector.show();
            }
        });
        showTimeSelector();
    }

    private void showTimeSelector(){
        mStartTime = getCurrentTimeStr();
        mTimeSelector = new TimeSelector(TimeSelectoriOSActivity.this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                if(mIsStartTime){
                    mStartTime = time;
                    Toast.makeText(TimeSelectoriOSActivity.this, time, Toast.LENGTH_SHORT).show();
                }else{
                    mEndTime = time;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
                    try {
                        Date startDate = simpleDateFormat.parse(mStartTime);
                        Date endDate = simpleDateFormat.parse(mEndTime);
                        long startTime = startDate.getTime();
                        long endTime = endDate.getTime();
                        if(startTime > endTime){
                            Toast.makeText(TimeSelectoriOSActivity.this, "> 错误", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(TimeSelectoriOSActivity.this, "< 正确", Toast.LENGTH_SHORT).show();
                        }
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                }
            }
        }, mStartTime, mEndTime);
        mTimeSelector.setIsLoop(true);
    }

    private String getCurrentTimeStr(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        return simpleDateFormat.format(new Date());
    }
}
