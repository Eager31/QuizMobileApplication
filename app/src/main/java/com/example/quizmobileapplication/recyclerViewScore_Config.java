package com.example.quizmobileapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recyclerViewScore_Config {
    private Context mcontext;
    private scoreAdapter scoreAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Result> results, List<String> keys) {
        mcontext = context;
        scoreAdapter = new scoreAdapter(results, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(scoreAdapter);
    }

    class ScoreItemView extends RecyclerView.ViewHolder {
        private TextView titleQuizz;
        private TextView highscore;
        private TextView participant;
        private Result result;
        private Button viewUserScore;

        private String key;

        public ScoreItemView (ViewGroup parent) {
            super(LayoutInflater.from(mcontext).inflate(R.layout.activity_score_item, parent, false));

            titleQuizz = (TextView) itemView.findViewById(R.id.titleQuizz);
            highscore = (TextView) itemView.findViewById(R.id.highscores);
            participant = (TextView) itemView.findViewById(R.id.participant);
            viewUserScore = (Button) itemView.findViewById(R.id.viewUserScore);

            viewUserScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mcontext, ResultListPerQuizz.class);
                    intent.putExtra("result", result);
                    mcontext.startActivity(intent);
                }
            });
        }

        public void bind(Result result, String key) {
            this.result = result;
            titleQuizz.setText(result.getQuizzName());
            highscore.setText(getMaxScore(result.getScoreList()));
            int nb = result.getScoreList().size();
            participant.setText(Integer.toString(nb));
            this.key = key;
        }

        public String getMaxScore(List<Score> allScore) {
            int maxScore = 0;
            if (allScore.size() == 0) { return Integer.toString(maxScore); }
            for (Score score : allScore) {
                if (score.getScore() > maxScore) {
                    maxScore = score.getScore();
                }
            }
            return Integer.toString(maxScore);
        }

    }

    class scoreAdapter extends RecyclerView.Adapter<ScoreItemView> {
        private List<Result> Results;
        private List<String> keys;

        public scoreAdapter(List<Result> results, List<String> keys) {
            this.Results = results;
            this.keys = keys;
        }

        @NonNull
        @Override
        public ScoreItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ScoreItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ScoreItemView holder, int position) {
            holder.bind(Results.get(position), keys.get(position));
        }

        @Override
        public int getItemCount() {
            return Results.size();
        }
    }
}
