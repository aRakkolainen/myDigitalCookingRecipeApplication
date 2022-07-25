/* Created by: Aino Räkköläinen 22.7.2022
* Purpose:
* for displaying the steps/methods which are those instructions
* in recipe how to cook something in add a recipe Activity,
* displaying the preview of those.
* Sources:
*  Making a custom listView with help of Android Studio for Beginners part 3 tutorial video:
* https://www.youtube.com/watch?v=rdGpT1pIJlw */
package com.example.mymobileapplication;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StepAdapter extends BaseAdapter {
    LayoutInflater mInFlater;
    ArrayList<String> steps;
    Context context;
    public StepAdapter(Context c, ArrayList<String> s) {
        context = c;
        steps=s;
        mInFlater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return steps.size();
    }

    @Override
    public Object getItem(int i) {
        return steps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            v = mInFlater.inflate(R.layout.my_detailed_list_steps_preview, null);
        }
        TextView stepText = (TextView) v.findViewById(R.id.stepsTextView);
        FloatingActionButton removeStepBtn = (FloatingActionButton) v.findViewById(R.id.removeStepBtn);
        removeStepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                steps.remove(i);
                stepText.setText("");
            }
        });
        try {
            if (steps.get(i).isEmpty() != true) {
                String step = i + 1 + ". " + steps.get(i);
                stepText.setText(step);
                //stepText.setTypeface(Typeface.SANS_SERIF);
            }
        } catch (NullPointerException e) {
            System.out.println("You didn't give a value");
        }
        return v;
    }
}