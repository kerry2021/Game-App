package com.example.gameproject.puzzle_game;

import android.graphics.drawable.BitmapDrawable;

public interface PuzzleRequester {
    void setPuzzlePieces(BitmapDrawable[] dividedDrawableImages);
    void updatePuzzle();
}
