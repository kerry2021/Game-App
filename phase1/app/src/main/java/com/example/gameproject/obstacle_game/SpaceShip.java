package com.example.gameproject.obstacle_game;

import android.graphics.Canvas;
import android.util.Log;

import static com.example.gameproject.obstacle_game.AdventureManager.getGridHeight;
import static com.example.gameproject.obstacle_game.AdventureManager.getGridWidth;

class SpaceShip extends SpaceItem {
    /**
     * The JumpHeight of this spaceship per frame of jumping.
     * (Still have to test after finish the coordinate system, not a certain value)
     */
    private static int jumpHeight = getGridWidth() / 30;
    /**
     * The units this ship would drop by default each frame
     */
    private static int dropHeight = getGridWidth() / 100;
    /**
     * the max number of frames this ship would keep jumping for
     */
    private int maxJumpDuration = 8;
    /**
     * the current number of frames this ship still have left to jump
     */
    private int jumpDurationLeft = 0;

    /**
     * Constructs a new Spaceship with default jumpHeight, dropHeight, maxJumpDuration.
     * (The default location should be somewhere in the middle width and low bottom of the screen.)
     */
    SpaceShip() {
        super(getGridWidth() / 2, getGridHeight() / 2);
    }



    /**
     * Moves the spaceship upward for one unit.
     */
    private void moveUp() {
        setCoordinate(getX(), getY() - jumpHeight);
    }

    /**
     * Moves the spaceship downward for one unit.
     */
    private void moveDown() {
        setCoordinate(getX(), getY() + dropHeight);
    }

    /**
     * responds to the event where the ship jumps
     */
    void jump() {
        Log.i("test", "jump");
        jumpDurationLeft = maxJumpDuration;
    }

    /**
     * Moves the spaceship until it reach the JumpHeight or go back to the default position
     * (Still have to test and change about the function, it's only the beginning version.)
     */
    void move() {
        /*int x = getX();
        int originalX = getGridWidth() / 2;
        if (x > originalX - jumpHeight & x < originalX) {
            moveUp();
        } else {
            moveDown();
        }*/

        if (jumpDurationLeft > 0) {
            moveUp();
            jumpDurationLeft--;
        } else {
            moveDown();
        }
    }
}
