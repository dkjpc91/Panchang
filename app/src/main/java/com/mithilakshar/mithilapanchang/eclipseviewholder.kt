package com.mithilakshar.mithilapanchang

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class eclipseviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var eclipseName: TextView
    var eclipseDesc: TextView
    var eclipseImage: ImageView

    init {
        eclipseName = itemView.findViewById(R.id.eclipseName)
        eclipseDesc = itemView.findViewById(R.id.eclipseDesc)
        eclipseImage = itemView.findViewById(R.id.eclipseImage)
    }
}
