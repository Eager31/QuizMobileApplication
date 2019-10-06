package com.example.quizmobileapplication;

public class User {

    private String name;
    private int age;
    private String address;
    private Boolean admin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public User() {
    }

    public User(String name, int age, String address, Boolean admin) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.admin = admin;
    }
}
