package com.example.uofthacksvii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import static android.graphics.Color.DKGRAY;
import static android.graphics.Color.LTGRAY;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    /** The spinner menu items. */
    public final static String NO_CHOICE = "Any";
    public final static String THAI = "Thai";
    public final static String ITALIAN = "Italian";
    public final static String AMERICAN = "American";
    public final static String CHINESE = "Chinese";
    public final static String INDIAN = "Indian";

    private Button breakfast;
    private Button lunch;
    private Button dinner;

    private TextView slider;
    private SeekBar seekBar;
    private String type;

    public String dayTime;

    /**
     * The list of choices for the menu choices spinner
     */
    private final String[] menuItems = {NO_CHOICE, THAI, ITALIAN, AMERICAN, CHINESE, INDIAN};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        breakfast = findViewById(R.id.breakfastBtn);
        lunch = findViewById(R.id.lunchBtn);
        dinner = findViewById(R.id.dinnerBtn);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        slider = (TextView) findViewById(R.id.timeSlider);
        seekBar.setMin(0);
        seekBar.setMax(120);
        seekBar.setProgress(60);
        // perform seek bar change listener event used for getting the progress value
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                type = String.valueOf(progressChangedValue);
                slider.setText(type);
            }
        });



        dl = (DrawerLayout)findViewById(R.id.activity_main);
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
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.Recommended:
                        Intent intent1 = new Intent(MainActivity.this, RecommendedPage.class);
                        startActivity(intent1);
                        break;
                    case R.id.Nutrition:
                        Intent intent2 = new Intent(MainActivity.this, NutritionPage.class);
                        startActivity(intent2);
                        break;
                    case R.id.Favourites:
                        Intent intent3 = new Intent(MainActivity.this, FavouritesPage.class);
                        startActivity(intent3);
                        break;

                    default:
                        return true;
                }


                return true;

            }
        });

        BottomNavigationView bottNav = findViewById(R.id.bottom_nav);
        bottNav.setOnNavigationItemSelectedListener(navListener);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, menuItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    /**
     * Sets colors for 3 buttons.
     *
     * @param btn1   a button
     * @param colour1 color for the first button
     * @param btn2   a button
     * @param colour2 color for second button
     * @param btn3   a button
     * @param colour3 color for third button
     */
    private void setBtnColour(Button btn1, int colour1, Button btn2, int colour2, Button btn3, int colour3) {
        btn1.setBackgroundColor(colour1);
        btn2.setBackgroundColor(colour2);
        btn3.setBackgroundColor(colour3);
    }

    public void breakfastBTN(View view) {
        dayTime = "breakfast";
        setBtnColour(breakfast, DKGRAY, lunch, LTGRAY, dinner, LTGRAY);
    }

    public void lunchBTN(View view) {
        dayTime = "breakfast";
        setBtnColour(breakfast, LTGRAY, lunch, DKGRAY, dinner, LTGRAY);
    }

    public void dinnerBTN(View view) {
        dayTime = "breakfast";
        setBtnColour(breakfast, LTGRAY, lunch, LTGRAY, dinner, DKGRAY);
    }

    public void SubmitRecipe(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra( "time",dayTime);
        intent.putExtra("time", slider.getText());
        intent.putExtra("choice", type);
        startActivity(intent);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFrag = null;

            switch (menuItem.getItemId()){

                case R.id.RecipeSearch:
                    selectedFrag = new TimedRecipeFragment();
                    break;

                case R.id.GeneralSearch:
                    selectedFrag = new GeneralRecipeFragment();
                    break;

                case R.id.CalorieBasedSearch:
                    selectedFrag = new CalorieRecipeFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFrag).commit();

            return true;

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}

