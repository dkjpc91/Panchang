package com.mithilakshar.mithilapanchang

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class mantraadapter(var datalist: ArrayList<mantradatamodel?>) :
    RecyclerView.Adapter<mantraviewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mantraviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mantraitem, parent, false)
        return mantraviewholder(view)
    }

    override fun onBindViewHolder(holder: mantraviewholder, position: Int) {
        holder.mantraName.text = datalist[position]!!.mantraName
        holder.mantraDesc.text = datalist[position]!!.mantraName

        holder.itemView.setOnClickListener { view ->
            val calendardialog = calendardialog(view.context)
            calendardialog.setcalendardialogtext(datalist[position]!!.mantraName)
            calendardialog.setcalendardialogtext1(datalist[position]!!.mantraDesc)
            calendardialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            calendardialog.show()
        }
    }

    override fun getItemCount(): Int {
        return datalist.size
    }
}
