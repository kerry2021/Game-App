package com.example.gameproject.obstacle_game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.ViewDebug;

import com.example.gameproject.R;

import java.util.List;

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
     * draw all items in the lists to the canvas
     */
    public void draw(Canvas canvas) {
        canvas.drawText("(--)ship(--)", spaceShip.getX(), spaceShip.getY(), shipPaint);
        for (Obstacle obstacle : spaceObstacles) {
            if(obstacle != null) {
                canvas.drawRect(obstacle.getHitBox(), obstaclePaint);
            }
        }
    }
}
