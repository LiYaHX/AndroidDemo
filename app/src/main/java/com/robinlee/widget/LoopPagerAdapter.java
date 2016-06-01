package com.robinlee.widget;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.robinlee.androiddemo.BuildConfig;
import com.robinlee.androiddemo.R;
import com.robinlee.androiddemo.SearchViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Home banner
 *
 * Created by RobinLee on 1/27/16.
 * @author RobinLee
 * @version 1.2
 */
public class LoopPagerAdapter extends PagerAdapter implements View.OnClickListener{

    private Context mContext;
    private List<String> mList = new ArrayList<String>();

    private boolean mIsLoop = false;

    public LoopPagerAdapter(Context context, List<String> list) {
        this.mContext = context;
        if(list != null){
            this.mList = list;
        }
    }

    /**
     * 初始化itemView
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        if(BuildConfig.DEBUG){
            Log.i(LoopPagerAdapter.class.getSimpleName(), "position : " + position);
        }

        View item = View.inflate(mContext, R.layout.item_of_home_banner, null);
        item.setOnClickListener(this);
        ImageView imgView = (ImageView) item.findViewById(R.id.mImgViewBannerItem);
        imgView.setImageResource(R.drawable.ic_loop_1 + position);
        container.addView(item, 0);
        return item;
    }

    @Override
    public void onClick(View v) {
        mContext.startActivity(new Intent(mContext, SearchViewActivity.class));     // RobinLee@2016-05-31 经测试不会黑屏
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getCount() {
        return this.mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public boolean isLoop(){
        return  this.mIsLoop;
    }

    public void setLoop(boolean isLoop){
        this.mIsLoop = isLoop;
    }

}
