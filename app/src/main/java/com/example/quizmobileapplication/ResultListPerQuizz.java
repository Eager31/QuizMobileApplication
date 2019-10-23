package com.example.quizmobileapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ResultListPerQuizz extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_per_quizze);
        recyclerView = (RecyclerView) findViewById(R.id.resultperquize);
        Intent intent = getIntent();
        result = (Result) intent.getSerializableExtra("result");
        new recyclerViewScorePerQuizze_Config().setConfig(recyclerView, ResultListPerQuizz.this, result.getScoreList());
    }
}
