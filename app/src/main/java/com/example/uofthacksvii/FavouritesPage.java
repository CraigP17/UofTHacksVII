package com.example.uofthacksvii;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class FavouritesPage extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    ListView lst;
    String[] items = {"Pizza","Tacos", "Burritos", "Pasta", "Burger", "Salad", "Apple"};
    String[] description = {"This is Pizza","This is Tacos","This is Burritos","This is Pasta",
            "This is Burger","This is Salad", "This is Apple" };
    Integer[] imgid = {R.drawable.ic_access_time_black_24dp,R.drawable.taco,
            R.drawable.ic_access_time_black_24dp, R.drawable.ic_pasta,
            R.drawable.burger, R.drawable.salad, R.drawable.ic_access_time_black_24dp };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_page);

        dl = (DrawerLayout)findViewById(R.id.activity_favourites_page);
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
                        Intent intent = new Intent(FavouritesPage.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.Recommended:
                        Intent intent1 = new Intent(FavouritesPage.this, RecommendedPage.class);
                        startActivity(intent1);
                        break;
                    case R.id.Nutrition:
                        Intent intent2 = new Intent(FavouritesPage.this, NutritionPage.class);
                        startActivity(intent2);
                        break;
                    case R.id.Favourites:
                        Intent intent3 = new Intent(FavouritesPage.this, FavouritesPage.class);
                        startActivity(intent3);
                        break;
                    case R.id.Shopping:
                        Intent intent4 = new Intent(FavouritesPage.this, ShoppingPage.class);
                        startActivity(intent4);
                        break;

                    default:
                        return true;
                }


                return true;

            }
        });

        lst = (ListView) findViewById(R.id.listview);
        customListview clv = new customListview(this,items,description,imgid);
        lst.setAdapter(clv);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
