/* Created by: Aino Räkköläinen 22.7.2022
* Purpose: This class is for making grocerylist objects,
* even though the grocery items are actually saved in hashMap.
* This has attributes title and items */
package com.example.mymobileapplication;

import com.example.mymobileapplication.GroceryItem;

import java.util.HashMap;

public class GroceryList {
    private String title;
    private HashMap<String, GroceryItem> items;
    public GroceryList(String t, HashMap<String, GroceryItem> i) {
        title = t;
        items= i;
    }

    public String getTitle() {
        return title;
    }

    public HashMap<String, GroceryItem> getItems() {
        return items;
    }
}
