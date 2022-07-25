/* Created by: Aino Räkköläinen Last edited: 25.7.2022
* Purpose: Making recipe objects
* */
package com.example.mymobileapplication;

import java.util.ArrayList;

public class Recipe {
    String name;
    ArrayList<Ingredient> ingredients;
    ArrayList<String> method;
    Recipe(String n, ArrayList<Ingredient> i, ArrayList<String> m) {
        name=n;
        ingredients=i;
        method=m;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getMethod() {
        return method;
    }
}
