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
     * the paint for ships
     */
    private Paint shipPaint;

    /**
     * the paint for obstacles
     */
    private Paint obstaclePaint;

    /**
     * a default drawer
     */
    AndroidDrawer() {
        setShipPaint();
        setObstaclePaint();
    }

    /**
     * set up the drawer with 2 lists of SpaceShip and SpaceObstacles
     *
     * @param spaceShip      the player ship
     * @param spaceObstacles the list of SpaceObstacle
     */
    AndroidDrawer(SpaceShip spaceShip, List<Obstacle> spaceObstacles) {
        this.spaceShip = spaceShip;
        this.spaceObstacles = spaceObstacles;
        setShipPaint();
        setObstaclePaint();
    }

    /**
     * initialize the shipPaint
     */
    private void setShipPaint() {
        shipPaint = new Paint();
        shipPaint.setTextSize(36);
        shipPaint.setTypeface(Typeface.DEFAULT_BOLD);
        shipPaint.setColor(Color.CYAN);
    }

    /**
     * initialize the obstaclePaint
     */
    private void setObstaclePaint() {
        obstaclePaint = new Paint();
        obstaclePaint.setColor(Color.rgb(255, 0, 0));
        obstaclePaint.setStrokeWidth(10);
    }

    /**
     * update the lists of space items
     *
     * @param spaceShip      the player's ship
     * @param spaceObstacles the list of SpaceObstacle
     */
    public void update(SpaceShip spaceShip, List<Obstacle> spaceObstacles) {
        this.spaceShip = spaceShip;
        this.spaceObstacles = spaceObstacles;
    }

    /**
     * draw all space items in the lists to the canvas
     *
     * @param canvas the canvas on which to draw the space items.
     */
    public void draw(Canvas canvas) {
        // draw the spaceship
        drawSpaceship(canvas);

        // draw the obstacles.
        drawSpaceship(canvas);
        for (Obstacle obstacle : spaceObstacles) {
            if(obstacle != null) {
                canvas.drawRect(obstacle.getHitBox(), obstaclePaint);
            }
        }

        // draw the lives
        int remainingLives = spaceShip.getLives();
        int distance = 0;
        while (remainingLives > 0) {
            drawLives(canvas, getGridWidth() / 30 + distance, getGridWidth() / 50);
            remainingLives--;
            distance += 75;
        }

        // draw the remaining invincible time
        int remainingTime = spaceShip.getInvincibleTime();
        if (remainingTime != 0) {
            drawTime(canvas, remainingTime, getGridWidth() / 30 + distance + 50);
            spaceShip.setInvincibleTime(remainingTime - 1);
        }

        // draw the out of screen time
        int outTime = spaceShip.getOutTime();
        if (outTime != 0) {
            drawTime(canvas, outTime, getGridWidth() / 30 + distance + 100);
        }
    }

    /** Draws the spaceship on canvas.
     *
     * @param canvas the canvas on which to draw the spaceship.
     */
    private void drawSpaceship(Canvas canvas) {
        canvas.drawText("(--)ship(--)", spaceShip.getX(), spaceShip.getY(), shipPaint);
    }

    /** Draws the lives on canvas.
     *
     * @param canvas the canvas on which to draw the lives.
     * @param x the first coordinate to draw the life.
     * @param y the second coordinate to draw the life.
     */
    private void drawLives(Canvas canvas, int x, int y) {
        canvas.drawText(".", x, y, shipPaint);
    }

    /** Draws the invincible time on canvas.
     *
     * @param canvas the canvas on which to draw invincible time.
     * @param remainingTime the remaining invincible time.
     * @param x the first coordinate to draw the invincible time.
     */
    private void drawTime(Canvas canvas, int remainingTime, int x) {
        String text = String.valueOf(remainingTime / 30 + 1);
        canvas.drawText(text, x, getGridHeight() / 20, shipPaint);
    }
}
