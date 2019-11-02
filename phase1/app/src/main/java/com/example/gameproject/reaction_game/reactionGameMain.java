package com.example.gameproject.reaction_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.R;

public class reactionGameMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_game_main);

        Button startReactionGameButton,customizeButton;

        startReactionGameButton = (Button) findViewById(R.id.play);
        customizeButton = (Button) findViewById(R.id.customize_mole_speed);

        startReactionGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(v.getContext(), reactionGameActivity.class));
            }
        });
        customizeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(v.getContext(), reactionCustomize.class));
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (reactionGameManager.getGameactivity() == null){
            findViewById(R.id.resume).setVisibility(View.GONE);
            findViewById(R.id.save).setVisibility(View.GONE);
        }
        else{
            findViewById(R.id.resume).setVisibility(View.VISIBLE);
            findViewById(R.id.save).setVisibility(View.VISIBLE);

        }
    }

}
