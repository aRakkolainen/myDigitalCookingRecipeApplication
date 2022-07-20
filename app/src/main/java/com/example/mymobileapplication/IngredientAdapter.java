package com.example.mymobileapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class IngredientAdapter extends BaseAdapter {
    LayoutInflater mInFlater;
    ArrayList<String> ingredientArrayList;
    Context context;
    public IngredientAdapter(Context c, ArrayList<String> i) {
        context = c;
        ingredientArrayList=i;
        mInFlater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return ingredientArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return ingredientArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            v = mInFlater.inflate(R.layout.my_detailed_list_ingredients, null);
        }
        TextView ingredientInfo = (TextView) v.findViewById(R.id.ingredientInfoTextView);
        ingredientInfo.setText(ingredientArrayList.get(i));
        return v;
    }
}

