package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gameproject.reaction_game.reactionGameMain;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText userName = (EditText)findViewById(R.id.username);
        EditText passWord = (EditText)findViewById(R.id.password);
        Button login = (Button)findViewById(R.id.login_button);
        TextView errorMsg = (TextView)findViewById(R.id.textView);

        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
    }
}
