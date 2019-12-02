package com.example.gameproject.reaction_game;

import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gameproject.R;

import java.util.Observable;
import java.util.Observer;

public class MoleDrawer implements Observer {

    private TextView t_score, t_timer;

    public MoleDrawer(ReactionGameActivity reaction){
        super();
        t_score = reaction.findViewById(R.id.score);
        t_timer = reaction.findViewById(R.id.timer);
        t_score.setText("0");
    }
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
        t_timer.setText(ts);
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
        t_score.setText(ts);
    }

    private void setNotHit(MoleManager moleManager){
        resetScreen(moleManager);
        moleManager.getHitPosition().setBackgroundResource(R.drawable.miss);
    }

}
