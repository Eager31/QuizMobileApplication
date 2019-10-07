package com.example.quizmobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DoingQuizActivity extends AppCompatActivity {

    private TextView mQuizTitleD_textView;
    private TextView mExplanationD_textView;
    private TextView mQuestionD_txtView;
    private TextView mSolutionAD_txtView;
    private TextView mSolutionBD_txtView;
    private TextView mSolutionCD_txtView;
    private TextView mSolutionDD_txtView;
    private Spinner  mRightAnswerD_spinner;
    private TextView mAdvance_txtView;

    private Button next_btn;

    private String key;
    private Quiz quiz;
    private ArrayList<Integer> randomNumbers;
    private Question question;

    private int pointsNumber;
    private int intNbQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing_quiz);

        //Link
        mQuizTitleD_textView = findViewById(R.id.quizTitleD_textView);
        mExplanationD_textView = findViewById(R.id.explanationD_textView);
        mQuestionD_txtView = findViewById(R.id.questionD_txtView);
        mSolutionAD_txtView = findViewById(R.id.solutionAD_txtView);
        mSolutionBD_txtView = findViewById(R.id.solutionBD_txtView);
        mSolutionCD_txtView = findViewById(R.id.solutionCD_txtView);
        mSolutionDD_txtView = findViewById(R.id.solutionDD_txtView);
        mRightAnswerD_spinner = findViewById(R.id.rightAnswerD_spinner);
        mAdvance_txtView = findViewById(R.id.advance_txtView);
        next_btn = findViewById(R.id.next_btn);

        //Getting extra
        Intent intent = getIntent();
        key = intent.getStringExtra("key"); //ID from dataBase
        quiz = (Quiz) intent.getSerializableExtra("quiz"); //Get the data from the Quiz
        randomNumbers = intent.getIntegerArrayListExtra("random"); //Get the question numbers

        //Default for first question
        mQuizTitleD_textView.setText(quiz.getTitle());
        intNbQuestion = 0;
        question = quiz.getQuestionsLists().get(randomNumbers.get(intNbQuestion)); //get information for the question
        String string = "Question n°" + question.getQuestionNumber();
        String stringAdvance = intNbQuestion+1 + "/5";
        mAdvance_txtView.setText(stringAdvance);
        mExplanationD_textView.setText(string);
        mQuestionD_txtView.setText(question.getQuestion());
        mSolutionAD_txtView.setText(question.getOption1());
        mSolutionBD_txtView.setText(question.getOption2());
        mSolutionCD_txtView.setText(question.getOption3());
        mSolutionDD_txtView.setText(question.getOption4());

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRightAnswerD_spinner.getSelectedItemPosition()+1 == question.getCorrectAnswer()){ //If answer is correct
                    pointsNumber++;
                }
                if(intNbQuestion+1 < 5) { //If it still questions to answer /!\ Set to 5 final version
                    reInit();
                } else { //End creation - Calling back display of the MainActivity
                    sendAndStop();
                }
            }
        });

    }

    //Update display for next question
    public void reInit(){
        intNbQuestion++;
        question = quiz.getQuestionsLists().get(randomNumbers.get(intNbQuestion)); //get information for the question
        String string = "Question n°" + question.getQuestionNumber();
        String stringAdvance = intNbQuestion+1 + "/5";
        mExplanationD_textView.setText(string);
        mAdvance_txtView.setText(stringAdvance);
        mQuestionD_txtView.setText(question.getQuestion());
        mSolutionAD_txtView.setText(question.getOption1());
        mSolutionBD_txtView.setText(question.getOption2());
        mSolutionCD_txtView.setText(question.getOption3());
        mSolutionDD_txtView.setText(question.getOption4());
    }

    public void sendAndStop(){
        //send the score
        //ToComplete
        //pointsNumber /5
        Toast.makeText(this, "Your score is: " + pointsNumber, Toast.LENGTH_LONG).show();
        finish();
        return;
    }


}
