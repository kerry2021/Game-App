package com.example.gameproject.puzzle_game;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import android.view.Gravity;

import com.example.gameproject.R;
import com.example.gameproject.User;

import java.util.ArrayList;

public class puzzleGameActivity extends AppCompatActivity implements PuzzleGameView {

    private PuzzleGamePresenter presenter;

    private static final String TAG = "Puzzle Game Activity";

    private TextView textViewTime;
    private TextView textViewpuzzleComp;
    private TextView textViewScore;
    private TextView textViewMoves;

    //background colour for the game screen, default is white
    private String backgroundColour = "#FFFFFF";

    //Time given to complete the puzzles
    private long countDownInMillis = 12000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_game);

        Intent intent = getIntent();
        if(intent.getStringExtra("background") != null){
            backgroundColour = intent.getStringExtra("background");
        }

        countDownInMillis = intent.getIntExtra("countDownTime", 120000 );

        User currentUser = (User) getIntent().getSerializableExtra("user");

        RelativeLayout currentLayout = findViewById(R.id.puzzle_game);
        currentLayout.setBackgroundColor(Color.parseColor(backgroundColour));

        textViewTime = findViewById(R.id.time);
        textViewpuzzleComp = findViewById(R.id.puzzle);
        textViewScore = findViewById(R.id.score);
        textViewMoves = findViewById(R.id.move);

        createOptionsButton(currentUser);

        presenter = new PuzzleGamePresenter(this, getApplicationContext());

        presenter.setGestureDetectGridView(findViewById(R.id.grid));

        int columns = PuzzleGameIntroActivity.customizedColumns;
        presenter.setNumColumns(columns);

        String customImagesKeys = currentUser.get("puzzle_game_custom_images");
        ArrayList<Bitmap> puzzles = new ArrayList<>();
        for (Bitmap image : CustomImageManager.getImageList(customImagesKeys, getApplicationContext())) {
            if (image != null) {
                puzzles.add(image);
            }
        }
        presenter.setPuzzles(puzzles);

        startCountDown();
        presenter.setDimensions();
        Log.i(TAG, "Game has Created.");
    }

    public void startCountDown() {
        presenter.startCountDown(countDownInMillis);
    }

    @Override
    public void showCountDownText(String text) {
        textViewTime.setText(text);
    }

    @Override
    public void showNumMoves(String text) {
        textViewMoves.setText(text);
    }

    @Override
    public void showNumCompleted(String text) {
        textViewpuzzleComp.setText(text);
    }

    @Override
    public void showScore(String text) {
        textViewScore.setText(text);
    }

    @Override
    public void setBackgroundClickable(boolean backgroundClickable) {
        findViewById(R.id.puzzle_game_options_button).setClickable(backgroundClickable);
    }

    private void pause() {
        presenter.pauseGame();
    }

    private void resume() {
        presenter.resumeGame();
    }

    /**
     * To create the options button.
     */
    private void createOptionsButton(User currentUser) {
        Button optionsButton;

        optionsButton = findViewById(R.id.puzzle_game_options_button);

        optionsButton.setOnClickListener(view -> {
            pause();

            // inflate the layout of the popup window
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            View popupView = inflater.inflate(R.layout.puzzle_game_options_window, null);

            // create the popup window
            int width = LinearLayout.LayoutParams.WRAP_CONTENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
            boolean focusable = false; // Taps outside the popup does not dismiss it
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

            // show the popup window
            // which view you pass in doesn't matter, it is only used for the window tolken
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

            Button returnToGameButton, exitGameButton;

            returnToGameButton = popupView.findViewById(R.id.return_to_game_button);
            exitGameButton = popupView.findViewById(R.id.exit_game_button);

            returnToGameButton.setOnClickListener(view1 -> {
                popupWindow.dismiss();
                resume();
            });

            exitGameButton.setOnClickListener(v -> {
                popupWindow.dismiss();

                Intent intent = new Intent(v.getContext(), PuzzleGameIntroActivity.class);
                intent.putExtra("background", backgroundColour);
                intent.putExtra("user", currentUser);
                startActivity(intent);
            });
        });
    }


    public int getStatusBarHeight() {
        int result = 0;
        Context context = getApplicationContext();
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    @Override
    public void showFinalScore(int score) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View popupView = inflater.inflate(R.layout.puzzle_game_final_score_window, null);
        TextView textViewFinalScore = popupView.findViewById(R.id.score);
        textViewFinalScore.setText(String.valueOf(score));

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        // Taps outside the popup does not dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, false);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        Button exitButton;

        exitButton = popupView.findViewById(R.id.exit_button);

        exitButton.setOnClickListener(view -> {
            popupWindow.dismiss();
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pause();
    }
}
