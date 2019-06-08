package com.example.wdftm.cmrdb_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Myadapter extends BaseAdapter {

    private Context context;
    private String[] Category, Name, Price;


    public Myadapter(Context context, String[] Category, String[] Name, String[] Price) {
        super();
        this.context = context;
        this.Category = Category;
        this.Name = Name;
        this.Price = Price;
    }

    @Override
    public int getCount() {
        return Name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, null);

        TextView cagetory = (TextView) view.findViewById(R.id.item_category);
        TextView name = (TextView) view.findViewById(R.id.item_name);
        TextView price = (TextView) view.findViewById(R.id.item_price);
        cagetory.setText(Category[position]);
        name.setText(Name[position]);
        price.setText(Price[position]);
        return view;
    }

}
