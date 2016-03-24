package com.robinlee.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.robinlee.androiddemo.BuildConfig;
import com.robinlee.androiddemo.R;

import java.lang.reflect.Field;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by RobinLee on 1/29/16.
 *
 * A ViewPager subclass enabling infinite scrolling of the viewPager elements
 *
 * When used for paginating views (in opposite to fragments), no code changes
 * should be needed only change xml's from <android.support.v4.view.ViewPager>
 * to <com.imbryk.viewPager.LoopViewPager>
 *
 * If "blinking" can be seen when paginating to first or last view, simply call
 * seBoundaryCaching( true ), or change DEFAULT_BOUNDARY_CASHING to true
 *
 * When using a FragmentPagerAdapter or FragmentStatePagerAdapter,
 * additional changes in the adapter must be done.
 * The adapter must be prepared to create 2 extra items e.g.:
 *
 * The original adapter creates 4 items: [0,1,2,3]
 * The modified adapter will have to create 6 items [0,1,2,3,4,5]
 * with mapping realPosition=(position-1)%count
 * [0->3, 1->0, 2->1, 3->2, 4->3, 5->0]
 *
 */
public class LoopViewPager extends ViewPager {

    private static final String TAG = LoopViewPager.class.getSimpleName();
    private static final boolean DEFAULT_BOUNDARY_CASHING = false;

    /* 外部的PageChangeListener */
    OnPageChangeListener mOuterPageChangeListener;
    /* 循环 PagerAdapter 包装器 */
    private LoopPagerAdapterWrapper mAdapter;
    /* 边界缓存：为最后一张滑向第一张白屏 */
    private boolean mBoundaryCaching = DEFAULT_BOUNDARY_CASHING;
    private float mRatio;


    public LoopViewPager(Context context) {
        this(context, null);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(mRatio <= 0){
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (width / mRatio);
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, heightMode);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void init(Context context, AttributeSet attrs) {
        super.setOnTouchListener(mOnTouchListener);
        super.addOnPageChangeListener(onPageChangeListener);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoopViewPager);
        if(typedArray != null){
            mIsAutoSchedulePlay = typedArray.getBoolean(R.styleable.LoopViewPager_flag_is_auto_schedule_play, false);
            mRatio = typedArray.getFloat(R.styleable.LoopViewPager_ratio, 0);
        }
        typedArray.recycle();

        initLoopScheduleTask();
//        setPageChangeDuration(1000);
    }

    private OnTouchListener mOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction() & MotionEvent.ACTION_MASK;
            switch (action){
                case MotionEvent.ACTION_DOWN:
                    stopLoop();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    startLoop();
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        private float mPreviousOffset = -1;
        private float mPreviousPosition = -1;

        @Override
        public void onPageSelected(int position) {
            if(BuildConfig.DEBUG){
                Log.i(OnPageChangeListener.class.getSimpleName(), "onPageSelected : position--" + position);
            }

            // 1. Get the real position of ViewPager;
            int realPosition = mAdapter.toRealPosition(position);
            // 2. Reset the previous position;
            if (mPreviousPosition != realPosition) {
                mPreviousPosition = realPosition;
                if (mOuterPageChangeListener != null) {
                    mOuterPageChangeListener.onPageSelected(realPosition);
                }
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if(BuildConfig.DEBUG){
                Log.i(OnPageChangeListener.class.getSimpleName(), "onPageScrolled : position--" + position +
                        "; positionOffset--" + positionOffset +
                        "; positionOffsetPixels--" + positionOffsetPixels);
            }

            // 1. Init the realPosition;
            int realPosition = position;
            if (mAdapter != null) {
                // 2. Reset the realPosition;
                realPosition = mAdapter.toRealPosition(position);

                // 3. 如果 positionOffset == 0 && mPreviousOffset == 0 && position 为第一个或最后一个;
                if (positionOffset == 0
                        && mPreviousOffset == 0
                        && (position == 0 || position == mAdapter.getCount() - 1)) {
                    setCurrentItem(realPosition, false);
                }
            }

            // 4. Reset 'mPreviousOffset';
            mPreviousOffset = positionOffset;
            if (mOuterPageChangeListener != null) {
                // 5. 如果 'realPosition' 不是 最后一个
                if(BuildConfig.DEBUG){
                    Log.i(OnPageChangeListener.class.getSimpleName(), " mOuterPageChangeListener is null ");
                }
                if (realPosition != mAdapter.getRealCount() - 1) {
                    mOuterPageChangeListener.onPageScrolled(realPosition, positionOffset, positionOffsetPixels);
                } else {    // 6. 如果 'realPosition' 是最后一个
                    if (positionOffset > .5) {  // 7. 如果滑动大于 0.5
                        mOuterPageChangeListener.onPageScrolled(0, 0, 0);
                    } else {                    // 8. 如果滑动小于 0.5
                        mOuterPageChangeListener.onPageScrolled(realPosition, 0, 0);
                    }
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if(BuildConfig.DEBUG){
                Log.i(OnPageChangeListener.class.getSimpleName(), " onPageScrollStateChanged : state--" + state);
            }

            if (mAdapter != null) {
                // 1. Get the current position;
                int position = LoopViewPager.super.getCurrentItem();
                // 2. Convert the current position to real position;
                int realPosition = mAdapter.toRealPosition(position);
                //
                if (state == ViewPager.SCROLL_STATE_IDLE
                        && (position == 0 || position == mAdapter.getCount() - 1)) {
                    setCurrentItem(realPosition, false);
                }
            }
            if (mOuterPageChangeListener != null) {
                mOuterPageChangeListener.onPageScrollStateChanged(state);
            }
        }
    };

    /**
     * helper function which may be used when implementing FragmentPagerAdapter
     *
     * @param position
     * @param count
     * @return (position-1)%count
     */
    public static int toRealPosition( int position, int count ){
        position = position-1;
        if( position < 0 ){
            position += count;
        }else{
            position = position%count;
        }
        return position;
    }

    /**
     * If  setto true, the boundary views (i.e. first and last) will never be destroyed
     * This may help to prevent "blinking" of some views
     *
     * @param flag
     */
    public void setBoundaryCaching(boolean flag) {
        mBoundaryCaching = flag;
        if (mAdapter != null) {
            mAdapter.setBoundaryCaching(flag);
        }
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        mAdapter = new LoopPagerAdapterWrapper(adapter);
        mAdapter.setBoundaryCaching(mBoundaryCaching);
        super.setAdapter(mAdapter);
        setCurrentItem(0, false);
    }

    @Override
    public PagerAdapter getAdapter() {
        return mAdapter != null ? mAdapter.getRealAdapter() : mAdapter;
    }

    @Override
    public int getCurrentItem() {
        return mAdapter != null ? mAdapter.toRealPosition(super.getCurrentItem()) : 0;
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        int realItem = mAdapter.toInnerPosition(item);
        super.setCurrentItem(realItem, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        if (getCurrentItem() != item) {
            setCurrentItem(item, true);
        }
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mOuterPageChangeListener = listener;
    }

    public boolean isLoop() {
        return mIsAutoSchedulePlay;
    }

    public void setIsLoop(boolean isLoop) {

        this.mIsAutoSchedulePlay = isLoop;

        if(isLoop){
            startLoop();
        }else{
            stopLoop();
        }
    }

    private void setPageChangeDuration(int duration){

        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            field.set(this, new PageChangeDurationScroller(getContext(), duration));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public class PageChangeDurationScroller extends Scroller {
        private int mDuration = 1000;

        public PageChangeDurationScroller(Context context) {
            super(context);
        }

        public PageChangeDurationScroller(Context context, int duration) {
            super(context);
            mDuration = duration;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
    }

    /*********************************** ScheduleExecutorService **********************************/
    private boolean mIsAutoSchedulePlay = false;
    private ScheduledExecutorService mSchedudExecutorService;
    private ScheduledFuture<?> mScheduleFuture;
    private final static int MSG_CURRENT_INDEX = 1;
    private final static int MSG_RESTART_LOOP = 2;
    private final static int DELAY_MILLIS = 2000;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_CURRENT_INDEX:
                    LoopViewPager.this.setCurrentItem(LoopViewPager.this.getCurrentItem() + 1, true);
                    break;
                case MSG_RESTART_LOOP:
                    startLoop();
                    break;
                default:
                    break;
            }
        }
    };
    private Runnable mPostLoopViewPagerRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.obtainMessage(MSG_CURRENT_INDEX).sendToTarget();
        }
    };

    private void initLoopScheduleTask(){
        if(!mIsAutoSchedulePlay){
            return;
        }

        mSchedudExecutorService = Executors.newSingleThreadScheduledExecutor();
        mScheduleFuture = mSchedudExecutorService.scheduleAtFixedRate(mPostLoopViewPagerRunnable, 2, 4, TimeUnit.SECONDS);
    }

    private void startLoop(){
        if(!mIsAutoSchedulePlay){
            return;
        }

        if(mScheduleFuture != null && mScheduleFuture.isCancelled()){
            mScheduleFuture = mSchedudExecutorService.scheduleAtFixedRate(mPostLoopViewPagerRunnable, 2, 4, TimeUnit.SECONDS);
        }
    }

    private void stopLoop(){
        if(!mIsAutoSchedulePlay){
            return;
        }

        if(mScheduleFuture != null){
            mScheduleFuture.cancel(true);
        }
    }
}
