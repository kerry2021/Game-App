package com.example.gameproject.obstacle_game;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gameproject.R;

public class obstacleGameRulesActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView image;
    private boolean first_page = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obstacle_game_rules);
        textView = findViewById(R.id.textView);
        image = findViewById(R.id.imageView);
        Button button = findViewById(R.id.next_page);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                first_page = !first_page;
                if(first_page){
                    textView.setText("1");
                }
            }
        });
    }
}
