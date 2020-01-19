package com.example.uofthacksvii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ResultsPage extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    // Instead of FileManager for getting query results
    FileManager file = new FileManager(this);
    ArrayList<Recipe> allRecipe = file.getRecipes();

    // Variables for Query Searches
    private String type;

    private String timeOfDay;
    private int minutes;
    private String cuisineType;
    private String search;

    private String macros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        switch (type) {
            case "time":
                timeOfDay = intent.getStringExtra("dayTime");
                minutes = Integer.parseInt(intent.getStringExtra("minutes"));
                cuisineType = intent.getStringExtra("choice");

                break;
            case "general":
                search = getIntent().getParcelableExtra("search");
                break;
            case "nutrition":
                //TODO
                break;
        }


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

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
