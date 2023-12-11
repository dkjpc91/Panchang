package com.mithilakshar.mithilapanchang;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class kathaapapter extends RecyclerView.Adapter<kathaviewholder> {

    ArrayList<kathadatamodel> datalist;

    public kathaapapter(ArrayList<kathadatamodel> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public kathaviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.kathaitem,parent,false);
        return new kathaviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull kathaviewholder holder, int position) {

        holder.kathaName.setText(datalist.get(position).kathaName);
        holder.kathaStory.setText(datalist.get(position).kathaStory);
        Picasso.get().load(datalist.get(position).kathaUrl).into(holder.kathaImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(), kathadesc.class);
                i.putExtra("kathaName", datalist.get(position).kathaName);
                i.putExtra("kathaStory", datalist.get(position).kathaStory);
                i.putExtra("kathaUrl", datalist.get(position).kathaUrl);
                view.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}
