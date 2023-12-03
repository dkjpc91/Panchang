package com.mithilakshar.mithilapanchang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class home extends AppCompatActivity {

    ImageSlider imageSlider;
    CardView calendar,holiday,eclipse,mantra,katha;
    TextView textViewMonth,textViewDate,textViewDay;

    FirebaseFirestore db;




    ArrayList<SlideModel> urllist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageSlider=findViewById(R.id.imageSlider);
        calendar=findViewById(R.id.calendar);
        holiday=findViewById(R.id.holiday);
        eclipse=findViewById(R.id.eclipse);
        katha=findViewById(R.id.katha);
        mantra=findViewById(R.id.mantra);
        textViewMonth=findViewById(R.id.textViewMonth);
        textViewDate=findViewById(R.id.textViewDate);
        textViewDay=findViewById(R.id.textViewDay);

        urllist=new ArrayList<>();



        db=FirebaseFirestore.getInstance();

        db.collection("banner").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot queryDocumentSnapshot: task.getResult()){
                        urllist.add(new SlideModel(queryDocumentSnapshot.getString("url"), ScaleTypes.CENTER_INSIDE));
                        imageSlider.setImageList(urllist,ScaleTypes.CENTER_INSIDE);
                    }
                }
            }
        });










        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEE");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");

        String currentMonth = monthFormat.format(new Date());
        String currentDay = dayFormat.format(new Date());
        String currentDate = dateFormat.format(new Date());


        String hindiMonth = translateToHindi(currentMonth);
        String  hindiDay = translateToHindiday(currentDay);

        textViewMonth.setText(hindiMonth);
        textViewDate.setText(currentDate);
        textViewDay.setText(hindiDay);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),calendar.class);
                i.putExtra("month", hindiMonth);
                startActivity(i);
            }
        });

        holiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),holiday.class);
                i.putExtra("month", hindiMonth);
                startActivity(i);
            }
        });

        eclipse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),eclipse.class);
                i.putExtra("month", hindiMonth);
                startActivity(i);

            }
        });
        mantra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),mantra.class);
                i.putExtra("month", hindiMonth);
                startActivity(i);

            }
        });
        katha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),katha.class);
                i.putExtra("month", hindiMonth);
                startActivity(i);

            }
        });

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


   /* private void startImageSlide() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                index=(index+1)% urllist.length;
                ImageView iv= (ImageView) imageSwitcher.getCurrentView();
                Picasso.get().load(urllist[index]).into(iv);
                startImageSlide();
            }
        }, 2500);
    }*/
}