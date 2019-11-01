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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button reactionGameButton, puzzleGameButton, obstacleGameButton, logInButton;
        reactionGameButton = (Button) findViewById(R.id.reaction_game_button);
        puzzleGameButton = (Button) findViewById(R.id.puzzle_game_button);
        obstacleGameButton = (Button) findViewById(R.id.obstacle_game_button);
        logInButton = (Button)findViewById(R.id.login_button);

        User.setFile(new File(this.getFilesDir(), "User_info.txt"));
        //create a default user
        currentUser = new User("player", "default");
        currentUser.write();

        setTitle("Welcome Back: " + currentUser.get("userName"));

        reactionGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(v.getContext(), reactionGameMain.class));
            }
        });
        puzzleGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(v.getContext(), PuzzleGameIntroActivity.class));
            }
        });
        obstacleGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(v.getContext(), ObstacleGameIntroActivity.class));
            }
        });
        logInButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivityForResult(new Intent(v.getContext(), LoginActivity.class), 1);
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
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        currentUser.write();
    }
}

