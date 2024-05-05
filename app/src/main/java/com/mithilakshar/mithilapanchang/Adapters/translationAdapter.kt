package com.mithilakshar.mithilapanchang.Adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.mithilakshar.mithilapanchang.Models.Translation

import com.mithilakshar.mithilapanchang.databinding.TranslationitemBinding


class translationAdapter(var datalist: List<Translation>) : RecyclerView.Adapter<translationAdapter.translationviewholder>() {


    class translationviewholder(val binding : TranslationitemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Translation){
            binding.apply {

                translation.text=model.description
            }

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): translationviewholder {

        val binding = TranslationitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return translationviewholder(binding)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: translationviewholder, position: Int) {

        val currentdata=datalist.get(position)

        if (currentdata != null) {
            holder.bind(currentdata)
        }

    }
}