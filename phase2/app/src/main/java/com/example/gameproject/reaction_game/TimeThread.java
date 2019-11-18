package com.example.gameproject.reaction_game;

import android.app.Activity;
import android.widget.Toast;


class TimeThread extends Thread {
    private boolean running;
    private reactionGameActivity reaction;
    public void run() {
        while (reaction.timer >= 0) {
            try {
                reaction.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (running) {
                            String t = "" + reaction.timer;
                            reaction.t_timer.setText(t);
                            reaction.timer--;
                        }
                        if (reaction.timer == -1) {
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
    public void setActivity(reactionGameActivity action){
        this.reaction = action;
    }
}
