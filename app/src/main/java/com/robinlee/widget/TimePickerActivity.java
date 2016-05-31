package com.robinlee.widget;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.robinlee.androiddemo.R;

/**
 * Created by RobinLee on 4/29/16.
 */
public class TimePickerActivity extends Activity{

    private TimePicker mTimePicker;
    private Button mBtnTimePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        initView();
    }

    private void initView(){
        mTimePicker = (TimePicker) this.findViewById(R.id.timePicker);
        mTimePicker.setCurrentHour(12);
        mTimePicker.setCurrentMinute(15);
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(TimePickerActivity.this, "hourOfDay", Toast.LENGTH_SHORT).show();
            }
        });

        mBtnTimePickerDialog = (Button) this.findViewById(R.id.mBtnTimePickerDialog);
        mBtnTimePickerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(TimePickerActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(TimePickerActivity.this, "dialog hourOfDay", Toast.LENGTH_SHORT).show();
                    }
                }, 12, 12, true);
                timePickerDialog.show();
            }
        });
    }
}
