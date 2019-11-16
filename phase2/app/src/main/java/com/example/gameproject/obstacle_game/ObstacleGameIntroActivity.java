package com.example.gameproject.obstacle_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gameproject.R;
import com.example.gameproject.User;

import androidx.appcompat.app.AppCompatActivity;

public class ObstacleGameIntroActivity extends AppCompatActivity {

    private User currentUser;
    private Intent startGameIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obstacle_game_intro);
        setTitle("Obstacle Game");

        Button startObstacleGameButton = (Button) findViewById(R.id.start_obstacle_game_button);
        Button obstacleGameRuleButton = (Button) findViewById(R.id.obstacle_game_rule_button);
        Button customizationButton = (Button) findViewById(R.id.customization_button);

        currentUser = (User) getIntent().getSerializableExtra("user");
        currentUser.set("progress", "3");

        startGameIntent = new Intent(this, ObstacleGameActivity.class);
        startGameIntent.putExtra("user", currentUser);
        Intent customizationIntent = new Intent(this, CustomizationActivity.class);
        //customizationIntent.putExtra("user", currentUser);

        startObstacleGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(startGameIntent);
            }
        });

        customizationButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivityForResult(customizationIntent, 1);
            }
        });

        obstacleGameRuleButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(v.getContext(), obstacleGameRulesActivity.class));
            }
        });


        final TextView introTextView = (TextView) findViewById(R.id.welcome);
        final TextView reminderTextView = (TextView) findViewById(R.id.before_start);
        final TextView customizationTextView = (TextView) findViewById(R.id.about_customization);

        introTextView.setText(R.string.welcome_obstacle_game);
        reminderTextView.setText(R.string.before_start);
        customizationTextView.setText(R.string.about_customization);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                currentUser.set("obstacle_game_difficulty", data.getStringExtra("difficulty"));
                startGameIntent.putExtra("user", currentUser);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        currentUser.write();
    }
}
