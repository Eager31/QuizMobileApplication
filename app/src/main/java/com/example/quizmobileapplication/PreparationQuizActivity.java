package com.example.quizmobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class PreparationQuizActivity extends AppCompatActivity {

    private TextView mTitleQuiz_textView;
    private Spinner mNumber_spinner;
    private Button mStart_btn;
    private Button mBack_btn;
    private Button mDelete_btn;
    private Button mModify_btn;

    private String key;
    private Quiz quiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparation_quiz);
        Intent intent = getIntent();

        key = intent.getStringExtra("key"); //ID from dataBase
        quiz = (Quiz) intent.getSerializableExtra("quiz"); //Get the data from the Quiz

        Log.i("KEY", key);

        //Matching
        mTitleQuiz_textView = findViewById(R.id.titleQuiz_textView);
        mTitleQuiz_textView.setText(quiz.getTitle());
        mNumber_spinner = findViewById(R.id.number_spinner);
        mStart_btn = findViewById(R.id.start_btn);
        mBack_btn = findViewById(R.id.back_btn);
        mStart_btn = findViewById(R.id.start_btn);
        mModify_btn = findViewById(R.id.modify_btn);

        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                return;
            }
        });

    }
}
