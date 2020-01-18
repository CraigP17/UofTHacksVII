package com.example.uofthacksvii;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GeneralRecipeFragment extends Fragment {


    private ImageButton salad;
    private ImageButton tacos;
    private ImageButton burger;
    private ImageButton noodles;

    private SearchView search;
    public String searchFood;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_general,container,false);

        salad = v.findViewById(R.id.salad);
        salad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFood = "salad";
            }
        });

        tacos = v.findViewById(R.id.tacos);
        tacos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFood = "tacos";

            }
        });

        burger = v.findViewById(R.id.burger);
        burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFood = "burger";

            }
        });

        noodles = v.findViewById(R.id.noodles);
        noodles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFood = "noodles";

            }
        });

        return v;
    }
}
