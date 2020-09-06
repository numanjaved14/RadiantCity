package com.test.radientcity.DataModels;

public class Datamodel_announce {

    public String name;
    public String desc;
    public int imageData;

    public Datamodel_announce(String name, String desc, int imageData) {
        this.name = name;
        this.desc = desc;
        this.imageData = imageData;
    }

    public int getImageData() {
        return imageData;
    }

    public void setImageData(int imageData) {
        this.imageData = imageData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
