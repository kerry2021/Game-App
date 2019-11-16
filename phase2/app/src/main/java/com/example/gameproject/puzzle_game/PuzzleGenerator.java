package com.example.gameproject.puzzle_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gameproject.R;

import java.util.ArrayList;
import java.util.Random;


/**
 * This class is responsible for generating and updating puzzles when user performs a move.
 */
public class PuzzleGenerator {

    private PuzzleRequester puzzleRequester;

    //a list of number each represent a tile, used for radomizing and checking win condition
    private String[] tileList;

    private int columns = 3;
    private int dimensions = columns * columns;

    //for inputting swipe functions
    public static final String up = "up";
    public static final String down = "down";
    public static final String left = "left";
    public static final String right = "right";

    /**
     * To randomize the tiles in the puzzle
     */
    void randomize() {
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
     * Swap the position of a current tile with another file
     */
    private void swap(Context context, int currentPosition, int swap) {
        String newPosition = tileList[currentPosition + swap];
        tileList[currentPosition + swap] = tileList[currentPosition];
        tileList[currentPosition] = newPosition;

        puzzleRequester.updatePuzzle(context);
    }

    /**
     * Either Swap or not swap depending on the position and direction user want to swap
     */
    public void moveTiles(Context context, String direction, int position) {

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

    void setColumns(int columns) {
        this.columns = columns;
        this.dimensions = columns * columns;
        tileList = new String[dimensions];
        for (int i = 0; i < dimensions; i++) {
            tileList[i] = String.valueOf(i);
        }
        randomize();
    }

    String[] getCurrentPosition() {
        return tileList;
    }

    void setPuzzleRequester(PuzzleRequester puzzleRequester) {
        this.puzzleRequester = puzzleRequester;
    }

}
