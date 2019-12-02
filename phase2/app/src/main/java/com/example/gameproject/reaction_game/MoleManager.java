package com.example.gameproject.reaction_game;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gameproject.R;

import java.util.HashMap;
import java.util.Observable;

public class MoleManager extends Observable{

    private Moles moles;
    private ImageButton hitPosition;
    private TimeThread time;
    private MoleThread generateMole;
    private ClickImage clicker;
    private boolean hit;
    private int timer, currStep,running;

    public MoleManager(ReactionGameActivity reaction,int speed){
        timer = 10;//TODO: only for testing, should be changed to 60 when all the bug fixed;
        generateMole = new MoleThread();
        time = new TimeThread();
        clicker = new ClickImage();
        moles = new Moles(reaction,clicker,speed,0);
        generateMole.setRunning(true);
        time.setRunning(true);
        clicker.setMovable(true);
        generateMole.setStep(1);
        generateMole.setActivity(reaction,this, moles);
        time.setActivity(reaction, this);
        clicker.setReaction(this);

    }

    public void updateScreen(int next, int step){
        running = 1;
        moles.generateNextMole(next);
        currStep = step;
        setChanged();
        notifyObservers();
    }

    public void ifHit (View v){
        running = 3;
        if (moles.checkSame(v)) {
            moles.generateScore();
            hit = true;
        }
        else{
            hit = false;
            hitPosition = (ImageButton)v;
        }

        moles.setNext(0);
        setChanged();
        notifyObservers();
    }

    public void updateTime(){
        running = 2;
        setChanged();
        notifyObservers();
        timer--;
    }

    public int getTimer(){
        return this.timer;
    }

    public int getScore(){
        return moles.getScore();
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
        return moles.getNextMole();
    }

    public ImageButton[] getAllMoles(){
        return moles.getAllMoles();
    }

    public void pause(){
        clicker.setMovable(false);
        generateMole.setRunning(false);
        time.setRunning(false);
    }

    public void resume(){
        clicker.setMovable(true);
        generateMole.setRunning(true);
        time.setRunning(true);
    }
    public void start(){
        generateMole.start();
        time.start();
    }

    public void stop(){
        generateMole.interrupt();
        time.interrupt();
        clicker.setMovable(false);
        generateMole.setRunning(false);
        time.setRunning(false);
    }

}
