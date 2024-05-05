package com.mithilakshar.mithilapanchang.Adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mithilakshar.mithilapanchang.Dialog.calendardialog
import com.mithilakshar.mithilapanchang.R
import com.mithilakshar.mithilapanchang.Models.calendardatamodel
import com.mithilakshar.mithilapanchang.Models.mantradatamodel
import com.mithilakshar.mithilapanchang.databinding.CalendardayitemBinding
import com.squareup.picasso.Picasso

class CalendarAdapter(var datalist: List<calendardatamodel>,val context: Context) : RecyclerView.Adapter<CalendarAdapter.calendarviewholder>() {

    class calendarviewholder(val binding: CalendardayitemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: calendardatamodel,context: Context){
            binding.apply {


                calendardayText.text=model.day
                calendardateText.text=model.date
                calendardescText.text=model.desc


            }

            binding.root.setOnClickListener {

                val calendarDialog = calendardialog(context)
                calendarDialog.setcalendardialogtext("${model.date}")
                calendarDialog.setcalendardialogtext1(" ${model.day}, ${model.desc}" )
                calendarDialog.show()
            }

        }
    }

        private var itemWidth = 0
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): calendarviewholder {

            val binding=CalendardayitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

            val layoutParams = binding.root.layoutParams
            layoutParams.width = itemWidth
            binding.root.layoutParams = layoutParams

            return calendarviewholder(binding)
        }

        override fun onBindViewHolder(holder: calendarviewholder, position: Int) {
            val currentdata=datalist.get(position)

            if (currentdata != null) {
                holder.bind(currentdata,context)
            }

        }

        fun setItemWidth(itemWidth: Int) {
            this.itemWidth = itemWidth
            notifyDataSetChanged() // Refresh the adapter to apply the new item width
        }

        override fun getItemCount(): Int {
            return datalist.size
        }

        private fun translateToHindi(currentMonth: String): String? {
            // Manually create a mapping for English to Hindi month names
            val monthTranslation: MutableMap<String, String> = HashMap()
            monthTranslation["January"] = "जनवरी"
            monthTranslation["February"] = "फ़रवरी"
            monthTranslation["March"] = "मार्च"
            monthTranslation["April"] = "अप्रैल"
            monthTranslation["May"] = "मई"
            monthTranslation["June"] = "जून"
            monthTranslation["July"] = "जुलाई"
            monthTranslation["August"] = "अगस्त"
            monthTranslation["September"] = "सितंबर"
            monthTranslation["October"] = "अक्टूबर"
            monthTranslation["November"] = "नवंबर"
            monthTranslation["December"] = "दिसंबर"
            // Return the translated month name
            return monthTranslation[currentMonth]
        }

        private fun translateToHindiday(currentDay: String): String? {
            // Manually create a mapping for English to Hindi month names
            val monthTranslation: MutableMap<String, String> = HashMap()
            monthTranslation["Mon"] = "सोमवार"
            monthTranslation["Tue"] = "मंगलवार"
            monthTranslation["Wed"] = "बुधवार"
            monthTranslation["Thu"] = "गुरुवार"
            monthTranslation["Fri"] = "शुक्रवार"
            monthTranslation["Sat"] = "शनिवार"
            monthTranslation["Sun"] = "रविवार"
            // Return the translated month name
            return monthTranslation[currentDay]
        }

        private fun translateToHindidate(date: String?): String? {
            // Manually create a mapping for English to Hindi month names
            val nmap: MutableMap<String?, String> = HashMap()
            nmap["01"] = "१"
            nmap["02"] = "२"
            nmap["03"] = "३"
            nmap["04"] = "४"
            nmap["05"] = "५"
            nmap["06"] = "६"
            nmap["07"] = "७"
            nmap["08"] = "८"
            nmap["09"] = "९"
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






