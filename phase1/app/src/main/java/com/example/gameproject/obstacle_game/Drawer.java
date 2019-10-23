package com.example.gameproject.obstacle_game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.List;

/**
 * draws the items in the game to the given canvas
 */

class Drawer {
    /**
     * List containing all SpaceShips in this adventure.
     */
    private List<SpaceShip> spaceShips;

    /**
     * List containing all obstacles in this adventure.
     */
    private List<SpaceItem> spaceObstacles;

    /**
     * the paint for ships
     */
    private Paint shipPaint;

    /**
     *  the paint for obstacles
     */
    private Paint obstaclePaint;

    /**
     * a default drawer
     */
    Drawer(){setShipPaint();}

    /**
     * set up the drawer with 2 lists of SpaceShip and SpaceObstacles
     * @param spaceShips the list of SpaceShip
     * @param spaceObstacles the list of SpaceObstacle
     */
    Drawer(List<SpaceShip> spaceShips, List<SpaceItem> spaceObstacles){
        this.spaceShips = spaceShips;
        this.spaceObstacles = spaceObstacles;
        setShipPaint();
    }

    /**
     * initialize the shipPaint
     */
    private void setShipPaint(){
        shipPaint = new Paint();
        shipPaint.setTextSize(36);
        shipPaint.setTypeface(Typeface.DEFAULT_BOLD);
        shipPaint.setColor(Color.CYAN);
    }

    /**
     * update the lists of space items
     * @param spaceShips the list of SpaceShip
     * @param spaceObstacles the list of SpaceObstacle
     */
    public void update(List<SpaceShip> spaceShips, List<SpaceItem> spaceObstacles){
        this.spaceShips = spaceShips;
        this.spaceObstacles = spaceObstacles;
    }

    /**
     * draw all items in the lists to the canvas
     * @param canvas the place to draw
     */
    public void draw(Canvas canvas){
        for (SpaceShip spaceShip: spaceShips) {
            canvas.drawText("(--)ship(--)", spaceShip.getX(), spaceShip.getY(), shipPaint);
        }
    }
}
