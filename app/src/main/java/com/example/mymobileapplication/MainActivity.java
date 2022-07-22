/* Basic information about this project:
* Created by: Aino Räkköläinen, Edited last: 3.6.2022
* This activity is mainly used for navigating between different parts of the app.
* Sources for this code:
* How to convert a string to an integer:
* https://www.freecodecamp.org/news/java-string-to-int-how-to-convert-a-string-to-an-integer/
* How to get current time:
* https://iqcode.com/code/other/how-to-get-current-date-time-in-android
* How to compare the current time and use the LocalDateTime object:
* https://developer.android.com/reference/java/time/LocalDateTime
* How to add the icon to text buttons:
* https://www.youtube.com/watch?v=cOeK32o8eB4
*/
package com.example.mymobileapplication;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {
    String username;
    String email;
    LocalDateTime localDateTime;
    String text;
    int pic;
    int profilePic;
    int hour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = this;
        TextView welcomeText = (TextView) findViewById(R.id.welcomeTextView);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        localDateTime = localDateTime.now();

        if (getIntent().hasExtra("username")) {
            username = getIntent().getExtras().getString("username");
            int hour = localDateTime.getHour();
            System.out.println(localDateTime);
            if (hour < 12) {
                text = "Good morning, " + username + "!";
                pic = R.drawable.morning;
                //imageView.setImageResource(R.drawable.morning);
            } else if(hour >= 12 && hour < 17) {
                text = "Good afternoon, " + username + "!";
                pic = R.drawable.lunch;
                //imageView.setImageResource(R.drawable.lunch);
            } else if (hour >= 17 && hour <= 21) {
                text = "Good evening, " + username + "!";
                pic = R.drawable.dinner;
                //imageView.setImageResource(R.drawable.dinner);
            } else if (hour > 21) {
                text = "Good night, " + username + "!";
                pic = R.drawable.moon;
                //imageView.setImageResource(R.drawable.moon);
            }
            else {
                text = "Welcome " + username + "!";
            }
            scaleImg(imageView, pic);
            welcomeText.setText(text);
        }
        
        if (getIntent().hasExtra("email address")) {
            email = getIntent().getExtras().getString("email address");
        }
        if (getIntent().hasExtra("profile pic")) {
            profilePic = Integer.parseInt(getIntent().getExtras().getString("profile pic"));
        }
        // Defining all buttons needed to navigate in this application and opening each activity:

        Button createNewRecipeBtn = (Button) findViewById(R.id.createNewRecipeBtn);
        Button openGroceryListBtn = (Button) findViewById(R.id.openGrogeryListBtn);
        Button searchForRecipesBtn = (Button) findViewById(R.id.searchForRecipesBtn);
        Button openUserprofileBtn = (Button) findViewById(R.id.openProfileBtn);
        Button openSettingsBtn = (Button) findViewById(R.id.openTheSettingsBtn);
        Button logOutBtn = (Button) findViewById(R.id.logOutBtn);
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
                openGroceryListIntent.putExtra("Username", username);
                startActivity(openGroceryListIntent);
            }
        });
        searchForRecipesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchForRecipesIntent = new Intent(context, SearchRecipesActivity.class);
                searchForRecipesIntent.putExtra("Username", username);
                startActivity(searchForRecipesIntent);
            }
        });
        openUserprofileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openUserprofileIntent = new Intent(context, UserprofileActivity.class);
                openUserprofileIntent.putExtra("Username", username);
                openUserprofileIntent.putExtra("Email", email);
                openUserprofileIntent.putExtra("ProfilePic", profilePic);
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

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logOutIntent = new Intent(context, LoginActivity.class);
                startActivity(logOutIntent);
            }
        });
    }
    private void scaleImg(ImageView img, int pic) {
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic, options);
        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();
        if (imgWidth > screenWidth) {
            int ratio = Math.round((float)imgWidth / (float)screenWidth);
            options.inSampleSize = ratio;
        }
        options.inJustDecodeBounds = false;
        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), pic, options);
        img.setImageBitmap(scaledImg);
    }

}