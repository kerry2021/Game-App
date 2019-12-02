package com.example.gameproject.reaction_game;

import android.view.View;
import android.widget.ImageButton;

import java.util.HashMap;

public class Moles {
    private final int daultSpeed = 750;
    private ImageButton[] buttons= new ImageButton[9];
    private HashMap<Integer, ImageButton> nextToMole = new HashMap<>();
    private HashMap<ImageButton, Integer> moleToID = new HashMap<>();
    private ImageButton nextMole;
    private int refreshTime;
    private int score,next;


    public Moles(ReactionGameActivity reaction, ClickImage clicker, int refreshTime, int score){
        this.refreshTime = refreshTime;
        this.score = score;
        for (int i =0; i<9; i++){
            int id = reaction.getResources().getIdentifier("btn_" + (i+1),"id", reaction.getPackageName());
            buttons[i]= reaction.findViewById(id);
            buttons[i].setOnClickListener(clicker);
            nextToMole.put(i + 1,buttons[i]);
            moleToID.put(buttons[i], i + 1);
        }
    }

    public ImageButton [] getAllMoles(){
        return buttons;
    }

    public void generateScore(){
        score += ((double)daultSpeed/getRefreshTime()) * 100;
    }

    public void generateRefreshTime(){
        refreshTime = (int) (Math.random() * 751) + 250;
    }

    public boolean checkSame(View v){
        return this.next == moleToID.get(v);
    }

    public void setNext(int next){
        this.next = next;
    }

    public void generateNextMole(int next){
        this.next = next;
        nextMole = nextToMole.get(next);
    }

    public int getRefreshTime(){
        return refreshTime;
    }

    public int getScore(){
        return score;
    }

    public ImageButton getNextMole(){
        return nextMole;
    }

}
