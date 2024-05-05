package com.mithilakshar.mithilapanchang.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mithilakshar.mithilapanchang.Models.eclipsedatamodel
import com.mithilakshar.mithilapanchang.R
import com.mithilakshar.mithilapanchang.Models.kathadatamodel
import com.mithilakshar.mithilapanchang.UI.View.KathaDescriptionActivity
import com.mithilakshar.mithilapanchang.databinding.EclipseitemBinding
import com.mithilakshar.mithilapanchang.databinding.KathaitemBinding
import com.squareup.picasso.Picasso

class kathaapapter(var datalist: List<kathadatamodel>) : RecyclerView.Adapter<kathaapapter.kathaviewholder>() {

    class kathaviewholder(val binding:KathaitemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: kathadatamodel){
            binding.apply {

                kathaName.text=model.kathaName
                kathaStory.text=model.kathaStory
                Picasso .get()
                    .load(model.kathaUrl)  // Replace with your image URL
                    .into(binding.kathaImage)

            }

            binding.root.setOnClickListener {

                val i = Intent(it.context, KathaDescriptionActivity::class.java)
                i.putExtra("kathaName", model.kathaName)
                i.putExtra("kathaStory", model.kathaStory)
                i.putExtra("kathaUrl", model.kathaUrl)
                i.putExtra("audioURL", model.audioURL)
                it.context.startActivity(i)

            }
    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kathaviewholder {
        val binding = KathaitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return kathaviewholder(binding)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: kathaviewholder, position: Int) {

        val currentdata=datalist.get(position)

        if (currentdata != null) {
            holder.bind(currentdata)
        }

    }


}
