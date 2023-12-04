package com.mithilakshar.mithilapanchang;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class calendarviewholder extends RecyclerView.ViewHolder {

    TextView calendardayText,calendardateText,calendardescText;

    public calendarviewholder(@NonNull View itemView) {

        super(itemView);

        calendardayText=itemView.findViewById(R.id.calendardayText);
        calendardateText=itemView.findViewById(R.id.calendardateText);
        calendardescText=itemView.findViewById(R.id.calendardescText);


    }
}
