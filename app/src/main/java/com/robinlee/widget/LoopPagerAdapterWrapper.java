package com.robinlee.widget;

import android.os.Parcelable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by RobinLee on 1/29/16.
 */
public class LoopPagerAdapterWrapper extends PagerAdapter {

    private PagerAdapter mAdapter;

    private SparseArray<ToDestroy> mToDestroy = new SparseArray<ToDestroy>();

    private boolean mBoundaryCaching;

    void setBoundaryCaching(boolean flag) {
        mBoundaryCaching = flag;
    }

    LoopPagerAdapterWrapper(PagerAdapter adapter) {
        this.mAdapter = adapter;
    }

    @Override
    public void notifyDataSetChanged() {
        mToDestroy = new SparseArray<ToDestroy>();
        super.notifyDataSetChanged();
    }

    /**
     *
     * @param position
     * @return
     */
    int toRealPosition(int position) {
        int realCount = getRealCount();
        if (realCount == 0)
            return 0;
        int realPosition = (position-1) % realCount;
        if (realPosition < 0)
            realPosition += realCount;

        Log.i(LoopPagerAdapterWrapper.class.getSimpleName(), "toRealPosition : position -- " + position +
                "; realPosition -- " + realPosition);
        return realPosition;
    }

    /**
     * 将 'outerPosition' 转换成 "innerPosition(realPosition)"
     *
     * @param realPosition
     * @return
     */
    public int toInnerPosition(int realPosition) {
        int position = (realPosition + 1);
        Log.i(LoopPagerAdapterWrapper.class.getSimpleName(), "toInnerPosition : realPosition--" + realPosition
                        + "; position -- " + position);
        return position;
    }

    private int getRealFirstPosition() {
        return 1;
    }

    private int getRealLastPosition() {
        return getRealFirstPosition() + getRealCount() - 1;
    }

    /**
     * Add first item and last item.
     *
     * @return
     */
    @Override
    public int getCount() {
        return mAdapter.getCount() + 2;
    }

    public int getRealCount() {
        return mAdapter.getCount();
    }

    public PagerAdapter getRealAdapter() {
        return mAdapter;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = (mAdapter instanceof FragmentPagerAdapter || mAdapter instanceof FragmentStatePagerAdapter)
                ? position
                : toRealPosition(position);
        Log.i(LoopPagerAdapterWrapper.class.getSimpleName(), "instantiateItem");
        if (mBoundaryCaching) {
            ToDestroy toDestroy = mToDestroy.get(position);
            if (toDestroy != null) {
                mToDestroy.remove(position);
                return toDestroy.object;
            }
        }
        return mAdapter.instantiateItem(container, realPosition);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int realFirst = getRealFirstPosition();
        int realLast = getRealLastPosition();
        int realPosition = (mAdapter instanceof FragmentPagerAdapter || mAdapter instanceof FragmentStatePagerAdapter)
                ? position
                : toRealPosition(position);
        Log.i(LoopPagerAdapterWrapper.class.getSimpleName(), "destroyItem : position -- " +
                position + "; realPosition -- " + realPosition);
        if (mBoundaryCaching && (position == realFirst || position == realLast)) {
            Log.i(LoopPagerAdapterWrapper.class.getSimpleName(), "destroyItem mToDestroy : position -- " +
                    position + "; realPosition -- " + realPosition);
            mToDestroy.put(position, new ToDestroy(container, realPosition, object));
        } else {
            mAdapter.destroyItem(container, realPosition, object);
        }
    }

    /*
     * Delegate rest of methods directly to the inner adapter.
     */
    @Override
    public void finishUpdate(ViewGroup container) {
        mAdapter.finishUpdate(container);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return mAdapter.isViewFromObject(view, object);
    }

    @Override
    public void restoreState(Parcelable bundle, ClassLoader classLoader) {
        mAdapter.restoreState(bundle, classLoader);
    }

    @Override
    public Parcelable saveState() {
        return mAdapter.saveState();
    }

    @Override
    public void startUpdate(ViewGroup container) {
        mAdapter.startUpdate(container);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mAdapter.setPrimaryItem(container, position, object);
    }

    /*
     * End delegation
     */

    /**
     * Container class for caching the boundary views
     */
    static class ToDestroy {
        ViewGroup container;
        int position;
        Object object;

        public ToDestroy(ViewGroup container, int position, Object object) {
            this.container = container;
            this.position = position;
            this.object = object;
        }
    }

}
