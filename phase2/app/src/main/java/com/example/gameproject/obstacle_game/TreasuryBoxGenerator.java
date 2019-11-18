package com.example.gameproject.obstacle_game;

public class TreasuryBoxGenerator extends ObstacleGenerator{
    private int maxBoxes = 3;
    private int currentBoxNum = 0;

    TreasuryBoxGenerator(int width, int height, int maxBoxes){
        super(width, height, width/30, height/30, width/90, 30*30*width/75, 2*30*30*width/75);
        this.maxBoxes = maxBoxes;
    }

    @Override
    public Obstacle checkGeneration() {
        Obstacle result = super.checkGeneration();
        if(result != null){
            currentBoxNum ++;
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
