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
    private int next;
    int score, timer;
    TextView t_score, t_timer;
    private myThread t;

    ClickImage click;

    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            update();
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_game);

        initButton();
        reInitButton();
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
        image_1 = findViewById(R.id.first);
        image_2 = findViewById(R.id.second);
        image_3 = findViewById(R.id.third);
        image_4 = findViewById(R.id.fourth);
        image_5 = findViewById(R.id.fifth);
        image_6 = findViewById(R.id.sixth);
        image_7 = findViewById(R.id.seventh);
        image_8 = findViewById(R.id.eighth);
        image_9 = findViewById(R.id.ninth);

        click = new ClickImage();
        image_1.setOnClickListener(click);
        image_2.setOnClickListener(click);
        image_3.setOnClickListener(click);
        image_4.setOnClickListener(click);
        image_5.setOnClickListener(click);
        image_6.setOnClickListener(click);
        image_7.setOnClickListener(click);
        image_8.setOnClickListener(click);
        image_9.setOnClickListener(click);
    }
    private void reInitButton(){
        image_1.setBackgroundResource(R.drawable.hole);
        image_2.setBackgroundResource(R.drawable.hole);
        image_3.setBackgroundResource(R.drawable.hole);
        image_4.setBackgroundResource(R.drawable.hole);
        image_5.setBackgroundResource(R.drawable.hole);
        image_6.setBackgroundResource(R.drawable.hole);
        image_7.setBackgroundResource(R.drawable.hole);
        image_8.setBackgroundResource(R.drawable.hole);
        image_9.setBackgroundResource(R.drawable.hole);

    }

    public void update(){
        reInitButton();
        if (next == 1)
            image_1.setBackgroundResource(R.drawable.mole);
        else if (next == 2)
            image_2.setBackgroundResource(R.drawable.mole);
        else if (next == 3)
            image_3.setBackgroundResource(R.drawable.mole);
        else if (next == 4)
            image_4.setBackgroundResource(R.drawable.mole);
        else if (next == 5)
            image_5.setBackgroundResource(R.drawable.mole);
        else if (next == 6)
            image_6.setBackgroundResource(R.drawable.mole);
        else if (next == 7)
            image_7.setBackgroundResource(R.drawable.mole);
        else if (next == 8)
            image_8.setBackgroundResource(R.drawable.mole);
        else if (next == 9)
            image_9.setBackgroundResource(R.drawable.mole);

    }

    class ClickImage implements OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.first:
                    if (next == 1)
                        score += 1;
                case R.id.second:
                    if (next == 2)
                        score += 1;
                case R.id.third:
                    if (next == 3)
                        score += 1;
                case R.id.fourth:
                    if (next == 4)
                        score += 1;
                case R.id.fifth:
                    if (next == 5)
                        score += 1;
                case R.id.sixth:
                    if (next == 6)
                        score += 1;
                case R.id.seventh:
                    if (next == 7)
                        score += 1;
                case R.id.eighth:
                    if (next == 8)
                        score += 1;
                case R.id.ninth:
                    if (next == 9)
                        score += 1;
                    String ts = "" + score;
                    t_score.setText(ts);
            }
        }
    }


    class myThread extends Thread{
        boolean Running;
        public void run(){
            try{
                while (Running) {
                    Thread.sleep(1000);
                    next = (int) (Math.random() * 9) + 1;
                    handler.sendEmptyMessage(1);
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
                                timer--;
                                String t = "" + timer;
                                t_timer.setText(t);
                                if (timer == 0) {
                                    Toast.makeText(reactionGameActivity.this, "Time Up", Toast.LENGTH_SHORT).show();
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
}
