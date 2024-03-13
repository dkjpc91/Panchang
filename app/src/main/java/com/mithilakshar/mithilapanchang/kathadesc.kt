package com.mithilakshar.mithilapanchang

import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import java.util.Locale

class kathadesc : AppCompatActivity(), OnInitListener {
    var kathaDesc: TextView? = null
    var kathaTitle: TextView? = null
    var kathaImg: ImageView? = null
    private var textToSpeech: TextToSpeech? = null
    var kathaD: String? = null
    private val handler = Handler()
    private var isFabClicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kathadesc)
        kathaTitle = findViewById(R.id.kathatitle)
        kathaDesc = findViewById(R.id.kathaDesc)
        kathaImg = findViewById(R.id.kathaImg)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val intent = intent
        val locale = Locale("hi", "IN")
        Locale.setDefault(locale)
        val kathaT = intent.getStringExtra("kathaName")
        kathaD = intent.getStringExtra("kathaStory")
        val kathaI = intent.getStringExtra("kathaUrl")
        kathaTitle.setText(kathaT)
        kathaDesc.setText(kathaD)
        Picasso.get().load(kathaI).into(kathaImg)
        textToSpeech = TextToSpeech(this, this)
        switchFabColor(fab)
        fab.setOnClickListener {
            isFabClicked = !isFabClicked
            if (isFabClicked) {
                fab.setImageResource(R.drawable.speaker)
                switchFabColor(fab)
                delayedTask(500)
            } else {
                fab.setImageResource(R.drawable.mutespeaker)
                switchFabColor(fab)
                textToSpeech!!.stop()
            }
        }
    }

    private fun switchFabColor(fab: FloatingActionButton) {
        if (isFabClicked) {
            // Set the original color if it's switched
            fab.backgroundTintList = ContextCompat.getColorStateList(this, R.color.fabColorOriginal)
        } else {
            // Set the switched color
            fab.backgroundTintList = ContextCompat.getColorStateList(this, R.color.fabColorSwitched)
        }
    }

    override fun onInit(i: Int) {
        if (i == TextToSpeech.SUCCESS) {


            // Set language
            textToSpeech!!.setLanguage(Locale.forLanguageTag("hi"))

            // Speak text
            textToSpeech!!.setPitch(1f)
            textToSpeech!!.setSpeechRate(0.6f)
        } else {
            // Handle error
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Shutdown TextToSpeech engine
        if (textToSpeech != null) {
            textToSpeech!!.stop()
            textToSpeech!!.shutdown()
        }
    }

    override fun onPause() {
        super.onPause()
        if (textToSpeech != null) {
            textToSpeech!!.stop()
            textToSpeech!!.shutdown()
        }
    }

    private fun delayedTask(delayMillis: Int) {
        handler.postDelayed({ // Your code to be executed after the delay
            textToSpeech!!.setLanguage(Locale.forLanguageTag("hi"))

            // Speak text
            textToSpeech!!.setPitch(1f)
            textToSpeech!!.setSpeechRate(0.5f)
            val chunkSize = 4000
            var i = 0
            while (i < kathaD!!.length) {
                val endIndex = Math.min(i + chunkSize, kathaD!!.length)
                val chunk = kathaD!!.substring(i, endIndex)
                textToSpeech!!.speak(chunk, TextToSpeech.QUEUE_ADD, null, null)
                i += chunkSize
            }
        }, delayMillis.toLong())
    }
}