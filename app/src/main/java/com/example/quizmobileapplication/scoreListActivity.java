package com.example.quizmobileapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class scoreListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_score);
        new FireBaseDatabaseHelper().readResults(new FireBaseDatabaseHelper.DataStatus() {
            @Override
            public void QuizIsLoaded(List<Quiz> quizzes, List<String> keys) {

            }

            @Override
            public void ResultIsLoaded(List<Result> results, List<String> keys) {
                new recyclerViewScore_Config().setConfig(recyclerView, scoreListActivity.this, results, keys);
            }

            @Override
            public void UsersIsLoaded(List<User> users, List<String> keys) {

            }

            @Override
            public void DataInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }


}
