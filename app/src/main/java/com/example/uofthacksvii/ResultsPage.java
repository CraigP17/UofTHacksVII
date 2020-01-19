package com.example.uofthacksvii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ResultsPage extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    ArrayList<Recipe> allRecipe;
    ListView lst;
    TextView searched;

    // Variables for Query Searches
    private String type;

    private String timeOfDay;
    private int minutes;
    private String cuisineType;
    private String search;

    private String macros;

    private String[] names;
    private String[] times;
    private Integer[] images;
    private Integer[] imagery = {R.drawable.ic_stars_24px,R.drawable.ic_stars_24px,
            R.drawable.ic_stars_24px,R.drawable.ic_stars_24px,R.drawable.ic_stars_24px,
            R.drawable.ic_stars_24px,R.drawable.ic_stars_24px,R.drawable.ic_stars_24px,
            R.drawable.ic_stars_24px,R.drawable.ic_stars_24px};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);

        // Instead of FileManager for getting query results
        FileManager file = new FileManager(getApplicationContext());
        allRecipe = file.getRecipes();

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        switch (type) {
            case "time":
                timeOfDay = intent.getStringExtra("dayTime");
                minutes = Integer.parseInt(intent.getStringExtra("minutes"));
                cuisineType = intent.getStringExtra("choice");
                allRecipe = file.searchByTimeAvailable(minutes, allRecipe);
                if (!cuisineType.equals("ANYTHING")) {
                    allRecipe = file.searchByCuisine(cuisineType.toLowerCase(), allRecipe);
                }
                break;
            case "general":
                search = getIntent().getStringExtra("service");
                System.out.println(search);
                break;
            case "nutrition":
                //TODO
                break;
        }
        names = getTitles(allRecipe);
        times = getTimes(allRecipe);
        images = getImages(allRecipe);

        dl = (DrawerLayout)findViewById(R.id.activity_results_page);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.Home:
                        Intent intent = new Intent(ResultsPage.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.Recommended:
                        Intent intent1 = new Intent(ResultsPage.this, RecommendedPage.class);
                        startActivity(intent1);
                        break;
                    case R.id.Nutrition:
                        Intent intent2 = new Intent(ResultsPage.this, NutritionPage.class);
                        startActivity(intent2);
                        break;
                    case R.id.Favourites:
                        Intent intent3 = new Intent(ResultsPage.this, FavouritesPage.class);
                        startActivity(intent3);
                        break;
                    case R.id.Shopping:
                        Intent intent4 = new Intent(ResultsPage.this, ShoppingPage.class);
                        startActivity(intent4);
                        break;

                    default:
                        return true;
                }

                return true;

            }
        });

        lst = (ListView) findViewById(R.id.resultList);
        customListview clv = new customListview(this,names,times,imagery);
        lst.setAdapter(clv);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(ResultsPage.this, RecipeActivity.class);
                System.out.println(name);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        searched = (TextView) findViewById(R.id.searches);
        String txt = cuisineType + " under " + minutes + "mins";
        searched.setText(txt);
        System.out.println(search);

    }


    public String[] getTitles(ArrayList<Recipe> recipes) {
        int count = recipes.size();
        String[] names = new String[count];
        for (int i = 0; i < count; i++) {
            names[i] = recipes.get(i).getName();
        }
        return names;
    }

    public String[] getTimes(ArrayList<Recipe> recipes) {
        int count = recipes.size();
        String[] times = new String[count];
        for (int i = 0; i < count; i++) {
            times[i] = "Under: " +  String.valueOf(recipes.get(i).getTime()) + " mins";
        }
        return times;
    }

    public Integer[] getImages(ArrayList<Recipe> recipes) {
        int count = recipes.size();
        Integer[] images = new Integer[count];
        for (int i = 0; i < count; i++) {
            images[i] = getResources().getIdentifier(recipes.get(i).getImage(), "drawable", getPackageName());
        }
        return images;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
