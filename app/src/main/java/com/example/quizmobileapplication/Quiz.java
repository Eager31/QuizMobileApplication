package com.example.quizmobileapplication;

import java.util.Date;
import java.util.List;

public class Quiz {

    private int creatorID;
    private Date date;
    private String title;
    private List<Question> questionsLists;

    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestionsLists() {
        return questionsLists;
    }

    public void setQuestionsLists(List<Question> questionsLists) {
        this.questionsLists = questionsLists;
    }

    public Quiz() {
    }

    public Quiz(int creatorID, Date date, String title, List<Question> questionsLists) {
        this.creatorID = creatorID;
        this.date = date;
        this.title = title;
        this.questionsLists = questionsLists;
    }
}
