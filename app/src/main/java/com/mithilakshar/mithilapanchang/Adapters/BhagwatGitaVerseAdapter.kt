package com.mithilakshar.mithilapanchang.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mithilakshar.mithilapanchang.Models.BhagwatGitaVerseItem
import com.mithilakshar.mithilapanchang.UI.Fragments.BhagwatGitaChapterFragmentDirections
import com.mithilakshar.mithilapanchang.UI.Fragments.BhagwatGitaVersesFragmentDirections
import com.mithilakshar.mithilapanchang.databinding.BhagwatgitaverseitemBinding


class BhagwatGitaVerseAdapter(val list: MutableList<BhagwatGitaVerseItem>,val context: Context):RecyclerView.Adapter<BhagwatGitaVerseAdapter.BhagwatGitaVerseViewHolder>() {

    class BhagwatGitaVerseViewHolder(val binding: BhagwatgitaverseitemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(model:BhagwatGitaVerseItem,context:Context){

            binding.apply {


                ChapterName.text=" श्रीमद् भगवद्गीता "
                sholktext.text= model.text
            }

            binding.root.setOnClickListener {

                val action =  BhagwatGitaVersesFragmentDirections
                    .actionBhagwatGitaVersesFragmentToBhagwatGitaVerseDetailsFragment(model.id)

                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BhagwatGitaVerseViewHolder {

        var binding=BhagwatgitaverseitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BhagwatGitaVerseViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BhagwatGitaVerseViewHolder, position: Int) {
        val currentdata=list.get(position)

        holder.bind(currentdata,context)
    }
}