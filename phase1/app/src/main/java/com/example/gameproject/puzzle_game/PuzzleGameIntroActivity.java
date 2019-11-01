package com.example.gameproject.puzzle_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gameproject.R;

public class PuzzleGameIntroActivity extends AppCompatActivity {

    /**
     * The spinner menu items.
     */
    public final static String THREE_BY_THREE = "3 by 3 puzzle";
    public final static String FOUR_BY_FOUR = "4 by 4 puzzle";
    public final static String FIVE_BY_FIVE = "5 by 5 puzzle";

    public static int customizedColumns = 3;//default value: 3 by 3 puzzle
    private Spinner spinner;

    /**
     * The list of choices for the spinner that allows the user to choose which
     * Java feature to demonstrate.
     */
    private final String[] puzzleDimensions = {THREE_BY_THREE, FOUR_BY_FOUR, FIVE_BY_FIVE};
    private String backgroundColour = "#FFFFFF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_game_intro);

        Intent intent = getIntent();
        if(intent.getStringExtra("background") != null){
            backgroundColour = intent.getStringExtra("background");
        }

        RelativeLayout currentLayout = findViewById(R.id.intro_layout);
        currentLayout.setBackgroundColor(Color.parseColor(backgroundColour));

        Button startPuzzleGameButton;

        startPuzzleGameButton = findViewById(R.id.start_puzzle_game_button);

        startPuzzleGameButton.setOnClickListener(v -> {
            Intent toGame = new Intent(v.getContext(), puzzleGameActivity.class);
            toGame.putExtra("background", backgroundColour);
            startActivity(toGame);
        });

        createCustomizePuzzleGameButton();

        final TextView introTextView = findViewById(R.id.puzzle_game_intro);
        introTextView.setText(R.string.puzzle_game_intro);

    }

    private void createCustomizePuzzleGameButton() {
        Button customizePuzzleGameButton;
        customizePuzzleGameButton = findViewById(R.id.customize_puzzle_game_button);
        customizePuzzleGameButton.setOnClickListener(view -> {
            // inflate the layout of the popup window
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            View popupView = inflater.inflate(R.layout.customize_puzzle_game_window, null);

            // create the popup window
            int width = LinearLayout.LayoutParams.WRAP_CONTENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
            // Taps outside the popup does not dismiss it
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, false);

            // show the popup window
            // which view you pass in doesn't matter, it is only used for the window tolken
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

            Button addImageButton, changeBackgroundButton, confirmButton, cancelButton;
            addImageButton = popupView.findViewById(R.id.add_picture_button);
            changeBackgroundButton = popupView.findViewById(R.id.background_button);
            confirmButton = popupView.findViewById(R.id.confirm_button);
            cancelButton = popupView.findViewById(R.id.cancel_button);

            addImageButton.setOnClickListener(view1 ->
                    startActivity(new Intent(view.getContext(), ImageActivity.class)));

            changeBackgroundButton.setOnClickListener(v -> {
                startActivity(new Intent(v.getContext(), BackgroundChangeActivity.class));
            });

            confirmButton.setOnClickListener(view12 -> {
                switch (spinner.getSelectedItem().toString()) {
                    case THREE_BY_THREE:
                        customizedColumns = 3;
                        break;
                    case FOUR_BY_FOUR:
                        customizedColumns = 4;
                        break;
                    case FIVE_BY_FIVE:
                        customizedColumns = 5;
                        break;
                }
                popupWindow.dismiss();
                //TODO: save picture (if uploaded), execute.
            });

            cancelButton.setOnClickListener(view13 -> {
                popupWindow.dismiss();
                //does not save changes
            });

            setUpSpinner(popupWindow.getContentView());
        });
    }

    private void setUpSpinner(View view) {
        // Set up the puzzleDimensions spinner.
        spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, puzzleDimensions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


}
