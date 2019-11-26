package com.example.gameproject.puzzle_game;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.graphics.Bitmap;

import android.view.Gravity;

import com.example.gameproject.R;
import com.example.gameproject.User;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class puzzleGameActivity extends AppCompatActivity {

    private static final String TAG = "Puzzle Game Activity";

    private TextView textViewTime;
    private TextView textViewPuzzleComp;
    private TextView textViewScore;
    private TextView textViewMoves;
    //Slightly tweaked version of gridview for displaying changes after swiping
    private GestureDetectGridView mGridView;
    private boolean movable = true;

    //making only 3x3 puzzle for now, will expand to harder puzzle
    private int columns = 3;
    private int dimensions = columns * columns;

    private int mColumnWidth, mColumnHeight;

    //for inputting swipe functions
    public static final String up = "up";
    public static final String down = "down";
    public static final String left = "left";
    public static final String right = "right";

    //background colour for the game screen, default is white
    private String backgroundColour = "#FFFFFF";

    //a list of number each represent a tile, used for radomizing and checking win condition
    private String[] tileList;

    //a list of identifiers referencing each image for the puzzle in drawable
    private ArrayList<ImageDividable> puzzles = new ArrayList<>();

    //total number of puzzles in the game
    private int puzzleNum = 2;

    //number of puzzle completed
    private int puzzleComplete = 0;

    //number of moves
    private int numMoves = 0;

    //current score
    private int score = 0;

    //Time given to complete the puzzles
    private long countDownInMillis = 12000;

    //Timer
    private CountDownTimer countDownTimer;
    //Time left during game
    private long timeLeftInMillis;
    //Time left during pause
    private long pauseTimeLeft;



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
        textViewPuzzleComp = findViewById(R.id.puzzle);
        textViewScore = findViewById(R.id.score);
        textViewMoves = findViewById(R.id.move);
        createOptionsButton(currentUser);
        columns = PuzzleGameIntroActivity.customizedColumns;
        dimensions = columns * columns;
        puzzles = ImageActivity.selectedImages;
        if (puzzles.size() == 0) {
            addDefaultImagesToList();
        }
        for (int i = 0; i < puzzles.size(); i++) {
            puzzles.get(i).setNumColumns(columns);
        }
        puzzleNum = puzzles.size();
        init();
        randomize();
        setDimensions();
        Log.i(TAG, "Game has Created.");
    }

    private void init() {
        puzzleComplete = 0;
        numMoves = 0;
        score = 0;
        mGridView = findViewById(R.id.grid);
        mGridView.setNumColumns(columns);
        mGridView.setPgActivity(this);
        tileList = new String[dimensions];
        for (int i = 0; i < dimensions; i++) {
            tileList[i] = String.valueOf(i);
        }
        timeLeftInMillis = countDownInMillis;
        startCountDown();
    }

    private void startCountDown() {
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
                showFinalScore();

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

    private void pause() {
        pauseTimeLeft = timeLeftInMillis;
        countDownTimer.cancel();
        setBackgroundClickable(false);
    }

    private void resume() {
        timeLeftInMillis = pauseTimeLeft;
        startCountDown();
        setBackgroundClickable(true);
    }

    private void updateNumMoves() {
        numMoves++;
        String numMovesFormatted = String.format(Locale.getDefault(),
                "# of Moves: %d", numMoves);
        textViewMoves.setText(numMovesFormatted);
    }

    private void clearNumMoves() {
        numMoves = 0;
        String clearNumMovesFormatted = String.format(Locale.getDefault(),
                "# of Moves: %d", numMoves);
        textViewMoves.setText(clearNumMovesFormatted);
    }

    private void updatePuzzleComplete() {
        puzzleComplete++;
        String puzzleFormatted = String.format(Locale.getDefault(),
                "Puzzles Completed: %d", puzzleComplete);
        textViewPuzzleComp.setText(puzzleFormatted);
    }

    private void upDateScore() {
        Integer scoreInteger = (int) ((100 * ((1 - (double) numMoves / 100))));
        score += scoreInteger;
        String scoreFormatted = String.format(Locale.getDefault(),
                "Score: %d", score);
        textViewScore.setText(scoreFormatted);
    }

    private void setBackgroundClickable(boolean backgroundClickable) {
        findViewById(R.id.puzzle_game_options_button).setClickable(backgroundClickable);
        movable = backgroundClickable;
    }

    /**
     * To create the options button.
     */
    private void createOptionsButton(User currentUser) {
        Button optionsButton;

        optionsButton = findViewById(R.id.puzzle_game_options_button);

        optionsButton.setOnClickListener(view -> {
            //pause the game
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


    /**
     * To randomize the tiles in the puzzle
     */
    private void randomize() {
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

    /**
     * Set dimensions for the  puzzles depending on the screen
     */
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

    /**
     * display the code after each movement
     */
    private void display(Context context, int puzzleNum) {
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;

        ArrayList<Bitmap> dividedImages = puzzles.get(puzzleNum).getDividedImages();
        BitmapDrawable[] dividedDrawableImages = new BitmapDrawable[dividedImages.size()];
        for (int i = 0; i < dividedImages.size(); i++) {
            BitmapDrawable bDrawable = new BitmapDrawable(getResources(),dividedImages.get(i));
            dividedDrawableImages[i] = bDrawable;
        }

        for (String s : tileList) {
            button = new Button(context);
            int tile = Integer.parseInt(s);
            button.setBackground(dividedDrawableImages[tile]);
            buttons.add(button);
        }
        mGridView.setAdapter(new PuzzleAdapter(buttons, mColumnWidth, mColumnHeight));
    }

    /**
     * Swap the position of a current tile with another file
     */
    private void swap(Context context, int currentPosition, int swap) {
        String newPosition = tileList[currentPosition + swap];
        tileList[currentPosition + swap] = tileList[currentPosition];
        tileList[currentPosition] = newPosition;
        // Update number of moves
        updateNumMoves();
        display(context, puzzleComplete);

        if (isSolved()) {
            Toast.makeText(context, "NEXT PUZZLE!", Toast.LENGTH_SHORT).show();

            // Update puzzleComplete
            updatePuzzleComplete();
            // Update score
            upDateScore();
            // Reset number of moves
            clearNumMoves();

            if (puzzleComplete < puzzleNum) {

                randomize();

                display(context, puzzleComplete);
            } else {
                Toast.makeText(context, "END OF GAME!", Toast.LENGTH_SHORT).show();
                countDownTimer.cancel();
                showFinalScore();
            }
        }
    }

    /**
     * Either Swap or not swap depending on the position and direction user want to swap
     */
    public void moveTiles(Context context, String direction, int position) {
        if (!movable) {
            return;
        }
        // Upper-left-corner tile
        if (position == 0) {
            switch (direction) {
                case right:
                    swap(context, position, 1);
                    break;

                case down:
                    swap(context, position, columns);
                    break;

                default:
                    Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                    break;
            }


        }
        // Upper-right-corner tile
        else if (position == columns - 1) {
            switch (direction) {
                case left:
                    swap(context, position, -1);
                    break;
                case down:
                    swap(context, position, columns);
                    break;
                default:
                    Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                    break;

            }

        }
        // Bottom-left corner tile
        else if (position == dimensions - columns) {
            switch (direction) {
                case up:
                    swap(context, position, -columns);
                    break;
                case right:
                    swap(context, position, 1);
                    break;
                default:
                    Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
        // Bottom-right corner tile
        else if (position == dimensions - 1) {
            switch (direction) {
                case up:
                    swap(context, position, -columns);
                    break;
                case left:
                    swap(context, position, -1);
                    break;
                default:
                    Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
        // Upper-center tiles
        else if (position > 0 && position < columns - 1) {
            switch (direction) {
                case left:
                    swap(context, position, -1);
                    break;
                case down:
                    swap(context, position, columns);
                    break;
                case right:
                    swap(context, position, 1);
                    break;
                default:
                    Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                    break;
            }


        }
        // Left-side tiles
        else if (position > columns - 1 && position < dimensions - columns &&
                position % columns == 0) {
            switch (direction) {
                case up:
                    swap(context, position, -columns);
                    break;
                case right:
                    swap(context, position, 1);
                    break;
                case down:
                    swap(context, position, columns);
                    break;
                default:
                    Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        // Right-side tiles
        else if ((position + 1) % columns == 0) {
            switch (direction) {
                case up:
                    swap(context, position, -columns);
                    break;
                case left:
                    swap(context, position, -1);
                    break;
                case down:
                     swap(context, position, columns);
                default:
                    Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                    break;
            }


            // Bottom-center tiles
        } else if (position < dimensions - 1 && position > dimensions - columns) {
            switch (direction) {
                case up:
                    swap(context, position, -columns);
                    break;
                case left:
                    swap(context, position, -1);
                    break;
                case right:
                    swap(context, position, 1);
                    break;
                default:
                    Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
                    break;
            }

            // Center tiles
        } else {
            switch (direction) {
                case up:
                    swap(context, position, -columns);
                    break;
                case left:
                    swap(context, position, -1);
                    break;
                case right:
                    swap(context, position, 1);
                    break;
                default:
                    swap(context, position, columns);
                    break;
            }
        }
    }

    /**
     * Check whether the puzzle is solved
     */
    private boolean isSolved() {
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

    private void showFinalScore() {
        pause();
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
        popupWindow.showAtLocation(mGridView, Gravity.CENTER, 0, 0);
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
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void addDefaultImagesToList() {
        /*
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View view = inflater.inflate(R.layout.activity_puzzle_game_select_image, null);
        ImageView default1View = (ImageView) view.findViewById(R.id.default1);
        ImageDividable default1Dividable = new ImageDividable(default1View);
        puzzles.add(default1Dividable);
        ImageView default2View = (ImageView) view.findViewById(R.id.default2);
        ImageDividable default2Dividable = new ImageDividable(default2View);
        puzzles.add(default2Dividable);
        */

        ImageView imageView1 = new ImageView(puzzleGameActivity.this);
        imageView1.setImageResource(R.drawable.default1);
        ImageDividable default1Dividable = new ImageDividable(imageView1);
        puzzles.add(default1Dividable);
        ImageView imageView2 = new ImageView(puzzleGameActivity.this);
        imageView2.setImageResource(R.drawable.default2);
        ImageDividable default2Dividable = new ImageDividable(imageView2);
        puzzles.add(default2Dividable);
    }
}
