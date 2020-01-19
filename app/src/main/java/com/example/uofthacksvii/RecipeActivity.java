package com.example.uofthacksvii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class RecipeActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private TextView instructions;
    private TextView ingredients;
    private TextView title;
    private ImageView image;
    private RatingBar favoriteStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        dl = (DrawerLayout)findViewById(R.id.activity_recipe);
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
                        Intent intent = new Intent(RecipeActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.Recommended:
                        Intent intent1 = new Intent(RecipeActivity.this, RecommendedPage.class);
                        startActivity(intent1);
                        break;
                    case R.id.Nutrition:
                        Intent intent2 = new Intent(RecipeActivity.this, NutritionPage.class);
                        startActivity(intent2);
                        break;
                    case R.id.Favourites:
                        Intent intent3 = new Intent(RecipeActivity.this, FavouritesPage.class);
                        startActivity(intent3);
                        break;
                    case R.id.Shopping:
                        Intent intent4 = new Intent(RecipeActivity.this, ShoppingPage.class);
                        startActivity(intent4);
                        break;

                    default:
                        return true;
                }


                return true;

            }
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        FileManager fm = new FileManager(getApplicationContext());
        Recipe recipe = fm.getRecipeByName(name);

        instructions = (TextView) findViewById(R.id.instructions);
        instructions.setText(recipe.printInstructions());

        ingredients = (TextView) findViewById(R.id.ingredients);
        ingredients.setText(recipe.printIngredients());

        title = (TextView) findViewById(R.id.title);
        title.setText(recipe.getName());

        image = (ImageView) findViewById(R.id.recipeImage);
        /*int imageID = getResources().getIdentifier(recipe.getImage(), "drawable", getPackageName());
        Drawable drawable = getResources().getDrawable(imageID, null);
        image.setImageDrawable(drawable);*/

        favoriteStar = (RatingBar) findViewById(R.id.ratingBar);

    }
}
