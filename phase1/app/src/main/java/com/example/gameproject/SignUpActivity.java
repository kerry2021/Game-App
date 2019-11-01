package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText userNameEditText = (EditText) findViewById(R.id.username);
        EditText passWordEditText = (EditText) findViewById(R.id.password);
        Button signUp = (Button) findViewById(R.id.sign_up_button);
        TextView errorMsg = (TextView) findViewById(R.id.textView);

        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String userName = userNameEditText.getText().toString();
                String passWord = passWordEditText.getText().toString();
                if (userName.equals("") || passWord.equals("")) {
                    errorMsg.setText("User name or password Cannot be empty");
                } else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("user", new User(userName, passWord));
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }

        });


    }
}
