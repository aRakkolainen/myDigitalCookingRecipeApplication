/* Created by: Aino Räkköläinen Last Edited: 25.7.2022 (This comment added)
* Purpose: This class is used for making objects of grocery items
* which includes attributes name and number where number refers to the amount of the item
* when it is added to grocery list. Those have basic get- and set- methods as well.*/
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

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
