package com.robinlee.widget;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.robinlee.androiddemo.BaseActivity;
import com.robinlee.androiddemo.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RobinLee on 3/24/16.
 */
public class LoopViewPagerActivity extends BaseActivity {

    private LoopViewPager mLoopViewPager;
    private LoopIndicator mLoopIndicator;
    private List<String> mBanners = new ArrayList<String>();
    private LoopPagerAdapter mLoopPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_view_pager);
        initData();
        initView();
    }

    private void initData() {
        String banner1 = "1";
        String banner2 = "2";
        String banner3 = "3";
        String banner4 = "4";
        mBanners.add(banner1);
        mBanners.add(banner2);
        mBanners.add(banner3);
        mBanners.add(banner4);
    }

    private void initView() {
        mLoopViewPager = (LoopViewPager) this.findViewById(R.id.mLoopViewPager);
        mLoopIndicator = (LoopIndicator) this.findViewById(R.id.mLoopIndicator);
        mLoopIndicator.setItemCount(mBanners.size());
        mLoopPagerAdapter = new LoopPagerAdapter(LoopViewPagerActivity.this, mBanners);
        mLoopViewPager.setBoundaryCaching(true);
        mLoopViewPager.setAdapter(mLoopPagerAdapter);
        mLoopViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mLoopViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mLoopIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    /**
     * LoopViewPager animation when page changing.
     *
     * @author RobinLee
     * @since 2/2/16
     */
    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {

        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View page, float position) {

            int pageWidth = page.getWidth();
            int pageHeight = page.getHeight();

            if (position < -1) {    // [-Infinity,-1)
                // This page is way off-screen to the left.
                page.setAlpha(0);

            } else if (position <= 1) {     // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    page.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    page.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                page.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else {    // (1,+Infinity]
                // This page is way off-screen to the right.
                page.setAlpha(0);
            }
        }
    }
}
