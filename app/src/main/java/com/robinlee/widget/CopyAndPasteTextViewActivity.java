package com.robinlee.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ArrowKeyMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.robinlee.androiddemo.R;
import java.util.ArrayList;

/**
 * Created by RobinLee on 6/12/16.
 */
public class CopyAndPasteTextViewActivity extends Activity{

    private ArrayList<String> mData = new ArrayList<>();
    private CopyAndPasteAdapter mCopyAndPasteAdapter;
    private ListView mListViewCopyAndPaste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_and_paste_textview);

        initData();
        initWidget();
    }

    private void initData(){
        mData.add("aaaaaaaaaaaaaaaaaaa");
        mData.add("fffffffffffffffffff");
        mData.add("ggggggggggggggggggg");
        mData.add("vvvvvvvvvvvvvvvvvvv");
        mData.add("bbbbbbbbbbbbbbbbbbb");
        mData.add("nnnnnnnnnnnnnnnnnnn");
        mData.add("hhhhhhhhhhhhhhhhhhh");
        mCopyAndPasteAdapter = new CopyAndPasteAdapter(CopyAndPasteTextViewActivity.this, mData);
    }

    private void initWidget(){
        mListViewCopyAndPaste = (ListView) this.findViewById(R.id.mListViewCopyAndPaste);
        mListViewCopyAndPaste.setAdapter(mCopyAndPasteAdapter);
    }

    class CopyAndPasteAdapter extends BaseAdapter{

        private Context mContext;
        private ArrayList<String> mContents = new ArrayList<>();

        public CopyAndPasteAdapter(Context context, ArrayList<String> contents) {
            this.mContext = context;

            if(contents == null || contents.size() <= 0){
                return;
            }

            mContents.clear();
            mContents.addAll(contents);
        }

        @Override
        public int getCount() {
            return mContents.size();
        }

        @Override
        public Object getItem(int position) {
            return mContents.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;
            if(convertView == null){
                convertView = View.inflate(this.mContext, R.layout.item_of_copy_and_paste, null);
                holder = new Holder();
                holder.mTextView = (TextView) convertView.findViewById(R.id.mTextView);
                holder.mTextView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        ((TextView) v).setFocusableInTouchMode(true);
                        ((TextView) v).setFocusable(true);
                        ((TextView) v).setClickable(true);
                        ((TextView) v).setLongClickable(true);
                        ((TextView) v).setMovementMethod(ArrowKeyMovementMethod.getInstance());
                        ((TextView) v).setText(((TextView) v).getText(), TextView.BufferType.SPANNABLE );
                        return false;
                    }
                });
            }

            return convertView;
        }

        class Holder{
            TextView mTextView;
        }
    }

}
