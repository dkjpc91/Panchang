package com.mithilakshar.mithilapanchang;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class eclipseviewholder extends RecyclerView.ViewHolder {

    TextView eclipseName,eclipseDesc;
    ImageView eclipseImage;
    public eclipseviewholder(@NonNull View itemView) {
        super(itemView);

        eclipseName=itemView.findViewById(R.id.eclipseName);
        eclipseDesc=itemView.findViewById(R.id.eclipseDesc);
        eclipseImage=itemView.findViewById(R.id.eclipseImage);
    }
}
