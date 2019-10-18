package com.example.gameproject.obstacle_game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AdventureManger {
    private static int gridHeight;

    private static int gridWidth;

    private List<SpaceShip> spaceShips;

    private List<SpaceItem> spaceObstacles;

    public AdventureManger(int height, int width) {
        gridHeight = height;
        gridWidth = width;
        spaceShips = new ArrayList<>();
    }

    static int getGridHeight() {
        return gridHeight;
    }

    static int getGridWidth() {
        return gridWidth;
    }

    void draw(Canvas canvas) {
        for (SpaceShip item: spaceShips) {
            item.draw(canvas);
        }
    }

    void update() {}

    void createSpaceItems() {
        spaceShips.add(new SpaceShip());
    }

}
