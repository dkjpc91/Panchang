package com.mithilakshar.mithilapanchang.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mithilakshar.mithilapanchang.Models.holidaydatamodel
import com.mithilakshar.mithilapanchang.databinding.HolidayitemBinding
import com.squareup.picasso.Picasso


class holidayadapter(var datalist: ArrayList<holidaydatamodel>) :  RecyclerView.Adapter<holidayadapter.holidayviewholder>() {

    class holidayviewholder(val binding:HolidayitemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: holidaydatamodel){
            binding.apply {

                holidayDesc.text=model.holidayDesc
                holidayName.text=model.holidayName
                Picasso .get()
                    .load(model.imageUrl)  // Replace with your image URL
                    .into(binding.holidayImage)

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holidayviewholder {
        val binding = HolidayitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return holidayviewholder(binding)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: holidayviewholder, position: Int) {
        val currentdata=datalist.get(position)

        if (currentdata != null) {
            holder.bind(currentdata)
        }

    }


}
