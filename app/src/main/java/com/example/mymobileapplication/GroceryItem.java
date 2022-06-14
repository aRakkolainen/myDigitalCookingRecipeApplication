package com.example.mymobileapplication;

public class GroceryItem {
    private String name;
    private int number;

    GroceryItem(String na, int nu) {
        name = na;
        number = nu;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}
