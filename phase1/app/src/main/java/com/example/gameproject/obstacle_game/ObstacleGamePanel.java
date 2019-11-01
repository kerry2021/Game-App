package com.example.gameproject.obstacle_game;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import com.example.gameproject.GamePanel;

import static com.example.gameproject.obstacle_game.AdventureManager.getGridHeight;
import static com.example.gameproject.obstacle_game.AdventureManager.getGridWidth;

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

    private int difficulty;
    /**
     * The manager that deals with all backend data
     */
    AdventureManager adventureManger;
    /**
     * the drawer that would transfer all backend data to visual representations
     */
    private Drawer drawer;

    private Paint reminderPaint;

    ObstacleGamePanel(Context context, int difficulty) {
        super(context);
        drawer = new AndroidDrawer();
        setReminderPaint();
        this.difficulty = difficulty;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        adventureManger = new AdventureManager(screenWidth, screenHeight, difficulty);
        adventureManger.createSpaceItems();
        super.surfaceCreated(surfaceHolder);
    }

    @Override
    public void update() {
        if (!adventureManger.getGameOver()) {
            adventureManger.update();
        } else if (adventureManger.checkEndGameCountDown()) {
            adventureManger.endCountDown();
        } else {
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
        drawGameCountDown(canvas);
        drawGameOver(canvas);
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

    private void setReminderPaint() {
        reminderPaint = new Paint();
        reminderPaint.setTextSize(100);
        reminderPaint.setColor(Color.MAGENTA);
    }

    private void drawGameCountDown(Canvas canvas) {
        if (adventureManger.checkStartGameCountDown()) {
            String text = String.valueOf(adventureManger.getStartGameCountDown() / 30 + 1);
            canvas.drawText(text, screenWidth / 2 , screenHeight / 2, reminderPaint);
        }
    }

    private void drawGameOver(Canvas canvas) {
        if (adventureManger.getGameOver()) {
            canvas.drawText("Game Over", screenWidth / 2, screenHeight / 2, reminderPaint);
        }
    }
}
