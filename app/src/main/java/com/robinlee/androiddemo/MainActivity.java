package com.robinlee.androiddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;

import com.robinlee.xmlparser.XmlParserActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnXmlParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        mBtnXmlParser = (Button) this.findViewById(R.id.mBtnXmlParser);
        mBtnXmlParser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mBtnXmlParser:
                startActivity(new Intent(MainActivity.this, XmlParserActivity.class));
                break;
            default:

                break;
        }
    }
}
