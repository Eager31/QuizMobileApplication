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

import java.util.ArrayList;
import java.util.List;

public class NewQuizActivityQuestions extends AppCompatActivity {


    private String nbQuestionRemaining;
    private int intNbQuestionRemaining; //local var to gain some time by casting in int
    private TextView quizTitle_textView; //Title quiz from previous Intent
    private TextView IndicatorQuestionNumber_textView; //Indicate the question number
    private int actualQuestion;
    private EditText questionTitle_editTxt; //The question itself
    private EditText solutionA_editTxt; //Answer A
    private EditText SolutionB_editTxt;
    private EditText SolutionC_editTxt;
    private EditText SolutionD_editTxt;
    private Spinner rightAnswer;
    private Button cancelButton;
    private Button nextButton;
    private Quiz actualQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quiz_questions);

        //Get the current intent
        Intent intent = getIntent();
        //Get the previous data
        //Get the actual state from the Quiz
        actualQuiz = (Quiz) intent.getSerializableExtra("Quiz");

        nbQuestionRemaining = intent.getStringExtra("NbQuestionRemaining");
        intNbQuestionRemaining = Integer.parseInt(nbQuestionRemaining);

         //Match and fill
        quizTitle_textView = findViewById(R.id.quizTitle_textView);
        quizTitle_textView.setText(actualQuiz.getTitle());

        IndicatorQuestionNumber_textView = findViewById(R.id.explanation_textView);
        if(actualQuiz.getQuestionsLists() == null){ //first question
            actualQuestion = 1;
        }
        String string = "Question n°" + actualQuestion;
        IndicatorQuestionNumber_textView.setText(string);

        questionTitle_editTxt = findViewById(R.id.questionTitle_editTxt);
        solutionA_editTxt = findViewById(R.id.solutionA_editTxt);
        SolutionB_editTxt = findViewById(R.id.solutionB_editTxt);
        SolutionC_editTxt = findViewById(R.id.solutionC_editTxt);
        SolutionD_editTxt = findViewById(R.id.solutionD_editTxt);

        rightAnswer = findViewById(R.id.rightAnswer_spinner);
        cancelButton = findViewById(R.id.back_btn);
        nextButton = findViewById(R.id.start_btn);
        intNbQuestionRemaining = intNbQuestionRemaining -1; //Reduce by one the number of execution for the Intent Call

        //Generate an empty list for our quiz
        List<Question> listQuestion = new ArrayList <>();
        actualQuiz.setQuestionsLists(listQuestion);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Adding the question and each of his components
                Question question = new Question();
                question.setQuestionNumber(actualQuestion);
                question.setQuestion(questionTitle_editTxt.getText().toString());
                question.setOption1(solutionA_editTxt.getText().toString());
                question.setOption2(SolutionB_editTxt.getText().toString());
                question.setOption3(SolutionC_editTxt.getText().toString());
                question.setOption4(SolutionD_editTxt.getText().toString());
                switch(rightAnswer.getSelectedItem().toString()) {
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

                actualQuiz.getQuestionsLists().add(question);
                Log.d("QV",String.valueOf(question.getQuestion()));
                Log.d("QV",String.valueOf(actualQuiz.getQuestionsLists()));
                Log.d("QV",String.valueOf(actualQuiz.getQuestionsLists().size()));
                Log.d("QV",String.valueOf(intNbQuestionRemaining));

                if(intNbQuestionRemaining > 0) { //If it still questions to answer
                    //Log.d("QV",String.valueOf(actualQuiz.getCreatorID()));
                    //Log.d("QV",String.valueOf(actualQuiz.getDate()));
                    //Log.d("QV",String.valueOf(actualQuiz.getTitle()));
                    //Log.d("QV",String.valueOf(actualQuiz.getQuestionsLists()));
                    reInit();
                } else { //End creation - Calling back display of the MainActivity
                    //TMP à compléter
                    actualQuiz.setCreatorID("Emilien");
                    actualQuiz.setDate("10/10/2019");
                    sendAndStop();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
               return;
            }
        });

    }

    //Recall the same component for advance
    public void reInit(){
        intNbQuestionRemaining--;
        //Reloading this part with new values
        actualQuestion = actualQuiz.getQuestionsLists().size() + 1; //the actual question
        String string = "Question n°" + actualQuestion;
        IndicatorQuestionNumber_textView.setText(string);
        questionTitle_editTxt.setText("");
        solutionA_editTxt.setText("");
        SolutionB_editTxt.setText("");
        SolutionC_editTxt.setText("");
        SolutionD_editTxt.setText("");
        rightAnswer.setSelection(0);
    }

    public void sendAndStop(){
        new FireBaseDatabaseHelper().addQuiz(actualQuiz, new FireBaseDatabaseHelper.DataStatus() {
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
                Toast.makeText(NewQuizActivityQuestions.this, "The Quiz record has "+
                        "been inserted successfully", Toast.LENGTH_LONG).show();
                finish();
                return;

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
