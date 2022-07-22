/* Created by: Aino Räkköläinen Edited last: 22.7.2022
* Purpose: This is for the new users to sign up in the application. In this activity,
* the user is asked to fill in email address and password and those are written to the text file.
* The user is given username based on the email address.
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    String[] userInfo;
    Context context;
    String profilePic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_in);
        // Defining our layout components:
        EditText givenEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText givenPassword = (EditText) findViewById(R.id.editTextTextPassword);
        Button signUpBtn = (Button) findViewById(R.id.signUpButton);
        TextView signUpProgressText = (TextView) findViewById(R.id.signUpProgressTextView);
        /*Spinner profilePicturePicker = (Spinner) findViewById(R.id.profilePicturePicker);
        String[] names = {"Choose profile picture", "Option 1", "Option 2", "Option 3"};
        int[] images = {0 , R.drawable.mandarin, R.drawable.icecream, R.drawable.apple};
        context = this;
        ProfilepictureAdapter profilepictureAdapter = new ProfilepictureAdapter(context, names, images);
        profilePicturePicker.setAdapter(profilepictureAdapter);
        profilePicturePicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                profilePic = names[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailAddress = givenEmailAddress.getText().toString();
                userInfo = emailAddress.split("@");
                username = userInfo[0];
                //username = givenUsername.getText().toString();
                password = givenPassword.getText().toString();
                if (password.length() < 4) {
                    signUpProgressText.setText("You need to give longer password!");
                } else {
                    Userprofile newUserprofile = new Userprofile(username, emailAddress, password);
                    String filename=username + ".txt";
                    String userInfo = newUserprofile.getUsername() + ";" + newUserprofile.getEmail() + ";" + newUserprofile.getPassword() + ";" + profilePic;
                    try {
                        FileOutputStream fileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                        fileOutputStream.write(userInfo.getBytes(StandardCharsets.UTF_8));
                        Toast.makeText(context, "Sign up done successfully!", Toast.LENGTH_LONG).show();
                        //signUpProgressText.setText("Sign up done successfully!");
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