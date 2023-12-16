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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

                SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
                SimpleDateFormat dayFormat = new SimpleDateFormat("EEE");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd");


                String currentMonth = monthFormat.format(new Date());
                String currentDay = dayFormat.format(new Date());
                String currentDate = dateFormat.format(new Date());

                String hindiMonth = translateToHindi(currentMonth);
                String hindiDay = translateToHindiday(currentDay);
                String hindidate= translateToHindidate(currentDate);

                calendardialog calendardialog=new calendardialog(view.getContext());

                calendardialog.setcalendardialogtext(hindidate+"  "+  hindiMonth  +" " + hindiDay);
                calendardialog.setcalendardialogtext1(datalist.get(position).getDesc()+"  " +datalist.get(position).getDay());
                calendardialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                calendardialog.show();

                Toast.makeText(view.getContext(), "मिथिला पंचांग", Toast.LENGTH_LONG).show();
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

    private String translateToHindi(String currentMonth) {
        // Manually create a mapping for English to Hindi month names
        Map<String, String> monthTranslation = new HashMap<>();
        monthTranslation.put("January", "जनवरी");
        monthTranslation.put("February", "फ़रवरी");
        monthTranslation.put("March", "मार्च");
        monthTranslation.put("April", "अप्रैल");
        monthTranslation.put("May", "मई");
        monthTranslation.put("June", "जून");
        monthTranslation.put("July", "जुलाई");
        monthTranslation.put("August", "अगस्त");
        monthTranslation.put("September", "सितंबर");
        monthTranslation.put("October", "अक्टूबर");
        monthTranslation.put("November", "नवंबर");
        monthTranslation.put("December", "दिसंबर");
        // Return the translated month name
        return monthTranslation.get(currentMonth);
    }

    private String translateToHindiday(String currentDay) {
        // Manually create a mapping for English to Hindi month names
        Map<String, String> monthTranslation = new HashMap<>();
        monthTranslation.put("Mon", "सोमवार");
        monthTranslation.put("Tue", "मंगलवार");
        monthTranslation.put("Wed", "बुधवार");
        monthTranslation.put("Thu", "गुरुवार");
        monthTranslation.put("Fri", "शुक्रवार");
        monthTranslation.put("Sat", "शनिवार");
        monthTranslation.put("Sun", "रविवार");
        // Return the translated month name
        return monthTranslation.get(currentDay);
    }

    private String translateToHindidate(String date) {
        // Manually create a mapping for English to Hindi month names
        Map<String, String> nmap = new HashMap<>();

        nmap.put("0", "०");
        nmap.put("1", "१");
        nmap.put("2", "२");
        nmap.put("3", "३");
        nmap.put("4", "४");
        nmap.put("5", "५");
        nmap.put("6", "६");
        nmap.put("7", "७");
        nmap.put("8", "८");
        nmap.put("9", "९");
        nmap.put("10", "१०");
        nmap.put("11", "११");
        nmap.put("12", "१२");
        nmap.put("13", "१३");
        nmap.put("14", "१४");
        nmap.put("15", "१५");
        nmap.put("16", "१६");
        nmap.put("17", "१७");
        nmap.put("18", "१८");
        nmap.put("19", "१९");
        nmap.put("20", "२०");
        nmap.put("21", "२१");
        nmap.put("22", "२२");
        nmap.put("23", "२३");
        nmap.put("24", "२४");
        nmap.put("25", "२५");
        nmap.put("26", "२६");
        nmap.put("27", "२७");
        nmap.put("28", "२८");
        nmap.put("29", "२९");
        nmap.put("30", "३०");
        nmap.put("31", "३१");
        // Return the translated month name
        return nmap.get(date);
    }

}
