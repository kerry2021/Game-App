package com.example.gameproject.obstacle_game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

class AdventureManager extends Observable {
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
    private List<SpaceShip> spaceShipList = new ArrayList<>();

    /**
     * List containing all obstacles in this adventure.
     */
    private List<Obstacle> spaceObstacles = new ArrayList<Obstacle>();

    /**
     * List containing all obstacles to be deleted at the end of this update
     */
    private List<Obstacle> obstacleGarbageCart = new ArrayList<Obstacle>();

    /**
     * List containing all spaceships to be deleted at the end of this update.
     */
    private List<SpaceShip> spaceshipGarbageCart = new ArrayList<>();

    /**
     * the generator for obstacles
     */
    private ItemGenerator<Obstacle> obstacleGenerator;

    /**
     * The score of the game.
     */
    private int score = 0;

    private Mode mode;

    /**
     * Width of obstacleGamePanel in unit.
     */
    private static int gridHeight;

    /**
     * Height of obstacleGamePanel in unit.
     */
    private static int gridWidth;

    /**
     * Check whether the game is over
     */
    private boolean gameOver = false;

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
     * Constructs this AdventureManger by default.
     *
     * @param height height of obstacleGamePanel in unit.
     * @param width  width of obstacleGamePanel in unit.
     */
    AdventureManager(int width, int height, int difficulty) {
        gridHeight = height;
        gridWidth = width;
        obstacleGenerator = new ObstacleGenerator(width, height, difficulty);
    }

    /**
     * Adds a spaceship to the game.
     * @param s the spaceship to be added.
     */
    void addSpaceShip(SpaceShip s) {
        spaceShipList.add(s);
    }

    /**
     * Gets the ship in this game.
     *
     * @return the player's ship.
     */
    List<SpaceShip> getSpaceShipList() {
        return spaceShipList;
    }

    /**
     * Gets the obstacles in this game.
     *
     * @return the list of SpaceObstacles.
     */
    List<Obstacle> getObstacles() {
        return spaceObstacles;
    }

    /**
     * Returns the game over boolean.
     * @return game over boolean.
     */
    boolean getGameOver() {
        return gameOver;
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
     * Gets the endGameCountDown time.
     * @return the endGameCountDown time.
     */
    int getEndGameCountDown() {
        return endGameCountDown;
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
     * Responds to events of player input, when the player touches, make the spaceship with index i jump.
     * @param i the index of spaceship which is supposed to jump.
     */
    void respondTouch(int i) {
        spaceShipList.get(i).jump();
    }


    /**
     * Updates the information of all items in this adventure.
     */
    void update() {
        // Check whether the game is start.
        if (!checkStartGameCountDown()) {
            setChanged();
            notifyObservers();
            return;
        }

        // check whether the game is over.
        if (spaceShipList.size() == 0) {
            // Sets the game to be end.
            gameOver = true;
            setChanged();
            notifyObservers();
            // Count Down the endGameCountDown time.
            checkEndGameCountDown();
            return;
        }

        // update conditions of every spaceship.
        updateSpaceShipList();

        // update conditions of every space obstacles.
        updateSpaceObstacles();

        // check to see whether to generate new obstacles.
        generateNewObstacle();

        // Updates the score of the game.
        updateScore();

        setChanged();
        notifyObservers();
    }


    /**
     * Checks the startGameCountDown ends or not.
     *
     * @return whether we can start the game.
     */
    private boolean checkStartGameCountDown() {
        if (startGameCountDown > 0) {
            startGameCountDown = startGameCountDown - 1;
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks whether the endGameCountDown time is over. If no then -1
     */
    private void checkEndGameCountDown() {
        if (endGameCountDown > 0) {
            endGameCountDown = endGameCountDown - 1;
        }
    }

    /**
     * Removes a spaceship from the game.
     * @param s the spaceship to be removed.
     */
    private void removeSpaceShip(SpaceShip s) {
        spaceShipList.remove(s);
    }

    /**
     * Updates the condition of all spaceships.
     */
    private void updateSpaceShipList() {
        for (SpaceShip s : spaceShipList) {
            // update the position of spaceship.
            s.move();
            // check whether the spaceship hits a obstacle or not.
            checkHit(s);

            // Check whether the spaceship is out of Screen.
            s.outOfScreen();

            // Check whether the adventure of this spaceship is over.
            s.checkGameOver();
            if (s.getGameOver()) {
                spaceshipGarbageCart.add(s);
            }
        }

        // clear dead spaceship.
        for (SpaceShip s : spaceshipGarbageCart) {
            removeSpaceShip(s);
        }
        spaceshipGarbageCart = new ArrayList<>();
    }

    /**
     * Checks whether the spaceship hits an obstacle.
     * @param s the spaceship to be checked.
     */
    private void checkHit(SpaceShip s) {
        int invincibleTime = s.getInvincibleTime();
        // If it's in 3 seconds invincible time(after it hits an obstacle), then the spaceship doesn't hit any obstacle.
        if (invincibleTime == 0) {
            for (Obstacle obstacle : spaceObstacles) {
                if (s.checkHit(obstacle)) {
                    obstacleGarbageCart.add(obstacle);
                }
            }
        } else {
            s.setInvincibleTime(invincibleTime - 1);
        }
    }

    /**
     * Removes the obstacle from the game.
     * @param o the obstacle to be removed.
     */
    private void removeObstacle(Obstacle o) {
        spaceObstacles.remove(o);
    }

    /**
     * Updates the condition of all space obstacles.
     */
    private void updateSpaceObstacles() {
        for (Obstacle obstacle : spaceObstacles) {
            obstacle.move();

            if (obstacle.outOfScreen()) {
                obstacleGarbageCart.add(obstacle);
            }
        }

        // clear obstacle garbage.
        for (Obstacle obstacle : obstacleGarbageCart) {
            removeObstacle(obstacle);
        }
        obstacleGarbageCart = new ArrayList<>();
    }

    /**
     * Adds the obstacle to obstacle list.
     * @param o the obstacle to be added.
     */
    private void addObstacle(Obstacle o) {
        spaceObstacles.add(o);
    }

    /**
     * Randomly generates a new obstacle.
     */
    private void generateNewObstacle() {
        Obstacle newObstacle = obstacleGenerator.checkGeneration();
        if (newObstacle != null) {
            addObstacle(newObstacle);
        }
    }

    /**
     * Updates the score of the game.
     */
    private void updateScore() {
        score++;
    }
}
