package com.example.gameproject.obstacle_game;

import android.util.Log;
import android.view.ViewDebug;

import java.util.ArrayList;
import java.util.List;

public class AdventureManager {
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
     * @return the player's ship
     */
    SpaceShip getShip() {
        return spaceShip;
    }

    /**
     * @return the list of SpaceObstacles
     */
    List<Obstacle> getObstacles() {
        return spaceObstacles;
    }


    /**
     * Updates the information of all items in this adventure.
     */
    void update() {
        //move everything
        spaceShip.move();
        for (Obstacle obstacle : spaceObstacles) {
            obstacle.move();

            if (obstacle.outOfScreen()) {
                garbageCart.add(obstacle);
            }
        }

        // Automatically check whether the spaceship hits a obstacle or not.
        if (spaceShip.getInvincibleTime() == 0) {
            for (Obstacle obstacle : spaceObstacles) {
                spaceShip.checkHit(obstacle);
            }
        }

        // Check whether the spaceship is out of Screen.
        spaceShip.outOfScreen();
        // we still need something to track whether the game is over or not, I'm not sure to put it in Obstacle game panel or here.

        //check to see whether to generate new obstacles.
        Obstacle newObstacle = obstacleGenerator.checkGeneration();
        if (newObstacle != null) {
            spaceObstacles.add(newObstacle);
        }
        //clear garbage
        for (Obstacle obstacle : garbageCart) {
            spaceObstacles.remove(obstacle);
        }
        garbageCart = new ArrayList<Obstacle>();
    }

    /**
     * Responds to events of player input, when the player touches, make the spaceship jump
     */
    void respondTouch() {
        spaceShip.jump();
    }

    /**
     * Create the items of this adventure.
     * (Still have to create the obstacle in the beginning.)
     */
    void createSpaceItems() {
        spaceShip = new SpaceShip();
        spaceObstacles.add(new Obstacle(2000, 0, 2100, 100));
    }

}
