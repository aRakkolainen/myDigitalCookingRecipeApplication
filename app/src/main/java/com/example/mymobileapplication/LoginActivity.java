/* Created by: Aino Räkköläinen Edited: 17.6.2022
* Sources:
* Reading file in android studio is done with the help of this tutorial video:
* https://www.youtube.com/watch?v=Ir9qeQqw-48
* A string read from text file is edited with split command according to this website:
* https://www.geeksforgeeks.org/split-string-java-examples/ */

package com.example.mymobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

public class LoginActivity extends AppCompatActivity {
    Context context;
    String userName;
    String password;
    String email;
    String wantedUsername;
    String wantedPassword;
    String filename;
    byte[] array = new byte[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context=this;
        Button logInBtn = (Button) findViewById(R.id.logInBtn);
        Button signUpBtn = (Button) findViewById(R.id.signUpBtn);
        EditText askedUsername = (EditText) findViewById(R.id.editTextUsername);
        EditText askedPassword = (EditText) findViewById(R.id.editTextPassword);
        TextView loginProgressInfo = (TextView) findViewById(R.id.logInProgressInfo);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = askedUsername.getText().toString();
                filename = userName + ".txt";
                // Reading the file of the user who is trying to log in.
                if(filename.toString() != null && filename.trim() !="") {
                    try {
                        FileInputStream fileInputStream = openFileInput(filename);
                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                        BufferedReader br = new BufferedReader(inputStreamReader);
                        //StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ( (line = br.readLine()) != null) {
                            // Splitting the information about the user with String array and split command:
                            String[] userInfo = line.split(";");
                            wantedPassword = userInfo[2];
                            email = userInfo[1];
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    password = askedPassword.getText().toString();
                    if (password.equals(wantedPassword)) {
                        loginProgressInfo.setText("Login succeeded!");
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("username", userName);
                        intent.putExtra("email address", email);
                        startActivity(intent);
                    } else {
                        loginProgressInfo.setText("Wrong password!");
                    }
                    loginProgressInfo.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            loginProgressInfo.setVisibility(View.GONE);
                        }
                    }, 3500);
                }


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