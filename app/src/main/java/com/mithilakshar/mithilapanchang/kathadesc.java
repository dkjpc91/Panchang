package com.mithilakshar.mithilapanchang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeechService;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.Locale;


public class kathadesc extends AppCompatActivity implements TextToSpeech.OnInitListener {

     TextView kathaDesc,kathaTitle;
    ImageView kathaImg;
    private TextToSpeech textToSpeech;

    String kathaD;
    private Handler handler = new Handler();
    private boolean isFabClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kathadesc);

        kathaTitle=findViewById(R.id.kathatitle);
        kathaDesc=findViewById(R.id.kathaDesc);
        kathaImg=findViewById(R.id.kathaImg);
        FloatingActionButton fab = findViewById(R.id.fab);

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
        switchFabColor(fab);





        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isFabClicked = !isFabClicked;

                if (isFabClicked) {
                    fab.setImageResource(R.drawable.speaker);
                    switchFabColor(fab);



                    delayedTask(500);;
                } else {
                    fab.setImageResource(R.drawable.mutespeaker);
                    switchFabColor(fab);

                    textToSpeech.stop();
                }





            }
        });










    }

    private void switchFabColor(FloatingActionButton fab) {
        if (isFabClicked) {
            // Set the original color if it's switched
            fab.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.fabColorOriginal));
        } else {
            // Set the switched color
            fab.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.fabColorSwitched));
        }

    }


    @Override
    public void onInit(int i) {

        if (i == TextToSpeech.SUCCESS) {




                        // Set language
            textToSpeech.setLanguage(Locale.forLanguageTag("hi"));

            // Speak text

            textToSpeech.setPitch(1f);
            textToSpeech.setSpeechRate(0.6f);

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

    private void delayedTask(int delayMillis) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                // Your code to be executed after the delay
                textToSpeech.setLanguage(Locale.forLanguageTag("hi"));

                // Speak text
                textToSpeech.setPitch(1f);
                textToSpeech.setSpeechRate(0.6f);
                textToSpeech.speak(kathaD, TextToSpeech.QUEUE_FLUSH, null, null);

            }
        }, delayMillis);
    }
}