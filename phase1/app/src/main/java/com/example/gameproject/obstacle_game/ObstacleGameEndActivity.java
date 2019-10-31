package com.example.gameproject.obstacle_game;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.TextView;
import com.example.gameproject.R;


public class ObstacleGameEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obstacle_game_end);

        Bundle bundle = getIntent().getExtras();
        String score = bundle.getString("score");

        final TextView thanks = findViewById(R.id.thanks_for_playing_obstacle);
        final TextView score_text = findViewById(R.id.obstacle_game_score);

        thanks.setText(R.string.thanks_obstacle_game);
        SpannableString content = new SpannableString(score);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        score_text.setText(content);
    }

}
