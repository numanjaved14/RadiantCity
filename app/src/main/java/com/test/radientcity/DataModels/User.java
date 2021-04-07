package com.test.radientcity.DataModels;

public class User {

    String firebaseId;
    String userName;
    String userMail;
    String address;
    String password;
    String type;

    public User() {
    }

    public User(String firebaseId, String userName, String userMail, String address, String password, String type) {
        this.firebaseId = firebaseId;
        this.userName = userName;
        this.userMail = userMail;
        this.address = address;
        this.password = password;
        this.type = type;
    }

    public User(String firebaseId, String userName) {
        this.firebaseId = firebaseId;
        this.userName = userName;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
