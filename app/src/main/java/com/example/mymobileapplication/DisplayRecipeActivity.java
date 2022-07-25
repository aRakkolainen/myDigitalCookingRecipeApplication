/*Created by: Aino Räkköläinen Edited: 21.7.2022
* Purpose:
* This activity displays the recipes the user have made in addRecipeActivity.
* Sources:
* How to remove the lines between listView items.
* https://stackoverflow.com/questions/1914477/how-do-i-remove-lines-between-listviews-on-android
* Reading file in android studio is done with the help of this tutorial video:
 * https://www.youtube.com/watch?v=Ir9qeQqw-48
 * A string read from text file is edited with split command according to this website:
 * https://www.geeksforgeeks.org/split-string-java-examples/
 * Making a custom listView with help of Android Studio for Beginners part 3 tutorial video:
 * https://www.youtube.com/watch?v=rdGpT1pIJlw */

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
    String line_ingredients;
    String line_method;
    String[] ingredientsArray;
    String[] methodArray;
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
        //Reading the selected recipe text file
        FileInputStream fileInputStream = null;
        try {
            if (filename != "null.txt") {
                fileInputStream = openFileInput(filename);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader br = new BufferedReader(inputStreamReader);
                recipeTitle = br.readLine();
                line_ingredients = br.readLine();
                line_method = br.readLine();
                ingredientsArray = line_ingredients.split(";");
                methodArray = line_method.split(";");
                for (int i=0; i < ingredientsArray.length; i++) {
                    ingredients.add(ingredientsArray[i]);
                }

                for (int i=0; i < methodArray.length; i++) {
                    methods.add(methodArray[i]);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Setting the adapters for showing the information about the recipe
        IngredientAdapter ingredientAdapter = new IngredientAdapter(getApplicationContext(), ingredients);
        title.setText(recipeTitle);
        ingredientsList.setAdapter(ingredientAdapter);
        if (methods.size() != 0) {
            MethodAdapter methodAdapter = new MethodAdapter(getApplicationContext(), methods);
            methodsList.setAdapter(methodAdapter);
        }
    }
}