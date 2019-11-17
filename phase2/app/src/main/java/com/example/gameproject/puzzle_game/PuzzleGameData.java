package com.example.gameproject.puzzle_game;


public class PuzzleGameData implements PuzzleGameDataGateway{

    //total number of puzzles in the game
    private int numPuzzles = 2;

    //number of puzzle completed
    private int numCompleted = 0;

    //number of moves
    private int numMoves = 0;

    //current score
    private int score = 0;

    @Override
    public void updateNumMoves() {
        numMoves++;
    }

    @Override
    public void clearNumMoves() {
        numMoves = 0;
    }

    @Override
    public int getNumMoves() {
        return numMoves;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void updateNumCompleted() {
        numCompleted++;
    }

    @Override
    public int getNumCompleted() {
        return numCompleted;
    }

    @Override
    public void setNumPuzzles(int numPuzzles) {
        this.numPuzzles = numPuzzles;
    }

    @Override
    public int getNumPuzzles() {
        return numPuzzles;
    }

    @Override
    public void saveUserData() {
        //TODO: send all data to user gameStats
    }

}
