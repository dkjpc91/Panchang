package com.mithilakshar.mithilapanchang.Adapters

import com.mithilakshar.mithilapanchang.Models.Commentary



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.mithilakshar.mithilapanchang.Models.Translation

import com.mithilakshar.mithilapanchang.databinding.TranslationitemBinding


class commentryAdapter(var datalist: List<Commentary>) : RecyclerView.Adapter<commentryAdapter.commentryViewholder>() {


    class commentryViewholder(val binding : TranslationitemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Commentary){
            binding.apply {

                    translation.text=model.description

            }

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): commentryViewholder {

        val binding = TranslationitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return commentryViewholder(binding)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: commentryViewholder, position: Int) {

        val currentdata=datalist.get(position)

        if (currentdata != null) {
            holder.bind(currentdata)
        }

    }
}