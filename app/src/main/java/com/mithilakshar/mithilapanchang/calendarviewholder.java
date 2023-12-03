package com.mithilakshar.mithilapanchang;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class calendarviewholder extends RecyclerView.ViewHolder {

    TextView calendarItemText;

    public calendarviewholder(@NonNull View itemView) {

        super(itemView);

        calendarItemText=itemView.findViewById(R.id.calendarItemText);


    }
}
