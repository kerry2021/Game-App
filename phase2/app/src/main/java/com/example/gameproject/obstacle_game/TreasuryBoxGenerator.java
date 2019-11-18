package com.example.gameproject.obstacle_game;

public class TreasuryBoxGenerator extends ObstacleGenerator{
    private int maxBoxes = 3;
    private int currentBoxNum = 0;

    TreasuryBoxGenerator(int width, int height, int maxBoxes){
        super(width, height, width/15, height/15, width/100, 30*10*width/100, 30*10*width/100);
        this.maxBoxes = maxBoxes;
    }

    @Override
    public Obstacle checkGeneration() {
        Obstacle result = super.checkGeneration();
        if(result != null){
            currentBoxNum++;
        }
        if(currentBoxNum > maxBoxes){
            return null;
        }
        else
        {
            return result;
        }
    }
}
