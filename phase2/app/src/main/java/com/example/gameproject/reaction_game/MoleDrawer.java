package com.example.gameproject.reaction_game;

import android.widget.ImageButton;

import com.example.gameproject.R;

import java.util.Observable;
import java.util.Observer;

public class MoleDrawer implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        MoleManager moleManager = (MoleManager) o;

        if (moleManager.watRunning() == 1) {
            if (moleManager.getStep() == 2)
                setMole(moleManager);
            else
                resetScreen(moleManager);
        }
        else if (moleManager.watRunning() == 2){
            setTime(moleManager);
        }
        else{
            if (moleManager.hitMole())
                setHit(moleManager);
            else
                setNotHit(moleManager);
        }

    }

    private void setTime(MoleManager moleManager){
        String ts = "" + moleManager.getTimer();
        moleManager.getT_time().setText(ts);
    }

    private void resetScreen(MoleManager moleManager){
        ImageButton [] buttons = moleManager.getAllMoles();
        for(int i =0;i < 9;i++)
            buttons[i].setBackgroundResource(R.drawable.hole);
    }

    private void setMole(MoleManager moleManager){
        resetScreen(moleManager);
        moleManager.getNextMole().setBackgroundResource(R.drawable.mole);
    }

    private void setHit(MoleManager moleManager){
        moleManager.getNextMole().setBackgroundResource(R.drawable.hit);
        String ts = "" + moleManager.getScore();
        moleManager.getT_score().setText(ts);
    }

    private void setNotHit(MoleManager moleManager){
        resetScreen(moleManager);
        moleManager.getHitPosition().setBackgroundResource(R.drawable.miss);
    }

}
