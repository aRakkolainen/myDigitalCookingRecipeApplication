/*Created by Aino Räkköläinen, edited last: 22.7.2022
* Sources:Android Studio for Beginners Part 3 -video and my course project in other course and from there
* the website https://apktutor.com/custom-listview-with-baseadapter-for-displaying-map-list/
* How to get current time in seconds:
* https://www.codegrepper.com/code-examples/java/java+get+current+time+in+seconds
* How to make the progress info TextView (Did the adding/removing succeed- text to be seen only for a few seconds (Didn't use this, replaced with toast messages!):
* https://stackoverflow.com/questions/17242955/how-to-display-a-textview-for-few-seconds-and-then-make-it-invisible
* How to make listView clickable also with buttons:
* https://stackoverflow.com/questions/40090758/android-listview-item-not-clickable-after-i-add-button
* How to show short messages with toast component:
* https://developer.android.com/guide/topics/ui/notifiers/toasts
* How to go through the content of the hashMap:
* https://stackoverflow.com/questions/8909810/how-to-print-all-key-and-values-from-hashmap-in-android
*/
package com.example.mymobileapplication;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymobileapplication.GroceryItem;
import com.example.mymobileapplication.GroceryList;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GroceryListActivity extends AppCompatActivity {
    String groceryListName;
    String addedItem;
    String removedItem;
    String filename;
    String groceryListFilename;
    String username;
    int amountOfAddedItem;
    int amountOfRemovedItem;
    int duration = Toast.LENGTH_SHORT;
    ListView myGroceryListView;
    CharSequence progress;
    //ArrayList<GroceryItem> groceryList = new ArrayList<>();
    ArrayList<String> groceryListNames = new ArrayList<>();
    ArrayList<GroceryList> groceryLists = new ArrayList<>();
    HashMap<String, GroceryItem> groceryItemHashMap = new HashMap<>();
    GroceryItem item;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        myGroceryListView = (ListView) findViewById(R.id.groceriesList);
        // Defining our buttons and editText-views:
        EditText listName = (EditText) findViewById(R.id.editTextListName);
        Button saveBtn = (Button) findViewById(R.id.saveBtn);
        Button saveTheListBtn = (Button) findViewById(R.id.saveTheListBtn);
        Button clearAllBtn = (Button) findViewById(R.id.clearAllBtn);
        // Adding an item to list components
        EditText itemName = (EditText) findViewById(R.id.editTextItemName);
        Button addItemBtn = (Button) findViewById(R.id.addItemBtn);
        EditText amountOfItems = (EditText) findViewById(R.id.editTextAmountOfItems);
        TextView groceryListNameText = (TextView) findViewById(R.id.groceryListTextView);
        // Removing an item from list components
        //EditText itemToRemove = (EditText) findViewById(R.id.itemToRemove);
        Button removeItemBtn = (Button) findViewById(R.id.removeItemBtn);
        //Updating the list -components:
        Button updateListBtn = (Button) findViewById(R.id.updateTheListBtn);
        // Setting onClickListeners for both buttons:
        // This button is used to save the name for the list
        if (getIntent().hasExtra("Username")) {
            username = getIntent().getExtras().getString("Username");
        }
        saveBtn.setOnClickListener(view -> {
            groceryListName = listName.getText().toString();
            groceryListNameText.setText(groceryListName);
            progress = "Name added successfully.";
            Toast toast = Toast.makeText(getApplicationContext(), progress, duration);
            toast.show();
        });

        saveTheListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GroceryList list = new GroceryList(groceryListName, groceryItemHashMap);
                groceryLists.add(list);
                filename = groceryListName + ".txt";
                groceryListFilename = username + "groceryLists.txt";
                try {
                    FileOutputStream fo = openFileOutput(groceryListFilename, Context.MODE_APPEND);
                    for (int i=0; i < groceryLists.size(); i++) {
                        String groceryListTitle = groceryLists.get(i).getTitle() + "\n";
                        fo.write(groceryListTitle.getBytes(StandardCharsets.UTF_8));
                    }
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    FileOutputStream fileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    //String title = groceryListName;
                    for (HashMap.Entry<String, GroceryItem> groceryItemEntry : groceryItemHashMap.entrySet()) {
                        String itemName = groceryItemEntry.getKey();
                        String itemAmount = String.valueOf(groceryItemEntry.getValue().getNumber());
                        String line = itemAmount + " " + itemName + "\n";
                        fileOutputStream.write(line.getBytes(StandardCharsets.UTF_8));
                    }
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addedItem = itemName.getText().toString();
                try {
                    amountOfAddedItem = parseInt((amountOfItems.getText().toString()));
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "You didn't give integer", duration).show();
                }
                if ( (addedItem.isEmpty() || amountOfAddedItem == 0)) {
                    progress = "Adding failed, you need to add the name/amount of at least 1 item";
                } else {
                    item = new GroceryItem(addedItem, amountOfAddedItem);
                    if (item.getNumber() == 1) {
                        progress ="You added " + item.getNumber() + " " + item.getName() + " successfully in the list." ;
                    } else {
                        progress = "You added " + amountOfAddedItem + " " + addedItem + "s" + " successfully in the list.";
                    }
                    groceryItemHashMap.put(addedItem, item);
                }
                Toast toast = Toast.makeText(getApplicationContext(), progress, duration);
                toast.show();
            }

        });
        removeItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (groceryItemHashMap.size() != 0) {
                    removedItem = itemName.getText().toString();
                    amountOfRemovedItem = parseInt((amountOfItems.getText().toString()));
                    try {
                        if (groceryItemHashMap.get(removedItem).getNumber() == 1) {
                            progress = "You removed " + groceryItemHashMap.get(removedItem).getName() + " successfully from the list.";
                        } else {
                            progress = "You removed " + groceryItemHashMap.get(removedItem).getName() + "s successfully from the list.";
                        }
                        groceryItemHashMap.remove(removedItem);
                    } catch (NullPointerException e) {
                        Toast.makeText(context, "No value!", duration).show();
                    }

                } else {
                    progress = "Your list is empty!";
                }
                Toast toast = Toast.makeText(getApplicationContext(), progress, duration);
                toast.show();
                }
        });

        context=this;
        updateListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemHashMapAdapter itemHashMapAdapter = new ItemHashMapAdapter(context, groceryItemHashMap);
                myGroceryListView.setAdapter(itemHashMapAdapter);
                ((BaseAdapter)myGroceryListView.getAdapter()).notifyDataSetChanged();
                progress = "The list updated successfully.";
                Toast toast = Toast.makeText(getApplicationContext(), progress, duration);
                toast.show();

            }
        });

        clearAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groceryItemHashMap.clear();
                progress = "List cleared successfully.";
                Toast toast = Toast.makeText(getApplicationContext(), progress, duration);
                toast.show();
            }
        });
    }
}