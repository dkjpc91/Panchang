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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class katha extends AppCompatActivity {

    ImageSlider kathaimageSlider;
    ArrayList<SlideModel> urllist;
    FirebaseFirestore db;

    RecyclerView kathaRecyclerView;
    ArrayList<kathadatamodel> dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katha);

        kathaimageSlider=findViewById(R.id.kathaimageSlider);
        kathaRecyclerView=findViewById(R.id.kathaRecyclerView);
        urllist=new ArrayList<>();
        dataList=new ArrayList<>();

        kathaRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        kathaapapter adapter=new kathaapapter(dataList);
        kathaRecyclerView.setAdapter(adapter);



        db=FirebaseFirestore.getInstance();

        db.collection("katha").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list= queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d:list){

                    kathadatamodel obj=d.toObject(kathadatamodel.class);
                    dataList.add(obj);
                }
                adapter.notifyDataSetChanged();
            }
        });

        db.collection("banner").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot queryDocumentSnapshot: task.getResult()){
                        urllist.add(new SlideModel(queryDocumentSnapshot.getString("url"), ScaleTypes.CENTER_INSIDE));
                        kathaimageSlider.setImageList(urllist,ScaleTypes.CENTER_INSIDE);
                    }
                }
            }
        });

    }
}