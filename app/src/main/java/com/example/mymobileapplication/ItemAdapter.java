// Created by: Aino Räkköläinen Last edited: 22.7.2022
// Sources for this code:
// How to create a strikethrough text when checkBox is checked:
// https://stackoverflow.com/questions/9786544/creating-a-strikethrough-text


package com.example.mymobileapplication;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {
    LayoutInflater mInFlater;
    //String[] items;
    //String[] amounts;
    ArrayList<String> items;
    Context context;

    public ItemAdapter(Context c, ArrayList<String> i) {
        context = c;
        items =i;
        mInFlater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            v = mInFlater.inflate(R.layout.my_detailed_grocery_list, null);
        }
        TextView itemTextView = (TextView) v.findViewById(R.id.itemTextView);
        itemTextView.setText(items.get(i));
        CheckBox checkBoxItem = (CheckBox) v.findViewById(R.id.itemCheckBox);
        checkBoxItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    itemTextView.setPaintFlags(itemTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    itemTextView.setPaintFlags(itemTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
            }
        });
        return v;
    }
}
