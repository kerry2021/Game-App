package com.example.gameproject.reaction_game;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gameproject.R;

import java.util.HashMap;
import java.util.Observable;

public class MoleManager extends Observable{

    private Moles moles;
    private ImageButton nextMole,hitPosition;
    private TimeThread time;
    private MoleThread generateMole;
    private ClickImage clicker;
    private boolean hit;
    private TextView t_score, t_timer;
    private int next, score, timer, currStep,running;
    protected int refreshTime;

    public MoleManager(ReactionGameActivity reaction,int speed){
        t_score = reaction.findViewById(R.id.score);
        t_timer = reaction.findViewById(R.id.timer);
        score = 0;
        timer = 10;//TODO: only for testing, should be changed when all the bug fixed
        refreshTime = speed;
        generateMole = new MoleThread();
        time = new TimeThread();
        clicker = new ClickImage();
        generateMole.setRunning(true);
        time.setRunning(true);
        clicker.setMovable(true);
        generateMole.setStep(1);
        generateMole.setActivity(reaction,this);
        time.setActivity(reaction, this);
        clicker.setReaction(this);

        moles = new Moles(reaction,clicker);

        t_score.setText("0");

    }

    public void updateScreen(int next, int step){
        running = 1;
        this.next = next;
        nextMole = moles.getMole(next);
        currStep = step;
        setChanged();
        notifyObservers();
    }

    public void ifHit (View v){
        running = 3;
        if (next == moles.getNext(v)) {
            score += ((double)750/refreshTime) * 100;
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
