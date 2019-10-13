package com.example.quizmobileapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    //Test initialisation Git

    private FirebaseAuth mAuth;

    private RecyclerView mQuizRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Toast.makeText(MainActivity.this, "Welcome on the App", Toast.LENGTH_LONG).show();

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
            public void UsersIsLoaded(List<User> users, List<String> keys) {

            }

            @Override
            public void User

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        FirebaseUser user = mAuth.getCurrentUser();
        getMenuInflater().inflate(R.menu.quiz_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.new_quizz:
                startActivity(new Intent(this, NewQuizActivity.class));
                return true;
            case R.id.sign_out:
                mAuth.signOut();
                RecyclerView_Config.logout();
                startActivity(new Intent(this, SignInActivity.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
