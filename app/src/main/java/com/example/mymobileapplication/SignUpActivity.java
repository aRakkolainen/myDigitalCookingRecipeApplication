/* Created by: Aino Räkköläinen Edited last: 22.6.2022
* Sources:
* How to read and write textfile in android is done with help of this tutorial:
* https://www.youtube.com/watch?v=Ir9qeQqw-48
* */

package com.example.mymobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class SignUpActivity extends AppCompatActivity {
    String emailAddress;
    String username;
    String password;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_in);
        // Defining our layout components:

        EditText givenEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText givenUsername = (EditText) findViewById(R.id.editTextTextUsername);
        EditText givenPassword = (EditText) findViewById(R.id.editTextTextPassword);
        Button signUpBtn = (Button) findViewById(R.id.signUpButton);
        TextView signUpProgressText = (TextView) findViewById(R.id.signUpProgressTextView);
        context=this;
        File path = context.getCacheDir();
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailAddress = givenEmailAddress.getText().toString();
                username = givenUsername.getText().toString();
                password = givenPassword.getText().toString();
                if (password.length() < 4) {
                    signUpProgressText.setText("You need to give longer password!");
                } else {
                    Userprofile newUserprofile = new Userprofile(username, emailAddress, password);
                    String filename=username + ".txt";
                    String userInfo = newUserprofile.getUsername() + ";" + newUserprofile.getEmail() + ";" + newUserprofile.getPassword();
                    try {
                        FileOutputStream fileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                        fileOutputStream.write(userInfo.getBytes(StandardCharsets.UTF_8));
                        signUpProgressText.setText("Sign up done successfully!");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}