package com.test.radientcity.DataModels;

public class Datamodel_service_show {

    public String title;
    public String date;
    public String time;
    public String acception;

    public Datamodel_service_show(String title, String date, String time, String acception) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.acception = acception;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAcception() {
        return acception;
    }

    public void setAcception(String acception) {
        this.acception = acception;
    }

}
