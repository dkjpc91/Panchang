package com.mithilakshar.mithilapanchang;

import com.google.firebase.firestore.FirebaseFirestore;

public class calendardatamodel {

    private String date,day,desc;

    int itemWidth;



    public calendardatamodel() {

    }

    public calendardatamodel(String date, String day, String desc, int itemWidth) {
        this.date = date;
        this.day = day;
        this.desc = desc;
        this.itemWidth = itemWidth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getItemWidth() {
        return itemWidth;
    }

    public void setItemWidth(int itemWidth) {
        this.itemWidth = itemWidth;
    }
}
