package com.example.gameproject.obstacle_game;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.gameproject.R;

import org.w3c.dom.Text;

import androidx.appcompat.app.AppCompatActivity;

public class ObstacleGameIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obstacle_game_intro);
        setTitle("Obstacle Game");

        Button startPuzzleGameButton;

        startPuzzleGameButton = (Button) findViewById(R.id.start_obstacle_game_button);

        startPuzzleGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(v.getContext(), ObstacleGameActivity.class));
            }
        });

        Button obstacleGameRuleButton;

        obstacleGameRuleButton = (Button) findViewById(R.id.obstacle_game_rule_button);

        Button customizationButton;

        customizationButton = (Button) findViewById(R.id.customization_button);

        customizationButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(v.getContext(), CustomizationActivity.class));
            }
        });

        customizationButton = (Button) findViewById(R.id.customization_button);

        final TextView introTextView = (TextView) findViewById(R.id.welcome);
        final TextView reminderTextView = (TextView) findViewById(R.id.before_start);
        final TextView customizationTextView = (TextView) findViewById(R.id.about_customization);

        introTextView.setText(R.string.welcome_obstacle_game);
        reminderTextView.setText(R.string.before_start);
        customizationTextView.setText(R.string.about_customization);
    }
}
