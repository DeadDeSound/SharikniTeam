package com.example.nezarsaleh.shareknitest.Arafa.DataModelAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nezarsaleh.shareknitest.Arafa.DataModel.BestRouteDataModelDetails;
import com.example.nezarsaleh.shareknitest.R;


public class BestRouteDataModelAdapterDetails extends ArrayAdapter<BestRouteDataModelDetails> {

    int resourse;
    Context context;
    BestRouteDataModelDetails[] BestrouteArray;
    LayoutInflater layoutInflater;

    public BestRouteDataModelAdapterDetails(Context context, int resource, BestRouteDataModelDetails[] objects) {
        super(context, resource, objects);
        this.context=context;
        this.resourse=resource;
        this.BestrouteArray =objects;
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

            vh.DriverName = (TextView) v.findViewById(R.id.DriverEnName);
            vh.SDG_Route_Start_FromTime = (TextView) v.findViewById(R.id.SDG_Route_Start_FromTime);
            vh.Nationality_en = (TextView) v.findViewById(R.id.Nationality_en);
            vh.SDG_RouteDays = (TextView) v.findViewById(R.id.search_results_days);

            v.setTag(vh);
        }else
        {
         vh = (ViewHolder) v.getTag();
        }
        BestRouteDataModelDetails bestRouteDataModel = BestrouteArray[position];

        vh.DriverName.setText(bestRouteDataModel.getDriverName());
        vh.SDG_Route_Start_FromTime.setText((bestRouteDataModel.getSDG_Route_Start_FromTime()));
        vh.Nationality_en.setText(bestRouteDataModel.getNationality_en());
        vh.SDG_RouteDays.setText(bestRouteDataModel.getSDG_RouteDays());

        return v;
    }



    static class ViewHolder
    {

        TextView DriverName;
        TextView SDG_Route_Start_FromTime ;
        TextView Nationality_en;
        TextView SDG_RouteDays ;


    }

}
