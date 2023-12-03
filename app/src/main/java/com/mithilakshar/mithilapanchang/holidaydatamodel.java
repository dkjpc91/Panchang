package com.mithilakshar.mithilapanchang;

public class holidaydatamodel {

    private String holidayName,holidayDesc,imageUrl;


    public holidaydatamodel() {

    }

    public holidaydatamodel(String holidayName, String holidayDesc, String imageUrl) {
        this.holidayName = holidayName;
        this.holidayDesc = holidayDesc;
        this.imageUrl = imageUrl;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public String getHolidayDesc() {
        return holidayDesc;
    }

    public void setHolidayDesc(String holidayDesc) {
        this.holidayDesc = holidayDesc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
