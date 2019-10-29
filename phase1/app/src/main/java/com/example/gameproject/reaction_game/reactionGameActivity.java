package com.example.gameproject.reaction_game;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameproject.R;

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

    ClickImage click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_game);

        initButton();
        reInitButton();
    }
    private void initButton(){
        image_1 = (ImageButton) findViewById(R.id.first);
        image_2 = (ImageButton) findViewById(R.id.second);
        image_3 = (ImageButton) findViewById(R.id.third);
        image_4 = (ImageButton) findViewById(R.id.fourth);
        image_5 = (ImageButton) findViewById(R.id.fifth);
        image_6 = (ImageButton) findViewById(R.id.sixth);
        image_7 = (ImageButton) findViewById(R.id.seventh);
        image_8 = (ImageButton) findViewById(R.id.eighth);
        image_9 = (ImageButton) findViewById(R.id.ninth);

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
    class ClickImage implements View.OnClickListener {

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
