package com.example.quizmobileapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

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
        mDelete_btn = findViewById(R.id.delete_btn);

        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                return;
            }
        });

        mModify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectModification();
            }
        });

        mDelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    public void redirectModification(){
        Intent intent2 = new Intent(this, UpdateQuizActivity.class);
        intent2.putExtra("key", key);
        intent2.putExtra("quiz", quiz);
        startActivity(intent2);
    }

    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder
                .setMessage("Are you sure to delete this Quiz ?")
                .setPositiveButton("Yes",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) { //Delete the entry + back
                       new FireBaseDatabaseHelper().deleteQuiz(key, new FireBaseDatabaseHelper.DataStatus() {
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

                           }

                           @Override
                           public void DataIsDeleted() {
                               Toast.makeText(PreparationQuizActivity.this, "Quiz has been"+
                                       "deleted successfully",Toast.LENGTH_LONG).show();
                               finish();
                               return;
                           }
                       });
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}
