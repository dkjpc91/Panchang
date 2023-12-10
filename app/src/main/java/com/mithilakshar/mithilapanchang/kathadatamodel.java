package com.mithilakshar.mithilapanchang;

public class kathadatamodel {

    String kathaName,kathaStory,kathaUrl;
    public kathadatamodel() {

    }
    public kathadatamodel(String kathaName, String kathaStory, String kathaUrl) {
        this.kathaName = kathaName;
        this.kathaStory = kathaStory;
        this.kathaUrl = kathaUrl;
    }

    public String getKathaName() {
        return kathaName;
    }

    public void setKathaName(String kathaName) {
        this.kathaName = kathaName;
    }

    public String getKathaStory() {
        return kathaStory;
    }

    public void setKathaStory(String kathaStory) {
        this.kathaStory = kathaStory;
    }

    public String getKathaUrl() {
        return kathaUrl;
    }

    public void setKathaUrl(String kathaUrl) {
        this.kathaUrl = kathaUrl;
    }
}
