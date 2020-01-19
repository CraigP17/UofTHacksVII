package com.example.uofthacksvii;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class GeneralRecipeFragment extends Fragment {

    SearchView recipeFind;
    ListView recipes;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    customListview customLV;

    private ImageButton salad;
    private ImageButton tacos;
    private ImageButton burger;
    private ImageButton noodles;

    public String searchFood;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_general,container,false);

        String[] list = {"Pasta", "Noodles", "Tacos", "Salad"};
        Integer[] imgid = {R.drawable.salad, R.drawable.taco, R.drawable.burger, R.drawable.ic_pasta };

        //adapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,list);
        recipeFind = (SearchView) v.findViewById(R.id.searcher);
        recipes = (ListView) v.findViewById(R.id.lister);
        customLV = new customListview(getActivity(), list, list, imgid);
        recipes.setAdapter(customLV);

        recipes.setTextFilterEnabled(true);

        recipeFind.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //Temporary
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                customLV.getFilter().filter(s);
                recipes.setTextFilterEnabled(true);
                return false;
            }
        });

        recipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String service = (String) recipes.getItemAtPosition(position);

                Intent intent = new Intent(getActivity().getBaseContext(), ResultsPage.class);
                intent.putExtra("type", "general");
                intent.putExtra("service",service);
                startActivity(intent);
            }
        });

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
