package com.example.gameproject.obstacle_game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;


class SpaceItem {
    /**
     * The first coordinate of this space item.
     */
    private int x;

    /**
     * The second coordinate of this space item.
     */
    private int y;

    /**
     * The Paint of this space item.
     */
    private Paint paintText = new Paint();

    /**
     * Constructs a new space item.
     */
    SpaceItem() {
    }

    /**
     * Constructs a new space item.
     *
     * @param x the first coordinate of this space item.
     * @param y the second coordinate of this space item.
     */
    SpaceItem(int x, int y) {
        this.x = x;
        this.y = y;

    }

    /**
     * set the coordinate of this space item.
     *
     * @param x the first coordinate of this space item.
     * @param y the second coordinate of this space item.
     */
    void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets this space item's first coordinate.
     *
     * @return the first coordinate of this space item.
     */
    int getX() {
        return x;
    }

    /**
     * Gets this space item's second coordinate.
     *
     * @return the second coordinate of this space item.
     */
    int getY() {
        return y;
    }

    /**
     * Gets this space item's Paint.
     *
     * @return the Paint of this space item.
     */
    Paint getPaintText() {
        return paintText;
    }

    /**
     * Moves this space item.
     */
    void move() {
    }

}
