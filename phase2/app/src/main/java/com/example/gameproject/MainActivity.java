package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.gameproject.obstacle_game.ObstacleGameIntroActivity;
import com.example.gameproject.puzzle_game.PuzzleGameIntroActivity;
import com.example.gameproject.reaction_game.ReactionGameMain;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private User currentUser;
    private Intent reactionGameIntent, puzzleGameIntent, obstacleGameIntent, achievementsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button reactionGameButton, puzzleGameButton, obstacleGameButton, logInButton,
                continueButton, achievementsButton;
        reactionGameButton = findViewById(R.id.reaction_game_button);
        puzzleGameButton = findViewById(R.id.puzzle_game_button);
        obstacleGameButton = findViewById(R.id.obstacle_game_button);
        logInButton = findViewById(R.id.login_button);
        achievementsButton = findViewById(R.id.achievements_button);
        continueButton = findViewById(R.id.continue_game);

        User.setFile(new File(this.getFilesDir(), "User_info.txt"));
        //create a default user
        currentUser = new User("player", "default");
        currentUser.write();
        setTitle("Welcome Back: " + currentUser.get("userName"));

        reactionGameIntent = new Intent(this, ReactionGameMain.class);
        puzzleGameIntent = new Intent(this, PuzzleGameIntroActivity.class);
        obstacleGameIntent = new Intent(this, ObstacleGameIntroActivity.class);
        achievementsIntent = new Intent(this, AchievementsActivity.class);


        reactionGameButton.setOnClickListener(v -> {
            currentUser.set("progress", "1");
            reactionGameIntent.putExtra("user", currentUser);
            currentUser.write();
            startActivity(reactionGameIntent);
        });
        puzzleGameButton.setOnClickListener(v -> {
            currentUser.set("progress", "2");
            puzzleGameIntent.putExtra("user", currentUser);
            currentUser.write();
            startActivity(puzzleGameIntent);
        });
        obstacleGameButton.setOnClickListener(v -> {
            currentUser.set("progress", "3");
            obstacleGameIntent.putExtra("user", currentUser);
            currentUser.write();
            startActivity(obstacleGameIntent);
        });
        logInButton.setOnClickListener(v -> {
            reactionGameIntent.putExtra("user", currentUser);
            currentUser.write();
            startActivity(achievementsIntent);
        });
        achievementsButton.setOnClickListener(v -> {
            achievementsIntent.putExtra("user", currentUser);
            startActivity(achievementsIntent);
        });
        continueButton.setOnClickListener(v -> {
            if(currentUser.get("progress").equals("1")){
                reactionGameIntent.putExtra("user", currentUser);
                startActivity(reactionGameIntent);
            }
            else if(currentUser.get("progress").equals("2")){
                puzzleGameIntent.putExtra("user", currentUser);
                startActivity(puzzleGameIntent);
            }
            else{
                obstacleGameIntent.putExtra("user", currentUser);
                startActivity(obstacleGameIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                currentUser = (User) data.getSerializableExtra("user");
                setTitle("Welcome Back:" + currentUser.get("userName"));
                currentUser.write();
            }
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        currentUser = User.getUser(currentUser.get("userName"), currentUser.get("passWord"));
        Log.i("info", currentUser.get("collectible progress"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        currentUser.write();
    }
}

