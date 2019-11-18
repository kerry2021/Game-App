package com.example.gameproject.reaction_game;

import static com.example.gameproject.reaction_game.reactionGameActivity.handler1;
import static com.example.gameproject.reaction_game.reactionGameActivity.handler2;
import static com.example.gameproject.reaction_game.reactionGameActivity.pause_before;
import static com.example.gameproject.reaction_game.reactionGameActivity.random;
import static com.example.gameproject.reaction_game.reactionGameActivity.speed;
import static com.example.gameproject.reaction_game.reactionGameActivity.next;

class MoleThread extends Thread {
    boolean Running;
    int step;// in order to let this thread remain in next status after clicking pause
    public void run(){
        try{
            while (true) {
                if (Running && step==1) {
                    if (pause_before) {
                        Thread.sleep(speed);
                        pause_before = false;
                    }
                    handler1.sendEmptyMessage(1);
                    setStep(2);
                    Thread.sleep(750);//time pause for 0.75s, allow the screen to stay in no mole for 0.75s

                }
                if (Running && step==2) {
                    if (pause_before) {
                        Thread.sleep(750);
                        pause_before = false;
                    }
                    next = (int) (Math.random() * 9) + 1;
                    handler2.sendEmptyMessage(1);
                    if (random)
                        speed = (int) (Math.random() * 751) + 250;
                    setStep(1);
                    Thread.sleep(speed);//time pause for 0.75s, by default, allow the screen to stay in mole for 0.75s
                    next = 0;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public void setRunning(boolean setRunning){
        this.Running = setRunning;
    }
    public void setStep(int step){
        this.step=step;
    }
}
