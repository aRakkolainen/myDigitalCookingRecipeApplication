package com.example.mymobileapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class AddRecipeActivity extends AppCompatActivity {
    String recipeTitle;
    String ingredientName;
    String ingredientAmount;
    String method;
    String filename;
    String recipeFilename;
    String username;
    Ingredient ingredient;
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ArrayList<String> methods = new ArrayList<>();
    ArrayList<Recipe> recipes = new ArrayList<>();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        Button saveChangesBtn = (Button) findViewById(R.id.saveChangesBtn);
        Button previewBtn = (Button) findViewById(R.id.previewBtn);
        Button saveRecipeBtn = (Button) findViewById(R.id.saveRecipeBtn);
        Button clearBtn = (Button) findViewById(R.id.clearBtn);
        EditText addRecipeTitle = (EditText) findViewById(R.id.EditTextTitle);
        EditText addIngredientName = (EditText) findViewById(R.id.editTextIngredientName);
        EditText addIngredientAmount = (EditText) findViewById(R.id.editTextIngredientAmount);
        EditText addMethod = (EditText) findViewById(R.id.editTextMethod);
        TextView recipeTitleTextView = (TextView) findViewById(R.id.titleText);
        TextView ingredientsTitle = (TextView) findViewById(R.id.ingredientsTitle);
        TextView methodTitle = (TextView) findViewById(R.id.Method);
        recipeTitleTextView.setVisibility(View.INVISIBLE);
        ListView ingredientsListView = (ListView) findViewById(R.id.ingredientListView);
        ListView methodsListView = (ListView) findViewById(R.id.methodsListView);
        ingredientsListView.setVisibility(View.INVISIBLE);
        methodsListView.setVisibility(View.INVISIBLE);
        context=this;

        if (getIntent().hasExtra("username")) {
            username = getIntent().getExtras().getString("username");
        }
        ingredientsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Do you want to remove this?", Toast.LENGTH_LONG).show();
            }
        });
        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recipeTitle = addRecipeTitle.getText().toString();
                ingredientName = addIngredientName.getText().toString();
                ingredientAmount = addIngredientAmount.getText().toString();
                method = addMethod.getText().toString();
                //System.out.println("You gave instructions: " + method);
                if (ingredientName.isEmpty() != true && ingredientAmount.isEmpty() != true) {
                    ingredient = new Ingredient(ingredientName, ingredientAmount);
                    ingredients.add(ingredient);
                }
                try {
                    if (method.isEmpty() != true) {
                        methods.add(method);
                    }
                } catch (NullPointerException e) {
                    System.out.println("No value!");
                }
                //ingredients.add(ingredient);
            }
        });
        previewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomAdapter customAdapter = new CustomAdapter(context, ingredients);
                recipeTitleTextView.setVisibility(View.VISIBLE);
                ingredientsTitle.setVisibility(View.VISIBLE);
                methodTitle.setVisibility(View.VISIBLE);
                ingredientsListView.setVisibility(View.VISIBLE);
                if (recipeTitle.isEmpty()) {
                    recipeTitleTextView.setText("Title comes here");
                } else {
                    recipeTitleTextView.setText(recipeTitle);
                }
                ingredientsListView.setAdapter(customAdapter);
                if (methods.size() != 0) {
                    MethodAdapter methodAdapter = new MethodAdapter(context, methods);
                    methodsListView.setVisibility(View.VISIBLE);
                    methodsListView.setAdapter(methodAdapter);
                }
            }
        });
        saveRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Recipe recipe = new Recipe(recipeTitle, ingredients, methods);
                recipes.add(recipe);
                recipeFilename = username + "recipes.txt";
                filename = recipeTitle + ".txt";
                try {
                    FileOutputStream fo = openFileOutput(recipeFilename, Context.MODE_APPEND);
                    for (int i=0; i < recipes.size(); i++) {
                        String recipeName = recipes.get(i).getName() + "\n";
                        fo.write(recipeName.getBytes(StandardCharsets.UTF_8));
                    }
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    FileOutputStream fileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    String title;
                    title = recipe.getName() + "\n";
                    fileOutputStream.write(title.getBytes(StandardCharsets.UTF_8));
                    for (int i=0; i < ingredients.size(); i++) {
                        if (ingredients.get(i).getAmount().isEmpty() != true && ingredients.get(i).getName().isEmpty() != true ) {
                            String ingredient = ingredients.get(i).getAmount().toString() + " " + ingredients.get(i).getName() + ";";
                            fileOutputStream.write(ingredient.getBytes(StandardCharsets.UTF_8));
                        }
                    }
                    String line = "\n";
                    fileOutputStream.write(line.getBytes(StandardCharsets.UTF_8));
                    for (int i=0; i < methods.size(); i++) {
                        if (methods.get(i).isEmpty() != true) {
                            method = methods.get(i) + ";";
                        }
                        fileOutputStream.write(method.getBytes(StandardCharsets.UTF_8));
                    }
                    fileOutputStream.write(line.getBytes(StandardCharsets.UTF_8));
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ingredients.clear();
                methods.clear();
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingredients.clear();
                methods.clear();
                recipeTitleTextView.setVisibility(View.INVISIBLE);
                ingredientsListView.setVisibility(View.INVISIBLE);
                methodsListView.setVisibility(View.INVISIBLE);
                ingredientsTitle.setVisibility(View.INVISIBLE);
                methodTitle.setVisibility(View.INVISIBLE);
            }
        });
    }
}