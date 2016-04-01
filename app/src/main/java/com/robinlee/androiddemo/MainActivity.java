package com.robinlee.androiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.robinlee.widget.BottomNavigationActivity;
import com.robinlee.widget.LoopViewPagerActivity;
import com.robinlee.widget.ShimmerActivity;
import com.robinlee.xmlparser.XmlParserActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DemosAdapter mDemosAdapter;
    private ListView mListViewDemoList;
    private HashMap<String, Class> mDemoItem = new HashMap<String, Class>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        List<HashMap<String, Class>> demos = new ArrayList<HashMap<String, Class>>();
        HashMap<String, Class> xmlParseDemo = new HashMap<String, Class>();
        xmlParseDemo.put(getResources().getString(R.string.str_xml_parse), XmlParserActivity.class);
        HashMap<String, Class> shimmerParseDemo = new HashMap<String, Class>();
        shimmerParseDemo.put(getResources().getString(R.string.str_shimmer), ShimmerActivity.class);
        HashMap<String, Class> loopViewPagerDemo = new HashMap<String, Class>();
        loopViewPagerDemo.put(getResources().getString(R.string.str_loop_view_pager), LoopViewPagerActivity.class);
        HashMap<String, Class> bottomNavigationDemo = new HashMap<String, Class>();
        bottomNavigationDemo.put(getResources().getString(R.string.str_bottom_navigation), BottomNavigationActivity.class);
        demos.add(xmlParseDemo);
        demos.add(shimmerParseDemo);
        demos.add(loopViewPagerDemo);
        demos.add(bottomNavigationDemo);

        mDemosAdapter = new DemosAdapter(MainActivity.this, demos);
    }

    private void initView() {
        mListViewDemoList = (ListView) this.findViewById(R.id.mListViewDemosList);
        mListViewDemoList.setAdapter(mDemosAdapter);
//        mListViewDemoList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

}
