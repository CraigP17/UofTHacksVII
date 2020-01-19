package com.example.uofthacksvii;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FileManager {
    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    private ArrayList<Recipe> recipes;
    private Context activity;

    public FileManager(Context context) {
        recipes = parseData(context);
        this.activity = context;
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

    public Recipe getRecipeByName(String name) {
        for(int i = 0; i < recipes.size(); i++) {
            if(recipes.get(i).getName().equals(name)) {
                return recipes.get(i);
            }
        }
        return null;
    }


    /**Adds a recipe to the users favourite list
     * @param recipe
     */
    //name|time|calories|# of servings| name of drawable | carbohydrates | fats | proteins |
    // list of ingredients | directions | description| link to web| type of cuisine|
    // list of meal type (bkl/ln/dn)
    /*public ArrayList<Recipe> searchByNutrition() {

    }*/
    public void addFavourite(String recipe){
        try {
            //Take the long string containing all users and split into an array list
            //name of recipe|isFavourited|visits|time|calories|carbohydrate|fats|proteins|servings
            String[] multiLine = this.read().split(System.getProperty("line.separator"));

            StringBuilder writeData = new StringBuilder();

            for (String u : multiLine) {
                //System.out.println("Found the users(getUsers)");
                String[] temp = u.split(",");
                if (temp[0].equals(recipe)) {
                    temp[1] = String.valueOf(1);
                    temp[2] = String.valueOf(Integer.parseInt(temp[2]) + 1);
                }

                writeData.append(getHelp(temp)).append("\n");
                //create a user with the information found in the file

            }
            saveFavourites(writeData.toString());
            System.out.println(writeData.toString());

        } catch (NullPointerException e) {
            //Catch placed in case the file does not exist and needs to be written
            System.out.println("Gotta write the file...Done");
            writeFavourites(); //EVENTUALLY this write method will initialize 3 empty users
            //name of recipe|isFavourited|visits|time|calories|carbohydrate|fats|proteins|servings

            String[] multiLine = this.read().split(System.getProperty("line.separator"));

            StringBuilder writeData = new StringBuilder();

            for (String u : multiLine) {
                //System.out.println("Found the users(getUsers)");
                String[] temp = u.split(",");
                if (temp[0].equals(recipe)) {
                    temp[1] = String.valueOf(1);
                    temp[2] = String.valueOf(Integer.parseInt(temp[2]) + 1);
                }

                writeData.append(getHelp(temp)).append("\n");

            }
            saveFavourites(writeData.toString());
            System.out.println(writeData.toString());
        }
    }

    private void saveFavourites(String writeData){
        try {
            OutputStreamWriter outStreamWriter = new OutputStreamWriter(activity.openFileOutput(
                    "favourites.txt", MainActivity.MODE_PRIVATE));

            outStreamWriter.write(writeData);
            outStreamWriter.close();
        } catch (IOException e) {
            Log.e(TAG, "Error encountered trying to open file for writing: " + "favourites.txt");
        }
    }

    private void writeFavourites() {
        //name of recipe|isFavourited|visits|time|calories|carbohydrate|fats|proteins|servings
        PrintWriter out = null;
        //load output stream to example file
        try {
            OutputStream outStream = activity.openFileOutput("favourites.txt", Context.MODE_PRIVATE);
            //create new print writer to send full strings not bytes
            out = new PrintWriter(outStream);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Error encountered trying to open file for writing: " + "favourites.txt");
        }

        out.println("Spaghetti alla Carbonara,0,0,25,336,36,48,4");
        out.println("Chow Fun (Fan),0,0,45,1082,53,18,4");
        out.println("Easy Indian Butter Chicken,0,0,60,880,12.8,26.4,6");
        out.println("Shrimp Pad Thai,0,0,35,286,27.5,12.5,4");
        out.println("The Classic Burger,0,0,30,480,37,23,4");
        out.println("Homemade Mac and Cheese,0,0,50,858,66.7,48.7,4");
        out.println("Mongolian Beef,0,0,2,246,12,52,15");
        out.println("Caprese Salad,0,0,8,248,13,17,16");
        out.println("Beef Tacos,0,0,10,226,16,2,12.65,45");
        out.close();
    }

    /**
     * @return This method returns a 3 line String contains 3 users information in each line
     */
    private String read() {

        String usersStr = "";
        try {
            FileInputStream inputStream = activity.openFileInput("favourites.txt");
            if (inputStream != null) {
                InputStreamReader inputReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputReader);
                String allRecipes;
                StringBuilder stringBuilder = new StringBuilder();

                while ((allRecipes = bufferedReader.readLine()) != null) {
                    stringBuilder.append(allRecipes).append("\n");
                }
                inputStream.close();
                usersStr = stringBuilder.toString();
                return usersStr;
            }

        } catch (FileNotFoundException e) {
            Log.e(TAG, "Error Finding the File: " + "favourites.txt");
        } catch (IOException e) {
            Log.e(TAG, "Error Reading. What happened: " + e.toString());
        }
        return null;
    }

    /**Removes the specified recipe from favourites and increments visits to recipe
     * @param recipe The name of the dish
     */
    public void removeFavourites(String recipe){
        try {
            //Take the long string containing all users and split into an array list
            //name of recipe|isFavourited|visits|time|calories|carbohydrate|fats|proteins|servings
            String[] multiLine = this.read().split(System.getProperty("line.separator"));

            StringBuilder writeData = new StringBuilder();

            for (String u : multiLine) {
                //System.out.println("Found the users(getUsers)");
                String[] temp = u.split(",");
                if (temp[0].equals(recipe)) {
                    temp[1] = String.valueOf(0);
                    temp[2] = String.valueOf(Integer.parseInt(temp[2]) + 1);
                }

                writeData.append(getHelp(temp)).append("\n");
                //create a user with the information found in the file

            }
            saveFavourites(writeData.toString());
            System.out.println(writeData);

        } catch (ArrayIndexOutOfBoundsException e) {
            //Catch placed in case the file does not exist and needs to be written
            System.out.println("Gotta write the file...Done");
            writeFavourites(); //EVENTUALLY this write method will initialize 3 empty users
            //name of recipe|isFavourited|visits|time|calories|carbohydrate|fats|proteins|servings

            String[] multiLine = this.read().split(System.getProperty("line.separator"));

            StringBuilder writeData = new StringBuilder();

            for (String u : multiLine) {
                //System.out.println("Found the users(getUsers)");
                String[] temp = u.split(",");
                if (temp[0].equals(recipe)) {
                    temp[1] = String.valueOf(0);
                    temp[2] = String.valueOf(Integer.parseInt(temp[2]) + 1);
                }

                writeData.append(getHelp(temp)).append("\n");

            }
            saveFavourites(writeData.toString());
            System.out.println(writeData);
        }
    }

    /**
     * @param recipe Name of dish
     * @return String representation of array
     */
    private String getHelp(String[] recipe){
        String temp = "";
        for(String s: recipe){
            temp = temp + s + ",";
        }
        return temp;
    }

    /**This method will increase the number of visits to a recipe. Should only be called if a recipe
     * is not added to favourites
     * @param recipe The name of the recipe chosen
     */
    public void incrementVisits(String recipe){
        try {
            //name of recipe|isFavourited|visits|time|calories|carbohydrate|fats|proteins|servings
            String[] multiLine = this.read().split(System.getProperty("line.separator"));

            StringBuilder writeData = new StringBuilder();

            for (String u : multiLine) {
                //System.out.println("Found the users(getUsers)");
                String[] temp = u.split(",");
                if (temp[0].equals(recipe)) {
                    temp[2] = String.valueOf(Integer.parseInt(temp[2]) + 1);
                }

                writeData.append(getHelp(temp)).append("\n");
                //create a user with the information found in the file

            }
            saveFavourites(writeData.toString());
            System.out.println(writeData);

        } catch (ArrayIndexOutOfBoundsException e) {
            //Catch placed in case the file does not exist and needs to be written
            System.out.println("Gotta write the file...Done");
            writeFavourites(); //EVENTUALLY this write method will initialize 3 empty users
            //name of recipe|isFavourited|visits|time|calories|carbohydrate|fats|proteins|servings

            String[] multiLine = this.read().split(System.getProperty("line.separator"));

            StringBuilder writeData = new StringBuilder();

            for (String u : multiLine) {
                //System.out.println("Found the users(getUsers)");
                String[] temp = u.split(",");
                if (temp[0].equals(recipe)) {
                    temp[2] = String.valueOf(Integer.parseInt(temp[2]) + 1);
                }

                writeData.append(getHelp(temp)).append("\n");

            }
            saveFavourites(writeData.toString());
            System.out.println(writeData);
        }
    }

    /**Boolean which describes whether or not a recipe is a favourite
     * @param recipe the name of the dish
     * @return boolean representing if recipe is a favourite
     */
    public boolean isFavourite(String recipe) {
        try {
            //name of recipe|isFavourited|visits|time|calories|carbohydrate|fats|proteins|servings
            String[] multiLine = this.read().split(System.getProperty("line.separator"));

            for (String u : multiLine) {
                //System.out.println("Found the users(getUsers)");
                String[] temp = u.split(",");
                //If recipe that we are looking for and is a favourite then return true
                if (temp[0].equals(recipe) && Integer.valueOf(temp[1]) == 1) {
                    return true;
                }

            }

        } catch (ArrayIndexOutOfBoundsException e) {
            writeFavourites(); //EVENTUALLY this write method will initialize 3 empty users
            //name of recipe|isFavourited|visits|time|calories|carbohydrate|fats|proteins|servings

            String[] multiLine = this.read().split(System.getProperty("line.separator"));

            for (String u : multiLine) {
                //System.out.println("Found the users(getUsers)");
                String[] temp = u.split(",");
                //If recipe that we are looking for and is a favourite then return true
                if (temp[0].equals(recipe) && Integer.valueOf(temp[1]) == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList Favourites() {
        try {
            //name of recipe|isFavourited|visits|time|calories|carbohydrate|fats|proteins|servings
            String[] multiLine = this.read().split(System.getProperty("line.separator"));

            ArrayList<String> recipes = new ArrayList<>();

            for (String u : multiLine) {
                //System.out.println("Found the users(getUsers)");
                String[] temp = u.split(",");
                //If recipe that we are looking for and is a favourite then return true
                if (Integer.valueOf(temp[2]) >= 2) {
                    recipes.add(temp[0]);
                }

            }
            return recipes;

        } catch (ArrayIndexOutOfBoundsException e) {
            writeFavourites(); //EVENTUALLY this write method will initialize 3 empty users
            //name of recipe|isFavourited|visits|time|calories|carbohydrate|fats|proteins|servings

            String[] multiLine = this.read().split(System.getProperty("line.separator"));
            ArrayList<String> recipes = new ArrayList<>();

            for (String u : multiLine) {
                //System.out.println("Found the users(getUsers)");
                String[] temp = u.split(",");
                //If recipe that we are looking for and is a favourite then return true
                if (Integer.valueOf(temp[2]) >= 2) {
                    recipes.add(temp[0]);
                }
            }
            return recipes;
        }

    }

}
