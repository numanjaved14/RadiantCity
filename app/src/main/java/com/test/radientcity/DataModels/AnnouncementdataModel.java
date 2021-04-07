package com.test.radientcity.DataModels;

public class AnnouncementdataModel {
    String date;
    String description;
    String firebaseId;
    String status;
    String title;

    public AnnouncementdataModel() {
    }

    public AnnouncementdataModel(String date, String description, String status, String title) {
        this.date = date;
        this.description = description;
        this.status = status;
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

