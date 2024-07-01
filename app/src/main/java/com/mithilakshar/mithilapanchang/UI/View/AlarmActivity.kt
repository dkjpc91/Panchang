package com.mithilakshar.mithilapanchang.UI.View

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mithilakshar.mithilapanchang.Adapters.RingtoneAdapter
import com.mithilakshar.mithilapanchang.R
import com.mithilakshar.mithilapanchang.databinding.ActivityAlarmBinding
import java.text.SimpleDateFormat
import java.util.*

class AlarmActivity : AppCompatActivity() {

    private lateinit var selectRingtoneButton: Button
    private lateinit var ringtoneRecyclerView: RecyclerView
    private lateinit var ringtoneAdapter: RingtoneAdapter
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("RingtonePrefs", MODE_PRIVATE)
    }

    private val ringtones = arrayOf(
        R.raw.ram,  // Replace these with actual raw resource IDs
        R.raw.ganpati
    )

    private var selectedDateTime: Calendar = Calendar.getInstance()
    private var savedRingtones: MutableList<Pair<String, String>> = mutableListOf()

    lateinit var binding: ActivityAlarmBinding
    private var alertDialog: AlertDialog? = null // Reference to AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectRingtoneButton = binding.saveButton
        ringtoneRecyclerView = binding.ringtoneRecyclerView
        ringtoneRecyclerView.layoutManager = LinearLayoutManager(this)

        selectRingtoneButton.setOnClickListener {
            showDatePicker()
        }

        loadSavedRingtones() // Load saved ringtones initially

        ringtoneAdapter = RingtoneAdapter(savedRingtones) { position ->
            deleteRingtone(position)
        }
        ringtoneRecyclerView.adapter = ringtoneAdapter
    }

    private fun showDatePicker() {
        val currentDate = Calendar.getInstance()
        DatePickerDialog(this, { _, year, month, dayOfMonth ->
            selectedDateTime.set(Calendar.YEAR, year)
            selectedDateTime.set(Calendar.MONTH, month)
            selectedDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            showTimePicker()
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun showTimePicker() {
        val currentTime = Calendar.getInstance()
        TimePickerDialog(this, { _, hourOfDay, minute ->
            selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            selectedDateTime.set(Calendar.MINUTE, minute)
            showRingtonePicker()
        }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime.get(Calendar.MINUTE), true).show()
    }

    private fun showRingtonePicker() {
        // Dialog to collect title and message text
        val dialogView = layoutInflater.inflate(R.layout.dialog_ringtone_details, null)
        val titleEditText = dialogView.findViewById<EditText>(R.id.titleEditText)
        val messageEditText = dialogView.findViewById<EditText>(R.id.messageEditText)

        alertDialog = AlertDialog.Builder(this)
            .setTitle("Enter Details")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                val title = titleEditText.text.toString()
                val messageText = messageEditText.text.toString()

                saveRingtone(title, messageText) // Save the selected time, title, and message
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun saveRingtone(title: String, messageText: String) {
        val editor = sharedPreferences.edit()
        val dateTime = selectedDateTime.timeInMillis
        val formattedDateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(selectedDateTime.time)

        val ringtoneKey = "ringtone_$dateTime"
        val ringtoneValue = "$title|$formattedDateTime"

        editor.putString(ringtoneKey, ringtoneValue)
        editor.apply()

        savedRingtones.add(title to formattedDateTime) // Add to the list to display
        ringtoneAdapter.notifyDataSetChanged()

        Toast.makeText(this, "Ringtone saved with selected date and time", Toast.LENGTH_SHORT).show()
    }

    private fun loadSavedRingtones() {
        savedRingtones.clear()
        val allEntries = sharedPreferences.all
        for ((key, value) in allEntries) {
            if (key.startsWith("ringtone_")) {
                val dateTime = key.removePrefix("ringtone_").toLongOrNull()
                dateTime?.let {
                    val ringtoneData = value as String
                    val dataParts = ringtoneData.split("|")
                    if (dataParts.size >= 2) {
                        val title = dataParts[0]
                        val dateTimeString = dataParts[1]
                        savedRingtones.add(title to dateTimeString)
                    }
                }
            }
        }
    }

    private fun deleteRingtone(position: Int) {
        val editor = sharedPreferences.edit()
        val dateTime = selectedDateTime.timeInMillis
        val ringtoneKey = "ringtone_$dateTime"
        editor.remove(ringtoneKey)
        editor.apply()
        savedRingtones.removeAt(position)
        ringtoneAdapter.notifyItemRemoved(position)
        Toast.makeText(this, "Ringtone deleted", Toast.LENGTH_SHORT).show()
    }
}
