//Created by Aino Räkköläinen, edited last: 8.6.2022
// Sources:Android Studio for Beginners Part 3 -video and my course project in other course and from there
// the website https://apktutor.com/custom-listview-with-baseadapter-for-displaying-map-list/
// How to get current time in seconds:
// https://www.codegrepper.com/code-examples/java/java+get+current+time+in+seconds
// How to make the progress info TextView (Did the adding/removing succeed- text to be seen only for a few seconds:
// https://stackoverflow.com/questions/17242955/how-to-display-a-textview-for-few-seconds-and-then-make-it-invisible

package com.example.mymobileapplication;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GroceryListActivity extends AppCompatActivity {
    String addedItem;
    String progress;
    String removedItem;
    int amountOfAddedItem;
    int amountOfRemovedItem;
    ListView myGroceryListView;
    ArrayList<GroceryItem> groceryList = new ArrayList<>();
    GroceryItem item;
    String[] items;
    String[] amounts;
    Context context;
    //Date currentTime = Calendar.getInstance().getTime();
    //LocalDateTime date = LocalDateTime.now();
    //int currentSeconds = date.toLocalTime().toSecondOfDay();
    //int seconds=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        myGroceryListView = (ListView) findViewById(R.id.groceriesList);
        // Defining our buttons and editText-views:
        // Adding an item to list components
        EditText itemName = (EditText) findViewById(R.id.editTextItemName);
        Button addItemBtn = (Button) findViewById(R.id.addItemBtn);
        EditText amountOfItems = (EditText) findViewById(R.id.editTextAmountOfItems);
        TextView addingItemProcessInfo = (TextView) findViewById(R.id.addingProcessInfo);
        // Removing an item from list components
        //EditText itemToRemove = (EditText) findViewById(R.id.itemToRemove);
        Button removeItemBtn = (Button) findViewById(R.id.removeItemBtn);
        TextView removingItemProcessInfo = (TextView) findViewById(R.id.removingProcessInfo);
        //Updating the list -components:
        Button updateListBtn = (Button) findViewById(R.id.updateTheListBtn);
        TextView updatingProcessInfo = (TextView) findViewById(R.id.updatingProgress);
        // Setting onClickListeners for both buttons:

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addedItem = itemName.getText().toString();
                try {
                    amountOfAddedItem = parseInt((amountOfItems.getText().toString()));
                } catch (NumberFormatException e) {

                }
                if ( (addedItem.isEmpty() == true)) {
                    addingItemProcessInfo.setText("Adding failed, you need to add the name of the item first!");
                } else if (amountOfAddedItem == 0){
                    addingItemProcessInfo.setText("Adding failed, you need to at least 1 item!");
                } else {
                    item = new GroceryItem(addedItem, amountOfAddedItem);
                    if (!groceryList.contains(item)) {
                        if (item.getNumber() == 1) {
                            progress ="You added " + item.getNumber() + " " + item.getName() + " successfully in the list." ;
                            addingItemProcessInfo.setText(progress);
                        } else {
                            progress = "You added " + amountOfAddedItem + " " + addedItem + "s" + " successfully in the list.";
                            addingItemProcessInfo.setText(progress);
                        }
                        groceryList.add(item);
                    } else {
                        progress="Adding failed, " + item.getName() + "is already in the list.";
                        addingItemProcessInfo.setText(progress);
                    }
                }
                addingItemProcessInfo.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        addingItemProcessInfo.setVisibility(View.GONE);
                    }
                }, 3500);
            }

        });
        removeItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (groceryList.size() != 0) {
                    removedItem = itemName.getText().toString();
                    amountOfRemovedItem = parseInt((amountOfItems.getText().toString()));
                    for (int i = 0; i < groceryList.size(); i++) {
                        if (groceryList.get(i).getName().equals(removedItem)) {
                            if (groceryList.get(i).getNumber() == 1) {
                                progress = "You removed " + groceryList.get(i).getName() + " successfully from the list.";
                            } else {
                                progress = "You removed " + groceryList.get(i).getName() + "s successfully from the list.";
                            }
                            groceryList.remove(groceryList.get(i));
                        } else {
                            progress = '"' + removedItem + '"' + " is not in the list.";
                        }
                        removingItemProcessInfo.setText(progress);
                    }
                } else {
                    progress="Your grocery list is empty so there was nothing to remove";
                    removingItemProcessInfo.setText(progress);
                }
                removingItemProcessInfo.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        removingItemProcessInfo.setVisibility(View.GONE);
                    }
                }, 3500);
            }
        });

        context=this;
        updateListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemAdapter itemAdapter = new ItemAdapter(context, groceryList);
                myGroceryListView.setAdapter(itemAdapter);
                updatingProcessInfo.setText("The list updated successfully.");

                updatingProcessInfo.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        updatingProcessInfo.setVisibility(View.GONE);
                    }
                }, 3500);
            }
        });
    }
}