package com.example.gameproject;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * a user, contains all information on his progress and customizations
 */
public class User {
    /**
     * A map containing information that may be needed for each mini game
     */
    private HashMap<String, String> gameStats;
    /**
     * the file that this user will be stored in(or read from)
     */
    private File file;

    /**
     * Create a new user
     * @param userName the user name
     * @param passWord the password
     */
    User(String userName, String passWord, File file)
    {
        gameStats = new HashMap<String, String>();
        gameStats.put("userName", userName);
        gameStats.put("passWord", passWord);
        gameStats.put("progress", "1");
        this.file = file;
    }

    User(HashMap<String, String> gameStats, File file){
        this.gameStats = gameStats;
        this.file = file;
    }

    /**
     * populates gameStats by inserting new information
     * @param key the key name of the information
     * @param value the content of the information
     */
    public void set(String key, String value){
        gameStats.put(key, value);
    }

    /**
     * translates the contents in this class into a String that can be written to a file
     * @return the encoded text
     */
    private String encode(){
        String outStr = "";
        for (String key : gameStats.keySet()) {
            outStr += key + ":" + gameStats.get(key) + ";";
        }
        return outStr + "\n";
    }

    /**
     * get the value responding to the key stored in this class
     * @param key the key
     * @return
     */
    public String get(String key){
        return gameStats.get(key);
    }

    public void write() {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(encode());
            Log.i("info", encode());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    static User getUser(String userName, String passWord, File file){
        try {
            Log.i("info", "here");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null){
                Log.i("info", line);
                String[] pairs = line.split(";");
                HashMap<String, String> newMap = new HashMap<String, String>();
                for(String pair: pairs){
                    newMap.put(pair.split(":")[0], pair.split(":")[1]);
                }
                if(newMap.get("userName").equals(userName) && newMap.get("passWord").equals(passWord)){
                    return new User(newMap, file);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

