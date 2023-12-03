package com.mithilakshar.mithilapanchang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class holiday extends AppCompatActivity {

    RecyclerView holidayRecyclerView;
    ArrayList<holidaydatamodel> dataList;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);

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


    }
}