package com.example.gameproject.obstacle_game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import java.util.List;
import static com.example.gameproject.obstacle_game.AdventureManager.getGridHeight;
import static com.example.gameproject.obstacle_game.AdventureManager.getGridWidth;

/**
 * draws the items in the game to the given canvas
 */

class AndroidDrawer implements Drawer<Canvas> {
    /**
     * List containing all SpaceShips in this adventure.
     */
    private SpaceShip spaceShip;

    /**
     * List containing all obstacles in this adventure.
     */
    private List<Obstacle> spaceObstacles;

    /**
     * The paint for ships
     */
    private Paint shipPaint;

    /**
     * The paint for obstacles
     */
    private Paint obstaclePaint;

    /**
     * The paint for reminder in the center of the screen
     */
    private Paint reminderPaint;


    /**
     * The boolean checks whether game is over or not.
     */
    private boolean gameOver = false;

    /**
     * A default drawer.
     */
    AndroidDrawer() {
        setShipPaint();
        setObstaclePaint();
        setReminderPaint();
    }

    /**
     * Sets up the drawer with 2 lists of SpaceShip and SpaceObstacles.
     *
     * @param spaceShip      the player ship.
     * @param spaceObstacles the list of SpaceObstacle.
     */
    AndroidDrawer(SpaceShip spaceShip, List<Obstacle> spaceObstacles) {
        this.spaceShip = spaceShip;
        this.spaceObstacles = spaceObstacles;
        setShipPaint();
        setObstaclePaint();
        setReminderPaint();
    }

    /**
     * Initializes the shipPaint.
     */
    private void setShipPaint() {
        shipPaint = new Paint();
        shipPaint.setTextSize(36);
        shipPaint.setTypeface(Typeface.DEFAULT_BOLD);
        shipPaint.setColor(Color.CYAN);
    }

    /**
     * Initializes the obstaclePaint.
     */
    private void setObstaclePaint() {
        obstaclePaint = new Paint();
        obstaclePaint.setColor(Color.rgb(255, 0, 0));
        obstaclePaint.setStrokeWidth(10);
    }

    /**
     * Initializes the reminderPaint.
     */
    private void setReminderPaint() {
        reminderPaint = new Paint();
        reminderPaint.setTextSize(100);
        reminderPaint.setColor(Color.MAGENTA);
    }

    /**
     * Updates the lists of space items.
     *
     * @param spaceShip      the player's ship.
     * @param spaceObstacles the list of SpaceObstacle.
     */
    public void update(SpaceShip spaceShip, List<Obstacle> spaceObstacles) {
        this.spaceShip = spaceShip;
        this.spaceObstacles = spaceObstacles;
    }

    /**
     * Draws all space items in the lists to the canvas
     *
     * @param canvas the canvas on which to draw the space items.
     */
    public void draw(Canvas canvas) {
        // draw the spaceship
        drawSpaceship(canvas);

        // draw the obstacles.
        for (Obstacle obstacle : spaceObstacles) {
            if(obstacle != null) {
                canvas.drawRect(obstacle.getHitBox(), obstaclePaint);
            }
        }

        // draw the lives
        drawLives(canvas);

        // draw the remaining invincible time
        drawInvincibleTime(canvas);

        // draw the out of screen time
        drawOutOfScreenTime(canvas);

        // draw the start game count down time.
    }

    /**
     * Draws "Game Over" on the screen.
     *
     * @param canvas the canvas on which to draw "Game Over".
     */
    private void drawGameOver(Canvas canvas) {
        canvas.drawText("Game Over", getGridWidth() / 2, getGridHeight() / 2, reminderPaint);
    }

    /** Draws the spaceship on canvas.
     *
     * @param canvas the canvas on which to draw the spaceship.
     */
    private void drawSpaceship(Canvas canvas) {
        canvas.drawText("(--)ship(--)", spaceShip.getX(), spaceShip.getY(), shipPaint);
    }

    /** Draws a single life on canvas.
     *
     * @param canvas the canvas on which to draw a life.
     * @param x the first coordinate to draw the life.
     * @param y the second coordinate to draw the life.
     */
    private void drawLife(Canvas canvas, int x, int y) {
        canvas.drawText(".", x, y, shipPaint);
    }

    /**
     * Draws the lives on canvas.
     * @param canvas the canvas on which to draw the lives.
     */

    private void drawLives(Canvas canvas) {
        int remainingLives = spaceShip.getLives();
        int distance = 125;
        canvas.drawText("Lives:", getGridWidth() / 30, getGridHeight() / 18, shipPaint);
        while (remainingLives > 0) {
            drawLife(canvas, getGridWidth() / 30 + distance, getGridWidth() / 40);
            remainingLives--;
            distance += 75;
        }
    }

    /** Draws the time on canvas.
     *
     * @param canvas the canvas on which to draw the time
     * @param time the time.
     * @param x the first coordinate to draw the time.
     */
    private void drawTime(Canvas canvas, int time, int x, int y, Paint paint) {
        String text = String.valueOf(time / 30 + 1);
        canvas.drawText(text, x, y, paint);
    }

    /**
     * Draws the invincible time on canvas.
     * @param canvas the canvas on which to draw invincible time.
     */
    private void drawInvincibleTime(Canvas canvas) {
        int invincibleTime = spaceShip.getInvincibleTime();
        if (invincibleTime != 0) {
            canvas.drawText("Remaining Invincible Time : ", getGridWidth() / 4, getGridHeight() / 10, shipPaint);
            drawTime(canvas, invincibleTime, getGridWidth() / 2 - getGridWidth() / 50, getGridHeight() / 10 + 2, shipPaint);
        }
    }

    /**
     * Draws the out of screen time on canvas.
     * @param canvas the canvas on which to draw out of screen time.
     */
    private void drawOutOfScreenTime(Canvas canvas) {
        int outTime = spaceShip.getOutTime();
        if (outTime != 0) {
            canvas.drawText("You can still be out of screen for : ", getGridWidth() / 4, getGridHeight() / 18, shipPaint);
            drawTime(canvas, outTime, getGridWidth() / 2 + getGridHeight() / 18, getGridHeight() / 18 + 2, shipPaint);
        }
    }
}
