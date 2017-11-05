package com.robinlee.widget;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.robinlee.androiddemo.R;

public class RadioGroupActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton mRadioBtnAsc;
    private RadioButton mRadioBtnDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_group);
        initWidget();
    }

    private void initWidget() {
        radioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        mRadioBtnAsc = (RadioButton) findViewById(R.id.mRadioBtnAsc);
        mRadioBtnAsc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonView.setTypeface(isChecked ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
            }
        });
        mRadioBtnDesc = (RadioButton) findViewById(R.id.mRadioBtnDesc);
        mRadioBtnDesc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonView.setTypeface(isChecked ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
            }
        });
    }
}
