package com.example.quizmobileapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    //Test initialisation Git

    private RecyclerView mQuizRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Toast.makeText(MainActivity.this, "Database OK", Toast.LENGTH_LONG).show();

        mQuizRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_quizzes);
        new FireBaseDatabaseHelper().readQuizzes(new FireBaseDatabaseHelper.DataStatus() {
            @Override
            public void QuizIsLoaded(List<Quiz> quizzes, List<String> keys) {
                new RecyclerView_Config().setConfig(mQuizRecyclerView, MainActivity.this, quizzes, keys);
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

            }
        });




    }
}
