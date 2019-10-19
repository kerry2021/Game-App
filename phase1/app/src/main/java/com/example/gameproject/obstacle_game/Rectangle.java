package com.example.gameproject.obstacle_game;

import android.graphics.Canvas;

public class Rectangle extends SpaceItem{

    /**
     * Constructs a space obstacle.
     */
    Rectangle() {
        super();
        setCoordinate(300, 0);
    }

    /**
     * Constructs a space obstacle at a specific coordinate.
     * (Still have to change, I thought we have to write a algorithm fot this.
     * For example, take a probability to draw an obstacle after 2s. Randomly decide its width and height.)
     *
     * @param x the first coordinate of this obstacle.
     * @param y the second coordinate of this obstacle.
     */
    Rectangle(int x, int y) {
        super(x, y);
    }

    /**
     * Draws a space obstacle.
     */
    void draw(Canvas canvas) {
        int x = getX();
        int y = getY();
        canvas.drawRect(0, 0, x, y, getPaintText());
    }

    /**
     * Updates the coordinate of this space obstacle to move it down 1 unit each time.
     */
    void move() {
        setCoordinate(getX(), getY() + 1);
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
