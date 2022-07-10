package com.example.mymobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
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
    private String[] profilepictures_array;
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
        Spinner profilepictures = (Spinner) findViewById(R.id.profilePictureSpinner);
        //ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>()
        //profilepictures.setAdapter()
        Resources res = getResources();
        profilepictures_array = res.getStringArray(R.array.profilepictures);
        context = getApplicationContext();
        profilepictures.setAdapter(new ArrayAdapter<String>(this, R.layout.profile_pictures_list, profilepictures_array));

        profilepictures.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                switch(item.toString()) {
                    case("mandarin"):
                        pic = R.drawable.profilepicture_mandarin;
                        break;
                    case("apple"):
                        pic = R.drawable.profilepicture_apple;
                        break;
                    case("pineapple"):
                        pic = R.drawable.profilepicture_pineapple;
                        break;
                    case("coffee"):
                        pic = R.drawable.profilepicture_coffee;
                        break;
                    case("ice cream"):
                        pic = R.drawable.profilepicture_icecream;
                        break;
                }
                scaleImg(profilePicture, pic);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //ProfilepictureAdapter profilepictureAdapter = new ProfilepictureAdapter(context, profilepictures_array);
        //profilepictures.setAdapter(profilepictureAdapter);
        if (getIntent().hasExtra("Username") && getIntent().hasExtra("Email")) {
            userName = getIntent().getExtras().getString("Username");
            emailAddress = getIntent().getExtras().getString("Email");
            username.setText(userName);
            email.setText(emailAddress);
        }
        filename = userName + "recipes.txt";
        System.out.println(filename);
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