package com.mithilakshar.mithilapanchang.Adapters


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.mithilakshar.mithilapanchang.Models.bhagwatGitaChapterItem
import com.mithilakshar.mithilapanchang.UI.Fragments.BhagwatGitaChapterFragmentDirections
import androidx.navigation.Navigation

import com.mithilakshar.mithilapanchang.databinding.BhagwatgitachapteritemBinding

class BhagwatGitaChapterAdapter(val list: MutableList<bhagwatGitaChapterItem>,val context: Context):RecyclerView.Adapter<BhagwatGitaChapterAdapter.BhagwatGitaViewHolder>() {

    class BhagwatGitaViewHolder(val binding:BhagwatgitachapteritemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind( model: bhagwatGitaChapterItem,context: Context){

            binding.apply {

                ChapterName.text=model.name
                chapternumber.text=   " अध्याय : ${translateToHindidate(model.chapter_number.toString())}"

            }

            binding.root.setOnClickListener {

                val action =  BhagwatGitaChapterFragmentDirections
                    .actionBhagwatGitaChapterFragmentToBhagwatGitaVersesFragment(model.chapter_number)
                Navigation.findNavController(it).navigate(action)


            }

        }

        private fun translateToHindidate(date: String): String? {
            // Manually create a mapping for English to Hindi month names
            val nmap: MutableMap<String, String> = HashMap()
            nmap["1"] = "१"
            nmap["2"] = "२"
            nmap["3"] = "३"
            nmap["4"] = "४"
            nmap["5"] = "५"
            nmap["6"] = "६"
            nmap["7"] = "७"
            nmap["8"] = "८"
            nmap["9"] = "९"
            nmap["10"] = "१०"
            nmap["11"] = "११"
            nmap["12"] = "१२"
            nmap["13"] = "१३"
            nmap["14"] = "१४"
            nmap["15"] = "१५"
            nmap["16"] = "१६"
            nmap["17"] = "१७"
            nmap["18"] = "१८"
            nmap["19"] = "१९"
            nmap["20"] = "२०"
            nmap["21"] = "२१"
            nmap["22"] = "२२"
            nmap["23"] = "२३"
            nmap["24"] = "२४"
            nmap["25"] = "२५"
            nmap["26"] = "२६"
            nmap["27"] = "२७"
            nmap["28"] = "२८"
            nmap["29"] = "२९"
            nmap["30"] = "३०"
            nmap["31"] = "३१"
            // Return the translated month name
            return nmap[date]
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BhagwatGitaViewHolder {
        var binding=BhagwatgitachapteritemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BhagwatGitaViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BhagwatGitaViewHolder, position: Int) {
        val currentdata:bhagwatGitaChapterItem=list.get(position)

        holder.bind(currentdata,context)
    }




}