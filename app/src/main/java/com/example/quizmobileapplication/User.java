package com.example.quizmobileapplication;


import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {


    private String email;
    private Boolean admin;
    private HashMap<Integer, Integer> marks;
    private String id;
    private String key;

    public void setId(String id) { this.id = id; }
    public String getId() { return this.id; }

    public void setKey(String key) { this.key = key; }
    public String getKey() { return this.key; }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
    public Boolean getAdmin() { return admin; }

    public void setMarks(HashMap<Integer, Integer> marks) { this.marks = marks; }
    public HashMap<Integer, Integer> getmarks() {
        return this.marks;
    }

    public User() { }

    public User(String email, Boolean admin, HashMap<Integer, Integer> marks, String id, String key) {
        this.email = email;
        this.admin = admin;
        this.marks = marks;
        this.id = id;
        this.key = key;
    }
}
