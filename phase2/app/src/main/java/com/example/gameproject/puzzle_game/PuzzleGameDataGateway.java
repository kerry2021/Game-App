package com.example.gameproject.puzzle_game;


/**
 * Gateway that separates the interactor from database.
 */
public interface PuzzleGameDataGateway {
    void updateNumMoves();
    void clearNumMoves();
    int getNumMoves();
    void setScore(int score);
    int getScore();
    void updateNumCompleted();
    int getNumCompleted();
    void setNumPuzzles(int numPuzzles);
    int getNumPuzzles();
    void saveUserData();//sends data to user's gameStats.
}