package com.example.gameproject.obstacle_game;

import java.util.ArrayList;
import java.util.List;

public class AdventureManager {
    /**
     * The time need to be counted down before the game start.
     */
    private int startGameCountDown = 30 * 5;

    /**
     * The time need to be counted down before the game end.
     */
    private int endGameCountDown = 30 * 3;

    /**
     * The player controlled spaceShip
     */
    private SpaceShip spaceShip;

    /**
     * List containing all obstacles in this adventure.
     */
    private List<Obstacle> spaceObstacles = new ArrayList<Obstacle>();

    /**
     * List containing all items to be deleted at the end of this update
     */
    private List<Obstacle> garbageCart = new ArrayList<Obstacle>();
    /**
     * the generator for obstacles
     */
    private ItemGenerator<Obstacle> obstacleGenerator;

    /**
     * Width of obstacleGamePanel in unit.
     */
    private static int gridHeight;

    /**
     * Height of obstacleGamePanel in unit.
     */
    private static int gridWidth;

    /**
     * the boolean checks whether game is over or not.
     */
    private boolean gameOver = false;

    private int score = 0;

    /**
     * Constructs this AdventureManger by default.
     *
     * @param height height of obstacleGamePanel in unit.
     * @param width  width of obstacleGamePanel in unit.
     */
    AdventureManager(int width, int height) {
        gridHeight = height;
        gridWidth = width;
        obstacleGenerator = new ObstacleGenerator(width, height);
    }

    /**
     * Gets the height of obstacleGamePanel in unit.
     *
     * @return the height of obstacleGamePanel in unit.
     */
    static int getGridHeight() {
        return gridHeight;
    }

    /**
     * Gets the width of obstacleGamePanel in units.
     *
     * @return the width of obstacleGamePanel in unit.
     */
    static int getGridWidth() {
        return gridWidth;
    }

    /**
     * Gets the ship in this game.
     *
     * @return the player's ship
     */
    SpaceShip getShip() {
        return spaceShip;
    }

    /**
     * Gets the obstacles in this game.
     *
     * @return the list of SpaceObstacles
     */
    List<Obstacle> getObstacles() {
        return spaceObstacles;
    }

    /**
     * Checks whether the game is over
     *
     * @return the checker of game over.
     */
    boolean getGameOver() {
        return gameOver;
    }

    /**
     * Checks the startGameCountDown ends or not.
     *
     * @return whether we can start the game.
     */
    boolean checkStartGameCountDown() {
        return (startGameCountDown > 0);
    }

    /**
     * Gets the startGameCountDown time.
     *
     * @return the startGameCountDown time.
     */
    int getStartGameCountDown() {
        return startGameCountDown;
    }

    /**
     * Counts down the number of startGameCountdown.
     */
    void startCountDown() {
        startGameCountDown--;
    }

    /**
     * Checks whether the endGameCountDown time is over.
     *
     * @return whether we can end the game and pop to the end game activity.
     */
    boolean checkEndGameCountDown() {
        return (endGameCountDown > 0);
    }


    /**
     * Counts down the number of endGameCountDown.
     */
    void endCountDown() {
        endGameCountDown--;
    }

    /**
     * Gets the score of the game.
     *
     * @return the score of the game
     */
    int getScore() {
        return score;
    }

    /**
     * Updates the score of the game.
     */
    private void updateScore() {
        score++;
    }

    /**
     * Updates the information of all items in this adventure.
     */
    void update() {
        //move everything
        if (checkStartGameCountDown()) {
            startCountDown();
            return;
        }

        spaceShip.move();

        // Count the time before the game start

        for (Obstacle obstacle : spaceObstacles) {
            obstacle.move();

            if (obstacle.outOfScreen()) {
                garbageCart.add(obstacle);
            }
        }

        // Automatically check whether the spaceship hits a obstacle or not.
        // If it's in 3 seconds invincible time(after it hits an obstacle), then the spaceship doesn't hit any obstacle.
        int invincibleTime = spaceShip.getInvincibleTime();
        if (invincibleTime == 0) {
            for (Obstacle obstacle : spaceObstacles) {
                if (spaceShip.checkHit(obstacle)) {
                    garbageCart.add(obstacle);
                }
            }
        } else {
            spaceShip.setInvincibleTime(invincibleTime - 1);
        }

        //check to see whether to generate new obstacles.
        Obstacle newObstacle = obstacleGenerator.checkGeneration();
        if (newObstacle != null) {
            spaceObstacles.add(newObstacle);
        }
        //clear garbage
        for (Obstacle obstacle : garbageCart) {
            spaceObstacles.remove(obstacle);
        }
        garbageCart = new ArrayList<>();

        // Check whether the spaceship is out of Screen.
        spaceShip.outOfScreen();

        // Updates the score of the game.
        updateScore();

        // Check whether the game is over.
        if (spaceShip.getLives() == 0 | spaceShip.getOutTime() == 1) {
            gameOver = true;
        }
    }

    /**
     * Responds to events of player input, when the player touches, make the spaceship jump
     */
    void respondTouch() {
        spaceShip.jump();
    }

    /**
     * Create the items of this adventure.
     */
    void createSpaceItems() {
        spaceShip = new SpaceShip();
    }
}
