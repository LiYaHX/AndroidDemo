package com.robinlee.widget.kotlin.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robinlee.androiddemo.R
import com.robinlee.widget.dialog.RLAlertDialogOnItemClickListener

/**
 * Created by robinlee on 2018/1/3.
 */

class KTRLListDialogFragment : DialogFragment() {

    private var alertDialogTitle: String? = null
    private var themeArray: IntArray? = null
    private var themeResId: Int = 0

    private var rlAlertDialogOnItemClickListener: KTRLAlertDialogOnItemClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, 0)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        rlAlertDialogOnItemClickListener = activity as KTRLAlertDialogOnItemClickListener
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val bundle = arguments
        if (bundle != null) {
            themeArray = bundle.getIntArray(KEY_LIST)
            themeResId = bundle.getInt(KEY_THEME_RES_ID)
            alertDialogTitle = bundle.getString(KEY_THEME_RES_ID, resources.getString(R.string.alert_dialog_title))
        }

        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(activity, themeResId)
        builder.setTitle(alertDialogTitle)
                .setItems(R.array.theme) { dialog, which ->
                    if (rlAlertDialogOnItemClickListener != null) {
                        rlAlertDialogOnItemClickListener!!.onItemClick(which)
                    }
                }
        // Create the AlertDialog object and return it
        return builder.create()
    }

    companion object {

        val KEY_TITLE = "title"
        val KEY_LIST = "list"
        val KEY_THEME_RES_ID = "theme_res_id"
    }

}
