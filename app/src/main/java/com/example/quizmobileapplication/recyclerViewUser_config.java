package com.example.quizmobileapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class recyclerViewUser_config {
    private Context mContext;
    private UsersAdapter mUsersAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<User> users, List<String> keys) {
        mContext = context;
        mUsersAdapter = new UsersAdapter(users, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mUsersAdapter);
    }

    class UserItemView extends RecyclerView.ViewHolder {
        private TextView userEmail;
        private TextView userAdmin;
        private Button deleteBtn;
        private Button switchAdminStateBtn;

        private String key;

        public UserItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.activity_user_item, parent, false));
            userAdmin = (TextView) itemView.findViewById(R.id.userAdmin);
            userEmail = (TextView) itemView.findViewById(R.id.userMail);
            deleteBtn = (Button) itemView.findViewById(R.id.deleteUser);
            switchAdminStateBtn = (Button) itemView.findViewById(R.id.switchAdminState);
        }

        public void bind(final User user, final String key) {
            this.key = key;
            userAdmin.setText(user.getAdmin().toString());
            userEmail.setText(user.getEmail());
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new FireBaseDatabaseHelper().deleteUser(key, new FireBaseDatabaseHelper.DataStatus() {
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
            });
            switchAdminStateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    user.setAdmin(!user.getAdmin());
                    new FireBaseDatabaseHelper().updateUser(key, user, new FireBaseDatabaseHelper.DataStatus() {
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
            });
        }
    }

    class UsersAdapter extends RecyclerView.Adapter<UserItemView>{
        private List <User> mUserList;
        private List<String> mKeys;

        public UsersAdapter(List<User> mUserList, List<String> mKeys) {
            this.mUserList = mUserList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public UserItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new UserItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull UserItemView holder, int position) {
            holder.bind(mUserList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mUserList.size();
        }
    }
}
