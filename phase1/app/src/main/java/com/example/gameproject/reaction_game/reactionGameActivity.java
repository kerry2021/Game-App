package com.example.gameproject.reaction_game;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.R;

import java.util.Random;

public class reactionGameActivity extends AppCompatActivity {
    private int next;
    int score, timer;
    TextView t_score, t_timer;
    private myThread t;
    Button[] buttons = new Button[10];
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
        click = new ClickImage();
        for(int i = 1; i < 10; i++){
            int id = getResources().getIdentifier("btn_" + i, "id", getPackageName());
            buttons[i] = findViewById(id);
            buttons[i].setOnClickListener(click);
        }
    }

    private void reInitButton(){
        for(int i = 1; i < 10; i++){
            buttons[i].setBackgroundResource(R.drawable.hole);
    }}

    public void update(){
        reInitButton();
        buttons[next].setBackgroundResource(R.drawable.mole);
    }

    class ClickImage implements OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.btn_1:
                    if (next == 1)
                        score += 1;
                case R.id.btn_2:
                    if (next == 2)
                        score += 1;
                case R.id.btn_3:
                    if (next == 3)
                        score += 1;
                case R.id.btn_4:
                    if (next == 4)
                        score += 1;
                case R.id.btn_5:
                    if (next == 5)
                        score += 1;
                case R.id.btn_6:
                    if (next == 6)
                        score += 1;
                case R.id.btn_7:
                    if (next == 7)
                        score += 1;
                case R.id.btn_8:
                    if (next == 8)
                        score += 1;
                case R.id.btn_9:
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
