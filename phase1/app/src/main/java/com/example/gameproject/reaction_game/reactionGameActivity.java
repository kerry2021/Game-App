package com.example.gameproject.reaction_game;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.R;

import java.util.Random;

public class reactionGameActivity extends AppCompatActivity {
    private ImageButton image_1;
    private ImageButton image_2;
    private ImageButton image_3;
    private ImageButton image_4;
    private ImageButton image_5;
    private ImageButton image_6;
    private ImageButton image_7;
    private ImageButton image_8;
    private ImageButton image_9;
    private ImageButton [] buttons=new ImageButton[9];
    private int next;
    int score, timer;
    TextView t_score, t_timer;
    private myThread t;

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
        t.setRunning(true);
        t_score.setText("0");
        timer();
    }

    @Override
    protected void onResume(){
        super.onResume();
        t.start();
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

        click = new ClickImage();
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

    class ClickImage implements OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.first) {
                if (next == 1) {
                    score += 1;
                    next = 0;
                }
            }
            else if (id == R.id.second) {
                if (next == 2) {
                    score += 1;
                    next = 0;
                }
            }
            else if (id == R.id.third) {
                if (next == 3) {
                    score += 1;
                    next = 0;
                }
            }
            else if (id == R.id.fourth) {
                if (next == 4) {
                    score += 1;
                    next = 0;
                }
            }
            else if (id == R.id.fifth) {
                if (next == 5) {
                    score += 1;
                    next = 0;
                }
            }
            else if (id == R.id.sixth) {
                if (next == 6) {
                    score += 1;
                    next = 0;
                }
            }
            else if (id == R.id.seventh) {
                if (next == 7) {
                    score += 1;
                    next = 0;
                }
            }
            else if (id == R.id.eighth) {
                if (next == 8) {
                    score += 1;
                    next = 0;
                }
            }
            else if (id == R.id.ninth) {
                if (next == 9) {
                    score += 1;
                    next = 0;
                }
            }
            String ts = "" + score;
            t_score.setText(ts);
            
        }
    }


    class myThread extends Thread{
        boolean Running;
        public void run(){
            try{
                while (Running) {
                    handler1.sendEmptyMessage(1);
                    Thread.sleep(750);//time pause for 0.75s, allow the screen to stay in no mole for 0.75s
                    next = (int) (Math.random() * 9) + 1;
                    handler2.sendEmptyMessage(1);
                    Thread.sleep(750);//time pause for 0.75s, allow the screen to stay in mole for 0.75s
                    next = 0;
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }

        }
        public void setRunning(boolean setRunning){
            this.Running = setRunning;
        }
    }


    public void timer() {
        new Thread() {
            @Override
            public void run() {
                while (timer >= 1) {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String t = "" + timer;
                                t_timer.setText(t);
                                timer--;
                                if (timer == 0) {
                                    Toast.makeText(reactionGameActivity.this, "Time Up", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }.start();
    }

    public void onStop(){
        super.onStop();
        t.interrupt();
    }
}
