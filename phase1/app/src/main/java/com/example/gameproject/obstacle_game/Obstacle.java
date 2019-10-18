package com.example.gameproject.obstacle_game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;


public class Obstacle {
    private int x;
    private int y;
    private Drawable image;
    private static Context context;

    Obstacle() {}

    Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
        context = AdventureManger.getContext();
    }

    void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void setImage(Drawable image) {
        this.image = image;
    }

    public static android.content.Context getContext() {
        return context;
    }

    void move() {}
    void draw(Canvas canvas) {
    }

}
