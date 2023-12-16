package com.mithilakshar.mithilapanchang;

public class mantradatamodel {

    String mantraDesc,mantraName,mantraImageurl;

    public mantradatamodel() {

    }

    public mantradatamodel(String mantraDesc, String mantraName, String mantraImageurl) {
        this.mantraDesc = mantraDesc;
        this.mantraName = mantraName;
        this.mantraImageurl = mantraImageurl;
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

    public String getMantraImageurl() {
        return mantraImageurl;
    }

    public void setMantraImageurl(String mantraImageurl) {
        this.mantraImageurl = mantraImageurl;
    }
}
