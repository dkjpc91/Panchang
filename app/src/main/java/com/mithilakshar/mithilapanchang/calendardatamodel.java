package com.mithilakshar.mithilapanchang;

import com.google.firebase.firestore.FirebaseFirestore;

public class calendardatamodel {

    private String date,day,desc;



    public calendardatamodel() {

    }

    public calendardatamodel(String date, String day, String desc) {
        this.date = date;
        this.day = day;
        this.desc = desc;
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
}
