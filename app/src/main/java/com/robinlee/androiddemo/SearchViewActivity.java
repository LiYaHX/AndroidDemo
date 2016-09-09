package com.robinlee.androiddemo;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SearchView;

/**
 * Created by RobinLee on 4/9/16.
 */
public class SearchViewActivity extends BaseActivity {

    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        initView();
    }

    private void initView(){
        mSearchView = (SearchView) this.findViewById(R.id.mSearchView);
        int searchBarId = mSearchView.getContext().getResources().getIdentifier("android:id/search_edit_frame", null, null);
        LinearLayout editSearchView = (LinearLayout) mSearchView.findViewById(searchBarId);
        editSearchView.setLayoutTransition(new LayoutTransition());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view_menu, menu);

        MenuItem searchViewItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchViewItem.getActionView();
//        [...]
        searchView.setIconifiedByDefault(false);

        return true;
    }
}
