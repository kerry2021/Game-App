package com.example.gameproject.obstacle_game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AdventureManger {
    /**
     * List containing all SpaceShips in this adventure.
     */
    private List<SpaceShip> spaceShips;

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
     * Constructs this AdventureManger.
     *
     * @param height height of obstacleGamePanel in unit.
     * @param width width of obstacleGamePanel in unit.
     */
    AdventureManger(int height, int width) {
        gridHeight = height;
        gridWidth = width;
        spaceShips = new ArrayList<>();
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
     * Draws this adventure.
     * (Still have to complete, I only put the spaceship first, because I want to test Spaceship.move()
     * first, and focus on the obstacle after that.)
     */
    void draw(Canvas canvas) {
        for (SpaceShip item: spaceShips) {
            item.draw(canvas);
        }
    }

    /**
     * Updates the information of all items in this adventure.
     * (Things we have to do first:
     * Know how to pass the obstacleGamePanel.onTouchEvent() into this function and then do Spaceship.update())
     */
    void update() {}

    /**
     * Create the items of this adventure.
     * (Still have to create the obstacle in the beginning.)
     */
    void createSpaceItems() {
        spaceShips.add(new SpaceShip());
    }

}
