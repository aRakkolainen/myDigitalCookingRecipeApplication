/*Created by: Aino Räkköläinen Edited: 11.7.2022
* Sources:
*  How to make custom spinner with images and text:
*  https://www.youtube.com/watch?v=wAOnzE2MjAM
* How to read and write textfile in android is done with help of this tutorial:
* https://www.youtube.com/watch?v=Ir9qeQqw-48
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
    private String filenameRecipes;
    private String filenameGroceryLists;
    private String line;
    private String recipeTitle;
    int profilePic;
    Spinner profilepictures;
    Context context;
    private ArrayList<String> recipeTitles = new ArrayList<>();
    private ArrayList<String> groceryListTitles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        TextView username = (TextView) findViewById(R.id.displayUsername);
        TextView email = (TextView) findViewById(R.id.displayEmail);
        ImageView profilePicture = (ImageView) findViewById(R.id.imageViewProfilePicture);
        Spinner recipes = (Spinner) findViewById(R.id.recipesSpinner);
        Spinner groceryLists = (Spinner) findViewById(R.id.groceryListSpinner);
        profilepictures = (Spinner) findViewById(R.id.profilePictureSpinner);
        //ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>()
        //profilepictures.setAdapter()
        //Resources res = getResources();

        String[] names = {"Choose profile picture", "Option 1", "Option 2", "Option 3"};
        int[] images = {0 , R.drawable.mandarin, R.drawable.icecream, R.drawable.apple};
        context = this;

        /*if (getIntent().hasExtra("profilePic")) {
            profilePic = getIntent().getExtras().getInt("profilePic");
        }*/
        //profilePicture.setImageResource(profilePic);
        ProfilepictureAdapter profilepictureAdapter = new ProfilepictureAdapter(context, names, images);
        profilepictures.setAdapter(profilepictureAdapter);
        profilepictures.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                scaleImg(profilePicture, images[i]);
                //profilePicture.setImageResource(images[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //profilePicture.setImageResource(profilePic);
            }
        });

        // Testing if the username and email is received correctly from main activity
        if (getIntent().hasExtra("Username") && getIntent().hasExtra("Email")) {
            userName = getIntent().getExtras().getString("Username");
            emailAddress = getIntent().getExtras().getString("Email");
            username.setText(userName);
            email.setText(emailAddress);
        }

        /*if (getIntent().hasExtra("profilePic")) {
            profilePic = getIntent().getExtras().getInt("profilePic");
        }*/

        // Reading the titles of the recipes the user has created from the text file
        filenameRecipes = userName + "recipes.txt";
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(filenameRecipes);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            recipeTitles.add("Your recipes");
            while ( (line = br.readLine()) != null) {
                recipeTitles.add(line);
            }
            fileInputStream.close();
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Deleting accident empty recipe
        for (int i=0; i < recipeTitles.size(); i++) {
            if (recipeTitles.get(i).equals("null")) {
                recipeTitles.remove(i);
            }
        }
        // Setting up the custom recipe adapter for showing the recipeTitles list in Spinner
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

        // Here we could also show with similar spinner thing the list of grocery list
        // the user has created and there the user could open the specific grocery list.
        filenameGroceryLists = userName + "groceryLists.txt";
        FileInputStream fileIStream = null;
        try {
            fileIStream= openFileInput(filenameGroceryLists);
            InputStreamReader inputStreamReader = new InputStreamReader(fileIStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            groceryListTitles.add("Your grocery lists");
            while ( (line = bufferedReader.readLine()) != null) {
                groceryListTitles.add(line);
            }
            fileIStream.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CustomGroceryListAdapter customGroceryListAdapter = new CustomGroceryListAdapter(context, groceryListTitles);
        groceryLists.setAdapter(customGroceryListAdapter);
        groceryLists.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                String groceryListTitle = item.toString();
                if ( item != null && groceryListTitle != "Your grocery lists") {
                    Intent displayGroceryListIntent = new Intent(getApplicationContext(), DisplayGroceryListActivity.class);
                    displayGroceryListIntent.putExtra("The title of the list", groceryListTitle);
                    startActivity(displayGroceryListIntent);
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