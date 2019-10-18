package com.example.gameproject.obstacle_game;

import android.graphics.Canvas;

public class Rectangle extends SpaceItem{

    Rectangle() {
        super();
        setCoordinate(300, 0);
    }

    Rectangle(int x, int y) {
        super(x, y);
    }

    void draw(Canvas canvas) {
        int x = getX();
        int y = getY();
        canvas.drawRect(0, 0, x, y, getPaintText());
    }

    void move() {
        setCoordinate(getX() - 1, getY());
    }

    Boolean outOfScreen() {
        int x = getX();
        return (x <= 0);
    }
}
