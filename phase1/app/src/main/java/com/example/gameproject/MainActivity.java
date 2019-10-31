package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.gameproject.obstacle_game.ObstacleGameActivity;
import com.example.gameproject.puzzle_game.PuzzleGameIntroActivity;
import com.example.gameproject.reaction_game.reactionGameActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button reactionGameButton, puzzleGameButton, obstacleGameButton;

        reactionGameButton = (Button) findViewById(R.id.reaction_game_button);
        puzzleGameButton = (Button) findViewById(R.id.puzzle_game_button);
        obstacleGameButton = (Button) findViewById(R.id.obstacle_game_button);

        File customizationFile = new File(this.getFilesDir(), "User_Info.txt");
        User.setFile(customizationFile);

        reactionGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), reactionGameActivity.class));
            }
        });
        puzzleGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), PuzzleGameIntroActivity.class));
            }
        });
        obstacleGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ObstacleGameActivity.class));
            }
        });
    }
}

