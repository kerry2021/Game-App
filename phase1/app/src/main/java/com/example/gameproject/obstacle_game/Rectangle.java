package com.example.gameproject.obstacle_game;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.gameproject.R;

import androidx.core.content.ContextCompat;

public class Rectangle extends Obstacle{

    Rectangle() {
        super();
        Drawable d = ContextCompat.getDrawable(getContext(), R.drawable.rectangle_view);
        d.setBounds(0, 0, 225, 225);
        setImage(d);
    }

    Rectangle(int x, int y) {
        super(x, y);
        Drawable d = ContextCompat.getDrawable(getContext(), R.drawable.rectangle_view);
        setImage(d);
    }


}
