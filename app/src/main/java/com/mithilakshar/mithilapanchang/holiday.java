package com.mithilakshar.mithilapanchang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class holiday extends AppCompatActivity {

    RecyclerView holidayRecyclerView;
    ArrayList<holidaydatamodel> dataList;
    FirebaseFirestore db;

    ImageSlider holidayimageSlider;
    ArrayList<SlideModel> urllist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);

        holidayimageSlider=findViewById(R.id.holidayimageSlider);
        urllist=new ArrayList<>();

        holidayRecyclerView=findViewById(R.id.holidayRecyclerView);
        holidayRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        dataList=new ArrayList<>();
        holidayadapter adapter=new holidayadapter(dataList);
        holidayRecyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        db.collection("holiday").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list= queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d:list){

                    holidaydatamodel obj=d.toObject(holidaydatamodel.class);
                    dataList.add(obj);
                }
                adapter.notifyDataSetChanged();
            }
        });

        db=FirebaseFirestore.getInstance();

        db.collection("banner").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot queryDocumentSnapshot: task.getResult()){
                        urllist.add(new SlideModel(queryDocumentSnapshot.getString("url"), ScaleTypes.CENTER_INSIDE));
                        holidayimageSlider.setImageList(urllist,ScaleTypes.FIT);
                    }
                }
            }
        });


    }
}