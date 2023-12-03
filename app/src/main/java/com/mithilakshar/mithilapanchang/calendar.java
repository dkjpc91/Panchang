package com.mithilakshar.mithilapanchang;

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
import java.util.List;
import java.util.Map;

public class calendar extends AppCompatActivity {

    ImageSlider calendarimageSlider;
    TextView monthName;

    ArrayList<SlideModel> calurl;
    FirebaseFirestore db;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarimageSlider=findViewById(R.id.calendarimageSlider);
        monthName=findViewById(R.id.monthName);

        Intent intent = getIntent();
        String hindiMonth = intent.getStringExtra("month");
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



        // Create a FragmentManager to manage fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Begin a new FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
        calendarfragment cf=new calendarfragment();
        fragmentTransaction.replace(R.id.fragmentContainer,cf);

        // Commit the transaction
        fragmentTransaction.commit();




    }




}