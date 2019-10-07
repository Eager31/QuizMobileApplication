package com.example.quizmobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateQuizActivity extends AppCompatActivity {

    private EditText mTitle_editTxt;
    private EditText mnbQuestions_editText;
    private Quiz quiz;
    private String key;
    private Button nextBtn;
    private Button cancelBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__quiz);

        Intent intent = getIntent();
        //Get the previous data
        //Get the actual state from the Quiz
        quiz = (Quiz) intent.getSerializableExtra("quiz");
        key = intent.getStringExtra("key");

        mTitle_editTxt = findViewById(R.id.title_editTxt);
        mnbQuestions_editText = findViewById(R.id.nombQuest_editText);

        mTitle_editTxt.setText(quiz.getTitle());
        String string = Integer.toString(quiz.getQuestionsLists().size()); //Get the amount of questions
        mnbQuestions_editText.setText(string);
        mnbQuestions_editText.setFocusable(false); //For the moment, disable the possibility to add new questions

        nextBtn = findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Transfer data to second screen
                nextPage();
            }
        });
        cancelBtn = findViewById(R.id.back_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                return;
            }
        });
    }
    public void nextPage(){
        Intent intentQuestion = new Intent(this, UpdateQuizActivityQuestions.class);
        intentQuestion.putExtra("quiz", quiz); //Serializable to pass objects through
        intentQuestion.putExtra("key", key); //Serializable to pass objects through
        intentQuestion.putExtra("NbQuestionRemaining", mnbQuestions_editText.getText().toString());
        startActivity(intentQuestion);
    }
}
