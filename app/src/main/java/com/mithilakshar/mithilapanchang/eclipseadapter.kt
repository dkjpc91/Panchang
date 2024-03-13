package com.mithilakshar.mithilapanchang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class eclipseadapter(var datalist: ArrayList<eclipsedatamodel?>) :
    RecyclerView.Adapter<eclipseviewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): eclipseviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.eclipseitem, parent, false)
        return eclipseviewholder(view)
    }

    override fun onBindViewHolder(holder: eclipseviewholder, position: Int) {
        holder.eclipseName.text = datalist[position]!!.eclipseName
        holder.eclipseDesc.text = datalist[position]!!.eclipseDesc
        Picasso.get().load(datalist[position]!!.imageUrl).into(holder.eclipseImage)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }
}
