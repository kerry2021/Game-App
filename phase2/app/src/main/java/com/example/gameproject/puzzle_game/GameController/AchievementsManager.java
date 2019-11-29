package com.example.gameproject.puzzle_game.GameController;

import com.example.gameproject.puzzle_game.GameElements.PuzzleGameDataGateway;

class AchievementsManager {

    private PuzzleGameDataGateway puzzleGameDataGateway;
    private CountDownGenerator countDownGenerator;

    //Threshold time of solving one puzzle for getting time achievement
    private long achievementTime = 30000;

    //Threshold score of finishing one puzzle for getting level score achievement
    private Integer levelScore = 70;

    //Threshold score of finishing the game for getting total score achievement
    private int totalScore = 150;

    AchievementsManager(PuzzleGameDataGateway puzzleGameDataGateway,
                        CountDownGenerator countDownGenerator) {
        this.puzzleGameDataGateway = puzzleGameDataGateway;
        this.countDownGenerator = countDownGenerator;
    }

    boolean checkTimeAchievement(){
        return countDownGenerator.getTotalTime() - countDownGenerator.getCurrentTime() <=
                achievementTime;
    }

    boolean checkLevelScoreAchievement(Integer currentPuzzleScore){
        return currentPuzzleScore >= levelScore;
    }

    boolean checkTotalScoreAchievement(){
        return puzzleGameDataGateway.getScore() >= totalScore;
    }

}
