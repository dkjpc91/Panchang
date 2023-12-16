package com.mithilakshar.mithilapanchang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeechService;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Locale;


public class kathadesc extends AppCompatActivity implements TextToSpeech.OnInitListener {

     TextView kathaDesc,kathaTitle;
    ImageView kathaImg;
    private TextToSpeech textToSpeech;

    String kathaD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kathadesc);

        kathaTitle=findViewById(R.id.kathatitle);
        kathaDesc=findViewById(R.id.kathaDesc);
        kathaImg=findViewById(R.id.kathaImg);
        Intent intent = getIntent();

        Locale locale=new Locale("hi", "IN");
        Locale.setDefault(locale);

      



        String kathaT = intent.getStringExtra("kathaName");
         kathaD = intent.getStringExtra("kathaStory");
        String kathaI = intent.getStringExtra("kathaUrl");

        kathaTitle.setText(kathaT);
        kathaDesc.setText(kathaD);
        Picasso.get().load(kathaI).into(kathaImg);



        textToSpeech = new TextToSpeech(this, this);










    }


    @Override
    public void onInit(int i) {

        if (i == TextToSpeech.SUCCESS) {


                        // Set language
            textToSpeech.setLanguage(Locale.forLanguageTag("hi"));

            // Speak text
            String text = kathaD;
            textToSpeech.setPitch(1f);
            textToSpeech.setSpeechRate(0.6f);
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            // Handle error

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Shutdown TextToSpeech engine
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    protected void onPause() {
        super.onPause();

        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}