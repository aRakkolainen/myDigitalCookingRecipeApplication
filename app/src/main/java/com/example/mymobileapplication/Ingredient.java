package com.example.mymobileapplication;

public class Ingredient {
    String name;
    String amount;
    Ingredient(String n, String a) {
        name=n;
        amount=a;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }
}
