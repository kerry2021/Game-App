package com.example.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText userNameEditText = (EditText) findViewById(R.id.username);
        EditText passWordEditText = (EditText) findViewById(R.id.password);
        Button signUp = (Button) findViewById(R.id.sign_up_button);
        Button login = (Button) findViewById(R.id.login_button);
        TextView errorMsg = (TextView) findViewById(R.id.textView);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String userName = userNameEditText.getText().toString();
                String passWord = passWordEditText.getText().toString();
                if (userName.equals("") || passWord.equals("")) {
                    errorMsg.setText("Incorrect password or Account does not exist");
                } else {
                    User potentialUser = User.getUser(userName, passWord);
                    if (potentialUser == null) {
                        Log.i("info", "error detected");
                        errorMsg.setText("Incorrect password or Account does not exist");
                    } else {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("user", potentialUser);
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivityForResult(new Intent(v.getContext(), SignUpActivity.class), 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("user", data.getSerializableExtra("user"));
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        }
    }
}
