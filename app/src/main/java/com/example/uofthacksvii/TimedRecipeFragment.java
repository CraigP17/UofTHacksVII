package com.example.uofthacksvii;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.graphics.Color.DKGRAY;
import static android.graphics.Color.LTGRAY;

public class TimedRecipeFragment extends Fragment {

    /** The spinner menu items. */
    public final static String NO_CHOICE = "ANYTHING";
    public final static String THAI = "Thai";
    public final static String ITALIAN = "Italian";
    public final static String AMERICAN = "American";
    public final static String CHINESE = "Chinese";
    public final static String INDIAN = "Indian";

    private Button breakfast;
    private Button lunch;
    private Button dinner;
    private Button submitBtn;

    private TextView slider;
    private SeekBar seekBar;
    private String type;

    private String dayTime;
    private Spinner spinner;
    private String cuisineChoice;

    /**
     * The list of choices for the menu choices spinner
     */
    private final String[] menuItems = {NO_CHOICE, THAI, ITALIAN, AMERICAN, CHINESE, INDIAN};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_timed,container,false);

        breakfast = v.findViewById(R.id.breakfastBtn);
        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dayTime = "breakfast";
                setBtnColour(breakfast, DKGRAY, lunch, LTGRAY, dinner, LTGRAY);
            }
        });

        lunch = v.findViewById(R.id.lunchBtn);
        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dayTime = "lunch";
                setBtnColour(breakfast, LTGRAY, lunch, DKGRAY, dinner, LTGRAY);
            }
        });

        dinner = v.findViewById(R.id.dinnerBtn);
        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dayTime = "dinner";
                setBtnColour(breakfast, LTGRAY, lunch, LTGRAY, dinner, DKGRAY);
            }
        });

        spinner = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, menuItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        submitBtn = v.findViewById(R.id.timeFindRecipe);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getBaseContext(), ResultsPage.class);
                intent.putExtra("type", "time");
                intent.putExtra( "dayTime",dayTime);
                intent.putExtra("minutes", slider.getText());

                cuisineChoice = spinner.getSelectedItem().toString();
                intent.putExtra("choice", cuisineChoice);
                startActivity(intent);
            }
        });

        seekBar = (SeekBar) v.findViewById(R.id.seekBar);
        slider = (TextView) v.findViewById(R.id.timeSlider);
        seekBar.setMin(0);
        seekBar.setMax(120);
        seekBar.setProgress(60);
        // perform seek bar change listener event used for getting the progress value
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                type = String.valueOf(progressChangedValue);
                slider.setText(type);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                type = String.valueOf(progressChangedValue);
                slider.setText(type);
            }
        });


        return v;
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

}
