package com.example.gameproject.obstacle_game;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.gameproject.GamePanel;

import java.util.Observer;

/*
 * a game panel
 * a sightly tweaked version of code found on https://www.youtube.com/watch?v=OojQitoAEXs&t=1234s
 */

class ObstacleGamePanel extends GamePanel {
    /**
     * The width of the screen. we are doing it reversely because we are using landscape mode
     */
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    /**
     * The height of the screen.
     */
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    /**
     * The manager that deals with all backend data
     */
    private AdventureManager adventureManger;

    /**
     * the drawer that would transfer all backend data to visual representations
     */
    private Drawer drawer;

    ObstacleGamePanel(Context context, int difficulty) {
        super(context);
        adventureManger = new AdventureManager(screenWidth, screenHeight, difficulty);
        adventureManger.addSpaceShip(createSpaceShip());

        drawer = new AndroidDrawer();
        adventureManger.addObserver((Observer) drawer);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        super.surfaceCreated(surfaceHolder);
    }

    @Override
    public void update() {
        adventureManger.update();
        if (adventureManger.getGameOver() && adventureManger.getEndGameCountDown() == 0) {
            Context myContext = getContext();
            Intent intent = new Intent(myContext, ObstacleGameEndActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("score", String.valueOf(adventureManger.getScore() / 30 * 100));
            intent.putExtras(bundle);
            myContext.startActivity(intent);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawer.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            adventureManger.respondTouch();
        }
        return true;
    }

    private SpaceShip createSpaceShip() {
        return new SpaceShip();
    }
}
