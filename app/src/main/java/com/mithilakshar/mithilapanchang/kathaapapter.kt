package com.mithilakshar.mithilapanchang

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class kathaapapter(var datalist: ArrayList<kathadatamodel?>) :
    RecyclerView.Adapter<kathaviewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kathaviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.kathaitem, parent, false)
        return kathaviewholder(view)
    }

    override fun onBindViewHolder(holder: kathaviewholder, position: Int) {
        holder.kathaName.text = datalist[position]!!.kathaName
        holder.kathaStory.text = datalist[position]!!.kathaStory
        Picasso.get().load(datalist[position]!!.kathaUrl).into(holder.kathaImage)
        holder.itemView.setOnClickListener { view ->
            val i = Intent(view.context, kathadesc::class.java)
            i.putExtra("kathaName", datalist[position]!!.kathaName)
            i.putExtra("kathaStory", datalist[position]!!.kathaStory)
            i.putExtra("kathaUrl", datalist[position]!!.kathaUrl)
            view.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return datalist.size
    }
}
