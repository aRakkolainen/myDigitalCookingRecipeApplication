/*Created by: Aino Räkköläinen 11.7.2022
* Purpose: This is used as a adapter for custom spinner for selecting the profile picture
* Sources:
* How to make custom spinner with images and text (Basically this whole adapter is created
* according to this tutorial video:
* https://www.youtube.com/watch?v=wAOnzE2MjAM */

package com.example.mymobileapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class ProfilepictureAdapter extends ArrayAdapter<String> {
    LayoutInflater mInFlater;
    Context context;
    String[] names;
    int[] images;
    public ProfilepictureAdapter(Context context, String[] names, int[] images){
        super(context, R.layout.profile_pictures, names);
        this.context = context;
        this.names = names;
        this.images = images;
        mInFlater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
   public View getDropDownView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.profile_pictures, null);
            TextView text = (TextView) row.findViewById(R.id.textViewDesc);
            ImageView img = (ImageView) row.findViewById(R.id.imageViewProfile);
            text.setText(names[position]);
            img.setImageResource(images[position]);
            return row;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.profile_pictures, null);
        TextView text = (TextView) row.findViewById(R.id.textViewDesc);
        ImageView img = (ImageView) row.findViewById(R.id.imageViewProfile);
        text.setText(names[position]);
        img.setImageResource(images[position]);
        return row;
    }
}
