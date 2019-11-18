package com.example.gameproject.reaction_game;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.MainActivity;
import com.example.gameproject.R;

import java.util.Random;

public class reactionGameActivity extends AppCompatActivity {
    private ImageButton pause_or_resume;;
    protected static ImageButton [] buttons=new ImageButton[9];
    private int speed = reactionCustomize.speed;
    private boolean random = reactionCustomize.random;
    private boolean pause_before;
    protected static int next;
    protected static int score, timer;
    protected static TextView t_score, t_timer;
    private myThread t;
    private timeThread time;
    private boolean pause = false;

    ClickImage click;

    public Handler handler1 = new Handler() {
        public void handleMessage(android.os.Message msg) {
            reInitButton();
        };
    };
    public Handler handler2 = new Handler() {
        public void handleMessage(android.os.Message msg) {
            update();
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_game);

        initButton();
        score = 0;
        timer = 60;
        t_score = findViewById(R.id.score);
        t_timer = findViewById(R.id.timer);
        t = new myThread();
        time = new timeThread();
        t.setRunning(true);
        time.setRunning(true);
        t.setStep(1);
        pause_before = false;
        t_score.setText("0");
    }

    @Override
    protected void onResume(){
        super.onResume();
        pause_or_resume.setBackgroundResource(R.drawable.pause);
        t.start();
        time.start();
    }
    private void initButton(){
        buttons[0] = findViewById(R.id.first);
        buttons[1] = findViewById(R.id.second);
        buttons[2] = findViewById(R.id.third);
        buttons[3] = findViewById(R.id.fourth);
        buttons[4] = findViewById(R.id.fifth);
        buttons[5] = findViewById(R.id.sixth);
        buttons[6] = findViewById(R.id.seventh);
        buttons[7] = findViewById(R.id.eighth);
        buttons[8] = findViewById(R.id.ninth);
        pause_or_resume = findViewById(R.id.pause_or_resume);

        pause_or_resume.setOnClickListener(view -> {//to pause
            if(!pause){
                click.setMovable(false);
                pause_or_resume.setBackgroundResource(R.drawable.resume);
                pause = true;
                t.setRunning(false);
                time.setRunning(false);
                pauseGame();
            }
        });


        click = new ClickImage();
        click.setMovable(true);

        for (int i =0;i < 9;i++)
            buttons[i].setOnClickListener(click);

    }
    private void reInitButton(){
        for(int i =0;i < 9;i++)
            buttons[i].setBackgroundResource(R.drawable.hole);
    }

    public void update(){
        buttons[next-1].setBackgroundResource(R.drawable.mole);
    }


    class myThread extends Thread{
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

    class timeThread extends Thread{
        boolean running;
        public void run() {
            while (timer >= 1) {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (running) {
                                String t = "" + timer;
                                t_timer.setText(t);
                                timer--;
                            }
                            if (timer == 0) {
                                Toast.makeText(reactionGameActivity.this, "Time Up", Toast.LENGTH_SHORT).show();
                                endGame();
                                onStop();
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
    }

    private void pauseGame(){
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View popupView = inflater.inflate(R.layout.pause_and_resume, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        // Taps outside the popup does not dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, false);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        Button exitButton,resumeButton;

        exitButton = popupView.findViewById(R.id.save);
        resumeButton = popupView.findViewById(R.id.resume_reaction);

        exitButton.setOnClickListener(view -> {
            popupWindow.dismiss();
            setContentView(R.layout.activity_reaction_game_main);
        });
        resumeButton.setOnClickListener(view -> {
            popupWindow.dismiss();
            click.setMovable(true);
            pause_or_resume.setBackgroundResource(R.drawable.pause);
            pause_before = true;
            pause = false;
            t.setRunning(true);
            time.setRunning(true);
        });
    }
    private void endGame(){
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View popupView = inflater.inflate(R.layout.reaction_end, null);
        TextView textViewFinalScore = popupView.findViewById(R.id.score_reaction);
        textViewFinalScore.setText(String.valueOf(score));

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        // Taps outside the popup does not dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, false);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        Button exitButton;

        exitButton = popupView.findViewById(R.id.exit_reaction);

        exitButton.setOnClickListener(view -> {
            popupWindow.dismiss();
            startActivity(new Intent(view.getContext(), reactionGameMain.class));
        });
    }
    @Override
    public void onStop(){
        super.onStop();
        t.interrupt();
        time.interrupt();
    }
}
