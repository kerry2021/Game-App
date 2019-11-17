package com.example.gameproject.obstacle_game;

import android.view.MotionEvent;

import java.util.List;

class SinglePlayerMode implements Mode{

    public void addSpaceShip(AdventureManager manager) {
        manager.addSpaceShip(new SpaceShip());
    }

    public void respondTouch(MotionEvent event, AdventureManager manager) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            manager.respondTouch(0);
        }
    }
}
