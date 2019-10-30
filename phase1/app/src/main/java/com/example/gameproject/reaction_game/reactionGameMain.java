package com.example.gameproject.reaction_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.R;
import com.example.gameproject.puzzle_game.puzzleGameActivity;

public class reactionGameMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_game_main);
        Button play;
        play = findViewById(R.id.play);
        play.setOnClickListener(v -> startActivity(new Intent(v.getContext(), reactionGameActivity.class)));

    }


}
