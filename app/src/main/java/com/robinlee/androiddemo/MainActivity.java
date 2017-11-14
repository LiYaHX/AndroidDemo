package com.robinlee.androiddemo;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.robinlee.lee.widget.CustomDrawableTextViewActivity;
import com.robinlee.material.design.MaterialDesignActivity;
import com.robinlee.widget.BottomNavigationActivity;
import com.robinlee.widget.CopyAndPasteTextViewActivity;
import com.robinlee.widget.LoopViewPagerActivity;
import com.robinlee.widget.RadioGroupActivity;
import com.robinlee.widget.ShimmerActivity;
import com.robinlee.widget.TimePickerActivity;
import com.robinlee.widget.TimeSelectoriOSActivity;
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

    /**
     * 2016-09-06
     *
     * Wait for testing
     */
    private void initData(){

        List<HashMap<String, Class>> demos = new ArrayList<HashMap<String, Class>>();

        Resources resources = getResources();
        demos.add((HashMap<String, Class>) createTaskItem(resources.getString(R.string.str_title_xml_parse), XmlParserActivity.class));
        demos.add(createTaskItem(resources.getString(R.string.str_title_shimmer), ShimmerActivity.class));
        demos.add(createTaskItem(resources.getString(R.string.str_title_loop_view_pager), LoopViewPagerActivity.class));
        demos.add(createTaskItem(resources.getString(R.string.str_title_bottom_navigation), BottomNavigationActivity.class));
        demos.add(createTaskItem(resources.getString(R.string.str_title_search_view), SearchViewActivity.class));
        demos.add(createTaskItem(resources.getString(R.string.str_title_time_picker), TimePickerActivity.class));
        demos.add(createTaskItem(resources.getString(R.string.str_title_time_selector), TimeSelectoriOSActivity.class));
        demos.add(createTaskItem(resources.getString(R.string.str_title_copy_and_paste_textview), CopyAndPasteTextViewActivity.class));
        demos.add(createTaskItem(resources.getString(R.string.str_title_radio_button), RadioGroupActivity.class));
        demos.add(createTaskItem(resources.getString(R.string.str_title_custom_drawable_textview), CustomDrawableTextViewActivity.class));
        mDemosAdapter = new DemosAdapter(MainActivity.this, demos);
    }

    /**
     * 2016-09-06
     *
     * Wait for testing
     *
     * @param taskName
     * @param taskActivity
     * @return
     */
    private HashMap<String, Class> createTaskItem(String taskName, Class taskActivity){
        HashMap<String, Class> task = new HashMap<String, Class>();
        task.put(taskName, taskActivity);
        return task;
    }

    private void initView() {
        mListViewDemoList = (ListView) this.findViewById(R.id.mListViewDemosList);
        mListViewDemoList.setAdapter(mDemosAdapter);
    }

}
