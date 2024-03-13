package com.mithilakshar.mithilapanchang

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class holidayviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var holidayName: TextView
    var holidayDesc: TextView
    var holidayImage: ImageView

    init {
        holidayName = itemView.findViewById(R.id.holidayName)
        holidayDesc = itemView.findViewById(R.id.holidayDesc)
        holidayImage = itemView.findViewById(R.id.holidayImage)
    }
}
