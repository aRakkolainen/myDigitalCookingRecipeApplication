package com.example.mymobileapplication;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ProfilepictureAdapter extends BaseAdapter {
    LayoutInflater mInFlater;
    Context context;
    String[] profilePictures;
    public ProfilepictureAdapter(Context c, String[] p) {
        context = c;
        profilePictures = p;
        mInFlater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override

    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            v = mInFlater.inflate(R.layout.profile_pictures_list, null);
        }
        //ImageView profilePicture = (ImageView) v.findViewById(R.id.imageViewProfile);
        //int id = (int) getItem(i);
        //System.out.println(id);
        //profilePicture.setImageResource(id);
        return null;
    }
}
