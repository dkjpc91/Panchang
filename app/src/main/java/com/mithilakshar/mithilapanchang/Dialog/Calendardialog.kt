package com.mithilakshar.mithilapanchang.Dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.Window
import android.widget.TextView
import com.mithilakshar.mithilapanchang.R

class calendardialog : Dialog {
    private var calendartext: TextView? = null
    private var calendartext1: TextView? = null
    private var calendartext2: TextView? = null
    private var calendartext3: TextView? = null
    private var calendartext4: TextView? = null

    constructor(context: Context) : super(context) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.calendardialog)
        calendartext = findViewById(R.id.calendardialogtext)
        calendartext1 = findViewById(R.id.calendardialogtext1)
        calendartext3 = findViewById(R.id.calendardialogtext3)
        calendartext4 = findViewById(R.id.calendardialogtext4)
    }

    constructor(context: Context, themeResId: Int) : super(context, themeResId)
    protected constructor(
        context: Context,
        cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ) : super(context, cancelable, cancelListener)

    fun setcalendardialogtext(text: String?) {
        calendartext?.text = text
    }

    fun setcalendardialogtext1(text: String?) {
        calendartext1?.text = text
    }
    fun setcalendardialogtext2(text: String?) {
        calendartext2?.text = text
    }
    fun setcalendardialogtext3(text: String?) {
        calendartext3?.text = text
    }

    fun setcalendardialogtext4(text: String?) {
        calendartext4?.text = text
    }
}
