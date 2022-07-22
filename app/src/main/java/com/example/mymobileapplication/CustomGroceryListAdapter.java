/* Created by: Aino Räkköläinen 22.7.2022*/
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
