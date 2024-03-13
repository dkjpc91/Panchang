package com.mithilakshar.mithilapanchang

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class mantraviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var mantraName: TextView
    var mantraDesc: TextView
    var mantraImage: ImageView

    init {
        mantraName = itemView.findViewById(R.id.mantraName)
        mantraDesc = itemView.findViewById(R.id.mantraDesc)
        mantraImage = itemView.findViewById(R.id.mantraImage)
    }
}
