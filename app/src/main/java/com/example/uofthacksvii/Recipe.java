package com.example.uofthacksvii;

import android.graphics.drawable.Drawable;
import android.media.Image;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Recipe {
    private String name;
    private int time;
    private int calories;
    private int servings;
    private String image;
    private float carbs;
    private float fat;
    private float protein;
    private String[] ingredients;
    private String[] instructions;
    private String description;
    private URL url;
    private String cuisine_type;
    private int[] meal_type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getCarbs() {
        return carbs;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getInstructions() {
        return instructions;
    }

    public void setInstructions(String[] instructions) {
        this.instructions = instructions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getCuisine_type() {
        return cuisine_type;
    }

    public void setCuisine_type(String cuisine_type) {
        this.cuisine_type = cuisine_type;
    }

    public int[] getMeal_type() {
        return meal_type;
    }

    public void setMeal_type(int[] meal_type) {
        this.meal_type = meal_type;
    }



    public Recipe(String[] recipe) throws MalformedURLException{
        this.name = recipe[0];
        this.time = Integer.parseInt(recipe[1]);
        this.calories = Integer.parseInt(recipe[2]);
        this.servings = Integer.parseInt(recipe[3]);
        this.image = recipe[4];
        this.carbs = Float.parseFloat(recipe[5]);
        this.fat = Float.parseFloat(recipe[6]);
        this.protein = Float.parseFloat(recipe[7]);

        this.ingredients = recipe[8].split(":");
        this.instructions = recipe[9].split(":");
        this.description = recipe[10];
        this.url = new URL(recipe[11]);
        this.cuisine_type = recipe[12];

        this.meal_type = new int[3];
        meal_type[0] = 0;
        meal_type[1] = 0;
        meal_type[2] = 0;
        String[] meals = recipe[13].split(":");
        if(meals[0] == "breakfast") {
            meal_type[0] = 1;
        }
        if(meals[1] == "lunch") {
            meal_type[1] = 1;
        }
        if(meals[2] == "dinner") {
            meal_type[2] = 1;
        }
    }

}
