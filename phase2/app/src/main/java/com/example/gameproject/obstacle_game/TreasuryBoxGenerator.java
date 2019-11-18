package com.example.gameproject.obstacle_game;

public class TreasuryBoxGenerator extends ObstacleGenerator{
    TreasuryBoxGenerator(int width, int height){
        super(width, height, width/30, height/30, width/75, 30*30*width/75, 2*30*30*width/75);
    }
}
