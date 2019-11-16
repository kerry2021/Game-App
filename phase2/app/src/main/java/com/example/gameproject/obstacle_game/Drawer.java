package com.example.gameproject.obstacle_game;

/**
 * an interface for generic drawer that receives updates from backend data and draws on surface T
 * @param <T> the surface to draw
 */
public interface Drawer<T> {
    public void draw(T surface);
}
