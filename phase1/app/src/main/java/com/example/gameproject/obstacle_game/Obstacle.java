package com.example.gameproject.obstacle_game;

import android.graphics.Canvas;
import android.graphics.Rect;

import static com.example.gameproject.obstacle_game.AdventureManager.getGridHeight;
import static com.example.gameproject.obstacle_game.AdventureManager.getGridWidth;

public class Obstacle extends SpaceItem {

    /**
     * the speed which obstacles would move(with respect to the screen)
     */
    private int speed;
    /**
     * Constructs a space obstacle at a x position as left corner and a y position as the top corner.
     *
     * @param left   the x position of the left of this obstacle
     * @param top    the y position of the top of this obstacle
     * @param right  the x position of the right of this obstacle
     * @param bottom the y position of the bottom of this obstacle
     */
    Obstacle(int left, int top, int right, int bottom, int speed) {
        super(new Rect(left, top, right, bottom));
        this.speed = speed;
    }

    /**
     * Updates the coordinate of this space obstacle to move it to the left each time.
     */
    void move() {
        setHitBoxTo(getX() - speed, getY());
    }

    /**
     * Determines whether the obstacle is out of screen or not. Use it as a sign of remove it from
     * the space obstacle list in AdventureManger.
     *
     * @return whether the obstacle is out of screen or not.
     */
    Boolean outOfScreen() {
        int x = getX();
        return (x <= 0);
    }
}
