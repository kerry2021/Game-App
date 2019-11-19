package com.example.gameproject.obstacle_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.gameproject.R;

import androidx.annotation.ContentView;

class SinglePlayerMode implements Mode{

    /**
     * Adds one spaceship to adventure manager based on the difficulty.
     * @param manager the manager for which to add spaceship.
     * @param difficulty the difficulty of the game.
     */
    public void addSpaceShip(AdventureManager manager, int difficulty) {
        if (difficulty == 3) {
            manager.addSpaceShip(new SpaceShip(3));
        } else {
            manager.addSpaceShip(new SpaceShip(4));
        }
    }

    /**
     * Makes manager respond to the touch in the game.
     * @param event the motion event.
     * @param manager the manager for which to respond.
     */
    public void respondTouch(MotionEvent event, AdventureManager manager) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            manager.respondTouch(0);
        }
    }

    /**
     * Sets up the bundle to transfer scores and collections to ObstacleGameEndActivity.
     * @param intent the intent for which to put the bundle
     * @param manager the manager of this game.
     */
    public void setUpBundle(Intent intent, AdventureManager manager) {
        Bundle bundle = new Bundle();
        bundle.putString("mode", "SinglePlayerMode");
        for (SpaceShip s : manager.getSpaceshipGarbageCart()) {
            bundle.putString("score", String.valueOf(s.getScore() / 30 * 100));
            bundle.putString("collection", String.valueOf(s.getCollection()));
        }
        intent.putExtras(bundle);
    }
}
