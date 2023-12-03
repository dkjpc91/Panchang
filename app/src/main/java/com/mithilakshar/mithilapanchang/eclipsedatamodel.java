package com.mithilakshar.mithilapanchang;

public class eclipsedatamodel {

    String eclipseName,eclipseDesc,imageUrl;

    public eclipsedatamodel() {

    }

    public eclipsedatamodel(String eclipseName, String eclipseDesc, String imageUrl) {
        this.eclipseName = eclipseName;
        this.eclipseDesc = eclipseDesc;
        this.imageUrl = imageUrl;
    }

    public String getEclipseName() {
        return eclipseName;
    }

    public void setEclipseName(String eclipseName) {
        this.eclipseName = eclipseName;
    }

    public String getEclipseDesc() {
        return eclipseDesc;
    }

    public void setEclipseDesc(String eclipseDesc) {
        this.eclipseDesc = eclipseDesc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
