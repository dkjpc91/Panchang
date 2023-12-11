package com.mithilakshar.mithilapanchang;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class calendaradapter extends RecyclerView.Adapter<calendarviewholder> {

    ArrayList<calendardatamodel> datalist;

    private int itemWidth;


    public calendaradapter(ArrayList<calendardatamodel> datalist) {
        this.datalist = datalist;


    }


    @NonNull
    @Override
    public calendarviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.calendardayitem,parent,false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = itemWidth;
        view.setLayoutParams(layoutParams);
        return new calendarviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull calendarviewholder holder, int position) {

        if(datalist.get(position).getDate().isEmpty()){

            holder.itemView.setVisibility(View.INVISIBLE);

        }else {

            holder.calendardescText.setText(datalist.get(position).getDesc());
            holder.calendardateText.setText(datalist.get(position).getDate());
            holder.calendardayText.setText(datalist.get(position).getDay());

        }





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendardialog calendardialog=new calendardialog(view.getContext());
                calendardialog.setcalendardialogtext(datalist.get(position).getDate());
                calendardialog.setcalendardialogtext1(datalist.get(position).getDay());
                calendardialog.setcalendardialogtext2(datalist.get(position).getDesc());
                calendardialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                calendardialog.show();

                Toast.makeText(view.getContext(), "ddf", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void setItemWidth(int itemWidth) {
        this.itemWidth = itemWidth;
        notifyDataSetChanged(); // Refresh the adapter to apply the new item width
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}
