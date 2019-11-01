package com.example.gameproject.puzzle_game;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.R;

import java.util.ArrayList;

public class BackgroundChangeActivity extends AppCompatActivity {
    String backgroundColour;
    String[] colours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_game_background_change);
        colours = this.getResources().getStringArray(R.array.colourOptions);

        createOptionButtons();

        Button buttonConfirm, buttonCancel;
        buttonConfirm = findViewById(R.id.confirm_background);
        buttonCancel = findViewById(R.id.cancel_background);
        buttonConfirm.setOnClickListener(v -> saveBackground());
        buttonCancel.setOnClickListener(v -> cancelBackground());

        final TextView introTextView = findViewById(R.id.background_option);
        introTextView.setText(R.string.colour_choose);
    }

    private void createOptionButtons() {
        ArrayList<ImageButton> options = new ArrayList<>();
        for (int i = 0; i < colours.length; i++) {
            int resourceId = this.getResources().
                    getIdentifier("imageButton" + (i+1), "id", this.getPackageName());
            options.add(findViewById(resourceId));
            int finalI = i;
            options.get(i).setOnClickListener(v -> backgroundColour = colours[finalI]);
        }
    }

    private void saveBackground(){
        Intent intent = new Intent(this, PuzzleGameIntroActivity.class);
        intent.putExtra("background", backgroundColour);
        startActivity(intent);
    }

    private void cancelBackground(){
        finish();
    }
}
