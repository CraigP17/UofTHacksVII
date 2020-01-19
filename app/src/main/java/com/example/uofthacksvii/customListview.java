package com.example.uofthacksvii;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class customListview extends ArrayAdapter<String> {

    private String[] items;
    private String[] description;
    private Integer[] imgid;

    private Activity context;

    public customListview(Activity context, String[] items, String[] description, Integer[] imgid) {
        super(context, R.layout.listview_layout, items);

        this.context = context;
        this.items = items;
        this.description = description;
        this.imgid = imgid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r = convertView;
        ViewHolder viewHolder=null;

        if(r == null){

            LayoutInflater layoutInflater =context.getLayoutInflater();

            r = layoutInflater.inflate(R.layout.listview_layout, null, true);

            viewHolder = new ViewHolder(r);

            r.setTag(viewHolder);


        }

        else{

            viewHolder = (ViewHolder) r.getTag();
        }

        viewHolder.image.setImageResource(imgid[position]);
        viewHolder.name.setText(items[position]);
        viewHolder.descriptiom.setText(description[position]);


        return r;
    }

    class ViewHolder{
        TextView name;
        TextView descriptiom;
        ImageView image;

        ViewHolder(View view){

            name = (TextView) view.findViewById(R.id.itemName);

            descriptiom = (TextView) view.findViewById(R.id.itemDescription);

            image = (ImageView) view.findViewById(R.id.imageView);




        }
    }
}
