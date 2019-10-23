package com.example.gameproject.obstacle_game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AdventureManager {
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
     * Constructs this AdventureManger by default.
     *
     * @param height height of obstacleGamePanel in unit.
     * @param width width of obstacleGamePanel in unit.
     */
    AdventureManager(int width, int height) {
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
     * @return the list of SpaceShip
     */
    List<SpaceShip> getShips(){
        return spaceShips;
    }

    /**
     * @return the list of SpaceObstacles
     */
    List<SpaceItem> getObstacles(){
        return spaceObstacles;
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
