package com.example.gameproject.obstacle_game;

import android.graphics.Rect;
import android.util.Log;

import static com.example.gameproject.obstacle_game.AdventureManager.getGridHeight;
import static com.example.gameproject.obstacle_game.AdventureManager.getGridWidth;

class SpaceShip extends SpaceItem {
    /**
     * The JumpHeight of this spaceship per frame of jumping.
     * (Still have to test after finish the coordinate system, not a certain value)
     */
    private static int jumpHeight = getGridHeight() / 30;
    /**
     * The units this ship would drop by default each frame
     */
    private static int dropHeight = getGridHeight() / 100;
    /**
     * The max number of frames this ship would keep jumping for
     */
    private int maxJumpDuration = 4;
    /**
     * The current number of frames this ship still have left to jump
     */
    private int jumpDurationLeft = 0;

    /**
     * The number of lives in a single game.
     */
    private int lives;

    /**
     * The boolean for checking whether the spaceship enter the invincible time.
     */
    private int invincibleTime = 0;

    /**
     * The time a spaceship can be out of screen.
     */
    private int outTime = 0;

    /**
     * Constructs a new Spaceship with default jumpHeight, dropHeight, maxJumpDuration.
     * (The default location should be somewhere in the middle width and low bottom of the screen.)
     */
    SpaceShip() {
        super(new Rect(0, 0, getGridWidth() / 30, getGridHeight() / 50));
        setHitBoxTo(getGridWidth() / 10, getGridHeight() / 2);
        setLives(3);
    }

    /**
     * Gets the remaining invincible time.
     * @return the remaining invincible time.
     */
    int getInvincibleTime() {
        return invincibleTime;
    }

    /**
     * Sets the length of invincible time.
     * @param i the length of time we want to set.
     */
    void setInvincibleTime(int i) {
        invincibleTime = i;
    }

    /**
     * Gets the number of remaining lives.
     * @return the number of remaining lives.
     */
    int getLives() {
        return lives;
    }

    void setLives(int life) {
        this.lives = life;
    }

    /**
     * Checks whether the spaceship hits an obstacle.
     * If yes, then deduct the lives, enter invincible time and return true
     * @param obstacle the obstacle to be checked.
     */
    boolean checkHit(Obstacle obstacle) {
        Rect r1 = this.getHitBox();
        Rect r2 = obstacle.getHitBox();
        if (r1.intersect(r2)) {
            lives--;
            setInvincibleTime(90);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Automatically the time which spaceship is out of screen.
     */
    void outOfScreen() {
        int y = getY();
        if (y < 0 | y > getGridHeight()) {
            if (outTime == 0) {
                setOutTime(150);
            } else {
                setOutTime(outTime - 1);
            }
        } else {
            setOutTime(0);
        }
    }

    /**
     * Updates the out time.
     *
     * @param time the new out time.
     */
    void setOutTime(int time) {
        this.outTime = time;
    }

    /**
     * Gets the out time.
     *
     * @return the out time of this screen.
     */
    int getOutTime() {
        return this.outTime;
    }

    /**
     * Moves the spaceship upward for one unit.
     */
    private void moveUp() {
        setHitBoxTo(getX(), getY() - jumpHeight);
    }

    /**
     * Moves the spaceship downward for one unit.
     */

    private void moveDown() {
        setHitBoxTo(getX(), getY() + dropHeight);
    }

    /**
     * responds to the event where the ship jumps
     */
    void jump() {
        jumpDurationLeft = maxJumpDuration;
    }

    /**
     * Moves the spaceship until it reach the JumpHeight or go back to the default position
     * (Still have to test and change about the function, it's only the beginning version.)
     */
    void move() {
        if (jumpDurationLeft > 0) {
            moveUp();
            jumpDurationLeft--;
        } else {
            moveDown();
        }
    }
}
