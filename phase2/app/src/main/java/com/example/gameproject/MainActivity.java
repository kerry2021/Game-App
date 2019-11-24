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
        currentUser = new User("player", "default");
        currentUser.write();
        setTitle("Welcome Back: " + currentUser.get("userName"));

        reactionGameIntent = new Intent(this, ReactionGameMain.class);
        puzzleGameIntent = new Intent(this, PuzzleGameIntroActivity.class);
        obstacleGameIntent = new Intent(this, ObstacleGameIntroActivity.class);


        reactionGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                currentUser.set("progress", "1");
                reactionGameIntent.putExtra("user", currentUser);
                currentUser.write();
                startActivity(reactionGameIntent);
            }
        });
        puzzleGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                currentUser.set("progress", "2");
                puzzleGameIntent.putExtra("user", currentUser);
                currentUser.write();
                startActivity(puzzleGameIntent);
            }
        });
        obstacleGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                currentUser.set("progress", "3");
                obstacleGameIntent.putExtra("user", currentUser);
                currentUser.write();
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

