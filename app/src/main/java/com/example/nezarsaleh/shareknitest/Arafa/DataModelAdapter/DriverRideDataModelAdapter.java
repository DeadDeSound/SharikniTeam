package com.example.nezarsaleh.shareknitest.Arafa.DataModelAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nezarsaleh.shareknitest.Arafa.DataModel.DriverRideDataModel;
import com.example.nezarsaleh.shareknitest.R;


public class DriverRideDataModelAdapter extends ArrayAdapter<DriverRideDataModel> {

    int resourse;
    Context context;
    DriverRideDataModel[] BestDriverArray;
    LayoutInflater layoutInflater;

    public DriverRideDataModelAdapter(Context context, int resource, DriverRideDataModel[] objects) {
        super(context, resource, objects);
        this.context=context;
        this.resourse=resource;
        this.BestDriverArray=objects;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder vh = null;
        if (v==null)
        {
            v = layoutInflater.inflate(resourse,parent,false);
            vh= new ViewHolder();
            vh.From = (TextView) v.findViewById(R.id.tvName);
            vh.To = (TextView) v.findViewById(R.id.tvNat);
            v.setTag(vh);
        }else
        {
         vh = (ViewHolder) v.getTag();
        }
        DriverRideDataModel bestDriverDataModel = BestDriverArray[position];
        vh.From.setText(bestDriverDataModel.getName());
        vh.To.setText(bestDriverDataModel.getBasis());
        return v;
    }


    static class ViewHolder
    {
        TextView From;
        TextView To;
    }

}
