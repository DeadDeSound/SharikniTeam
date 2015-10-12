package com.example.nezarsaleh.shareknitest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import java.util.List;

/**
 * Created by Nezar Saleh on 10/6/2015.
 */
public class Ride_Details_Passengers_Adapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Ride_Details_Passengers_DataModel> PassengersItems;


    public Ride_Details_Passengers_Adapter(Activity activity, List<Ride_Details_Passengers_DataModel> PassengersItems) {
        this.activity = activity;
        this.PassengersItems = PassengersItems;

    }

    @Override
    public int getCount() {
        return PassengersItems.size();
    }

    @Override
    public Object getItem(int position) {
        return PassengersItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.ride_details_passengers_list_item, null);


        TextView AccountName = (TextView) convertView.findViewById(R.id.AccountName);
        TextView AccountNationalityEn = (TextView) convertView.findViewById(R.id.AccountNationalityEn);


        final Ride_Details_Passengers_DataModel m = PassengersItems.get(position);

        AccountName.setText(m.getAccountName());
        AccountNationalityEn.setText(m.getAccountNationalityEn());


        return convertView;


    }
}
