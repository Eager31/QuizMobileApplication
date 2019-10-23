package com.example.quizmobileapplication;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable {

    private String QuizzKey;
    private String QuizzName;
    private List<Score> ScoreList;
    private String key;

    public String getQuizzKey() {
        return QuizzKey;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() { return this.key; }

    public String getQuizzName() { return QuizzName; }

    public List<Score> getScoreList() {
        return ScoreList;
    }

    public void setQuizzKey(String quizzKey) {
        QuizzKey = quizzKey;
    }

    public void setQuizzName(String quizzName) {
        QuizzName = quizzName;
    }

    public void setScoreList(List<Score> scoreList) {
        ScoreList = scoreList;
    }

    public Result() {

    }

    public Result(String QuizzKey, String QuizzName, List<Score> ScoreList) {
        this.QuizzKey = QuizzKey;
        this.QuizzName = QuizzName;
        this.ScoreList = ScoreList;
    }
}
