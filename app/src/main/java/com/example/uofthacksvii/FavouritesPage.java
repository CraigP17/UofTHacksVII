package com.example.uofthacksvii;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class FavouritesPage extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private Button addFavButton;
    private TextView t1;
    private TextView t2;
    private TextView t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_page);

        addListenerOnButton();
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView);
        t3 = (TextView) findViewById(R.id.textView);

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
                    case R.id.SignIn:
                        Intent intent1 = new Intent(FavouritesPage.this, RecommendedPage.class);
                        startActivity(intent1);
                        break;
                    case R.id.QRcode:
                        Intent intent2 = new Intent(FavouritesPage.this, NutritionPage.class);
                        startActivity(intent2);
                        break;
                    case R.id.Search:
                        Intent intent3 = new Intent(FavouritesPage.this, FavouritesPage.class);
                        startActivity(intent3);
                        break;

                    default:
                        return true;
                }


                return true;

            }
        });


    }

    public void addListenerOnButton() {
        addFavButton = (Button) findViewById(R.id.button);
        addFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(FavouritesPage.this, RecipeSearch.class);
                FavouritesPage.this.startActivity(myIntent);
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
