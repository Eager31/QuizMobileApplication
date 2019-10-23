package com.example.quizmobileapplication;

import java.io.Serializable;

public class Score implements Serializable {
    private int score;
    private String user;

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public Score() {

    }

    public Score(String user, int score) {
        this.user = user;
        this.score = score;
    }
}

