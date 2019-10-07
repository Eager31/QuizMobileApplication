package com.example.quizmobileapplication;

import java.util.Date;

public class Result {

    private String IDQuiz;
    private String IDUser;
    private String date;
    private Long score;

    public String getIDQuiz() {
        return IDQuiz;
    }

    public void setIDQuiz(String IDQuiz) {
        this.IDQuiz = IDQuiz;
    }

    public String getIDUser() {
        return IDUser;
    }

    public void setIDUser(String IDUser) {
        this.IDUser = IDUser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Result() {
    }

    public Result(String IDQuiz, String IDUser, String date, Long score) {
        this.IDQuiz = IDQuiz;
        this.IDUser = IDUser;
        this.date = date;
        this.score = score;
    }
}
