package com.example.gameproject.reaction_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.gameproject.R;
import com.example.gameproject.User;

public class ReactionCustomize extends AppCompatActivity {
    protected static int speed = 750;
    protected static boolean random = false;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = ReactionGameMain.currentUser;

        setContentView(R.layout.activity_reaction_customize);
        Button confirmButton;
        Spinner spinner = (Spinner) findViewById(R.id.speed);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.moleSpeed, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        confirmButton = (Button) findViewById(R.id.confirm);
        confirmButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String getSpeed = spinner.getSelectedItem().toString();
                if (getSpeed.equals("0.25 s/mole")) {
                    random = false;
                    speed = 250;
                    user.set("reaction_game_speed", String.valueOf(250));
                }
                else if (getSpeed.equals("0.5 s/mole")) {
                    random = false;
                    speed = 500;
                    user.set("reaction_game_speed", String.valueOf(500));

                }
                else if (getSpeed.equals("1 s/mole")) {
                    random = false;
                    speed = 1000;
                    user.set("reaction_game_speed", String.valueOf(1000));

                }
                else
                    random = true;

                finish();
            }
        });


    }


}
