package com.robinlee.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.robinlee.androiddemo.BuildConfig;
import com.robinlee.androiddemo.R;

/**
 * Created by RobinLee on 3/21/16.
 */
public class LoopIndicator extends HorizontalScrollView {

    private static final String TAG = "LoopIndicator";

    private static final int DEFAULT_ITEM_COUNT = 1;
    private static final int DEFUALT_ITEM_PADDING = 0;
    private static final int DEFAULT_PARENT_PADDING = 0;

    private Context mContext;
    private int mItemCount = DEFAULT_ITEM_COUNT;
    /**
     *  0 : padding_left;
     *  1 : padding_top;
     *  2 : padding_right;
     *  3 : padding_bottom **/
    private int[] mItemPadding = new int[]{
                DEFUALT_ITEM_PADDING, DEFUALT_ITEM_PADDING,
                DEFUALT_ITEM_PADDING, DEFUALT_ITEM_PADDING
            };
    private int[] mContainerPadding = new int[]{
                DEFAULT_PARENT_PADDING, DEFAULT_PARENT_PADDING,
                DEFAULT_PARENT_PADDING, DEFAULT_PARENT_PADDING,
            };

    private LinearLayout mViewContainer;

    public LoopIndicator(Context context) {
        this(context, null);
    }

    public LoopIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoopIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoopIndicator, defStyleAttr, 0);
        if(typedArray != null){

            int containerPadding = typedArray.getDimensionPixelSize(R.styleable.LoopIndicator_container_padding, 0);
            if(containerPadding <= 0){
                mContainerPadding[0] = typedArray.getDimensionPixelSize(R.styleable.LoopIndicator_container_padding_left, 0);
                mContainerPadding[1] = typedArray.getDimensionPixelSize(R.styleable.LoopIndicator_container_padding_top, 0);
                mContainerPadding[2] = typedArray.getDimensionPixelSize(R.styleable.LoopIndicator_container_padding_right, 0);
                mContainerPadding[3] = typedArray.getDimensionPixelSize(R.styleable.LoopIndicator_container_padding_bottom, 0);
            }else{
                mContainerPadding = new int[]{containerPadding, containerPadding, containerPadding, containerPadding};
            }

            int itemPadding = typedArray.getDimensionPixelSize(R.styleable.LoopIndicator_item_padding, 0);
            if(itemPadding <= 0){
                mItemPadding[0] = typedArray.getDimensionPixelSize(R.styleable.LoopIndicator_container_padding_left, 0);
                mItemPadding[1] = typedArray.getDimensionPixelSize(R.styleable.LoopIndicator_container_padding_top, 0);
                mItemPadding[2] = typedArray.getDimensionPixelSize(R.styleable.LoopIndicator_container_padding_right, 0);
                mItemPadding[3] = typedArray.getDimensionPixelSize(R.styleable.LoopIndicator_container_padding_bottom, 0);
            }else{
                mItemPadding = new int[]{itemPadding, itemPadding, itemPadding, itemPadding};
            }

            mItemCount = typedArray.getInteger(R.styleable.LoopIndicator_items_count, 0);
        }
        typedArray.recycle();

        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr){

        mContext = context;

        mViewContainer = new LinearLayout(context);
        mViewContainer.setOrientation(LinearLayout.HORIZONTAL);
        mViewContainer.setPadding(mContainerPadding[0], mContainerPadding[1], mContainerPadding[2], mContainerPadding[3]);
        addView(mViewContainer);

        initItems(context, mItemCount);
    }

    private void initItems(Context context, int itemCount){

        if(context == null){
            if(BuildConfig.DEBUG){
                Log.e(TAG, "'context' is null! Please debug.");
            }
            return;
        }

        if(itemCount <= 0){
            itemCount = DEFAULT_ITEM_COUNT;
        }

        for(int i = 0; i < itemCount; ++i){
            ImageView imgView = new ImageView(context);
            imgView.setPadding(mItemPadding[0], mItemPadding[1], mItemPadding[2], mItemPadding[3]);
            imgView.setImageResource(R.drawable.dot);
            mViewContainer.addView(imgView);
        }

        onPageSelected(0);
    }

    private void resetItems(Context context, int itemCount){
        if(context == null){
            return;
        }

        if(itemCount <= 0){
            itemCount = DEFAULT_ITEM_COUNT;
        }

        if(mViewContainer == null){
            return;
        }

        mViewContainer.removeAllViews();
        for(int i = 0; i < itemCount; ++i){
            ImageView imgView = new ImageView(context);
            imgView.setPadding(mItemPadding[0], mItemPadding[1], mItemPadding[2], mItemPadding[3]);
            imgView.setImageResource(R.drawable.dot);
            mViewContainer.addView(imgView);
        }

        onPageSelected(0);
        postInvalidate();
    }

    public void onPageSelected(int position){

        if(position < 0){
            return;
        }

        int size = mViewContainer.getChildCount();
        for(int i = 0; i < size; ++i){
            if(i == position){
                mViewContainer.getChildAt(i).setEnabled(true);
            }else{
                mViewContainer.getChildAt(i).setEnabled(false);
            }
        }
    }

    public int getItemCount() {
        return mItemCount;
    }

    public void setItemCount(int mItemCount) {
        this.mItemCount = mItemCount;
        resetItems(mContext, mItemCount);
    }
}
