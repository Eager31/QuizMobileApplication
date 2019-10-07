package com.example.quizmobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UpdateQuizActivityQuestions extends AppCompatActivity {


    private TextView mQuizTitle_textView;
    private TextView mExplanation_textView;
    private EditText mQuestionTitle_editTxt;
    private EditText mSolutionA_editTxt;
    private EditText mSolutionB_editTxt;
    private EditText mSolutionC_editTxt;
    private EditText mSolutionD_editTxt;
    private Spinner mRightAnswer_spinner;
    private Button back_btn;
    private Button next_btn;

    private Quiz quiz;
    private String key;
    private String nbQuestionRemaning;
    private int intNbQuestionRemaining; //Gaining some time by a cast
    private Question question; //local question treated
    private int actualQuestionNb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__quiz_questions);

        //Link
        mQuizTitle_textView = findViewById(R.id.quizTitleM_textView);
        mExplanation_textView = findViewById(R.id.explanationM_textView);
        mQuestionTitle_editTxt = findViewById(R.id.questionTitleM_editTxt);
        mSolutionA_editTxt = findViewById(R.id.solutionAM_editTxt);
        mSolutionB_editTxt = findViewById(R.id.solutionBM_editTxt);
        mSolutionC_editTxt =  findViewById(R.id.solutionCM_editTxt);
        mSolutionD_editTxt = findViewById(R.id.solutionDM_editTxt);
        mRightAnswer_spinner = findViewById(R.id.rightAnswerM_spinner);
        back_btn = findViewById(R.id.back_btn);
        next_btn = findViewById(R.id.next_btn);

        //Get intent extra
        Intent intent = getIntent();
        quiz = (Quiz) intent.getSerializableExtra("quiz"); //The quiz and its questions
        key = intent.getStringExtra("key");
        nbQuestionRemaning = intent.getStringExtra("NbQuestionRemaining");
        intNbQuestionRemaining = Integer.parseInt(nbQuestionRemaning);

        //Fill first question
        mQuizTitle_textView.setText(quiz.getTitle());
        question = quiz.getQuestionsLists().get(0); //0 fort first Element in the list, and so on, first question
        actualQuestionNb = question.getQuestionNumber();
        String string = "Question n°" + actualQuestionNb; //So it will be 1 first exec
        mExplanation_textView.setText(string);
        mQuestionTitle_editTxt.setText(question.getQuestion());
        mSolutionA_editTxt.setText(question.getOption1());
        mSolutionB_editTxt.setText(question.getOption2());
        mSolutionC_editTxt.setText(question.getOption3());
        mSolutionD_editTxt.setText(question.getOption4());
        mRightAnswer_spinner.setSelection(question.getCorrectAnswer()-1);
        //intNbQuestionRemaining--; //Reduce by one the number of execution

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question.setQuestionNumber(actualQuestionNb);
                question.setQuestion(mQuestionTitle_editTxt.getText().toString());
                question.setOption1(mSolutionA_editTxt.getText().toString());
                question.setOption2(mSolutionB_editTxt.getText().toString());
                question.setOption3(mSolutionC_editTxt.getText().toString());
                question.setOption4(mSolutionD_editTxt.getText().toString());
                switch(mRightAnswer_spinner.getSelectedItem().toString()) {
                    case "Answer A":
                        question.setCorrectAnswer(1);
                        break;
                    case "Answer B":
                        question.setCorrectAnswer(2);
                        break;
                    case "Answer C":
                        question.setCorrectAnswer(3);
                        break;
                    case "Answer D":
                        question.setCorrectAnswer(4);
                        break;
                }

                quiz.getQuestionsLists().set(actualQuestionNb-1, question); //modify the actual question by the new one
                Log.i("QST", quiz.getQuestionsLists().get(actualQuestionNb-1).getQuestion());
                intNbQuestionRemaining--; //Reduce by one the number of execution
                if(intNbQuestionRemaining > 0) { //If it still questions to answer
                    reInit();
                } else { //End modification - Calling back display of the MainActivity
                    sendAndStop();
                }

            }
        });

        //Cancel modifications
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                return;
            }
        });
    }

    public void reInit(){
        //Reloading this part with new values
        actualQuestionNb++;
        question = quiz.getQuestionsLists().get(actualQuestionNb-1);
        String string = "Question n°" + question.getQuestionNumber();
        mExplanation_textView.setText(string);
        mQuestionTitle_editTxt.setText(question.getQuestion());
        mSolutionA_editTxt.setText(question.getOption1());
        mSolutionB_editTxt.setText(question.getOption2());
        mSolutionC_editTxt.setText(question.getOption3());
        mSolutionD_editTxt.setText(question.getOption4());
        mRightAnswer_spinner.setSelection(question.getCorrectAnswer()-1);

    }
    public void sendAndStop(){
        new FireBaseDatabaseHelper().updateQuiz(key, quiz, new FireBaseDatabaseHelper.DataStatus() {
            @Override
            public void QuizIsLoaded(List<Quiz> quizzes, List<String> keys) {

            }

            @Override
            public void ResultIsLoaded(List<Result> results, List<String> keys) {

            }

            @Override
            public void UserIsLoaded(List<User> users, List<String> keys) {

            }

            @Override
            public void DataInserted() {

            }

            @Override
            public void DataIsUpdated() {
                Toast.makeText(UpdateQuizActivityQuestions.this, "The quiz has been updated"+
                        " successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(UpdateQuizActivityQuestions.this, MainActivity.class));

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
