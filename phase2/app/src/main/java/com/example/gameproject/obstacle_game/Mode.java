package com.example.gameproject.obstacle_game;

import android.view.MotionEvent;

import java.util.List;

public interface Mode {
    void addSpaceShip(AdventureManager manager);

    void respondTouch(MotionEvent event, AdventureManager manager);
}
