package com.example.gameproject.reaction_game;

import android.app.Activity;
import android.widget.Toast;

import static com.example.gameproject.reaction_game.reactionGameActivity.t_timer;
import static com.example.gameproject.reaction_game.reactionGameActivity.timer;

class TimeThread extends Thread {
    boolean running;
    reactionGameActivity reaction;
    public void run() {
        while (timer >= 0) {
            try {
                reaction.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (running) {
                            String t = "" + timer;
                            t_timer.setText(t);
                            timer--;
                        }
                        if (timer == -1) {
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
