package com.mithilakshar.mithilapanchang.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mithilakshar.mithilapanchang.R

class RingtoneAdapter(
    private val ringtones: MutableList<Pair<String, String>>,
    private val deleteRingtone: (Int) -> Unit
) : RecyclerView.Adapter<RingtoneAdapter.RingtoneViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RingtoneViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alarm_data, parent, false)
        return RingtoneViewHolder(view)
    }

    override fun onBindViewHolder(holder: RingtoneViewHolder, position: Int) {
        val (title, dateTimeString) = ringtones[position]
        holder.bind(title, dateTimeString, deleteRingtone)
    }

    override fun getItemCount(): Int = ringtones.size

    inner class RingtoneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.ringtoneTextView)
        private val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)

        fun bind(title: String, dateTimeString: String, deleteRingtone: (Int) -> Unit) {
            titleTextView.text = title
            timeTextView.text = dateTimeString // Display formatted date/time
            deleteButton.setOnClickListener {
                deleteRingtone(adapterPosition)
            }
        }
    }
}
