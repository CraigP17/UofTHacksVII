package com.example.uofthacksvii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class RecommendedPage extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    ListView lst;
    String[] items = {"Homemade Mac and Cheese", "Mongolian Beef", "Caprese Salad", "Chow Fun (Fan)"};
    String[] description = {"This is a nice rich mac and cheese. Serve with a salad for a great meatless dinner. " +
            "Hope you enjoy it.","The best healthy and homemade recipe that is better than " +
            "Chinese takeout and PF Chang's. Make this super easy recipe at home today!",
            "No dish can taste as good with fewer ingredients. Try this quick and simple recipe."
            ,"Definitely a five star recipe. It's quick, easy, and nutritious."};
    Integer[] imgid = {R.drawable.close_up_photography_of_baked_mac_806357,
            R.drawable.mongolian_beef_thumb_323x323,
            R.drawable.caprese, R.drawable.cooked_rice_on_black_ceramic_plate_723198};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_page);

        // Make Navigation View
        dl = (DrawerLayout)findViewById(R.id.activity_recommended_page);
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
                        Intent intent = new Intent(RecommendedPage.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.Recommended:
                        Intent intent1 = new Intent(RecommendedPage.this, RecommendedPage.class);
                        startActivity(intent1);
                        break;
                    case R.id.Nutrition:
                        Intent intent2 = new Intent(RecommendedPage.this, NutritionPage.class);
                        startActivity(intent2);
                        break;
                    case R.id.Favourites:
                        Intent intent3 = new Intent(RecommendedPage.this, FavouritesPage.class);
                        startActivity(intent3);
                        break;
                    case R.id.Shopping:
                        Intent intent4 = new Intent(RecommendedPage.this, ShoppingPage.class);
                        startActivity(intent4);
                        break;

                    default:
                        return true;
                }


                return true;

            }
        });

        lst = (ListView) findViewById(R.id.listview2);
        customListview clv = new customListview(this,items,description,imgid);
        lst.setAdapter(clv);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(RecommendedPage.this, RecipeActivity.class);
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
