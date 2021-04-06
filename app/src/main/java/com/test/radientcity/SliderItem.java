package com.test.radientcity;

public class SliderItem {

    private int image;

    private String imageURL;

    SliderItem(int image,String imageURL){
        this.image = image;
        this.imageURL=imageURL;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getImage(){
        return image;
    }
}
