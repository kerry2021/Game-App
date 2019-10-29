package com.example.gameproject.puzzle_game;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import com.example.gameproject.R;

import java.util.ArrayList;
import java.util.Random;

public class puzzleGameActivity extends AppCompatActivity {

    private static final String TAG = "Puzzle Game Activity";

    //Slightly tweaked version of gridview for displaying changes after swiping
    private static GestureDetectGridView mGridView;

    //making only 3x3 puzzle for now, will expand to harder puzzle
    private static final int COLUMNS = 3;
    private static final int DIMENSIONS = COLUMNS * COLUMNS;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_game);

        init();

        randomize();

        setDimensions();
        Log.i(TAG, "Game has Created.");
    }

    private void init() {
        mGridView = (GestureDetectGridView) findViewById(R.id.grid);
        mGridView.setNumColumns(COLUMNS);

        tileList = new String[DIMENSIONS];
        for (int i = 0; i < DIMENSIONS; i++) {
            tileList[i] = String.valueOf(i);
        }
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

                mColumnWidth = displayWidth / COLUMNS;
                mColumnHeight = requiredHeight / COLUMNS;

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
        for (int i = 0; i < 18; i++){
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
            Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            puzzleComplete++;
            if(puzzleComplete < puzzleNum){

                randomize();

                display(context, puzzleComplete);
            }

            else{
                Toast.makeText(context, "END OF GAME!", Toast.LENGTH_SHORT).show();
                //TODO: Direct to a endgame score screen and lead back to mainscreen
            }
        }
    }

    /**Either Swap or not swap depending on the position and direction user want to swap*/
    public static void moveTiles(Context context, String direction, int position) {

        // Upper-left-corner tile
        if (position == 0) {

            if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-center tiles
        } else if (position > 0 && position < COLUMNS - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-right-corner tile
        } else if (position == COLUMNS - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Left-side tiles
        } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                position % COLUMNS == 0) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Right-side AND bottom-right-corner tiles
        } else if (position == COLUMNS * 2 - 1 || position == COLUMNS * 3 - 1) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) {

                // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                // right-corner tile.
                if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                        COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-left corner tile
        } else if (position == DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-center tiles
        } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Center tiles
        } else {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else swap(context, position, COLUMNS);
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
}
