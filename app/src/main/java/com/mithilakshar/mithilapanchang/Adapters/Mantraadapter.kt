package com.mithilakshar.mithilapanchang.Adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mithilakshar.mithilapanchang.Dialog.Mantradialog
import com.mithilakshar.mithilapanchang.Models.mantradatamodel
import com.mithilakshar.mithilapanchang.R
import com.mithilakshar.mithilapanchang.Dialog.calendardialog
import com.mithilakshar.mithilapanchang.Models.eclipsedatamodel
import com.mithilakshar.mithilapanchang.databinding.EclipseitemBinding
import com.mithilakshar.mithilapanchang.databinding.MantraitemBinding
import com.squareup.picasso.Picasso

class mantraadapter(var datalist:  List<Map<String, Any?>>,var context: Context) : RecyclerView.Adapter<mantraadapter.mantraviewholder>() {

    class mantraviewholder(val binding:MantraitemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Map<String, Any?>,context: Context){
            binding.apply {

                mantraName.text= model.get("mantraName").toString()
                Picasso .get()
                    .load(model.get("mantraImageurl").toString())  // Replace with your image URL
                    .into(binding.mantraImage)

            }
            binding.root.setOnClickListener {

                val calendardialog = Mantradialog(it.context)
                calendardialog.setmantradialogtext(model.get("mantraName").toString())
                calendardialog.setmantradialogtext1(model.get("mantraDesc").toString())
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
            holder.bind(currentdata,context)
        }

    }


}
