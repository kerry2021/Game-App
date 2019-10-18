package com.example.gameproject.obstacle_game;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;


class SpaceItem{
    private int x;
    private int y;
    private Paint painText;

    SpaceItem() {
        setPainText();
    }

    SpaceItem(int x, int y) {
        this.x = x;
        this.y = y;
        setPainText();
    }

    private void setPainText() {
        this.painText = new Paint();
        Paint paintText = new Paint();
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        paintText.setColor(Color.WHITE);
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
        return painText;
    }

    void move(){}

    void draw(){}
}
