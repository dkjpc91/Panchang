package com.mithilakshar.mithilapanchang.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mithilakshar.mithilapanchang.Models.eclipsedatamodel
import com.mithilakshar.mithilapanchang.databinding.EclipseitemBinding
import com.squareup.picasso.Picasso

class eclipseadapter(var datalist: List<eclipsedatamodel>) : RecyclerView.Adapter<eclipseadapter.eclipseviewholder>() {


    class eclipseviewholder(val binding:EclipseitemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: eclipsedatamodel){
            binding.apply {

                eclipseDesc.text=model.eclipseDesc
                eclipseName.text=model.eclipseName
                Picasso .get()
                    .load(model.imageUrl)  // Replace with your image URL
                    .into(binding.eclipseImage)

            }

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): eclipseviewholder {
        val binding = EclipseitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return eclipseviewholder(binding)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: eclipseviewholder, position: Int) {
        val currentdata=datalist.get(position)

        if (currentdata != null) {
            holder.bind(currentdata)
        }

    }



}
