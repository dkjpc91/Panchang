package com.mithilakshar.mithilapanchang;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class holidayviewholder extends RecyclerView.ViewHolder {

    TextView holidayName,holidayDesc;
    ImageView holidayImage;
    public holidayviewholder(@NonNull View itemView) {
        super(itemView);

        holidayName=itemView.findViewById(R.id.holidayName);
        holidayDesc=itemView.findViewById(R.id.holidayDesc);
        holidayImage=itemView.findViewById(R.id.holidayImage);

    }
}
