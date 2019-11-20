package com.example.gameproject.reaction_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.R;
import com.example.gameproject.User;

public class ReactionGameMain extends AppCompatActivity {
    private ReactionGameManager gameManager;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_game_main);
        Intent i = getIntent();
        user = (User) i.getSerializableExtra("USER");
        gameManager = new ReactionGameManager(user, this);

        Button customizeButton;

        customizeButton = (Button) findViewById(R.id.customize_mole_speed);

        customizeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ReactionCustomize.class));
            }
        });
        setplay();

    }


    private void setplay() {
        findViewById(R.id.play).setOnClickListener(v -> {
            gameManager.newGame();

            Intent i = new Intent(this, ReactionGameActivity.class);
            startActivity(i);

        });

    }}

