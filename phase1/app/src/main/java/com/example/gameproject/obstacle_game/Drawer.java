package com.example.gameproject.obstacle_game;

import java.util.List;

/**
 * an interface for generic drawer that receives updates from backend data and draws on surface T
 * @param <T> the surface to draw
 */
public interface Drawer<T> {
    public void draw(T surface);
    public void update(SpaceShip spaceShip, List<Obstacle> spaceObstacles);
}
