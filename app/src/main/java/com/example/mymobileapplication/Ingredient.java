/* Created by: Aino Räkköläinen Last edited: 25.7.2022 (Just adding this comment)
* Purpose: Class for making ingredient objects to recipes */
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
