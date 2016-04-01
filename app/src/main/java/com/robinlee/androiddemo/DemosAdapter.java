package com.robinlee.androiddemo;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by RobinLee on 4/1/16.
 */
public class DemosAdapter extends BaseAdapter implements View.OnClickListener{

    private Context mContext;
    private List<HashMap<String, Class>> mDemos = new ArrayList<HashMap<String, Class>>();

    public DemosAdapter(Context context, List<HashMap<String, Class>> demos) {
        super();
        this.mContext = context;

        if(demos == null){
            return;
        }

        mDemos.clear();
        mDemos.addAll(demos);
    }

    @Override
    public int getCount() {
        return mDemos.size();
    }

    @Override
    public Object getItem(int position) {
        return mDemos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_of_listview_demo, null);
            holder = new Holder();
            holder.mBtnDemoItem = (Button) convertView.findViewById(R.id.mBtnDemoItem);
            holder.mBtnDemoItem.setOnClickListener(this);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }

        HashMap<String, Class> demoItem = (HashMap<String, Class>) getItem(position);
        if(demoItem != null){
            holder.mBtnDemoItem.setText(demoItem.keySet().toString());
            holder.mBtnDemoItem.setTag(demoItem);
        }

        return convertView;
    }

    @Override
    public void onClick(View v) {

        if(mContext == null){
            return;
        }

        HashMap<String, Class> demoItem = (HashMap<String, Class>) v.getTag();
        if(demoItem == null){
            return;
        }

        mContext.startActivity(new Intent(mContext, demoItem.values().iterator().next()));
    }

    class Holder{
        Button mBtnDemoItem;
    }

}
