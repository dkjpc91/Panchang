package com.mithilakshar.mithilapanchang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class eclipseadapter extends RecyclerView.Adapter<eclipseviewholder> {

    ArrayList<eclipsedatamodel> datalist;

    public eclipseadapter(ArrayList<eclipsedatamodel> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public eclipseviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.eclipseitem,parent,false);
        return new eclipseviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull eclipseviewholder holder, int position) {

        holder.eclipseName.setText(datalist.get(position).eclipseName);
        holder.eclipseDesc.setText(datalist.get(position).eclipseDesc);
        Picasso.get().load(datalist.get(position).imageUrl).into(holder.eclipseImage);

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}
