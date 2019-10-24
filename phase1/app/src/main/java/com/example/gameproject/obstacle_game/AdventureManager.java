package com.example.gameproject.obstacle_game;

import java.util.ArrayList;
import java.util.List;

public class AdventureManager {
    /**
     * List containing all SpaceShips in this adventure.
     */
    private SpaceShip spaceShip;

    /**
     * List containing all obstacles in this adventure.
     */
    private List<SpaceItem> spaceObstacles;

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
    List<SpaceItem> getObstacles() {
        return spaceObstacles;
    }


    /**
     * Updates the information of all items in this adventure.
     */
    void update() {
        spaceShip.move();
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
    }

}
