package com.test.radientcity.DataModels;

public class Dummy {
    private String name,email;

    public Dummy(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Dummy() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
