package com.mithilakshar.mithilapanchang;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Decemberfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Decemberfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView calendarRecycler;
    private ArrayList<calendardatamodel> dataqueue;

    private FirebaseFirestore db;

    public Decemberfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Decemberfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Decemberfragment newInstance(String param1, String param2) {
        Decemberfragment fragment = new Decemberfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_decemberfragment, container, false);

        dataqueue=new ArrayList<>();

        calendaradapter adapter=new calendaradapter(dataqueue);
        db = FirebaseFirestore.getInstance();

        db.collection("December").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list= queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d:list){
                    calendardatamodel obj=d.toObject(calendardatamodel.class);
                    dataqueue.add(obj);

                }

                adapter.notifyDataSetChanged();

            }
        });



        calendarRecycler=view.findViewById(R.id.calendarRecycler);






        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),7, LinearLayoutManager.HORIZONTAL,false);
        calendarRecycler.setLayoutManager(layoutManager);


        calendarRecycler.setAdapter(adapter);

        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenWidth=screenWidth-293;
        int itemWidth = screenWidth / 5; // Number of columns is 5
        adapter.setItemWidth(itemWidth);



        return view;

    }
}