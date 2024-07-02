package com.mithilakshar.mithilapanchang.UI.View

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mithilakshar.mithilapanchang.Adapters.RingtoneAdapter
import com.mithilakshar.mithilapanchang.Adapters.RingtonePickerAdapter
import com.mithilakshar.mithilapanchang.R
import com.mithilakshar.mithilapanchang.Room.Ringtone

import com.mithilakshar.mithilapanchang.Utility.AlarmHelper
import com.mithilakshar.mithilapanchang.Utility.MyApplication
import com.mithilakshar.mithilapanchang.ViewModel.RingtoneViewmodel
import com.mithilakshar.mithilapanchang.databinding.ActivityAlarmBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.lifecycle.Observer
import java.util.*

class AlarmActivity : AppCompatActivity() {

    private lateinit var alarmHelper: AlarmHelper

    private lateinit var selectRingtoneButton: Button
    private lateinit var ringtoneRecyclerView: RecyclerView
    private lateinit var ringtoneAdapter: RingtoneAdapter

    private val ringtoneViewModel: RingtoneViewmodel by viewModels {
        RingtoneViewmodel.RingtoneViewmodelFactory((application as MyApplication).repository)
    }
    private var selectedDateTime: Calendar = Calendar.getInstance()

    private val ringtones = arrayOf(
        R.raw.ram,  // Replace these with actual raw resource IDs
        R.raw.ganpati
    )



    lateinit var binding: ActivityAlarmBinding
    private var alertDialog: AlertDialog? = null // Reference to AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectRingtoneButton = binding.saveButton
        ringtoneRecyclerView = binding.ringtoneRecyclerView
        ringtoneRecyclerView.layoutManager = LinearLayoutManager(this)
        ringtoneAdapter=RingtoneAdapter(mutableListOf()){

            ringtoneViewModel.deleteringtone(it)
        }

        ringtoneRecyclerView.apply {
            adapter = ringtoneAdapter
            layoutManager = LinearLayoutManager(this@AlarmActivity)
        }

        // Observe the LiveData
        ringtoneViewModel.allringtone.observe(this, Observer {
        it.let {
            ringtoneAdapter.setringtone(it)
        }
        })


        alarmHelper = AlarmHelper(this)



        selectRingtoneButton.setOnClickListener {
            showDatePicker()
        }







        //loadSavedRingtones()

    }

    private fun showDatePicker() {
        val currentDate = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                selectedDateTime.set(Calendar.YEAR, year)
                selectedDateTime.set(Calendar.MONTH, month)
                selectedDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                showTimePicker()
            },
            currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
            currentDate.get(Calendar.DAY_OF_MONTH)
        ).show()
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
        val ringtoneNames = ringtones.map { resources.getResourceEntryName(it) }.toTypedArray()

        // Dialog to collect title and message text
        val dialogView = layoutInflater.inflate(R.layout.dialog_ringtone_details, null)
        val titleEditText = dialogView.findViewById<EditText>(R.id.titleEditText)
        val messageEditText = dialogView.findViewById<EditText>(R.id.messageEditText)

        alertDialog = AlertDialog.Builder(this)
            .setTitle("Enter Details")
            .setView(dialogView)
            .setPositiveButton("go ") { _, _ ->
                val title = titleEditText.text.toString()
                val messageText = messageEditText.text.toString()



                // Save the selected time, title, and message
                val ringtonePickerView = ListView(this)

                val ringtonePickerDialog = AlertDialog.Builder(this)
                    .setTitle("Select Ringtone")
                    .setView(ringtonePickerView)
                    .create()

                ringtonePickerDialog.show()

                ringtonePickerView.adapter = RingtonePickerAdapter(this, ringtoneNames, ringtones) { selectedRingtone, selectedTitle, selectedMessage ->
                    saveRingtone(selectedRingtone, title, messageText)
                    //loadSavedRingtones()
                    ringtoneAdapter.notifyDataSetChanged()
                    alertDialog?.dismiss()
                    ringtonePickerDialog.dismiss()// Dismiss the main AlertDialog after selecting the ringtone
                }


            }
            .setNegativeButton("Cancel", null)
            .show()
    }


    private fun saveRingtone(selectedRingtone: Int, title: String, messageText: String) {

        ringtoneViewModel.insertringtone(Ringtone(0,messageText,selectedRingtone,selectedDateTime.timeInMillis))

        // Add to the list to display
        ringtoneAdapter.notifyDataSetChanged()

        Toast.makeText(this, "Ringtone saved with selected date and time", Toast.LENGTH_SHORT)
            .show()
    }


}
