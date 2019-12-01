package com.example.gameproject.reaction_game;

import android.view.View;
import android.widget.ImageButton;

import java.util.HashMap;

public class Moles {
    private ImageButton[] buttons= new ImageButton[9];
    private HashMap<Integer, ImageButton> nextToMole = new HashMap<>();
    private HashMap<ImageButton, Integer> moleToID = new HashMap<>();

    public Moles(ReactionGameActivity reaction, ClickImage clicker){
        for (int i =0; i<9; i++){
            int id = reaction.getResources().getIdentifier("btn_" + (i+1),"id", reaction.getPackageName());
            buttons[i]= reaction.findViewById(id);
            buttons[i].setOnClickListener(clicker);
            nextToMole.put(i + 1,buttons[i]);
            moleToID.put(buttons[i], i + 1);
        }
    }

    public ImageButton getMole(int next){
        return nextToMole.get(next);
    }

    public int getNext(View mole){
        return moleToID.get(mole);
    }

    public ImageButton [] getAllMoles(){
        return buttons;
    }

}
