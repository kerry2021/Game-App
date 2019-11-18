package com.example.gameproject.obstacle_game;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.gameproject.User;

/**
 * The activity that starts the Obstacle Game
 * a sightly tweaked version of code found on https://www.youtube.com/watch?v=OojQitoAEXs&t=1234s
 */
public class ObstacleGameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User currentUser = (User) getIntent().getSerializableExtra("user");

        // If this player has no past difficulty setting, then give him a default of 3(hard).

        String difficulty  = currentUser.get("obstacle_game_difficulty");
        if(difficulty == null){
            currentUser.set("obstacle_game_difficulty", "3");
            currentUser.write();
            difficulty = "3";
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // In future, if we want to extend the game and allow 2 or 3 player to play at the same time,
        // we can write new mode strategy and use it.
        Mode mode = new SinglePlayerMode();
        setContentView(new ObstacleGamePanel(this, Integer.valueOf(difficulty), mode));

    }
}