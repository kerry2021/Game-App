package com.example.gameproject.reaction_game;

import android.content.Context;
import android.util.Log;

import com.example.gameproject.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReactionGameManager {
    public static ReactionGameActivity gameactivity;

    static ReactionGameActivity getGameactivity(){
        return gameactivity;
    }
    void newGame(){
        gameactivity = new ReactionGameActivity();
    }

}
