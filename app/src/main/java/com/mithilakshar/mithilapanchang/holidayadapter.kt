package com.mithilakshar.mithilapanchang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class holidayadapter(var datalist: ArrayList<holidaydatamodel?>) :
    RecyclerView.Adapter<holidayviewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holidayviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holidayitem, parent, false)
        return holidayviewholder(view)
    }

    override fun onBindViewHolder(holder: holidayviewholder, position: Int) {
        holder.holidayName.text = datalist[position].getHolidayName()
        holder.holidayDesc.text = datalist[position].getHolidayDesc()
        Picasso.get().load(datalist[position].getImageUrl()).into(holder.holidayImage)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }
}
