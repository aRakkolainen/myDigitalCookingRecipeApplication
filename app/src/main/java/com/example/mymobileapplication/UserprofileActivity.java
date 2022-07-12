/*Created by: Aino Räkköläinen Edited: 11.7.2022
* Sources:
*  How to make custom spinner with images and text:
*  https://www.youtube.com/watch?v=wAOnzE2MjAM
 */

package com.example.mymobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UserprofileActivity extends AppCompatActivity {
    private String userName;
    private String emailAddress;
    private String filename;
    private String line;
    private String recipeTitle;
    int pic;
    int profilePic;
    Spinner profilepictures;
    private String[] profilepictures_img_array;
    private String[] profilepictures_names;
    Context context;
    private ArrayList<String> recipeTitles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        TextView username = (TextView) findViewById(R.id.displayUsername);
        TextView email = (TextView) findViewById(R.id.displayEmail);
        ImageView profilePicture = (ImageView) findViewById(R.id.imageViewProfilePicture);
        Spinner recipes = (Spinner) findViewById(R.id.recipesSpinner);
        profilepictures = (Spinner) findViewById(R.id.profilePictureSpinner);
        //ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>()
        //profilepictures.setAdapter()
        //Resources res = getResources();

        //profilepictures_names = res.getStringArray(R.array.profilepictures);
        String[] names = {"Choose profile picture", "Option 1", "Option 2", "Option 3"};
        int[] images = {0 , R.drawable.mandarin, R.drawable.icecream, R.drawable.pineapple};
        context = this;

        if (getIntent().hasExtra("profilePic")) {
            profilePic = getIntent().getExtras().getInt("profilePic");
        }
        profilePicture.setImageResource(profilePic);
        ProfilepictureAdapter profilepictureAdapter = new ProfilepictureAdapter(context, names, images);
        profilepictures.setAdapter(profilepictureAdapter);
        profilepictures.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                profilePicture.setImageResource(images[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                profilePicture.setImageResource(profilePic);
            }
        });




        if (getIntent().hasExtra("Username") && getIntent().hasExtra("Email")) {
            userName = getIntent().getExtras().getString("Username");
            emailAddress = getIntent().getExtras().getString("Email");
            username.setText(userName);
            email.setText(emailAddress);
        }

        if (getIntent().hasExtra("profilePic")) {
            profilePic = getIntent().getExtras().getInt("profilePic");
        }
        filename = userName + "recipes.txt";
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            recipeTitles.add("Your recipes");
            while ( (line = br.readLine()) != null) {
                recipeTitles.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i=0; i < recipeTitles.size(); i++) {
            System.out.println(recipeTitles.get(i));
        }
        CustomRecipeAdapter customRecipeAdapter = new CustomRecipeAdapter(getApplicationContext(), recipeTitles);
        recipes.setAdapter(customRecipeAdapter);
        recipes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                recipeTitle = item.toString();
                if (item != null && recipeTitle != "Your recipes") {
                    Intent displayRecipeActivityIntent = new Intent(getApplicationContext(), DisplayRecipeActivity.class);
                    displayRecipeActivityIntent.putExtra("recipe title", recipeTitle);
                    startActivity(displayRecipeActivityIntent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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