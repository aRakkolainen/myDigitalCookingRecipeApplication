/* Created by: Aino Räkköläinen Edited: 4.7.2022
* Purpose: This is used for implementing basic login system in this app.
* Sources:
* Reading file in android studio is done with the help of this tutorial video:
* https://www.youtube.com/watch?v=Ir9qeQqw-48
* A string read from text file is edited with split command according to this website:
* https://www.geeksforgeeks.org/split-string-java-examples/
* Operators in Java:
* https://www.w3schools.com/java/java_operators.asp */

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
import android.widget.Toast;

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
    CharSequence text;
    String username;
    String password;
    String logInInfo;
    String wantedEmail;
    String wantedUsername;
    String wantedPassword;
    String filename;
    String[] user;
    int duration = Toast.LENGTH_SHORT;
    String profilePic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context=this;
        Button logInBtn = (Button) findViewById(R.id.logInBtn);
        Button signUpBtn = (Button) findViewById(R.id.signUpBtn);
        EditText askedEmail = (EditText) findViewById(R.id.editTextUsername);
        EditText askedPassword = (EditText) findViewById(R.id.editTextPassword);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logInInfo = askedEmail.getText().toString();
                if (logInInfo.contains("@")) {
                    user = logInInfo.split("@");
                    username = user[0];
                } else {
                    username = logInInfo;
                }
                filename = username + ".txt";
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
                            wantedUsername = userInfo[0];
                            wantedPassword = userInfo[2];
                            wantedEmail = userInfo[1];
                            //profilePic = userInfo[3];

                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(wantedUsername + wantedPassword + wantedEmail + profilePic);
                    password = askedPassword.getText().toString();
                    if (password.equals(wantedPassword) && logInInfo.equals(wantedUsername)) {
                        text = "Login succeeded!";
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("username", username);
                        intent.putExtra("email address", wantedEmail);
                        //intent.putExtra("profile pic", profilePic);
                        startActivity(intent);
                    } else if (password.equals(wantedPassword) && logInInfo.equals(wantedEmail)) {
                        text = "Login succeeded!";
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("username", username);
                        intent.putExtra("email address", wantedEmail);
                        //intent.putExtra("profile pic", profilePic);
                        startActivity(intent);
                    } else {
                        text = "Wrong password or username!";
                    }
                    Toast.makeText(context, text, duration).show();
                }


            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = "Loading the sign up page...";
                Toast.makeText(context.getApplicationContext(), text, duration);
                Intent signUpIntent = new Intent(context, SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });
    }
}