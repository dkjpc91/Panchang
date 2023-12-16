package com.mithilakshar.mithilapanchang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;

import android.view.View;

import android.widget.TextView;


import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.Locale;
import java.util.Map;


public class home extends AppCompatActivity implements TextToSpeech.OnInitListener {


    CardView calendar, holiday, eclipse, mantra, katha;
    TextView textViewMonth, textViewDate, textViewDay, homedesc,homedesc2;

    FirebaseFirestore db;
    FirebaseMessaging firebaseMessaging;


    ImageSlider imageSlider;
    ArrayList<SlideModel> urllist;
    private TextToSpeech textToSpeech;

    String speak;
    int counter=0;

    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageSlider = findViewById(R.id.imageSlider);
        calendar = findViewById(R.id.calendar);
        holiday = findViewById(R.id.holiday);
        eclipse = findViewById(R.id.eclipse);
        katha = findViewById(R.id.katha);
        mantra = findViewById(R.id.mantra);
        textViewMonth = findViewById(R.id.textViewMonth);
        textViewDate = findViewById(R.id.textViewDate);
        textViewDay = findViewById(R.id.textViewDay);
        homedesc = findViewById(R.id.homedesc);
        homedesc2 = findViewById(R.id.homedesc2);

        urllist = new ArrayList<>();


        db = FirebaseFirestore.getInstance();

        db.collection("banner").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                        urllist.add(new SlideModel(queryDocumentSnapshot.getString("url"), ScaleTypes.CENTER_INSIDE));
                        imageSlider.setImageList(urllist, ScaleTypes.CENTER_INSIDE);
                    }
                }
            }
        });


        //firebase message

        firebaseMessaging.getInstance().subscribeToTopic("notification").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String msg = "Subscribed";
                if (!task.isSuccessful()) {
                    msg = "Subscribe failed";}
            }
        });



        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEE");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");


        String currentMonth = monthFormat.format(new Date());
        String currentDay = dayFormat.format(new Date());
        String currentDate = dateFormat.format(new Date());

        String hindiMonth = translateToHindi(currentMonth);
        String hindiDay = translateToHindiday(currentDay);
        String hindidate= translateToHindidate(currentDate);

        textViewMonth.setText(hindiMonth);
        textViewDate.setText(hindidate);
        textViewDay.setText(hindiDay);



        CollectionReference collectionRef = db.collection(currentMonth);
        Query query = collectionRef.whereEqualTo("date", currentDate);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot doc : task.getResult()) {
                        String date = doc.getString("date");
                        String day = doc.getString("day");
                        String desc = doc.getString("desc");
                        String daydesc = doc.getString("speak");

                        homedesc.setText(hindidate+" "+hindiMonth+ " २०२४, " + day);
                        homedesc2.setText(desc);

                        speak=daydesc;

                    }
                }

            }
        });







        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), calendar.class);
                i.putExtra("currentMonth", currentMonth);
                i.putExtra("currentDate", currentDate);
                startActivity(i);
                counter++;
            }
        });

        holiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), holiday.class);
                i.putExtra("month", hindiMonth);
                startActivity(i);
                counter++;
            }
        });

        eclipse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), eclipse.class);
                i.putExtra("month", hindiMonth);
                startActivity(i);
                counter++;

            }
        });
        mantra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), mantra.class);
                i.putExtra("month", hindiMonth);
                startActivity(i);
                counter++;

            }
        });
        katha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), katha.class);
                i.putExtra("month", hindiMonth);
                startActivity(i);
                counter++;

            }
        });

        textToSpeech = new TextToSpeech(this, this);


    }


    private String translateToHindi(String currentMonth) {
        // Manually create a mapping for English to Hindi month names
        Map<String, String> monthTranslation = new HashMap<>();
        monthTranslation.put("January", "जनवरी");
        monthTranslation.put("February", "फ़रवरी");
        monthTranslation.put("March", "मार्च");
        monthTranslation.put("April", "अप्रैल");
        monthTranslation.put("May", "मई");
        monthTranslation.put("June", "जून");
        monthTranslation.put("July", "जुलाई");
        monthTranslation.put("August", "अगस्त");
        monthTranslation.put("September", "सितंबर");
        monthTranslation.put("October", "अक्टूबर");
        monthTranslation.put("November", "नवंबर");
        monthTranslation.put("December", "दिसंबर");
        // Return the translated month name
        return monthTranslation.get(currentMonth);
    }

    private String translateToHindiday(String currentDay) {
        // Manually create a mapping for English to Hindi month names
        Map<String, String> monthTranslation = new HashMap<>();
        monthTranslation.put("Mon", "सोमवार");
        monthTranslation.put("Tue", "मंगलवार");
        monthTranslation.put("Wed", "बुधवार");
        monthTranslation.put("Thu", "गुरुवार");
        monthTranslation.put("Fri", "शुक्रवार");
        monthTranslation.put("Sat", "शनिवार");
        monthTranslation.put("Sun", "रविवार");
        // Return the translated month name
        return monthTranslation.get(currentDay);
    }

    private String translateToHindidate(String date) {
        // Manually create a mapping for English to Hindi month names
        Map<String, String> nmap = new HashMap<>();

        nmap.put("0", "०");
        nmap.put("1", "१");
        nmap.put("2", "२");
        nmap.put("3", "३");
        nmap.put("4", "४");
        nmap.put("5", "५");
        nmap.put("6", "६");
        nmap.put("7", "७");
        nmap.put("8", "८");
        nmap.put("9", "९");
        nmap.put("10", "१०");
        nmap.put("11", "११");
        nmap.put("12", "१२");
        nmap.put("13", "१३");
        nmap.put("14", "१४");
        nmap.put("15", "१५");
        nmap.put("16", "१६");
        nmap.put("17", "१७");
        nmap.put("18", "१८");
        nmap.put("19", "१९");
        nmap.put("20", "२०");
        nmap.put("21", "२१");
        nmap.put("22", "२२");
        nmap.put("23", "२३");
        nmap.put("24", "२४");
        nmap.put("25", "२५");
        nmap.put("26", "२६");
        nmap.put("27", "२७");
        nmap.put("28", "२८");
        nmap.put("29", "२९");
        nmap.put("30", "३०");
        nmap.put("31", "३१");
        // Return the translated month name
        return nmap.get(date);
    }





    public void onInit(int i) {

        if (i == TextToSpeech.SUCCESS) {


            // Set language
            delayedTask(0000);



            // Speak text

        } else {
            // Handle error

        }

    }


    protected void onDestroy() {
        super.onDestroy();
        // Shutdown TextToSpeech engine

            textToSpeech.stop();
            textToSpeech.shutdown();

    }


    @Override
    protected void onPause() {
        super.onPause();


            textToSpeech.stop();
            textToSpeech.shutdown();

    }

    @Override
    protected void onResume() {
        super.onResume();


        delayedTask(1000);

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
                textToSpeech.speak(speak, TextToSpeech.QUEUE_FLUSH, null, null);

            }
        }, delayMillis);
    }


}
