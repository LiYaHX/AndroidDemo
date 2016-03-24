package com.robinlee.androiddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.robinlee.widget.LoopViewPagerActivity;
import com.robinlee.widget.ShimmerActivity;
import com.robinlee.xmlparser.XmlParserActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnXmlParser;
    private Button mBtnShimmer;
    private Button mBtnLoopViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        mBtnXmlParser = (Button) this.findViewById(R.id.mBtnXmlParser);
        mBtnXmlParser.setOnClickListener(this);
        mBtnShimmer = (Button) this.findViewById(R.id.mBtnShimmer);
        mBtnShimmer.setOnClickListener(this);
        mBtnLoopViewPager = (Button) this.findViewById(R.id.mBtnLoopViewPager);
        mBtnLoopViewPager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mBtnXmlParser:
                startActivity(new Intent(MainActivity.this, XmlParserActivity.class));
                break;
            case R.id.mBtnShimmer:
                startActivity(new Intent(MainActivity.this, ShimmerActivity.class));
                break;
            case R.id.mBtnLoopViewPager:
                startActivity(new Intent(MainActivity.this, LoopViewPagerActivity.class));
                break;
            default:

                break;
        }
    }
}
