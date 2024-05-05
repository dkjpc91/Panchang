package com.mithilakshar.mithilapanchang.Dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import com.mithilakshar.mithilapanchang.R

class Networkdialog (context: Context) : Dialog(context) {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.networkdialog)

            // Set the dialog to be non-cancellable on back button press
            this.setCancelable(false)

            // Alternatively, to disable cancelling on touch outside the dialog
            window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

        }
    }