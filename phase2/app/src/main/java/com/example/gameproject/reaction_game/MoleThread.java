package com.example.gameproject.reaction_game;


class MoleThread extends Thread {
    private boolean Running;
    private reactionGameActivity reaction;
    private int step;// in order to let this thread remain in next status after clicking pause
    public void run(){
        try{
            while (true) {
                if (Running && step==1) {
                    if (reaction.pause_before) {
                        Thread.sleep(reaction.speed);
                        reaction.pause_before = false;
                    }
                    reaction.handler1.sendEmptyMessage(1);
                    setStep(2);
                    Thread.sleep(750);//time pause for 0.75s, allow the screen to stay in no mole for 0.75s

                }
                if (Running && step==2) {
                    if (reaction.pause_before) {
                        Thread.sleep(750);
                        reaction.pause_before = false;
                    }
                    reaction.next = (int) (Math.random() * 9) + 1;
                    reaction.handler2.sendEmptyMessage(1);
                    if (reaction.random)
                        reaction.speed = (int) (Math.random() * 751) + 250;
                    setStep(1);
                    Thread.sleep(reaction.speed);//time pause for 0.75s, by default, allow the screen to stay in mole for 0.75s
                    reaction.next = 0;
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
    public void setActivity(reactionGameActivity action){
        this.reaction = action;
    }
}
