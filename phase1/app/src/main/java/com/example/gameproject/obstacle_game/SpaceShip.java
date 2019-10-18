package com.example.gameproject.obstacle_game;

public class SpaceShip extends SpaceItem{
    SpaceShip() {
        super(50, AdventureManger.getGrjdWidth() / 2);
    }

    void move() {
        setCoordinate(getX(), getY() + 1);
    }
}
