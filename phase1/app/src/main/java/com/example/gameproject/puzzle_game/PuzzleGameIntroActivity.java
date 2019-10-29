package com.example.gameproject.puzzle_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.gameproject.R;

public class PuzzleGameIntroActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_game_intro);

        Button startPuzzleGameButton;

        startPuzzleGameButton = (Button) findViewById(R.id.start_puzzle_game_button);

        startPuzzleGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(v.getContext(), puzzleGameActivity.class));
            }
        });

        createCustomizePuzzleGameButton();

        final TextView introTextView = (TextView) findViewById(R.id.puzzle_game_intro);
        introTextView.setText(R.string.puzzle_game_intro);
    }

    private void createCustomizePuzzleGameButton() {
        Button customizePuzzleGameButton;
        customizePuzzleGameButton = (Button) findViewById(R.id.customize_puzzle_game_button);
        customizePuzzleGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                // inflate the layout of the popup window
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.customize_puzzle_game_window, null);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = false; // Taps outside the popup does not dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                Button addImageButton, confirmButton, cancelButton;
                addImageButton = (Button) popupView.findViewById(R.id.add_picture_button);
                confirmButton = (Button) popupView.findViewById(R.id.confirm_button);
                cancelButton = (Button) popupView.findViewById(R.id.cancel_button);

                addImageButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        //TODO: add image.
                    }
                });

                confirmButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        popupWindow.dismiss();
                        //TODO: save changes, execute.
                    }
                });

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        popupWindow.dismiss();
                        //does not save changes
                    }
                });
            }
        });
    }
}
