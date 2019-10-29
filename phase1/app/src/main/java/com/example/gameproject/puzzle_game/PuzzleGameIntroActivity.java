package com.example.gameproject.puzzle_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gameproject.R;

public class PuzzleGameIntroActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_game_intro);

        Button startPuzzleGameButton;

        startPuzzleGameButton = (Button) findViewById(R.id.start_puzzle_game_button);

        startPuzzleGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(v.getContext(), puzzleGameActivity.class));
            }
        });

        final TextView helloTextView = (TextView) findViewById(R.id.puzzle_game_intro);
        helloTextView.setText(R.string.puzzle_game_intro);
    }
}
