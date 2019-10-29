package com.example.gameproject.obstacle_game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import com.example.gameproject.GamePanel;

/*
 * a game panel
 * a sightly tweaked version of code found on https://www.youtube.com/watch?v=OojQitoAEXs&t=1234s
 */

public class ObstacleGamePanel extends GamePanel {
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
    AdventureManager adventureManger;
    /**
     * the drawer that would transfer all backend data to visual representations
     */
    private Drawer drawer;

    ObstacleGamePanel(Context context) {
        super(context);
        drawer = new AndroidDrawer();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        adventureManger = new AdventureManager(screenWidth, screenHeight);
        adventureManger.createSpaceItems();
        super.surfaceCreated(surfaceHolder);
    }

    @Override
    public void update() {
        if (! adventureManger.getGameOver()) {
            adventureManger.update();
        }
    }

    public void surfaceDestoryed(SurfaceHolder surfaceHolder) {
        super.surfaceDestroyed(surfaceHolder);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawer.update(adventureManger.getShip(), adventureManger.getObstacles());
        drawer.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            adventureManger.respondTouch();
        }
        return true;
    }
}
