package com.mithilakshar.mithilapanchang.Adapters

import java.text.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mithilakshar.mithilapanchang.R
import com.mithilakshar.mithilapanchang.Room.Ringtone

class RingtoneAdapter(
    var ringtones: MutableList<Ringtone>,
    private val deleteRingtone: (Ringtone) -> Unit // Lambda to delete a Ringtone
) : RecyclerView.Adapter<RingtoneAdapter.RingtoneViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RingtoneViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alarm_data, parent, false)
        return RingtoneViewHolder(view)
    }

    override fun onBindViewHolder(holder: RingtoneViewHolder, position: Int) {
        holder.bind(ringtones[position])
    }

    override fun getItemCount(): Int = ringtones.size

    inner class RingtoneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.ringtoneTextView)
        private val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)

        fun bind(ringtone: Ringtone) {
            titleTextView.text = ringtone.message
            // Format the date time for display
            val formattedDateTime = ringtone.dateTimeInMillis
            timeTextView.text = formattedDateTime.toString()
            deleteButton.setOnClickListener {
                val removedPosition = adapterPosition
                ringtones.removeAt(removedPosition)
                notifyItemRemoved(removedPosition)
                deleteRingtone(ringtone)  // Pass Ringtone object for deletion
            }
        }
    }

    fun setringtone(newlist: List<Ringtone>) {
        ringtones = newlist.toMutableList()
        notifyDataSetChanged()
    }
}
