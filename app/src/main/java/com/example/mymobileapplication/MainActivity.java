/* Basic information about this project:
* Made by: Aino Räkköläinen, Edited last: 3.6.2022
* Sources for this code:
* How to convert a string to an integer:
* https://www.freecodecamp.org/news/java-string-to-int-how-to-convert-a-string-to-an-integer/
* How to get current time:
* https://iqcode.com/code/other/how-to-get-current-date-time-in-android
* How to compare the current time and use the LocalDateTime object:
* https://developer.android.com/reference/java/time/LocalDateTime
* How
*/
package com.example.mymobileapplication;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {
    String username;
    String email;
    LocalDateTime localDateTime;
    String text;
    int hour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = this;
        TextView welcomeText = (TextView) findViewById(R.id.welcomeTextView);
        localDateTime = localDateTime.now();
        if (getIntent().hasExtra("username")) {
            username = getIntent().getExtras().getString("username");
            int hour = localDateTime.getHour();
            System.out.println(localDateTime);
            if (hour < 12) {
                text = "Good morning " + username + "!";
            } else if(hour > 12 && hour < 17) {
                text = "Good afternoon " + username + "!";
            } else if (hour > 17) {
                text = "Good night " + username + "!";
            } else {
                text = "Welcome " + username + "!";
            }
            welcomeText.setText(text);
        }
        
        if (getIntent().hasExtra("email address")) {
            email = getIntent().getExtras().getString("email address");
        }
        // Defining all buttons needed to navigate in this application and opening each activity:

        Button createNewRecipeBtn = (Button) findViewById(R.id.createNewRecipeBtn);
        Button openGroceryListBtn = (Button) findViewById(R.id.openGrogeryListBtn);
        Button searchForRecipesBtn = (Button) findViewById(R.id.searchForRecipesBtn);
        Button openUserprofileBtn = (Button) findViewById(R.id.openProfileBtn);
        Button openSettingsBtn = (Button) findViewById(R.id.openTheSettingsBtn);

        createNewRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createRecipeIntent = new Intent(context, AddRecipeActivity.class);
                createRecipeIntent.putExtra("username", username);
                startActivity(createRecipeIntent);
            }
        });
        openGroceryListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGroceryListIntent = new Intent(context, GroceryListActivity.class);
                startActivity(openGroceryListIntent);
            }
        });
        searchForRecipesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchForRecipesIntent = new Intent(context, SearchRecipesActivity.class);
                startActivity(searchForRecipesIntent);
            }
        });
        openUserprofileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openUserprofileIntent = new Intent(context, UserprofileActivity.class);
                openUserprofileIntent.putExtra("Username", username);
                openUserprofileIntent.putExtra("Email", email);
                startActivity(openUserprofileIntent);
            }
        });
        openSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openSettingsIntent = new Intent(context, SettingsActivity.class);
                startActivity(openSettingsIntent);
            }
        });
    }

}