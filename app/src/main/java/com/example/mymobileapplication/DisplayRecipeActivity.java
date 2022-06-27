/*Created by: Aino Räkköläinen Edited: 27.6.2022
* Functionality of this class/activity:
* This activity displays the recipes the user have made in addRecipeActivity.
* Sources:
* How to remove the lines between listView items.
* https://stackoverflow.com/questions/1914477/how-do-i-remove-lines-between-listviews-on-android */

package com.example.mymobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DisplayRecipeActivity extends AppCompatActivity {
    String recipeTitle;
    String filename;
    String line;
    ArrayList<String> ingredients = new ArrayList<>();
    ArrayList<String> methods = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recipe);
        TextView title = (TextView) findViewById(R.id.titleTextView);
        ListView ingredientsList = (ListView) findViewById(R.id.ingredientsList);
        ListView methodsList = (ListView) findViewById(R.id.methodsList);
        if ( getIntent().hasExtra("recipe title")) {
            recipeTitle = getIntent().getStringExtra("recipe title");
        }
        filename = recipeTitle + ".txt";
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            while ((line = br.readLine()) != null) {
                String[] lines = line.split(";");
                System.out.println(lines[0]);
                //System.out.println(line);
                //System.out.println(lines[0]);



            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CustomAdapter2 ingredientAdapter = new CustomAdapter2(getApplicationContext(), ingredients);
        title.setText(recipeTitle);
        ingredientsList.setAdapter(ingredientAdapter);
        if (methods.size() != 0) {
            CustomAdapter2 customAdapter2 = new CustomAdapter2(getApplicationContext(), methods);
            methodsList.setAdapter(customAdapter2);
        }
    }
}