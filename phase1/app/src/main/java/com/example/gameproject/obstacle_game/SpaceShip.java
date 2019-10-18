package com.example.gameproject.obstacle_game;

import android.graphics.Canvas;

import static com.example.gameproject.obstacle_game.AdventureManger.getGridHeight;
import static com.example.gameproject.obstacle_game.AdventureManger.getGridWidth;

class SpaceShip extends SpaceItem{
    private static int JumpHeight = getGridWidth() / 6;

    SpaceShip() {
        super(getGridWidth() / 2, getGridHeight() / 2);
    }

    private void moveUp() {
        setCoordinate(getX() - 1, getY());
    }

    private void moveDown() {
        setCoordinate(getX() + 1, getY());
    }

    void move() {
        int x = getX();
        int originalX = getGridWidth() / 2;
        if (x > originalX - JumpHeight & x < originalX) {
            moveUp();
        } else {
            moveDown();
        }
    }

    void draw(Canvas canvas) {
        canvas.drawText(".", getX() * AdventureManger.getGridWidth(),
                getY() * AdventureManger.getGridHeight(), getPaintText());
    }
}
