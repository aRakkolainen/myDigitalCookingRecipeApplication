package com.example.mymobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    Context context;
    String userName;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context=this;
        Button logInBtn = (Button) findViewById(R.id.logInBtn);
        Button signUpBtn = (Button) findViewById(R.id.signUpBtn);
        EditText askedUsername = (EditText) findViewById(R.id.editTextUsername);
        EditText askedPassword = (EditText) findViewById(R.id.editTextPassword);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent = new Intent(context, SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });
    }
}