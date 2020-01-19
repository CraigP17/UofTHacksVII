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

public class GeneralRecipeFragment extends Fragment {

    SearchView recipeFind;
    ListView recipes;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    private ImageButton salad;
    private ImageButton tacos;
    private ImageButton burger;
    private ImageButton noodles;

    public String searchFood;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_general,container,false);

        list = new ArrayList<String>();
        list.add("Pasta");
        list.add("Noodles");
        list.add("Tacos");
        list.add("Salad");

        //adapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,list);
        adapter = new ArrayAdapter(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, list);
        recipes.setAdapter(adapter);

        recipeFind.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //Temporary
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);
                return false;
            }
        });

        recipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String service = (String) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(getActivity().getBaseContext(), ResultsPage.class);
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
