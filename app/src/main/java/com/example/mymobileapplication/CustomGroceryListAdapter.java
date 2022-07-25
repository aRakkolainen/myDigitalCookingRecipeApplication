/* Created by: Aino Räkköläinen 22.7.2022
* Purpose: Custom adapter for showing the titles of the grocery lists
* the user has added in their profile. This is needed to make custom
* listView according to the Android Studio tutorial video part 3.
* Sources:
* Android Studio for Beginners part 3 tutorial video:
* https://www.youtube.com/watch?v=rdGpT1pIJlw
* Fixing a bug:
* https://stackoverflow.com/questions/28601476/java-lang-nullpointerexception-attempt-to-invoke-virtual-method-int-android-vi*/
package com.example.mymobileapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomGroceryListAdapter extends BaseAdapter {
    LayoutInflater mInFlater;
    ArrayList<String> groceryListTitles;
    Context context;
    public CustomGroceryListAdapter(Context c, ArrayList<String> g) {
        context = c;
        groceryListTitles = g;
        mInFlater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return groceryListTitles.size();
    }

    @Override
    public Object getItem(int i) {
        return groceryListTitles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            v = mInFlater.inflate(R.layout.my_detailed_list_ingredients, null);
        }
        TextView ingredientInfo = (TextView) v.findViewById(R.id.ingredientInfoTextView);
        ingredientInfo.setTextSize(24);
        ingredientInfo.setText(groceryListTitles.get(i));

        return v;
    }
}
