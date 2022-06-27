package com.example.mymobileapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomRecipeAdapter extends BaseAdapter {
    LayoutInflater mInFlater;
    ArrayList<String> recipeTitles;
    Context context;
    public CustomRecipeAdapter(Context c, ArrayList<String> r) {
        context = c;
        recipeTitles=r;
        mInFlater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return recipeTitles.size();
    }

    @Override
    public Object getItem(int i) {
        return recipeTitles.get(i);
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
        ingredientInfo.setTextSize(24);
        ingredientInfo.setText(recipeTitles.get(i));

        return v;
    }
}

