package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.gameproject.obstacle_game.ObstacleGameActivity;
import com.example.gameproject.obstacle_game.ObstacleGameIntroActivity;
import com.example.gameproject.puzzle_game.PuzzleGameIntroActivity;
import com.example.gameproject.reaction_game.reactionGameMain;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private User currentUser;
    private Intent reactionGameIntent, puzzleGameIntent, obstacleGameIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button reactionGameButton, puzzleGameButton, obstacleGameButton, logInButton, continueButton;
        reactionGameButton = (Button) findViewById(R.id.reaction_game_button);
        puzzleGameButton = (Button) findViewById(R.id.puzzle_game_button);
        obstacleGameButton = (Button) findViewById(R.id.obstacle_game_button);
        logInButton = (Button)findViewById(R.id.login_button);
        continueButton = (Button)findViewById(R.id.continue_game);

        User.setFile(new File(this.getFilesDir(), "User_info.txt"));
        //create a default user
        Log.i("info", "restart");
        currentUser = new User("player", "default");
        currentUser.write();
        setTitle("Welcome Back: " + currentUser.get("userName"));

        reactionGameIntent = new Intent(this, reactionGameMain.class);
        reactionGameIntent.putExtra("user", currentUser);
        puzzleGameIntent = new Intent(this, PuzzleGameIntroActivity.class);
        puzzleGameIntent.putExtra("user", currentUser);
        obstacleGameIntent = new Intent(this, ObstacleGameIntroActivity.class);
        obstacleGameIntent.putExtra("user", currentUser);

        reactionGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(reactionGameIntent);
            }
        });
        puzzleGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(puzzleGameIntent);
            }
        });
        obstacleGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(obstacleGameIntent);
            }
        });
        logInButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivityForResult(new Intent(v.getContext(), LoginActivity.class), 1);
            }
        });
        continueButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(currentUser.get("progress").equals("1"))
                    startActivity(reactionGameIntent);
                else if(currentUser.get("progress").equals("2")){
                    startActivity(puzzleGameIntent);
                }
                else{
                    startActivity(obstacleGameIntent);
                }
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
                Log.i("info", currentUser.get("userName"));
                currentUser.write();
                reactionGameIntent.putExtra("user", currentUser);
                puzzleGameIntent.putExtra("user", currentUser);
                obstacleGameIntent.putExtra("user", currentUser);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        currentUser.write();
    }
}

