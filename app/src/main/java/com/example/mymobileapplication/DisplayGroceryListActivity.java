/* Created by: Aino Räkköläinen 22.7.2022
* Purpose: This activity is used for displaying the recipe
* visually on the screen.
* Sources:
* Reading file in android studio is done with the help of this tutorial video:
* https://www.youtube.com/watch?v=Ir9qeQqw-48
* Making a custom listView with a help of Android Studio for Beginners part 3 tutorial video:
 * https://www.youtube.com/watch?v=rdGpT1pIJlw
 * Fixing a bug:
 * https://stackoverflow.com/questions/28601476/java-lang-nullpointerexception-attempt-to-invoke-virtual-method-int-android-vi*/
package com.example.mymobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DisplayGroceryListActivity extends AppCompatActivity {
    String listTitle;
    String filename;
    String line;
    ArrayList<String> items = new ArrayList<>();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_grocery_list);
        ListView groceryList = (ListView) findViewById(R.id.groceryList);
        TextView title = (TextView) findViewById(R.id.textViewTitle);

        if (getIntent().hasExtra("The title of the list")) {
            listTitle = getIntent().getExtras().getString("The title of the list");
        }
        filename = listTitle + ".txt";
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            while ((line = br.readLine()) != null) {
                items.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        title.setText(listTitle);
        context = this;
        ItemAdapter itemAdapter = new ItemAdapter(context, items);
        groceryList.setAdapter(itemAdapter);
        ((BaseAdapter)groceryList.getAdapter()).notifyDataSetChanged();


    }
}