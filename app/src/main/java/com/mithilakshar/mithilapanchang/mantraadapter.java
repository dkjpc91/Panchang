package com.mithilakshar.mithilapanchang;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class mantraadapter extends RecyclerView.Adapter<mantraviewholder> {

    ArrayList<mantradatamodel> datalist;
    FragmentManager fm;

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


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "ddf", Toast.LENGTH_LONG).show();
                calendardialog calendardialog=new calendardialog(view.getContext());
                calendardialog.setcalendardialogtext(datalist.get(position).mantraName);
                calendardialog.setcalendardialogtext1(datalist.get(position).mantraDesc);
                calendardialog.setcalendardialogtext2(datalist.get(position).mantraDesc);
                calendardialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                calendardialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}
