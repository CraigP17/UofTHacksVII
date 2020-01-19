package com.example.uofthacksvii;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
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

import java.io.File;
import java.util.ArrayList;

public class FavouritesPage extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    ListView lst;
    String[] items;
    String[] description;
    Integer[] imgid;

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

        FileManager fm = new FileManager(getApplicationContext());
        ArrayList<String> favs = fm.Favourites();
        items = new String[favs.size()];
        description = new String[favs.size()];
        imgid = new Integer[favs.size()];

        for(int i = 0; i < favs.size(); i++) {
            Recipe recipe = fm.getRecipeByName(favs.get(i));
            items[i] = favs.get(i);
            description[i] = recipe.getDescription();

            Resources resources = getResources();
            String imageName = recipe.getImage();
            int resourceId = resources.getIdentifier(imageName, "drawable", getPackageName());
            imgid[i] = resourceId;
        }

        lst = (ListView) findViewById(R.id.listview);
        customListview clv = new customListview(this,items,description,imgid);
        lst.setAdapter(clv);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(FavouritesPage.this, RecipeActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
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
