package com.example.gameproject.obstacle_game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/*
 * a game panel
 * a sightly tweaked version of code found on https://www.youtube.com/watch?v=OojQitoAEXs&t=1234s
 */

public class obstacleGamePanel extends SurfaceView implements SurfaceHolder.Callback {
    /**
     * The width of a space item.
     */
    public static float charWidth;

    /**
     * The height of a space item.
     */
    public static float charHeight;

    /**
     * The adventure(obstacle game) content.
     */
    public AdventureManger adventureManger;

    /**
     * The width of the screen.
     */
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    /**
     * The height of the screen.
     */
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    /**
     * The part of the program that manages time.
     */
    private obstacleThread thread;

    public obstacleGamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new obstacleThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Paint paintText = new Paint();
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        charWidth = paintText.measureText("W");
        charHeight = -paintText.ascent() + paintText.descent();

        // Use the letter size and screen height to determine the size of the Space.
        adventureManger = new AdventureManger(
                (int) (screenHeight / charHeight), (int) (screenWidth / charWidth));
        adventureManger.createSpaceItems();

        thread = new obstacleThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("test", "touched");
        return super.onTouchEvent(event);
    }

    /**
     * Updates the adventure information.
     */
    public void update() {
        adventureManger.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            adventureManger.draw(canvas);
        }
    }

    //canvas.drawText("ae", 1, 100, new Paint());
}
