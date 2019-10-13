package com.example.quizmobileapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class SignInActivity extends AppCompatActivity {

    private EditText mEmail_editTxt;
    private EditText mPassword_editTxt;

    private Button mSignIn_btn;
    private Button mRegister_btn;

    private ProgressBar mProgress_bar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();

        mEmail_editTxt = (EditText) findViewById(R.id.email_editText);
        mPassword_editTxt = (EditText) findViewById(R.id.password_editText);

        mSignIn_btn = (Button) findViewById(R.id.signin_btn);
        mRegister_btn = (Button) findViewById(R.id.register_btn);

        mProgress_bar = (ProgressBar) findViewById(R.id.loading_progressBar);

        mSignIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty()) return;
                inProgress(true);
                mAuth.signInWithEmailAndPassword(mEmail_editTxt.getText().toString(), mPassword_editTxt.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(SignInActivity.this, "User signned in", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish(); return;
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(SignInActivity.this, "Sign in failed!" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        mRegister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty()) return;
                inProgress(true);
                mAuth.createUserWithEmailAndPassword(mEmail_editTxt.getText().toString(), mPassword_editTxt.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(SignInActivity.this, "User registered successfully", Toast.LENGTH_LONG).show();
                        User user = new User(mEmail_editTxt.getText().toString(), false, null);
                        addUserInRegister(user);
                        inProgress(false);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(SignInActivity.this, "Registration failed!" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    private void inProgress(boolean x) {
        if (x) {
            mProgress_bar.setVisibility(View.VISIBLE);
            mSignIn_btn.setEnabled(false);
            mRegister_btn.setEnabled(false);
        } else{
            mProgress_bar.setVisibility(View.GONE);
            mSignIn_btn.setEnabled(true);
            mRegister_btn.setEnabled(true);
        }
    }

    private boolean isEmpty() {
        if (TextUtils.isEmpty(mEmail_editTxt.getText().toString())) {
            mEmail_editTxt.setError("Email is required");
            return true;
        }
        if (TextUtils.isEmpty(mPassword_editTxt.getText().toString())) {
            mPassword_editTxt.setError("Password is required");
            return true;
        }
        return false;
    }

    private void addUserInRegister(User user) {
        new FireBaseDatabaseHelper().addUser(user, new FireBaseDatabaseHelper.DataStatus() {
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
