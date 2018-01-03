package com.robinlee.widget.dialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.robinlee.androiddemo.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.robinlee.widget.dialog.RLDialogFragment.KEY_CONTENT;
import static com.robinlee.widget.dialog.RLDialogFragment.KEY_THEME_RES_ID;
import static com.robinlee.widget.dialog.RLDialogFragment.KEY_TITLE;

/**
 * Current state : four kinds alert dialog;
 * Next purpose : set all kinds of theme for four kinds (global setting)
 *
 * @author robinlee
 * @since 2018-01-03
 * @version 1.0
 * @history 1.0
 * @history_since 2018-01-03
 * @link <a href="https://developer.android.com/reference/android/R.style.html">R.style</a></br>
 * <a href="https://developer.android.com/guide/topics/ui/themes.html">Themes and styles</a></br>
 * <a href="https://android.googlesource.com/platform/frameworks/base/+/refs/heads/master/core/res/res/values/themes.xml">theme</a></br>
 * <a href="https://android.googlesource.com/platform/frameworks/base/+/refs/heads/master/core/res/res/values/styles.xml">style</a>
 */
public class DialogActivity extends AppCompatActivity implements View.OnClickListener, RLAlertDialogOnItemClickListener{

    private static int DIALOG_THEME = android.R.style.Theme_DeviceDefault_Dialog;

    private TextView mTextViewThemeName;
    private Button mBtnDefaultAlertDialog;
    private Button mBtnListAlertDialog;
    private Button mBtnRadioListAlertDialog;
    private Button mBtnCheckboxListAlertDialog;

    private int[] themeArray = {
//            android.R.style.Animation_Dialog,
//            android.R.style.DeviceDefault_ButtonBar_AlertDialog,
//            android.R.style.DeviceDefault_Light_ButtonBar_AlertDialog,
//            android.R.style.Holo_ButtonBar_AlertDialog,
//            android.R.style.Holo_Light_ButtonBar_AlertDialog,
//            android.R.style.ThemeOverlay_Material_Dialog,
//            android.R.style.ThemeOverlay_Material_Dialog_Alert,
            android.R.style.Theme_DeviceDefault_Dialog,
            android.R.style.Theme_DeviceDefault_Dialog_Alert,
            android.R.style.Theme_DeviceDefault_Light_Dialog,
            android.R.style.Theme_DeviceDefault_Light_Dialog_Alert,
            android.R.style.Theme_Holo_Dialog,
            android.R.style.Theme_Holo_Light_Dialog,
            android.R.style.Theme_Material_Dialog,
            android.R.style.Theme_Material_Dialog_Alert,
            android.R.style.Theme_Material_Light_Dialog,
            android.R.style.Theme_Material_Light_Dialog_Alert,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        initWidget();
    }

    private void initDefaultDialog(int titleRes, int contentRes){
        RLDialogFragment rlDialogFragment = new RLDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, getResources().getString(titleRes));
        bundle.putString(KEY_CONTENT, getResources().getString(contentRes));
        bundle.putInt(KEY_THEME_RES_ID, DIALOG_THEME);
        rlDialogFragment.setArguments(bundle);
        rlDialogFragment.show(getSupportFragmentManager(), getResources().getString(titleRes));
    }

    private void initListDialog(int titleRes){
        RLListDialogFragment rlDialogFragment = new RLListDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, getResources().getString(titleRes));
        bundle.putInt(KEY_THEME_RES_ID, DIALOG_THEME);
        rlDialogFragment.setArguments(bundle);
        rlDialogFragment.show(getSupportFragmentManager(), getResources().getString(titleRes));
    }

    private void initCheckboxListDialog(int titleRes){
        RLCheckBoxListDialogFragment rlDialogFragment = new RLCheckBoxListDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, getResources().getString(titleRes));
        bundle.putInt(KEY_THEME_RES_ID, DIALOG_THEME);
        rlDialogFragment.setArguments(bundle);
        rlDialogFragment.show(getSupportFragmentManager(), getResources().getString(titleRes));
    }

    private void initCustomDialog(int titleRes){
        RLCustomViewDialogFragment rlDialogFragment = new RLCustomViewDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_THEME_RES_ID, DIALOG_THEME);
        rlDialogFragment.setArguments(bundle);
        rlDialogFragment.show(getSupportFragmentManager(), getResources().getString(titleRes));
    }

    private void initWidget(){

        mTextViewThemeName = (TextView) findViewById(R.id.mTextViewThemeName);
        mTextViewThemeName.setText("Theme_DeviceDefault_Dialog");

        mBtnDefaultAlertDialog = (Button) findViewById(R.id.mBtnDefaultAlertDialog);
        mBtnDefaultAlertDialog.setOnClickListener(this);
        mBtnListAlertDialog = (Button) findViewById(R.id.mBtnListAlertDialog);
        mBtnListAlertDialog.setOnClickListener(this);
        mBtnRadioListAlertDialog = (Button) findViewById(R.id.mBtnRadioListAlertDialog);
        mBtnRadioListAlertDialog.setOnClickListener(this);
        mBtnCheckboxListAlertDialog = (Button) findViewById(R.id.mBtnCheckboxListAlertDialog);
        mBtnCheckboxListAlertDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mBtnDefaultAlertDialog:
                initDefaultDialog(R.string.alert_dialog_title, R.string.alert_dialog_content);
                break;
            case R.id.mBtnListAlertDialog:
                initListDialog(R.string.alert_dialog_title);
                break;
            case R.id.mBtnRadioListAlertDialog:
                initCheckboxListDialog(R.string.alert_dialog_title);
                break;
            case R.id.mBtnCheckboxListAlertDialog:
                initCustomDialog(R.string.alert_dialog_title);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        mTextViewThemeName.setText(getResources().getStringArray(R.array.theme)[position]);
        DIALOG_THEME = themeArray[position];
    }
}
