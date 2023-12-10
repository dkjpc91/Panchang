package com.mithilakshar.mithilapanchang;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class mantraviewholder extends RecyclerView.ViewHolder {

    TextView mantraName,mantraDesc;
    ImageView mantraImage;
    public mantraviewholder(@NonNull View itemView) {
        super(itemView);

        mantraName=itemView.findViewById(R.id.mantraName);
        mantraDesc=itemView.findViewById(R.id.mantraDesc);
        mantraImage=itemView.findViewById(R.id.mantraImage);
    }
}
