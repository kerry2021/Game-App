package com.example.gameproject.reaction_game;

import android.content.Context;
import android.util.Log;

import com.example.gameproject.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class reactionGameManager {
    public static reactionGameActivity gameactivity;
    private User user;
    private Context context;
    private static final String SUFFIX = "reaction_data";

    public reactionGameManager(User user, Context context) {
        this.user = user;
        this.context = context;
    }
    void newGame(){
        gameactivity = new reactionGameActivity();
    }
    void loadGame(){

        String filename = user.get("userName") + SUFFIX;
        try {
            InputStream inputStream = context.openFileInput(filename);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                gameactivity = (reactionGameActivity) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("reactionGameActivity", "FileNotFoundException");
            gameactivity = new reactionGameActivity();

        } catch (IOException e) {
            Log.e("reactionGameActivity", "IOException");
            gameactivity = new reactionGameActivity();

        } catch (ClassNotFoundException e) {
            Log.e("reactionGameActivity", "ClassNotFoundException");
            gameactivity = new reactionGameActivity();
        }
    }

    void saveGame(){
        String filename = user.get("userName") + SUFFIX;
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(filename, context.MODE_PRIVATE));
            outputStream.writeObject(gameactivity);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
