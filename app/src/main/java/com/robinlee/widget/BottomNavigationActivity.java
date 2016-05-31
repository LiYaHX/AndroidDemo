package com.robinlee.widget;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.robinlee.androiddemo.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;

/**
 * Created by RobinLee on 4/1/16.
 */
public class BottomNavigationActivity extends AppCompatActivity {

    private BottomBar mBottomBar;
    private BottomBarBadge mUnreadMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(int menuItemId) {

                if (menuItemId == R.id.bottomBarItemOne) {
                    Toast.makeText(BottomNavigationActivity.this, "bottomBarItemOne", Toast.LENGTH_SHORT).show();
                } else if (menuItemId == R.id.bottomBarItemTwo) {
                    mUnreadMessages.hide();
                    Toast.makeText(BottomNavigationActivity.this, "bottomBarItemTwo", Toast.LENGTH_SHORT).show();
                } else if (menuItemId == R.id.bottomBarItemThree) {
                    mUnreadMessages.hide();
                    Toast.makeText(BottomNavigationActivity.this, "bottomBarItemThree", Toast.LENGTH_SHORT).show();
                } else if (menuItemId == R.id.bottomBarItemFour) {
                    mUnreadMessages.hide();
                    Toast.makeText(BottomNavigationActivity.this, "bottomBarItemFour", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMenuTabReSelected(int menuItemId) {

                if (menuItemId == R.id.bottomBarItemOne) {
                    Toast.makeText(BottomNavigationActivity.this, "re bottomBarItemOne", Toast.LENGTH_SHORT).show();
                } else if (menuItemId == R.id.bottomBarItemTwo) {
                    Toast.makeText(BottomNavigationActivity.this, "re bottomBarItemTwo", Toast.LENGTH_SHORT).show();
                } else if (menuItemId == R.id.bottomBarItemThree) {
                    Toast.makeText(BottomNavigationActivity.this, "re bottomBarItemThree", Toast.LENGTH_SHORT).show();
                } else if (menuItemId == R.id.bottomBarItemFour) {
                    Toast.makeText(BottomNavigationActivity.this, "re bottomBarItemFour", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
        mBottomBar.mapColorForTab(1, 0xFF5D4037);
        mBottomBar.mapColorForTab(2, "#7B1FA2");
        mBottomBar.mapColorForTab(3, "#FF5252");

        mUnreadMessages = mBottomBar.makeBadgeForTabAt(0, "#FF0000", 13);
        mUnreadMessages.setTextSize(14);
        // Make a Badge for the first tab, with red background color and a value of "13".
        // Control the badge's visibility
        mUnreadMessages.show();
        // Change the displayed count for this badge.
        mUnreadMessages.setCount(2);
        // Change the show / hide animation duration.
        mUnreadMessages.setAnimationDuration(200);
        // If you want the badge be shown always after unselecting the tab that contains it.
        mUnreadMessages.setAutoShowAfterUnSelection(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mBottomBar.onSaveInstanceState(outState);
    }
}