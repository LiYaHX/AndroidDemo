package com.robinlee.widget.kotlin.dialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.robinlee.androiddemo.R
import com.robinlee.widget.dialog.RLDialogFragment.KEY_CONTENT
import com.robinlee.widget.dialog.RLDialogFragment.KEY_THEME_RES_ID
import com.robinlee.widget.dialog.RLDialogFragment.KEY_TITLE

/**
 * Current state : four kinds alert dialog;
 * Next purpose : set all kinds of theme for four kinds (global setting)
 *
 * @author robinlee
 * @since 2018-01-03
 * @version 1.0
 * @history 1.0
 * @history_since 2018-01-03
 * @link [R.style](https://developer.android.com/reference/android/R.style.html)
 * [Themes and styles](https://developer.android.com/guide/topics/ui/themes.html)
 * [theme](https://android.googlesource.com/platform/frameworks/base/+/refs/heads/master/core/res/res/values/themes.xml)
 * [style](https://android.googlesource.com/platform/frameworks/base/+/refs/heads/master/core/res/res/values/styles.xml)
 */
class KTDialogActivity : AppCompatActivity(), View.OnClickListener, KTRLAlertDialogOnItemClickListener {

    private var mTextViewThemeName: TextView? = null
    private var mBtnDefaultAlertDialog: Button? = null
    private var mBtnListAlertDialog: Button? = null
    private var mBtnRadioListAlertDialog: Button? = null
    private var mBtnCheckboxListAlertDialog: Button? = null

    private val themeArray = intArrayOf(
            //            android.R.style.Animation_Dialog,
            //            android.R.style.DeviceDefault_ButtonBar_AlertDialog,
            //            android.R.style.DeviceDefault_Light_ButtonBar_AlertDialog,
            //            android.R.style.Holo_ButtonBar_AlertDialog,
            //            android.R.style.Holo_Light_ButtonBar_AlertDialog,
            //            android.R.style.ThemeOverlay_Material_Dialog,
            //            android.R.style.ThemeOverlay_Material_Dialog_Alert,
            android.R.style.Theme_DeviceDefault_Dialog, android.R.style.Theme_DeviceDefault_Dialog_Alert, android.R.style.Theme_DeviceDefault_Light_Dialog, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert, android.R.style.Theme_Holo_Dialog, android.R.style.Theme_Holo_Light_Dialog, android.R.style.Theme_Material_Dialog, android.R.style.Theme_Material_Dialog_Alert, android.R.style.Theme_Material_Light_Dialog, android.R.style.Theme_Material_Light_Dialog_Alert)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        initWidget()
    }

    private fun initDefaultDialog(titleRes: Int, contentRes: Int) {
        val rlDialogFragment = KTRLDialogFragment()
        val bundle = Bundle()
        bundle.putString(KEY_TITLE, resources.getString(titleRes))
        bundle.putString(KEY_CONTENT, resources.getString(contentRes))
        bundle.putInt(KEY_THEME_RES_ID, DIALOG_THEME)
        rlDialogFragment.arguments = bundle
        rlDialogFragment.show(supportFragmentManager, resources.getString(titleRes))
    }

    private fun initListDialog(titleRes: Int) {
        val rlDialogFragment = KTRLListDialogFragment()
        val bundle = Bundle()
        bundle.putString(KEY_TITLE, resources.getString(titleRes))
        bundle.putInt(KEY_THEME_RES_ID, DIALOG_THEME)
        rlDialogFragment.arguments = bundle
        rlDialogFragment.show(supportFragmentManager, resources.getString(titleRes))
    }

    private fun initCheckboxListDialog(titleRes: Int) {
        val rlDialogFragment = KTRLCheckBoxListDialogFragment()
        val bundle = Bundle()
        bundle.putString(KEY_TITLE, resources.getString(titleRes))
        bundle.putInt(KEY_THEME_RES_ID, DIALOG_THEME)
        rlDialogFragment.arguments = bundle
        rlDialogFragment.show(supportFragmentManager, resources.getString(titleRes))
    }

    private fun initCustomDialog(titleRes: Int) {
        val rlDialogFragment = KTRLCustomViewDialogFragment()
        val bundle = Bundle()
        bundle.putInt(KEY_THEME_RES_ID, DIALOG_THEME)
        rlDialogFragment.arguments = bundle
        rlDialogFragment.show(supportFragmentManager, resources.getString(titleRes))
    }

    private fun initWidget() {

        mTextViewThemeName = findViewById(R.id.mTextViewThemeName) as TextView
        mTextViewThemeName!!.text = "Theme_DeviceDefault_Dialog"

        mBtnDefaultAlertDialog = findViewById(R.id.mBtnDefaultAlertDialog) as Button
        mBtnDefaultAlertDialog!!.setOnClickListener(this)
        mBtnListAlertDialog = findViewById(R.id.mBtnListAlertDialog) as Button
        mBtnListAlertDialog!!.setOnClickListener(this)
        mBtnRadioListAlertDialog = findViewById(R.id.mBtnRadioListAlertDialog) as Button
        mBtnRadioListAlertDialog!!.setOnClickListener(this)
        mBtnCheckboxListAlertDialog = findViewById(R.id.mBtnCheckboxListAlertDialog) as Button
        mBtnCheckboxListAlertDialog!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mBtnDefaultAlertDialog -> initDefaultDialog(R.string.alert_dialog_title, R.string.alert_dialog_content)
            R.id.mBtnListAlertDialog -> initListDialog(R.string.alert_dialog_title)
            R.id.mBtnRadioListAlertDialog -> initCheckboxListDialog(R.string.alert_dialog_title)
            R.id.mBtnCheckboxListAlertDialog -> initCustomDialog(R.string.alert_dialog_title)
            else -> {
            }
        }
    }

    override fun onItemClick(position: Int) {
        mTextViewThemeName!!.text = resources.getStringArray(R.array.theme)[position]
        DIALOG_THEME = themeArray[position]
    }

    companion object {

        private var DIALOG_THEME = android.R.style.Theme_DeviceDefault_Dialog
    }
}
