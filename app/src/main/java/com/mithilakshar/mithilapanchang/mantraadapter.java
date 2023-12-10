package com.mithilakshar.mithilapanchang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class mantraadapter extends RecyclerView.Adapter<mantraviewholder> {

    ArrayList<mantradatamodel> datalist;

    public mantraadapter(ArrayList<mantradatamodel> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public mantraviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.mantraitem,parent,false);
        return new mantraviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mantraviewholder holder, int position) {

        holder.mantraName.setText(datalist.get(position).mantraName);
        holder.mantraDesc.setText(datalist.get(position).mantraName);
        Picasso.get().load(datalist.get(position).getMantraImage()).into(holder.mantraImage);

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}
