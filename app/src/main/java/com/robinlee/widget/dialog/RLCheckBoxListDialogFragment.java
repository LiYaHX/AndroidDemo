package com.robinlee.widget.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robinlee.androiddemo.R;

import java.util.ArrayList;

/**
 * Created by robinlee on 2018/1/3.
 */

public class RLCheckBoxListDialogFragment extends DialogFragment{

    public static final String KEY_TITLE    = "title";
    public static final String KEY_THEME_RES_ID  = "theme_res_id";

    private String alertDialogTitle;
    private int    themeResId;
    private ArrayList mSelectedItems;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mSelectedItems = new ArrayList();

        Bundle bundle = getArguments();
        if(bundle != null){
            themeResId = bundle.getInt(KEY_THEME_RES_ID);
            alertDialogTitle = bundle.getString(KEY_THEME_RES_ID, getResources().getString(R.string.alert_dialog_title));
        }

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), themeResId);
        builder.setTitle(alertDialogTitle)
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(R.array.fruit, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    mSelectedItems.add(which);
                                } else if (mSelectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
               .setPositiveButton(R.string.alert_dialog_ensure, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
               .setNegativeButton(R.string.alert_dialog_cancle, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
