package com.robinlee.material.design;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.robinlee.androiddemo.R;

/**
 * Created by RobinLee on 16/9/28.
 *
 * <a>http://stackoverflow.com/questions/26346727/android-material-design-button-styles</a>
 * <a>http://www.materialdoc.com/raised-button/</a>
 */

public class ButtonActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_style);
    }


    public void openLink(View view){
        int id = view.getId();
        switch (id){
            case R.id.btnRef1:
                Intent officeWebSite = new Intent(Intent.ACTION_VIEW, Uri.parse("https://material.google.com/"));
                startActivity(officeWebSite);
                break;
            case R.id.btnRef2:
                Intent refResource = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.materialdoc.com/raised-button/"));
                startActivity(refResource);
                break;
            default:
                break;
        }
    }

}
