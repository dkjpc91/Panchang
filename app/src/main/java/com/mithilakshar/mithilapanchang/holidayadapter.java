package com.mithilakshar.mithilapanchang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class holidayadapter extends RecyclerView.Adapter<holidayviewholder> {

    ArrayList<holidaydatamodel> datalist;

    public holidayadapter(ArrayList<holidaydatamodel> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public holidayviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.holidayitem,parent,false);
        return new holidayviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holidayviewholder holder, int position) {

        holder.holidayName.setText(datalist.get(position).getHolidayName());
        holder.holidayDesc.setText(datalist.get(position).getHolidayDesc());
        Picasso.get().load(datalist.get(position).getImageUrl()).into(holder.holidayImage);


    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}
