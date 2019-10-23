package com.example.quizmobileapplication;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Quiz implements Serializable {

    private String creatorID;
    private String date;
    private String title;
    private List<Question> questionsLists;
    private List<Score> scoreList;

    public List<Score> getScoresList() {
        return scoreList;
    }

    public void setScoresList(List<Score> scoresList) {
        this.scoreList = scoresList;
    }

    public String getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(String creatorID) {
        this.creatorID = creatorID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public Quiz(String creatorID, String date, String title, List<Question> questionsLists) {
        this.creatorID = creatorID;
        this.date = date;
        this.title = title;
        this.questionsLists = questionsLists;
    }


}
