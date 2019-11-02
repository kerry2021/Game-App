package com.example.gameproject.reaction_game;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.R;

public class reactionGameMain extends AppCompatActivity {
    private reactionGameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_game_main);

        Button customizeButton;

        customizeButton = (Button) findViewById(R.id.customize_mole_speed);

        customizeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), reactionCustomize.class));
            }
        });
        setplay();
        setresume();
        setsave();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (reactionGameManager.getGameactivity() == null) {
            findViewById(R.id.resume).setVisibility(View.GONE);
            findViewById(R.id.save).setVisibility(View.GONE);
        } else {
            findViewById(R.id.resume).setVisibility(View.VISIBLE);
            findViewById(R.id.save).setVisibility(View.VISIBLE);

        }
    }

    private void setplay() {
        findViewById(R.id.play).setOnClickListener(v -> {
            gameManager.newGame();

            Intent i = new Intent(this, reactionGameActivity.class);
            startActivity(i);

        });

    }

    private void setresume() {
        findViewById(R.id.resume).setOnClickListener(v -> {
            gameManager.loadGame();

            Intent i = new Intent(this, reactionGameActivity.class);
            startActivity(i);

        });

    }

    private void setsave() {
        try {
            findViewById(R.id.save).setOnClickListener(v -> {

                gameManager.saveGame();
                setContentView(R.layout.activity_reaction_game_main);

            });
        }catch (NullPointerException e){

        }
    }
}
