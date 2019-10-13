package com.example.quizmobileapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class RecyclerView_Config {

    FirebaseAuth mAuth;

    private static FirebaseUser user;
    private Context mContext;
    private QuizzesAdapter mQuizAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Quiz> quizzes, List<String> keys){
        mContext = context;

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mQuizAdapter = new QuizzesAdapter(quizzes, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mQuizAdapter);
    }

    // Quiz RecyclerView - Match the recycler element into activity_main.xml
    class QuizItemView extends RecyclerView.ViewHolder {
        private TextView mAuthor;
        private TextView mTitle;
        private TextView mDate;
        private TextView mNote;
        private String key;
        private Quiz mQuiz; //Quiz to pass in parameters

        public QuizItemView(ViewGroup parent) {

            //Inflate
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.quiz_list_item, parent, false));

            //Data link
            mAuthor = (TextView) itemView.findViewById(R.id.author_txtView);
            mTitle = (TextView) itemView.findViewById(R.id.tilte_txtView);
            mDate = (TextView) itemView.findViewById(R.id.date_txtView);
            mNote = (TextView) itemView.findViewById(R.id.note_txtView); //Only useful for the user

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, PreparationQuizActivity.class);
                    intent.putExtra("key", key);
                    intent.putExtra("quiz", mQuiz);
                    mContext.startActivity(intent);
                }
            });

        }

        //Fill the data inside
        public void Bind(Quiz quiz, String key) {
            this.mQuiz = quiz; //Get a local quiz with his attributes
            mTitle.setText(this.mQuiz.getTitle().toString());
            mAuthor.setText(this.mQuiz.getCreatorID().toString());
            mDate.setText(this.mQuiz.getDate().toString());
            //mNote.setText(quiz.getNoteFromUser()); --> To code, get the note from the user
            this.key = key.toString();
        }
    }

        //Responsible for creating Quiz Item View, passing keys to methods
    class QuizzesAdapter extends RecyclerView.Adapter<QuizItemView>{
        private List<Quiz> mQuizzesList;
        private List<String> mKeys;

        public QuizzesAdapter(List<Quiz> mQuizzesList, List<String> mKeys) {
            this.mQuizzesList = mQuizzesList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public QuizItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new QuizItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull QuizItemView holder, int position) {
            holder.Bind(mQuizzesList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mQuizzesList.size();
        }
    }

    public static void logout() {
        user = null;
    }




}
