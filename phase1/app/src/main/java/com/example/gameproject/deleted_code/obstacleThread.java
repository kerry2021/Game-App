package com.example.gameproject.deleted_code;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.gameproject.GamePanel;

/*
 * The main thread that handles games
 * a sightly tweaked version of code found on https://www.youtube.com/watch?v=OojQitoAEXs&t=1234s
 */

/**
 * This is the Thread copy from FishTank's MainThread.
 * The Thread you did before is below.
 */

/*
public class obstacleThread extends Thread {

    public static Canvas canvas;

    private obstacleGamePanel gamePanel;

    private SurfaceHolder surfaceHolder;

    private boolean isRunning;

    public obstacleThread(SurfaceHolder surfaceHolder, obstacleGamePanel view) {
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = view;
    }

    @Override
    public void run() {
        while (isRunning) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
*/
/**
 * This is the Thread you copy from the internet. I don't know why it couldn't run now, try to figure it out.
 */

/*
public class obstacleThread extends Thread {
    public static final int MAX_FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private obstacleGamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public obstacleThread(SurfaceHolder surfaceHolder, obstacleGamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        long startTime;
        long timeMills = 1000 / MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000 / MAX_FPS;

        while (running) {
            startTime = System.nanoTime();
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            timeMills = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMills;
            try {
                if (waitTime > 0) {
                    this.sleep(waitTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == MAX_FPS) {
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
            }
            System.out.println(averageFPS);
        }
    }
}

*/
