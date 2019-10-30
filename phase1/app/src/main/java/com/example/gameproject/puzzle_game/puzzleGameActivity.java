package com.example.gameproject.puzzle_game;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import android.view.Gravity;

import com.example.gameproject.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class puzzleGameActivity extends AppCompatActivity {

    private static final String TAG = "Puzzle Game Activity";

    private TextView textViewTime;
    private TextView textViewpuzzleComp;
    private TextView textViewScore;
    //Slightly tweaked version of gridview for displaying changes after swiping
    private static GestureDetectGridView mGridView;

    //making only 3x3 puzzle for now, will expand to harder puzzle
    private static int columns = 3;
    private static int dimensions = columns * columns;

    private static int mColumnWidth, mColumnHeight;

    //for inputting swipe functions
    public static final String up = "up";
    public static final String down = "down";
    public static final String left = "left";
    public static final String right = "right";

    //a list of number each represent a tile, used for radomizing and checking win condition
    private static String[] tileList;

    //a list of identifiers referencing each image for the puzzle in drawable
    private static ArrayList<Integer> puzzles = new ArrayList<>();

    //total number of puzzles in the game
    private static int puzzleNum = 2;

    //number of puzzle completed
    private static int puzzleComplete = 0;

    //number of moves
    private static int numMoves = 0;

    //current score
    private static int score = 0;

    //Time given to complete the puzzles
    private static final long COUNTDOWN_IN_MILLIS = 120000;

    //Timer
    private static CountDownTimer countDownTimer;
    //Time left during game
    private long timeLeftInMillis;
    //Time left during pause
    private long pauseTimeLeft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_game);
        textViewTime = findViewById(R.id.time);
        textViewpuzzleComp = findViewById(R.id.puzzle);
        textViewScore = findViewById(R.id.score);
        createOptionsButton();
        columns = PuzzleGameIntroActivity.customizedColumns;
        init();
        randomize();
        setDimensions();
        Log.i(TAG, "Game has Created.");
    }

    private void init() {
        puzzleComplete = 0;
        numMoves = 0;
        score = 0;
        mGridView = (GestureDetectGridView) findViewById(R.id.grid);
        mGridView.setNumColumns(columns);
        tileList = new String[dimensions];
        for (int i = 0; i < dimensions; i++) {
            tileList[i] = String.valueOf(i);
        }

        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();
    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                //TODO: Jump to EndGameScreen

            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes,
                seconds);
        textViewTime.setText(timeFormatted);
    }

    private void pause(){
        pauseTimeLeft = timeLeftInMillis;
        countDownTimer.cancel();
    }

    private void resume(){
        timeLeftInMillis = pauseTimeLeft;
        startCountDown();
    }
    /**
     * To create the options button.
     */
    private void createOptionsButton() {
        Button optionsButton;

        optionsButton = (Button) findViewById(R.id.puzzle_game_options_button);

        optionsButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                pause();
                // inflate the layout of the popup window
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
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

                returnToGameButton = (Button) popupView.findViewById(R.id.return_to_game_button);
                exitGameButton = (Button) popupView.findViewById(R.id.exit_game_button);

                returnToGameButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        popupWindow.dismiss();
                        resume();
                    }
                });

                exitGameButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        startActivity(new Intent(v.getContext(), PuzzleGameIntroActivity.class));
                    }
                });
            }
        });
    }


    /** To randomize the tiles in the puzzle*/
    private static void randomize() {
        int index;
        String temp;
        Random random = new Random();

        for (int i = tileList.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = tileList[index];
            tileList[index] = tileList[i];
            tileList[i] = temp;
        }
    }

    /**Set dimensions for the  puzzles depending on the screen*/
    private void setDimensions() {
        ViewTreeObserver vto = mGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int displayWidth = mGridView.getMeasuredWidth();
                int displayHeight = mGridView.getMeasuredHeight();

                int statusbarHeight = getStatusBarHeight(getApplicationContext());
                int requiredHeight = displayHeight - statusbarHeight;

                mColumnWidth = displayWidth / columns;
                mColumnHeight = requiredHeight / columns;

                createPuzzleList();
                display(getApplicationContext(), puzzleComplete);
            }
        });
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    /** Create a list of images stored in drawable to create puzzle */
    private void createPuzzleList() {
        for (int i = 0; i < 9 * puzzleNum; i++){
            puzzles.add(getResources().getIdentifier("p"+i, "drawable", getPackageName()));
        }

    }

    /** display the code after each movement */
    private static void display(Context context, int puzzleNum) {
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;
        for (int i = 0; i < tileList.length; i++) {
            button = new Button(context);
            if (tileList[i].equals("0"))
                button.setBackgroundResource(puzzles.get(puzzleNum*9));
            else if (tileList[i].equals("1"))
                button.setBackgroundResource(puzzles.get(puzzleNum*9+1));
            else if (tileList[i].equals("2"))
                button.setBackgroundResource(puzzles.get(puzzleNum*9+2));
            else if (tileList[i].equals("3"))
                button.setBackgroundResource(puzzles.get(puzzleNum*9+3));
            else if (tileList[i].equals("4"))
                button.setBackgroundResource(puzzles.get(puzzleNum*9+4));
            else if (tileList[i].equals("5"))
                button.setBackgroundResource(puzzles.get(puzzleNum*9+5));
            else if (tileList[i].equals("6"))
                button.setBackgroundResource(puzzles.get(puzzleNum*9+6));
            else if (tileList[i].equals("7"))
                button.setBackgroundResource(puzzles.get(puzzleNum*9+7));
            else if (tileList[i].equals("8"))
                button.setBackgroundResource(puzzles.get(puzzleNum*9+8));
            buttons.add(button);
        }
        mGridView.setAdapter(new PuzzleAdapter(buttons, mColumnWidth, mColumnHeight));
    }

    /**Swap the position of a current tile with another file*/
    private static void swap(Context context, int currentPosition, int swap) {
        String newPosition = tileList[currentPosition + swap];
        tileList[currentPosition + swap] = tileList[currentPosition];
        tileList[currentPosition] = newPosition;
        display(context, puzzleComplete);

        if (isSolved()) {
            Toast.makeText(context, "NEXT PUZZLE!", Toast.LENGTH_SHORT).show();
            puzzleComplete++;
            if(puzzleComplete < puzzleNum){

                randomize();

                display(context, puzzleComplete);
            }

            else{
                Toast.makeText(context, "END OF GAME!", Toast.LENGTH_SHORT).show();
                countDownTimer.cancel();
                //TODO: Direct to a endgame score screen and lead back to mainscreen
            }
        }
    }

    /**Either Swap or not swap depending on the position and direction user want to swap*/
    public static void moveTiles(Context context, String direction, int position) {

        // Upper-left-corner tile
        if (position == 0) {

            if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, columns);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-center tiles
        } else if (position > 0 && position < columns - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, columns);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-right-corner tile
        } else if (position == columns - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, columns);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Left-side tiles
        } else if (position > columns - 1 && position < dimensions - columns &&
                position % columns == 0) {
            if (direction.equals(up)) swap(context, position, -columns);
            else if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, columns);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Right-side AND bottom-right-corner tiles
        } else if (position == columns * 2 - 1 || position == columns * 3 - 1) {
            if (direction.equals(up)) swap(context, position, -columns);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) {

                // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                // right-corner tile.
                if (position <= dimensions - columns - 1) swap(context, position,
                        columns);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-left corner tile
        } else if (position == dimensions - columns) {
            if (direction.equals(up)) swap(context, position, -columns);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-center tiles
        } else if (position < dimensions - 1 && position > dimensions - columns) {
            if (direction.equals(up)) swap(context, position, -columns);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Center tiles
        } else {
            if (direction.equals(up)) swap(context, position, -columns);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else swap(context, position, columns);
        }
    }

    /**Check whether the puzzle is solved*/
    private static boolean isSolved() {
        boolean solved = false;

        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i].equals(String.valueOf(i))) {
                solved = true;
            } else {
                solved = false;
                break;
            }
        }

        return solved;
    }

    private static void endGame() {
        //TODO: implement jump to endgame screen
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
