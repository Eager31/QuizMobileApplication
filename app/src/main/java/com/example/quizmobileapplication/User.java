package com.example.quizmobileapplication;

import java.util.HashMap;

public class User {

    private String email;
    private Boolean admin;
    private HashMap<Integer, Integer> marks;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public HashMap<Integer, Integer> getmarks() {
        return this.marks;
    }

    public void setMarks(HashMap<Integer, Integer> marks) {
        this.marks = marks;
    }

    public User() {
    }

    public User(String email, Boolean admin, HashMap<Integer, Integer> marks) {
        this.email = email;
        this.admin = admin;
        this.marks = marks;
    }
}
