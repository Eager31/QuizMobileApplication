package com.example.quizmobileapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class recyclerViewScorePerQuizze_Config {
    private Context mContext;

    private ScoreAdapter mScoreAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Score> score) {
        mContext = context;
        mScoreAdapter = new ScoreAdapter(score);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mScoreAdapter);
    }

    class ScoreItemView extends RecyclerView.ViewHolder {
        private TextView userMail;
        private TextView userScore;

        public ScoreItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.activity_result_per_quizz_item, parent, false));

            userMail = (TextView) itemView.findViewById(R.id.userEmail);
            userScore = (TextView) itemView.findViewById(R.id.scoreUser);
        }

        public void bind(Score score) {
            userMail.setText(score.getUser());
            userScore.setText(Integer.toString(score.getScore()));
        }
    }

    class ScoreAdapter extends RecyclerView.Adapter<ScoreItemView> {
        private List<Score> mScore;

        public ScoreAdapter(List<Score> mScore) {
            this.mScore = mScore;
        }

        @NonNull
        @Override
        public ScoreItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ScoreItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ScoreItemView holder, int position) {
            holder.bind(mScore.get(position));
        }

        @Override
        public int getItemCount() {
            return mScore.size();
        }
    }
}
