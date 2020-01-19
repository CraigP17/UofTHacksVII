package com.example.uofthacksvii;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {
    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    private ArrayList<Recipe> recipes;

    public FileManager(Context context) {
        recipes = parseData(context);
    }

    public ArrayList<Recipe> parseData(Context context) {
        ArrayList<Recipe> resultList = new ArrayList<Recipe>();

        InputStream dataStream = context.getResources().openRawResource(R.raw.recipe_data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(dataStream));
        String readLine;
        try {
            while ((readLine = reader.readLine()) != null) {
                String[] row = readLine.split("\t");
                if(row.length == 14) {
                    Recipe recipe = new Recipe(row);
                    resultList.add(recipe);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public ArrayList<Recipe> searchByTimeAvailable(int time, ArrayList<Recipe> recipeList) {
        ArrayList<Recipe> results = new ArrayList<Recipe>();
        for(int i = 0; i < recipeList.size(); i++) {
            if(recipeList.get(i).getTime() <= time) {
                results.add(recipeList.get(i));
            }
        }

        return results;
    }

    public ArrayList<Recipe> searchByCuisine(String cuisine, ArrayList<Recipe> recipeList) {
        ArrayList<Recipe> results = new ArrayList<Recipe>();
        for(int i = 0; i < recipeList.size(); i++) {
            if(recipeList.get(i).getCuisine_type().equals(cuisine)) {
                results.add(recipeList.get(i));
            }
        }
        return results;
    }

    public ArrayList<Recipe> searchByMealType(int type, ArrayList<Recipe> recipeList) {
        ArrayList<Recipe> results = new ArrayList<Recipe>();
        for(int i = 0; i < recipeList.size(); i++) {
            for(int j = 0; j < 3; j++) {
                if(j == type && recipeList.get(i).getMeal_type()[j] == 1) {
                    results.add(recipeList.get(i));
                }
            }
        }

        return results;
    }

    /*public ArrayList<Recipe> searchByNutrition() {

    }*/
}
