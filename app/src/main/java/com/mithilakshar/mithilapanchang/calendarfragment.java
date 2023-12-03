package com.mithilakshar.mithilapanchang;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link calendarfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class calendarfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView calendarRecycler;
    private ArrayList<calendardatamodel> dataqueue;



    String[] gridItemText = {"1", "2", " 3", " 4", " 5", " 6","7", "8", " 9", " 10", " 11", " 12",
            "13", "14", " 15", "16", "17", "18","19", "20", "21", " 22", " 23", " 24","25", "26",
            "27", " 28", " 29", " 30","31", " 32", " 33", " 34","35"};

    public calendarfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment calendarfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static calendarfragment newInstance(String param1, String param2) {
        calendarfragment fragment = new calendarfragment();
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

        View view = inflater.inflate(R.layout.fragment_calendarfragment, container, false);

        dataqueue=new ArrayList<>();

        dataqueue.add(new calendardatamodel("3"));
        dataqueue.add(new calendardatamodel("4"));
        dataqueue.add(new calendardatamodel("5"));
        dataqueue.add(new calendardatamodel(" 6"));
        dataqueue.add(new calendardatamodel("7"));
        dataqueue.add(new calendardatamodel("8"));
        dataqueue.add(new calendardatamodel("3"));
        dataqueue.add(new calendardatamodel("7"));
        dataqueue.add(new calendardatamodel("8"));
        dataqueue.add(new calendardatamodel("9"));
        dataqueue.add(new calendardatamodel("4"));
        dataqueue.add(new calendardatamodel("5"));
        dataqueue.add(new calendardatamodel(" 6"));
        dataqueue.add(new calendardatamodel("9"));
        dataqueue.add(new calendardatamodel("51"));
        dataqueue.add(new calendardatamodel(" 12"));
        dataqueue.add(new calendardatamodel("23"));
        dataqueue.add(new calendardatamodel(" 1"));
        dataqueue.add(new calendardatamodel("2"));
        dataqueue.add(new calendardatamodel("3"));
        dataqueue.add(new calendardatamodel("7"));
        dataqueue.add(new calendardatamodel("8"));
        dataqueue.add(new calendardatamodel("9"));
        dataqueue.add(new calendardatamodel("4"));
        dataqueue.add(new calendardatamodel("5"));
        dataqueue.add(new calendardatamodel(" 6"));
        dataqueue.add(new calendardatamodel("7"));
        dataqueue.add(new calendardatamodel("8"));
        dataqueue.add(new calendardatamodel("9"));
        dataqueue.add(new calendardatamodel("51"));
        dataqueue.add(new calendardatamodel("34"));
        dataqueue.add(new calendardatamodel("45"));
        dataqueue.add(new calendardatamodel("56"));
        dataqueue.add(new calendardatamodel("49"));
        dataqueue.add(new calendardatamodel("52"));

        calendarRecycler=view.findViewById(R.id.calendarRecycler);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),5);

        calendarRecycler.setLayoutManager(gridLayoutManager);

        calendaradapter adapter=new calendaradapter(dataqueue);
        calendarRecycler.setAdapter(adapter);

        return view;

    }


}