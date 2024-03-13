package com.mithilakshar.mithilapanchang

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class kathaviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var kathaName: TextView
    var kathaStory: TextView
    var kathaImage: ImageView

    init {
        kathaName = itemView.findViewById(R.id.kathaName)
        kathaStory = itemView.findViewById(R.id.kathaStory)
        kathaImage = itemView.findViewById(R.id.kathaImage)
    }
}
