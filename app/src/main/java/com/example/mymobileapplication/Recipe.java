package com.example.mymobileapplication;

import java.util.ArrayList;

public class Recipe {
    String name;
    ArrayList<String> ingredients;
    ArrayList<String> directions;
    Recipe(String n, ArrayList<String> i, ArrayList<String> d) {
        name=n;
        ingredients=i;
        directions=d;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getDirections() {
        return directions;
    }
}
