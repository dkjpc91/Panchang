package com.mithilakshar.mithilapanchang

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.Window
import android.widget.TextView

class calendardialog : Dialog {
    private var calendartext: TextView? = null
    private var calendartext1: TextView? = null

    constructor(context: Context) : super(context) {
        requestWindowFeature(Window.FEATURE_SWIPE_TO_DISMISS)
        setContentView(R.layout.calendardialog)
        calendartext = findViewById(R.id.calendardialogtext)
        calendartext1 = findViewById(R.id.calendardialogtext1)
    }

    constructor(context: Context, themeResId: Int) : super(context, themeResId)
    protected constructor(
        context: Context,
        cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ) : super(context, cancelable, cancelListener)

    fun setcalendardialogtext(text: String?) {
        calendartext!!.text = text
    }

    fun setcalendardialogtext1(text: String?) {
        calendartext1!!.text = text
    }
}
