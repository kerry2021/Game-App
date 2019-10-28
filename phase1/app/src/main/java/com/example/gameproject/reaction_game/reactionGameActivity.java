package com.example.gameproject.reaction_game;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.R;

public class reactionGameActivity extends AppCompatActivity {
    private ImageButton one;
    private ImageButton two;
    private ImageButton three;
    private ImageButton four;
    private ImageButton five;
    private ImageButton six;
    private ImageButton seven;
    private ImageButton eight;
    private ImageButton nine;

    MyClick click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_game);

        initButton();
        reInitButton();
    }
    private void initButton(){
        one = (ImageButton) findViewById(R.id.first);
        two = (ImageButton) findViewById(R.id.second);
        three = (ImageButton) findViewById(R.id.third);
        four = (ImageButton) findViewById(R.id.fourth);
        five = (ImageButton) findViewById(R.id.fifth);
        six = (ImageButton) findViewById(R.id.sixth);
        seven = (ImageButton) findViewById(R.id.seventh);
        eight = (ImageButton) findViewById(R.id.eighth);
        nine = (ImageButton) findViewById(R.id.ninth);

        click = new MyClick();
        one.setOnClickListener(click);
        two.setOnClickListener(click);
        three.setOnClickListener(click);
        four.setOnClickListener(click);
        five.setOnClickListener(click);
        six.setOnClickListener(click);
        seven.setOnClickListener(click);
        eight.setOnClickListener(click);
        nine.setOnClickListener(click);
    }
    private void reInitButton(){
        one.setBackgroundResource(R.drawable.hole);
        two.setBackgroundResource(R.drawable.hole);
        three.setBackgroundResource(R.drawable.hole);
        four.setBackgroundResource(R.drawable.hole);
        five.setBackgroundResource(R.drawable.hole);
        six.setBackgroundResource(R.drawable.hole);
        seven.setBackgroundResource(R.drawable.hole);
        eight.setBackgroundResource(R.drawable.hole);
        nine.setBackgroundResource(R.drawable.hole);

    }
    class MyClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.first:

                case R.id.second:

                case R.id.third:

                case R.id.fourth:

                case R.id.fifth:

                case R.id.sixth:

                case R.id.seventh:

                case R.id.eighth:

                case R.id.ninth:

                default:
                    break;
            }
        }
    }
}
