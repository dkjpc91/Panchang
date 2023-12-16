package com.mithilakshar.mithilapanchang;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class calendar extends AppCompatActivity {

    ImageSlider calendarimageSlider;
    TextView monthName;
    ImageView backfragment,forwardfragment;

    ArrayList<SlideModel> calurl;
    FirebaseFirestore db;

    String[] fragmentindex={"January","February","March","April","May","June","July","August","September","October","November","December"};
    int fragmentindexnumber;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarimageSlider=findViewById(R.id.calendarimageSlider);
        monthName=findViewById(R.id.monthName);
        forwardfragment=findViewById(R.id.forwardfragment);
        backfragment=findViewById(R.id.backfragment);

        Intent intent = getIntent();
        String currentMonth = intent.getStringExtra("currentMonth");
        String currentDate = intent.getStringExtra("currentDate");
        String hindiMonth = translateToHindi(currentMonth);

        monthName.setText(hindiMonth);

        calurl=new ArrayList<>();

        db=FirebaseFirestore.getInstance();
        db.collection("banner").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot queryDocumentSnapshot: task.getResult()){
                        calurl.add(new SlideModel(queryDocumentSnapshot.getString("url"), ScaleTypes.CENTER_INSIDE));
                        calendarimageSlider.setImageList(calurl,ScaleTypes.CENTER_INSIDE);
                    }
                }
            }
        });


        loadfragment(currentMonth);


        for (int i = 0; i < fragmentindex.length; i++) {
            if (fragmentindex[i].indexOf(currentMonth) != -1) {
                fragmentindexnumber=i;
                break;
            }
        }

        backfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(fragmentindexnumber==0){
                    fragmentindexnumber=fragmentindex.length-1;
                    loadfragment(fragmentindex[fragmentindexnumber]);
                    String hindiMonth = translateToHindi(fragmentindex[fragmentindexnumber]);
                    monthName.setText(hindiMonth);

                }
                else {
                    fragmentindexnumber=fragmentindexnumber-1;
                    loadfragment(fragmentindex[fragmentindexnumber]);
                    String hindiMonth = translateToHindi(fragmentindex[fragmentindexnumber]);
                    monthName.setText(hindiMonth);

                }


            }
        });

        forwardfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentindexnumber=(fragmentindexnumber+1)%fragmentindex.length;
                loadfragment(fragmentindex[fragmentindexnumber]);
                String hindiMonth = translateToHindi(fragmentindex[fragmentindexnumber]);
                monthName.setText(hindiMonth);

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


    private void loadfragment(String currentMonth) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        // Begin a new FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();



        switch (currentMonth){

            case "December":
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                Decemberfragment df=new Decemberfragment();
                fragmentTransaction.replace(R.id.fragmentContainer,df);
                fragmentTransaction.commit();
                break;
            case "January":
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
               Januaryfragment jf=new Januaryfragment();
                fragmentTransaction.replace(R.id.fragmentContainer,jf);
                fragmentTransaction.commit();
                break;

            case "February":
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                februaryfragment ff=new februaryfragment();
                fragmentTransaction.replace(R.id.fragmentContainer,ff);
                fragmentTransaction.commit();
                break;
            case "March":
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                marchfragment mf=new marchfragment();
                fragmentTransaction.replace(R.id.fragmentContainer,mf);
                fragmentTransaction.commit();
                break;
            case "April":
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                aprilfragment af=new aprilfragment();
                fragmentTransaction.replace(R.id.fragmentContainer,af);
                fragmentTransaction.commit();
                break;
            case "May":
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                mayfragment myf=new mayfragment();
                fragmentTransaction.replace(R.id.fragmentContainer,myf);
                fragmentTransaction.commit();
                break;


            case "June":
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                junefragment jef=new junefragment();
                fragmentTransaction.replace(R.id.fragmentContainer,jef);
                fragmentTransaction.commit();
                break;
            case "July":
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                julyfragment jyf=new julyfragment();
                fragmentTransaction.replace(R.id.fragmentContainer,jyf);
                fragmentTransaction.commit();
                break;

            case "August":
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                augustfragment atf=new augustfragment();
                fragmentTransaction.replace(R.id.fragmentContainer,atf);
                fragmentTransaction.commit();
                break;
            case "September":
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                septemberfragment sf=new septemberfragment();
                fragmentTransaction.replace(R.id.fragmentContainer,sf);
                fragmentTransaction.commit();
                break;
            case "October":
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                octoberfragment of=new octoberfragment();
                fragmentTransaction.replace(R.id.fragmentContainer,of);
                fragmentTransaction.commit();
                break;
            case "November":
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                novemberfragment nf=new novemberfragment();
                fragmentTransaction.replace(R.id.fragmentContainer,nf);
                fragmentTransaction.commit();
                break;




        }

    }


}