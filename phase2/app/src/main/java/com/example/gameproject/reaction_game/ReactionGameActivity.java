package com.example.gameproject.reaction_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.R;
import com.example.gameproject.User;

import java.util.Observer;


public class ReactionGameActivity extends AppCompatActivity {
    private User user;
    private ImageButton pause_or_resume;;
    protected int speed;
    protected boolean random = ReactionCustomize.random;
    protected boolean pause_before;
    protected int next;
    private boolean pause = false;
    private MoleManager moleManager;
    private MoleDrawer moleDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_game);

        moleManager = new MoleManager(this);
        moleDrawer = new MoleDrawer();
        moleManager.addObserver((Observer)moleDrawer);
        initButton();
        pause_before = false;
        user = (User) getIntent().getSerializableExtra("user");
        setSpeed();

    }

    protected void setSpeed() {
        try {
            speed = Integer.parseInt(user.get("reaction_game_speed"));
        } catch (Exception e) {
            speed = 750;
        }
    }



    @Override
    protected void onResume(){
        super.onResume();
        pause_or_resume.setBackgroundResource(R.drawable.pause);
        moleManager.start();
    }
    private void initButton(){
        pause_or_resume = findViewById(R.id.pause_or_resume);

        pause_or_resume.setOnClickListener(view -> {//to pause
            if(!pause){
                pause_or_resume.setBackgroundResource(R.drawable.resume);
                pause = true;
                moleManager.pause();
                pauseGame();
            }
        });
    }


    private void pauseGame(){
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View popupView = inflater.inflate(R.layout.pause_and_resume, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        // Taps outside the popup does not dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, false);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        Button exitButton,resumeButton;

        exitButton = popupView.findViewById(R.id.save);
        resumeButton = popupView.findViewById(R.id.resume_reaction);

        exitButton.setOnClickListener(view -> {
            popupWindow.dismiss();
            setContentView(R.layout.activity_reaction_game_main);
        });
        resumeButton.setOnClickListener(view -> {
            popupWindow.dismiss();
            pause_or_resume.setBackgroundResource(R.drawable.pause);
            pause_before = true;
            pause = false;
            moleManager.resume();
        });
    }
    public void endGame(){
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View popupView = inflater.inflate(R.layout.reaction_end, null);
        TextView textViewFinalScore = popupView.findViewById(R.id.score_reaction);
        textViewFinalScore.setText(String.valueOf(moleManager.getScore()));

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        // Taps outside the popup does not dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, false);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        Button exitButton;

        exitButton = popupView.findViewById(R.id.exit_reaction);

        exitButton.setOnClickListener(view -> {
            popupWindow.dismiss();
            startActivity(new Intent(view.getContext(), ReactionGameMain.class));
        });
    }
    @Override
    public void onStop(){
        super.onStop();
        moleManager.stop();
    }
}
