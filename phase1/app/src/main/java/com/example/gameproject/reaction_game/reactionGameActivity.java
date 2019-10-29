package com.example.gameproject.reaction_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.R;

public class reactionGameActivity extends AppCompatActivity {

    Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reaction_game_menu);
        play = findViewById(R.id.startgame);
    }

    public void Listener(View v) {
        if (v.getId()==R.id.startgame){
            Play();
        }
    }


    private void Play() {
        Intent play = new Intent(this, reactionGame.class);
        startActivity(play);
    }
}
