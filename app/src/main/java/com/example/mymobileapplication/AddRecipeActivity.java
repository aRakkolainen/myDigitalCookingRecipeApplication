package com.example.mymobileapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddRecipeActivity extends AppCompatActivity {
    String recipeTitle;
    String ingredientName;
    String ingredientAmount;
    String method;
    Ingredient ingredient;
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ArrayList<String> methods = new ArrayList<>();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        Button saveChangesBtn = (Button) findViewById(R.id.saveChangesBtn);
        Button previewBtn = (Button) findViewById(R.id.previewBtn);
        EditText addRecipeTitle = (EditText) findViewById(R.id.EditTextTitle);
        EditText addIngredientName = (EditText) findViewById(R.id.editTextIngredientName);
        EditText addIngredientAmount = (EditText) findViewById(R.id.editTextIngredientAmount);
        EditText addMethod = (EditText) findViewById(R.id.editTextMethod);
        TextView recipeTitleTextView = (TextView) findViewById(R.id.titleText);
        ListView ingredientsListView = (ListView) findViewById(R.id.ingredientListView);
        ListView methodsListView = (ListView) findViewById(R.id.methodsListView);
        context=this;

        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recipeTitle = addRecipeTitle.getText().toString();
                ingredientName = addIngredientName.getText().toString();
                ingredientAmount = addIngredientAmount.getText().toString();
                ingredient = new Ingredient(ingredientName, ingredientAmount);
                method = addMethod.getText().toString();
                try {
                    methods.add(method);
                } catch (NullPointerException e) {
                    System.out.println("No value!");
                }
                ingredients.add(ingredient);
            }
        });
        previewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomAdapter customAdapter = new CustomAdapter(context, ingredients);
                recipeTitleTextView.setText(recipeTitle);
                ingredientsListView.setAdapter(customAdapter);
                if (methods.size() != 0) {
                    CustomAdapter2 customAdapter2 = new CustomAdapter2(context, methods);
                    methodsListView.setAdapter(customAdapter2);
                }
            }
        });

        /*saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Custom = new IngredientAdapter(context, ingredients);
                ingredientsListView.setAdapter(ingredientAdapter);
            }
        });*/
    }
}