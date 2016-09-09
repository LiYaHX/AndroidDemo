package com.robinlee.material.design;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.robinlee.androiddemo.DemosAdapter;
import com.robinlee.androiddemo.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by robinlee on 16/9/6.
 */
public class MaterialDesignActivity extends AppCompatActivity{

    private DemosAdapter mMaterialDesignDemosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design_main);
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
        demos.add(createTaskItem(resources.getString(R.string.str_title_bottom_sheet), BottomSheetsActivity.class));

        mMaterialDesignDemosAdapter = new DemosAdapter(MaterialDesignActivity.this, demos);
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
        ListView mListViewMaterialDesignDemosList = (ListView) this.findViewById(R.id.mListViewMaterialDesignDemosList);
        mListViewMaterialDesignDemosList.setAdapter(mMaterialDesignDemosAdapter);
    }

}
