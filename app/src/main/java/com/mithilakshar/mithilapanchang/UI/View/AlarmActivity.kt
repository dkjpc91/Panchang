package com.mithilakshar.mithilapanchang.UI.View

import android.app.DatePickerDialog
import android.app.TimePickerDialog

import android.os.Bundle
import android.view.View

import android.widget.EditText
import android.widget.ListView

import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
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

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.mithilakshar.mithilapanchang.ViewModel.HomeViewModel
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random


class AlarmActivity : AppCompatActivity() {

    private lateinit var alarmHelper: AlarmHelper

    private lateinit var selectRingtoneButton: AppCompatButton
    private lateinit var ringtoneRecyclerView: RecyclerView
    private lateinit var ringtoneAdapter: RingtoneAdapter
    var notificationurl: List<String> = arrayListOf()
    private val ringtoneViewModel: RingtoneViewmodel by viewModels {
        RingtoneViewmodel.RingtoneViewmodelFactory((application as MyApplication).repository)
    }
    private var selectedDateTime: Calendar = Calendar.getInstance()

    private val ringtones = arrayOf(
        R.raw.ram,
        R.raw.shyama,
        R.raw.jai_jai_bhairab,
        R.raw.maithili,
        R.raw.achyutam_keshavam,
        R.raw.adharam_madhuram,
        R.raw.bajrang_baan,
        R.raw.bajrang_baan_,
        R.raw.ganpati,
        R.raw.gayatri_mantra,
        R.raw.hanuman_chalisa,
        R.raw.hanuman_gyan_gun,
        R.raw.jai_hanuman,
        R.raw.jai_raja_raam,
        R.raw.jai_hanuman_sant,
        R.raw.kesari_ke_laal,
        R.raw.prabhu_ji,
        R.raw.ram_aayege,
        R.raw.serawaliye,
        R.raw.shashank_shekhar,
        R.raw.ye_chamak
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

        alarmHelper = AlarmHelper(this)

        val viewModel: HomeViewModel by lazy {
            ViewModelProvider(this).get(HomeViewModel::class.java)
        }

        lifecycleScope.launch {

            notificationurl = viewModel.getappbarImagelist("appbar")
            if (notificationurl.size != 0) {
                val random = Random.nextInt(notificationurl.size)

            }


        }


        ringtoneAdapter=RingtoneAdapter(mutableListOf()){

            ringtoneViewModel.deleteringtone(it)

            alarmHelper.cancelAlarm(it.title,it.message,it.selectedRingtone,notificationurl[0])
            Toast.makeText(this, "आहाँ के अलार्म ${it.title} कैंसिल भ गेल ", Toast.LENGTH_LONG)
                .show()

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







        selectRingtoneButton.setOnClickListener {
            showDatePicker()
        }









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
        val ringtoneName = ringtones.map { resources.getResourceEntryName(it) }.toTypedArray()
         val ringtoneNames = arrayOf(
            "राम",
             "श्यामा",
             "जय जय भैरव",
             "मैथिली",
            "अच्युतम केशवम",
            "अधरम मधुरम",
            "बजरंग बाण",
            "बजरंग-बाण",
            "गणपति",
            "गायत्री मंत्र",
            "हनुमान चालीसा",
            "हनुमान ज्ञान गुण",
            "जय हनुमान",
            "जय राजा राम",
            "जय हनुमान संत",
            "केसरी के लाल",
            "प्रभु जी",
            "राम आएंगे",
            "सेरावालिये",
            "शशांक शेखर",
            "ये चमक"
        )

        // Dialog to collect title and message text
        val dialogView = layoutInflater.inflate(R.layout.dialog_ringtone_details, null)
        val titleEditText = dialogView.findViewById<EditText>(R.id.titleEditText)
        val messageEditText = dialogView.findViewById<EditText>(R.id.messageEditText)

        alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("आगू बढू") { _, _ ->

                val title = if (titleEditText.text.isBlank()) "अलार्म" else titleEditText.text.toString()
                val messageText = if (messageEditText.text.isBlank()) "मिथिला पंचांग" else messageEditText.text.toString()



                // Save the selected time, title, and message
                val ringtonePickerView = ListView(this)


                val ringtonePickerDialog = AlertDialog.Builder(this)
                    .setTitle("मनपसंद टोन सेलेक्ट करू")
                    .setView(ringtonePickerView)
                    .create()

                ringtonePickerDialog.show()

                ringtonePickerView.adapter = RingtonePickerAdapter(this, ringtoneName, ringtones) { selectedRingtone, selectedTitle, selectedMessage ->


                    Toast.makeText(this, "picker tone# $selectedRingtone", Toast.LENGTH_LONG)
                        .show()
                    saveRingtone(selectedRingtone, title, messageText)
                    ringtoneAdapter.notifyDataSetChanged()
                    alertDialog?.dismiss()
                    ringtonePickerDialog.dismiss()// Dismiss the main AlertDialog after selecting the ringtone
                }


            }
            .show()


    }


    private fun saveRingtone(selectedRingtone: Int, title: String, messageText: String) {

        ringtoneViewModel.insertringtone(Ringtone(0,messageText,title,selectedRingtone,selectedDateTime.timeInMillis))

        // Add to the list to display
        ringtoneAdapter.notifyDataSetChanged()
        val calendar = Calendar.getInstance().apply {
            timeInMillis = selectedDateTime.timeInMillis
        }
        Toast.makeText(this, "आहाँ के अलार्म ${title} सेट भ गेल $selectedRingtone", Toast.LENGTH_LONG)
            .show()
        alarmHelper.setAlarm (calendar,title,messageText,selectedRingtone,notificationurl[0])


    }


}
