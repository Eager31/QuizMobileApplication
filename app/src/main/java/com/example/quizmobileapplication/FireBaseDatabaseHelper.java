package com.example.quizmobileapplication;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FireBaseDatabaseHelper {

    //Instance
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceQuizzes;
    private DatabaseReference mReferenceResults;
    private DatabaseReference mReferenceUsers;

    //Arrays
    private List<Quiz> quizzes = new ArrayList<>();
    private List<Result> results = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public interface DataStatus{
        //Loaded
        void QuizIsLoaded(List<Quiz> quizzes, List<String> keys);
        void ResultIsLoaded(List<Result> results, List<String> keys);
        void UserIsLoaded(List<User> users, List<String> keys);
        //Others
        void DataInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }


    public FireBaseDatabaseHelper(){
        mDatabase =  FirebaseDatabase.getInstance(); //Get the instance
        mReferenceQuizzes = mDatabase.getReference("Quiz table");
        mReferenceResults = mDatabase.getReference("Results Table");
        mReferenceUsers = mDatabase.getReference("User Table");
    }

    //Quizzes methods
    public void readQuizzes(final DataStatus dataStatus){
        mReferenceQuizzes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                quizzes.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    //insert quiz into
                    Quiz quiz = keyNode.getValue(Quiz.class);
                    quizzes.add(quiz);
                }
                dataStatus.QuizIsLoaded(quizzes, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addQuiz(Quiz quiz, final DataStatus dataStatus){
        String key = mReferenceQuizzes.push().getKey();
        mReferenceQuizzes.child(key).setValue(quiz).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataInserted();
            }
        });

    }

    //Results methods
    public void readResults(final DataStatus dataStatus){
        mReferenceResults.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                results.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    //insert book into
                    Result result = keyNode.getValue(Result.class);
                    results.add(result);
                }
                dataStatus.ResultIsLoaded(results, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addResult(Result result, final DataStatus dataStatus){
        String key = mReferenceResults.push().getKey();
        mReferenceResults.child(key).setValue(result).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataInserted();
            }
        });
    }


    //Users methods
    public void readUsers(final DataStatus dataStatus){
        mReferenceUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    //insert book into
                    User user = keyNode.getValue(User.class);
                    users.add(user);
                }
                dataStatus.UserIsLoaded(users, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addUser(User user, final DataStatus dataStatus){
        String key = mReferenceUsers.push().getKey();
        mReferenceUsers.child(key).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataInserted();
            }
        });
    }





}
