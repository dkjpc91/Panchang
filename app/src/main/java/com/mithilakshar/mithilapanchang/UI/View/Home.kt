package com.mithilakshar.mithilapanchang.UI.View

import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.mithilakshar.mithilapanchang.R
import com.mithilakshar.mithilapanchang.databinding.ActivityHomeBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class home : AppCompatActivity(), OnInitListener {

    lateinit var binding: ActivityHomeBinding
    var db: FirebaseFirestore? = null
    var firebaseMessaging: FirebaseMessaging? = null

    private var textToSpeech: TextToSpeech? = null
    var speak: String? = null
    var counter = 0
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val monthFormat = SimpleDateFormat("MMMM")
        val dayFormat = SimpleDateFormat("EEE")
        val dateFormat = SimpleDateFormat("dd")
        val currentMonth = monthFormat.format(Date())
        val currentDay = dayFormat.format(Date())
        val currentDate = dateFormat.format(Date())
        val hindiMonth = translateToHindi(currentMonth)
        val hindiDay = translateToHindiday(currentDay)
        val hindidate = translateToHindidate(currentDate)



        val collectionRef = db!!.collection(currentMonth)
        val query = collectionRef.whereEqualTo("date", currentDate)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (doc in task.result) {
                    val date = doc.getString("date")
                    val day = doc.getString("day")
                    val desc = doc.getString("desc")
                    val daydesc = doc.getString("speak")

                    speak = daydesc
                }
            }
        }

        textToSpeech = TextToSpeech(this, this)
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

    private fun translateToHindidate(date: String): String? {
        // Manually create a mapping for English to Hindi month names
        val nmap: MutableMap<String, String> = HashMap()
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

    override fun onInit(i: Int) {
        if (i == TextToSpeech.SUCCESS) {


            // Set language
            delayedTask(0)


            // Speak text
        } else {
            // Handle error
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Shutdown TextToSpeech engine
        textToSpeech!!.stop()
        textToSpeech!!.shutdown()
    }

    override fun onPause() {
        super.onPause()
        textToSpeech!!.stop()
        textToSpeech!!.shutdown()
    }

    override fun onResume() {
        super.onResume()
        delayedTask(1000)
    }

    private fun delayedTask(delayMillis: Int) {
        handler.postDelayed({ // Your code to be executed after the delay
            textToSpeech!!.setLanguage(Locale.forLanguageTag("hi"))

            // Speak text
            textToSpeech!!.setPitch(1f)
            textToSpeech!!.setSpeechRate(0.6f)
            textToSpeech!!.speak(speak, TextToSpeech.QUEUE_FLUSH, null, null)
        }, delayMillis.toLong())
    }
}
