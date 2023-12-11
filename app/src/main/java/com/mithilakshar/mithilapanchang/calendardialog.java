package com.mithilakshar.mithilapanchang;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class calendardialog extends Dialog {

    private TextView calendartext,calendartext1,calendartext2;
    public calendardialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_SWIPE_TO_DISMISS);
        setContentView(R.layout.calendardialog);
        calendartext=findViewById(R.id.calendardialogtext);
        calendartext1=findViewById(R.id.calendardialogtext1);
        calendartext2=findViewById(R.id.calendardialogtext2);


    }

    public calendardialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected calendardialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setcalendardialogtext(String text){
        calendartext.setText(text);
    }
    public void setcalendardialogtext1(String text){
        calendartext1.setText(text);
    }
    public void setcalendardialogtext2(String text){
        calendartext2.setText(text);
    }
}
