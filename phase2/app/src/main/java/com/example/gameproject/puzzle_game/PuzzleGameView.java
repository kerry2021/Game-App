package com.example.gameproject.puzzle_game;

interface PuzzleGameView {
    void showCountDownText(String text);
    void setBackgroundClickable(boolean backgroundClickable);
    int getStatusBarHeight();
    void showNumMoves(String text);
    void showNumCompleted(String text);
    void showScore(String text);
    void showFinalScore(int score);
}