package com.example.gameproject.obstacle_game.GameController;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;


public interface Mode {
    void addSpaceShip(AdventureManager manager, int difficulty);

    void respondTouch(MotionEvent event, AdventureManager manager);

    void setUpBundle(Intent intent, AdventureManager manager);
}
