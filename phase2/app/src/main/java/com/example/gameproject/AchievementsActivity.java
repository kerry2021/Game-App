package com.example.gameproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.puzzle_game.ImageSplitter;

import java.util.ArrayList;

public class AchievementsActivity extends AppCompatActivity {

    private GridView achievementGrid;
    private User currentUser;
    private int progress;
    private Bitmap[] puzzlePieces = new Bitmap[9];

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        Intent intent = getIntent();
        currentUser = (User) intent.getSerializableExtra("user");

        Intent backIntent = new Intent(this, MainActivity.class);

        Button backButton;

        backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(v -> {
            backIntent.putExtra("user", currentUser);
            startActivity(backIntent);
        });

        if (currentUser.get("collectible progress") == null) {
            progress = 0;
        } else {
            progress = Integer.parseInt(currentUser.get("collectible progress"));
        }
        setPuzzlePieces();
        if (progress >= 9) {
            Toast.makeText(getApplicationContext(), "YOU UNLOCKED A BONUS PUZZLE!",
                    Toast.LENGTH_SHORT).show();
        }

        achievementGrid = findViewById(R.id.achievements_grid);
        AchievementsAdapter achievementsAdapter = new AchievementsAdapter(getApplicationContext(),
                puzzlePieces);
        achievementGrid.setAdapter(achievementsAdapter);
    }

    private void setPuzzlePieces() {
        Bitmap puzzle = BitmapFactory.decodeResource(getResources(),
                R.drawable.achievement_puzzle);
        ImageSplitter imageSplitter = new ImageSplitter(3);
        ArrayList<Bitmap> imageList = imageSplitter.splitImage(puzzle);
        int unlockedPieces;
        if (progress > 9) {
            unlockedPieces = 9;
        } else {
            unlockedPieces = progress;
        }
        for (int i = 0; i < unlockedPieces; i++) {
            puzzlePieces[i] = imageList.get(i);
        }

    }
}
