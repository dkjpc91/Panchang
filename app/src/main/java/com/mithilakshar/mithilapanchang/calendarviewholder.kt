package com.mithilakshar.mithilapanchang

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class calendarviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var calendardayText: TextView
    var calendardateText: TextView
    var calendardescText: TextView

    init {
        calendardayText = itemView.findViewById(R.id.calendardayText)
        calendardateText = itemView.findViewById(R.id.calendardateText)
        calendardescText = itemView.findViewById(R.id.calendardescText)
    }
}
