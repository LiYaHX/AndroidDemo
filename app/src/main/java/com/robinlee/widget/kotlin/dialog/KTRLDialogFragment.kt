package com.robinlee.widget.kotlin.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robinlee.androiddemo.R

/**
 * Created by robinlee on 2018/1/3.
 */

class KTRLDialogFragment : DialogFragment() {

    private var alertDialogTitle: String? = null
    private var alertDialogContent: String? = null
    private var themeResId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, 0)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val bundle = arguments
        if (bundle != null) {
            themeResId = bundle.getInt(KEY_THEME_RES_ID)
            alertDialogTitle = bundle.getString(KEY_THEME_RES_ID, resources.getString(R.string.alert_dialog_title))
            alertDialogContent = bundle.getString(KEY_THEME_RES_ID, resources.getString(R.string.alert_dialog_content))
        }

        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(activity, themeResId)
        builder.setTitle(alertDialogTitle)
                .setMessage(alertDialogContent)
                .setPositiveButton(R.string.alert_dialog_ensure) { dialog, id ->
                    // FIRE ZE MISSILES!
                }
                .setNegativeButton(R.string.alert_dialog_cancle) { dialog, id ->
                    // User cancelled the dialog
                }
        // Create the AlertDialog object and return it
        return builder.create()
    }

    companion object {

        val KEY_TITLE = "title"
        val KEY_CONTENT = "content"
        val KEY_THEME_RES_ID = "theme_res_id"
    }
}
