package com.mithilakshar.mithilapanchang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class calendaradapter extends RecyclerView.Adapter<calendarviewholder> {

    ArrayList<calendardatamodel> datalist;

    public calendaradapter(ArrayList<calendardatamodel> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public calendarviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.calendardayitem,parent,false);
        return new calendarviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull calendarviewholder holder, int position) {

        holder.calendarItemText.setText(datalist.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}
