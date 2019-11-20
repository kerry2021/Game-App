package com.example.gameproject.reaction_game;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gameproject.R;

import java.util.HashMap;
import java.util.Observable;

public class MoleManager extends Observable{
    private ImageButton[] buttons= new ImageButton[9];
    private HashMap <Integer, ImageButton> nextToMole = new HashMap<>();
    private HashMap<ImageButton, Integer> moleToNext = new HashMap<>();
    private ImageButton nextMole,hitPosition;
    private TimeThread time;
    private MoleThread mole;
    private ClickImage clicker;
    private boolean hit;
    private TextView t_score, t_timer;
    private int next, score, timer, currStep,running;

    public MoleManager(reactionGameActivity reaction){
        t_score = reaction.findViewById(R.id.score);
        t_timer = reaction.findViewById(R.id.timer);
        score = 0;
        timer = 60;
        mole = new MoleThread();
        time = new TimeThread();
        clicker = new ClickImage();
        mole.setRunning(true);
        time.setRunning(true);
        clicker.setMovable(true);
        mole.setStep(1);
        mole.setActivity(reaction,this);
        time.setActivity(reaction, this);
        clicker.setReaction(this);

        for (int i =0; i<9; i++){
            int id = reaction.getResources().getIdentifier("btn_" + (i+1),"id", reaction.getPackageName());
            buttons[i]= reaction.findViewById(id);
            buttons[i].setOnClickListener(clicker);
            nextToMole.put(i + 1,buttons[i]);
            moleToNext.put(buttons[i], i + 1);
        }

        t_score.setText("0");

    }

    public void updateScreen(int next, int step){
        running = 1;
        this.next = next;
        nextMole = nextToMole.get(next);
        currStep = step;
        setChanged();
        notifyObservers();
    }

    public void ifHit (View v){
        running = 3;
        if (next == moleToNext.get(v)) {
            score += 1;
            hit = true;
        }
        else{
            hit = false;
            hitPosition = (ImageButton)v;
        }

        next = 0;
        setChanged();
        notifyObservers();
    }

    public void updateTime(){
        running = 2;
        setChanged();
        notifyObservers();
        timer--;
    }

    public TextView getT_score(){
        return t_score;
    }

    public int getTimer(){
        return this.timer;
    }

    public TextView getT_time(){
        return this.t_timer;
    }

    public int getScore(){
        return this.score;
    }

    public int watRunning(){
        return running;
    }
    public ImageButton getHitPosition(){
        return this.hitPosition;
    }

    public boolean hitMole(){
        return this.hit;
    }

    public int getStep(){
        return this.currStep;
    }

    public ImageButton getNextMole(){
        return nextMole;
    }

    public ImageButton[] getAllMoles(){
        return buttons;
    }

    public void pause(){
        clicker.setMovable(false);
        mole.setRunning(false);
        time.setRunning(false);
    }

    public void resume(){
        clicker.setMovable(true);
        mole.setRunning(true);
        time.setRunning(true);
    }
    public void start(){
        mole.start();
        time.start();
    }

    public void stop(){
        mole.interrupt();
        time.interrupt();
    }

}
