/* Created by: Aino Räkköläinen Edited last: 25.7.2022
* Purpose: This is for the new users to sign up in the application. In this activity,
* the user is asked to fill in email address and password and those are written to the text file.
* The user is given the username based on the email address.
* Sources:
* How to read and write textfile in android is done with help of this tutorial:
* https://www.youtube.com/watch?v=Ir9qeQqw-48
* How to check if some file already exists used for how to check if some user exists already:
* https://www.codegrepper.com/code-examples/whatever/how+to+check+if+file+exists+in+android+studio
* */

package com.example.mymobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
        setContentView(R.layout.activity_sign_up);
        // Defining our layout components:
        EditText givenEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText givenPassword = (EditText) findViewById(R.id.editTextTextPassword);
        Button signUpBtn = (Button) findViewById(R.id.signUpButton);
        context = this;
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailAddress = givenEmailAddress.getText().toString();
                userInfo = emailAddress.split("@");
                username = userInfo[0];
                password = givenPassword.getText().toString();
                if (password.length() < 4) {
                    Toast.makeText(context, "You need to give longer password!", Toast.LENGTH_LONG).show();
                } else {
                    String filename=username + ".txt";
                    File dir = getFilesDir();
                    File file = new File(dir, filename);
                    if(file.exists()) {
                        Toast.makeText(context, "User with this email already exists, use other email address", Toast.LENGTH_LONG).show();
                    } else {
                        Userprofile newUserprofile = new Userprofile(username, emailAddress, password);
                        String userInfo = newUserprofile.getUsername() + ";" + newUserprofile.getEmail() + ";" + newUserprofile.getPassword() + ";" + profilePic;
                        try {
                            FileOutputStream fileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                            fileOutputStream.write(userInfo.getBytes(StandardCharsets.UTF_8));
                            Toast.makeText(context, "Sign up done successfully!", Toast.LENGTH_LONG).show();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });
    }
}