package com.example.gameproject.obstacle_game;

import android.content.Context;

public class AdventureManger {
    private static int gridHeight;

    private static int gridWidth;

    private static Context context;

    AdventureManger(int height, int width, Context currentContext) {
        gridHeight = height;
        gridWidth = width;
        context = currentContext;
    }

    static int getGridHeight() {
        return gridHeight;
    }

    static int getGrjdWidth() {
        return gridWidth;
    }

    static Context getContext() {
        return context;
    }
}
