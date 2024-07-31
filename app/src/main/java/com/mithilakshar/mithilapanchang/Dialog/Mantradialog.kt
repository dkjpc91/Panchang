package com.mithilakshar.mithilapanchang.Dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.mithilakshar.mithilapanchang.R
import com.mithilakshar.mithilapanchang.Utility.ViewShareUtil

class Mantradialog : Dialog {
    private var mantratext: TextView? = null
    private var mantradesc: TextView? = null
    private var shareImage: ImageView? = null





    constructor(context: Context) : super(context) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.mantradialog)
        mantratext = findViewById(R.id.sanskrit_quote)
        mantradesc = findViewById(R.id.hindi_translation)
        shareImage = findViewById(R.id.shareicon)

        val shareImageroot: CardView?  = findViewById(R.id.rootview)

        shareImage?.setOnClickListener {
            if (shareImageroot != null) {
                sharemantraimage(shareImageroot, context)
            }
        }

    }

    constructor(context: Context, themeResId: Int) : super(context, themeResId)
    protected constructor(
        context: Context,
        cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ) : super(context, cancelable, cancelListener)

    fun setmantradialogtext(text: String?) {
        mantratext?.text = text
    }
    fun setmantradialogtext1(text: String?) {
        mantradesc?.text = text
    }

    fun sharemantraimage(view: CardView,context: Context) {
        ViewShareUtil.shareViewAsImageDirectly(view, context)
    }




}
