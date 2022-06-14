package com.example.mymobileapplication;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {
    LayoutInflater mInFlater;
    //String[] items;
    //String[] amounts;
    ArrayList<GroceryItem> groceryItemArrayList;
    Context context;

    public ItemAdapter(Context c, ArrayList<GroceryItem> g) {
        context = c;
        groceryItemArrayList = g;
        mInFlater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return groceryItemArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return groceryItemArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            v = mInFlater.inflate(R.layout.grocery_list_items, null);
        }
        TextView nameTextView = (TextView) v.findViewById(R.id.itemNameTextView);
        if (groceryItemArrayList.get(i).getNumber() > 1) {
            nameTextView.setText(groceryItemArrayList.get(i).getNumber() + " " + groceryItemArrayList.get(i).getName() + "s");
        } else {
            nameTextView.setText(groceryItemArrayList.get(i).getNumber() + " " + groceryItemArrayList.get(i).getName());
        }
        return v;
    }
}
