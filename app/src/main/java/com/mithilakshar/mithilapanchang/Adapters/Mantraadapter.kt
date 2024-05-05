package com.mithilakshar.mithilapanchang.Adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mithilakshar.mithilapanchang.Models.mantradatamodel
import com.mithilakshar.mithilapanchang.R
import com.mithilakshar.mithilapanchang.Dialog.calendardialog
import com.mithilakshar.mithilapanchang.Models.eclipsedatamodel
import com.mithilakshar.mithilapanchang.databinding.EclipseitemBinding
import com.mithilakshar.mithilapanchang.databinding.MantraitemBinding
import com.squareup.picasso.Picasso

class mantraadapter(var datalist: List<mantradatamodel>) : RecyclerView.Adapter<mantraadapter.mantraviewholder>() {

    class mantraviewholder(val binding:MantraitemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: mantradatamodel){
            binding.apply {

                mantraDesc.text=model.mantraDesc
                mantraName.text=model.mantraName
                Picasso .get()
                    .load(model.mantraImageurl)  // Replace with your image URL
                    .into(binding.mantraImage)

            }
            binding.root.setOnClickListener {

                val calendardialog = calendardialog(it.context)
                calendardialog.setcalendardialogtext(model.mantraName)
                calendardialog.setcalendardialogtext1(model.mantraDesc)
                calendardialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                calendardialog.show()

            }

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mantraviewholder {

        val binding = MantraitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return mantraviewholder(binding)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: mantraviewholder, position: Int) {

        val currentdata=datalist.get(position)

        if (currentdata != null) {
            holder.bind(currentdata)
        }

    }


}
