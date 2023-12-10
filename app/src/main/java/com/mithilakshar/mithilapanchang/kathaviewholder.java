package com.mithilakshar.mithilapanchang;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class kathaviewholder extends RecyclerView.ViewHolder {

    TextView kathaName,kathaStory;
    ImageView kathaImage;
    public kathaviewholder(@NonNull View itemView) {
        super(itemView);

        kathaName=itemView.findViewById(R.id.kathaName);
        kathaStory=itemView.findViewById(R.id.kathaStory);
        kathaImage=itemView.findViewById(R.id.kathaImage);


    }
}
