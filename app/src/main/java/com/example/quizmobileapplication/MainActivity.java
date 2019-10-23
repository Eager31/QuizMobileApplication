package com.example.quizmobileapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    //Test initialisation Git

    private FirebaseAuth mAuth;
    private FirebaseUser actualUser;
    private User user;

    private RecyclerView mQuizRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        actualUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        Intent intent = getIntent();

        user = (User) intent.getSerializableExtra("actualUser");
        if (user.getId() == null || user.getKey() == null) {
            user.setId(actualUser.getUid());
            new FireBaseDatabaseHelper().updateUser(user.getKey(), user, new FireBaseDatabaseHelper.DataStatus() {
                @Override
                public void QuizIsLoaded(List<Quiz> quizzes, List<String> keys) {

                }

                @Override
                public void ResultIsLoaded(List<Result> results, List<String> keys) {

                }

                @Override
                public void UsersIsLoaded(List<User> users, List<String> keys) {

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

        Toast.makeText(MainActivity.this, "Welcome on the App", Toast.LENGTH_LONG).show();

        mQuizRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_quizzes);

        new FireBaseDatabaseHelper().readQuizzes(new FireBaseDatabaseHelper.DataStatus() {
            @Override
            public void QuizIsLoaded(List<Quiz> quizzes, List<String> keys) {
                new RecyclerView_Config().setConfig(mQuizRecyclerView, MainActivity.this, quizzes, keys, user);
            }

            @Override
            public void ResultIsLoaded(List<Result> results, List<String> keys) {

            }

            @Override
            public void UsersIsLoaded(List<User> users, List<String> keys) {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.quiz_activity_menu, menu);
        if (user.getAdmin()) {
            menu.getItem(1).setVisible(true);
        } else {
            menu.getItem(1).setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.new_quizz:
                Intent intent = new Intent(this, NewQuizActivity.class);
                intent.putExtra("actualUser", user);
                startActivity(intent);
                return true;
            case R.id.scores:
                startActivity(new Intent(this, scoreListActivity.class));
                return true;
            case R.id.sign_out:
                mAuth.signOut();
                RecyclerView_Config.logout();
                startActivity(new Intent(this, SignInActivity.class));
                return true;
            case R.id.manage_users:
                startActivity(new Intent(this, UserListActivity.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
