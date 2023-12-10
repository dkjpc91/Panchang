package com.mithilakshar.mithilapanchang;

public class mantradatamodel {

    String mantraDesc,mantraName,mantraImageurl;

    public mantradatamodel() {

    }
    public mantradatamodel(String mantraDesc, String mantraName, String mantraImage) {
        this.mantraDesc = mantraDesc;
        this.mantraName = mantraName;
        this.mantraImageurl = mantraImage;
    }

    public String getMantraDesc() {
        return mantraDesc;
    }

    public void setMantraDesc(String mantraDesc) {
        this.mantraDesc = mantraDesc;
    }

    public String getMantraName() {
        return mantraName;
    }

    public void setMantraName(String mantraName) {
        this.mantraName = mantraName;
    }

    public String getMantraImage() {
        return mantraImageurl;
    }

    public void setMantraImage(String mantraImage) {
        this.mantraImageurl = mantraImage;
    }
}
