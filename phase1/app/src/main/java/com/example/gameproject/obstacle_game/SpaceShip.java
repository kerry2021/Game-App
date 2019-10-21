package com.example.gameproject.obstacle_game;

import android.graphics.Canvas;
import android.util.Log;

import static com.example.gameproject.obstacle_game.AdventureManger.getGridHeight;
import static com.example.gameproject.obstacle_game.AdventureManger.getGridWidth;

class SpaceShip extends SpaceItem{
    /**
     * The JumpHeight of this spaceship.
     * (Still have to test after finish the coordinate system, not a certain value)
     */
    private static int JumpHeight = getGridWidth() / 6;

    /**
     * Constructs a new Spaceship.
     * (The default location should be somewhere in the middle width and low bottom of the screen.)
     */
    SpaceShip() {
        super(getGridWidth() / 2, getGridHeight() / 2);
    }

    /**
     * Moves the spaceship upward for one unit.
     */
    private void moveUp() {
        setCoordinate(getX() - 1, getY());
    }

    /**
     * Moves the spaceship downward for one unit.
     */
    private void moveDown() {
        setCoordinate(getX() + 1, getY());
    }

    /**
     * Moves the spaceship until it reach the JumpHeight or go back to the default position
     * (Still have to test and change about the function, it's only the beginning version.)
     */
    void move() {
        int x = getX();
        int originalX = getGridWidth() / 2;
        if (x > originalX - JumpHeight & x < originalX) {
            moveUp();
        } else {
            moveDown();
        }
    }

    /**
     * Draws this spaceship item.
     */
    void draw(Canvas canvas) {
        Log.i("Test", "drawn spaceship");
        /*canvas.drawText("(--)Ship", getX() * AdventureManger.getGridWidth(),
                getY() * AdventureManger.getGridHeight(), getPaintText());*/
        canvas.drawText("ship(--)", 100, 100, getPaintText());
    }
}
