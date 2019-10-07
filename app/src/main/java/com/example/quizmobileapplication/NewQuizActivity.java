package com.example.quizmobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewQuizActivity extends AppCompatActivity {

    private EditText quizTitle;
    private EditText nbQuestions;
    private Button nextBtn;
    private Button cancelBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quiz);

        quizTitle = (EditText) findViewById(R.id.title_editTxt);
        nbQuestions = (EditText) findViewById(R.id.nombQuest_editText);
        nextBtn = (Button) findViewById(R.id.next_btn);
        cancelBtn = (Button) findViewById(R.id.back_btn);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Transfer data to second screen
                nextPage();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                return;
            }
        });
    }

    public void nextPage(){
        Quiz quizToSend = new Quiz();
        quizToSend.setTitle(quizTitle.getText().toString());
        Intent intentQuestion = new Intent(this, NewQuizActivityQuestions.class);
        intentQuestion.putExtra("Quiz", quizToSend); //Serializable to pass objects through
        intentQuestion.putExtra("NbQuestionRemaining", nbQuestions.getText().toString());
        startActivity(intentQuestion);
    }

}
