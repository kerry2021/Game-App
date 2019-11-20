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
    private User user;
    private Context context;
    private static final String SUFFIX = "reaction_data";


    public ReactionGameManager(User user, Context context) {
        this.user = user;
        this.context = context;
    }
    static ReactionGameActivity getGameactivity(){
        return gameactivity;
    }
    void newGame(){
        gameactivity = new ReactionGameActivity();
    }
    void loadGame(){

        String filename = user.get("userName") + SUFFIX;
        try {
            InputStream inputStream = context.openFileInput(filename);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                gameactivity = (ReactionGameActivity) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("ReactionGameActivity", "FileNotFoundException");
            gameactivity = new ReactionGameActivity();

        } catch (IOException e) {
            Log.e("ReactionGameActivity", "IOException");
            gameactivity = new ReactionGameActivity();

        } catch (ClassNotFoundException e) {
            Log.e("ReactionGameActivity", "ClassNotFoundException");
            gameactivity = new ReactionGameActivity();
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
