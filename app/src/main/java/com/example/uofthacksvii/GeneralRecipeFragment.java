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

    SearchView mySearchView;
    ListView myList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_general,container,false);

        FileManager f = new FileManager(getActivity());

        ArrayList<Recipe> recipes = f.getRecipes();

        mySearchView = (SearchView) v.findViewById(R.id.searchView);
        myList = (ListView) v.findViewById(R.id.mylist);

        ArrayList<String> list = new ArrayList<>();
        list = collectNames(recipes);




        final ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,list);

        myList.setAdapter(adapter);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);
                return false;
            }
        });

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String service = (String) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getActivity().getBaseContext(), RecipeActivity.class);
                intent.putExtra("name", service);
                startActivity(intent);

            }
        });





        return v;
    }

    public ArrayList<String> collectNames(ArrayList<Recipe> all){

        ArrayList<String> output = new ArrayList<>();

        for(Recipe r: all){
            output.add(r.getName());
        }
        return output;
    }
}
