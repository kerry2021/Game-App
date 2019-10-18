package com.example.gameproject.obstacle_game;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;


class SpaceItem{
    private int x;
    private int y;
    private Paint paintText = new Paint();

    SpaceItem() {
        setPainText();
    }

    SpaceItem(int x, int y) {
        this.x = x;
        this.y = y;
        setPainText();
    }

    private void setPainText() {
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        paintText.setColor(Color.CYAN);
    }

    void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Paint getPaintText() {
        return paintText;
    }

    void move(){}

    void draw(){}
}
