package com.example.gameproject.reaction_game;

import android.widget.Toast;


class TimeThread extends Thread {
    private boolean running;
    private ReactionGameActivity reaction;
    private MoleManager moleManager;
    public void run() {
        while (moleManager.getTimer() >= 0) {
            try {
                reaction.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (running) {
                            moleManager.updateTime();
                        }
                        if (moleManager.getTimer() == -1) {
                            Toast.makeText(reaction, "Time Up", Toast.LENGTH_SHORT).show();
                            reaction.endGame();
                            reaction.onStop();
                        }
                    }
                });
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            } catch (Exception e){
            }
        }
    }
    public void setRunning(boolean setRunning){
        this.running = setRunning;
    }
    public void setActivity(ReactionGameActivity action, MoleManager moleManager){
        this.reaction = action;
        this.moleManager = moleManager;
    }
}
