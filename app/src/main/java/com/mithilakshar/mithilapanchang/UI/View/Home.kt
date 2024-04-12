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
/*

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






        //val collectionRef = db!!.collection(currentMonth)
       // val query = collectionRef.whereEqualTo("date", currentDate)
        //query.get().addOnCompleteListener { task ->
     */
/*       if (task.isSuccessful) {
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
*//*






*/
/*
    override fun onInit(i: Int) {
        if (i == TextToSpeech.SUCCESS) {


            // Set language
            //delayedTask(0)


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
    }*//*

}}
*/
