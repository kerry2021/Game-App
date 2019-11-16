package com.example.gameproject.obstacle_game;

import android.util.Log;

import java.util.Random;

/**
 * A strategy of auto-generating obstacles
 */
public class ObstacleGenerator implements ItemGenerator<Obstacle> {
    /**
     * The x coordinate that all obstacles would generate (their left side) from.(presumably out of the screen)
     */
    private int generationLine;
    /**
     * the width of the screen
     */
    private int screenWidth;
    /**
     * the height of the screen
     */
    private int screenHeight;
    /**
     * The minimum distance a obstacle can generate from another obstacle
     */
    private int minDistance;
    /**
     * The width of the obstacles we are generating
     */
    private int obstacleWidth;
    /**
     * The height of the obstacles we are generating
     */
    private int obstacleHeight;
    /**
     * whether we have generated the first item or not
     */
    private boolean isFirstItem = true;
    /**
     * The last obstacle generated
     */
    private Obstacle lastObstacle;
    /**
     * the random Generator used
     */
    private Random randGenerator;
    /**
     * 1 - easy
     * 2 - medium
     * 3 - hard
     */
    private int difficulty;

    /**
     * set up the generator by screen width and screen height, the distance and obstacle parameters
     * will be automatically set up
     *
     * @param screenWidth  The screen width
     * @param screenHeight The screen height
     */
    ObstacleGenerator(int screenWidth, int screenHeight, int difficulty) {
        minDistance = screenWidth / difficulty;
        Log.i("info", String.valueOf(screenWidth) + String.valueOf(screenHeight));
        obstacleWidth = screenWidth / 15;
        obstacleHeight = screenHeight / 15;
        generationLine = screenWidth*2;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        randGenerator = new Random();
    }

    public Obstacle checkGeneration() {
        //generate the upper height of the rectangle, then use it to construct a new obstacle
        int randHeight = randGenerator.nextInt(screenHeight - obstacleHeight);
        Obstacle testObstacle = new Obstacle(generationLine, randHeight, generationLine + obstacleWidth, randHeight + obstacleHeight, screenWidth/100);
        if (isFirstItem) {
            lastObstacle = testObstacle;
            isFirstItem = false;
        } else {
            lastObstacle.move();

            if (testObstacle.getX() - lastObstacle.getX() >= minDistance) {
                lastObstacle = testObstacle;
            } else {
                return null;
            }

        }
        return lastObstacle;
    }


}
