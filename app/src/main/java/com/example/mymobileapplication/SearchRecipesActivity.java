/* Created by: Aino Räkköläinen Last Edited: 21.7.2022
* Purpose: This is for searching specific recipe from all recipes the user has created.
* Sources:
* How to make the search view work with listview:
* https://www.geeksforgeeks.org/android-searchview-with-example/
* How to make items clickable in listView:
* https://stackoverflow.com/questions/9596663/how-to-make-items-clickable-in-list-view */

package com.example.mymobileapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SearchRecipesActivity extends AppCompatActivity {
    String filename;
    String userName;
    String line;
    String recipeTitle;
    ListView searchResults;
    ArrayAdapter<String> adapter;
    ArrayList<String> recipes = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipes);
        // Making the arrayList about all the recipes the specific user has created
        if (getIntent().hasExtra("Username")) {
            userName = getIntent().getExtras().getString("Username");
            filename = userName + "recipes.txt";
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = openFileInput(filename);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader br = new BufferedReader(inputStreamReader);
                while ( (line = br.readLine()) != null) {
                    recipes.add(line);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i=0; i < recipes.size(); i++) {
            if (recipes.get(i).equals("null")) {
                recipes.remove(i);
            }
        }

        //initialising the listView
        searchResults = (ListView) findViewById(R.id.listViewSearchResults);
        searchResults.isClickable();
        searchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                recipeTitle = item.toString();
                if (item != null) {
                    Intent displayRecipeActivityIntent = new Intent(getApplicationContext(), DisplayRecipeActivity.class);
                    displayRecipeActivityIntent.putExtra("recipe title", recipeTitle);
                    startActivity(displayRecipeActivityIntent);
                }
            }
        });
            //Setting the adapter
            adapter = new ArrayAdapter<>(this,R.layout.recipe_item,recipes);
        searchResults.setAdapter(adapter);
    }
    // This next part is done same way as in this tutorial in GeeksForGeeks website
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu with items using MenuInflator
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        // Initialise menu item search bar with id and take its object
        MenuItem searchViewItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (recipes.contains(s)) {
                    adapter.getFilter().filter(s);
                    searchResults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent displayRecipeActivityIntent = new Intent(getApplicationContext(), DisplayRecipeActivity.class);
                            Object item = adapterView.getItemAtPosition(i);
                            recipeTitle = item.toString();
                            displayRecipeActivityIntent.putExtra("recipe title", recipeTitle);
                            startActivity(displayRecipeActivityIntent);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Recipe not found", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}