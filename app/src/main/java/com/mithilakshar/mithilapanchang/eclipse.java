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

public class eclipse extends AppCompatActivity {

    RecyclerView eclipseRecyclerView;

    ArrayList<eclipsedatamodel> data;

    ArrayList<SlideModel> eclurl;
    ImageSlider eclipseimageSlider;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eclipse);
        eclipseimageSlider=findViewById(R.id.eclipseimageSlider);

        eclipseRecyclerView=findViewById(R.id.eclipseRecyclerView);
        eclipseRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        data=new ArrayList<>();
        eclurl=new ArrayList<>();

        eclipseadapter eclipseadapter=new eclipseadapter(data);
        eclipseRecyclerView.setAdapter(eclipseadapter);

        db=FirebaseFirestore.getInstance();
        db.collection("eclipse").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d:list){

                    eclipsedatamodel obj=d.toObject(eclipsedatamodel.class);
                    data.add(obj);

                }

                eclipseadapter.notifyDataSetChanged();


            }
        });

        db.collection("banner").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot queryDocumentSnapshot: task.getResult()){
                        eclurl.add(new SlideModel(queryDocumentSnapshot.getString("url"), ScaleTypes.CENTER_INSIDE));
                        eclipseimageSlider.setImageList(eclurl,ScaleTypes.CENTER_INSIDE);
                    }
                }
            }
        });




    }
}