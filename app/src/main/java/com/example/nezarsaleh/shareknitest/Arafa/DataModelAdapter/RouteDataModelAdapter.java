package com.example.nezarsaleh.shareknitest.Arafa.DataModelAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nezarsaleh.shareknitest.Arafa.DataModel.RouteDataModel;
import com.example.nezarsaleh.shareknitest.R;


public class RouteDataModelAdapter extends ArrayAdapter<RouteDataModel> {

    int resourse;
    Context context;
    RouteDataModel[] routeArray;
    LayoutInflater layoutInflater;

    public RouteDataModelAdapter(Context context, int resource, RouteDataModel[] objects) {
        super(context, resource, objects);
        this.context=context;
        this.resourse=resource;
        this.routeArray=objects;
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
            vh.RouteNameEn = (TextView) v.findViewById(R.id.RouteEnName);
            vh.FromRegionEnName = (TextView) v.findViewById(R.id.FromRegionEnName);
            vh.ToRegionEnName = (TextView) v.findViewById(R.id.ToRegionEnName);
            vh.StartFromTime = (TextView) v.findViewById(R.id.StartFromTime);
            vh.EndToTime = (TextView) v.findViewById(R.id.EndToTime);
            vh.Basis = (TextView) v.findViewById(R.id.BasisEnName);
            vh.StartFromTime2 = (TextView) v.findViewById(R.id.StartFromTime2);
            vh.StartToTime = (TextView) v.findViewById(R.id.StartToTime);
            vh.EndFromTime = (TextView) v.findViewById(R.id.EndFromTime);
            vh.EndToTime2 = (TextView) v.findViewById(R.id.EndToTime2);
            vh.FromEmirateEnName = (TextView) v.findViewById(R.id.EmployerEnName);
            vh.FromRegionEnName2 = (TextView) v.findViewById(R.id.FromRegionEnName2);
            vh.FromStreetName = (TextView) v.findViewById(R.id.FromStreetName);
            vh.ToEmirateEnName = (TextView) v.findViewById(R.id.AccountTypeEnName);
            vh.ToRegionEnName2 = (TextView) v.findViewById(R.id.ToRegionEnName2);
            vh.ToStreetName = (TextView) v.findViewById(R.id.ToStreetName);
            vh.NoOfSeats = (TextView) v.findViewById(R.id.NoOfSeats);
            vh.NationalityEnName = (TextView) v.findViewById(R.id.NationalityEnName);
            vh.PrefLanguageEnName = (TextView) v.findViewById(R.id.PrefLanguageEnName);
            v.setTag(vh);
        }else
        {
            vh = (ViewHolder) v.getTag();
        }
        RouteDataModel routeDataModel = routeArray[position];
        vh.RouteNameEn.setText(routeDataModel.getEnName());
        vh.FromRegionEnName.setText(routeDataModel.getFromRegionEn());
        vh.ToRegionEnName.setText(routeDataModel.getToRegionEn());
        //vh.StartFromTime.setText(routeDataModel.getStartFromTime());
        //vh.EndToTime.setText(routeDataModel.getEndToTime());
        vh.Basis.setText(routeDataModel.getBasisEn());
        //vh.StartFromTime2.setText(routeDataModel.getStartFromTime());
        //vh.StartToTime.setText(routeDataModel.getStartToTime());
        //vh.EndFromTime.setText(routeDataModel.getEndToTime());
        //vh.EndToTime2.setText(routeDataModel.getEndFromTime());
        vh.FromEmirateEnName.setText(routeDataModel.getFromEmirateEn());
        vh.FromRegionEnName2.setText(routeDataModel.getFromRegionEn());
        vh.FromStreetName.setText(routeDataModel.getFromStreetName());
        vh.ToEmirateEnName.setText(routeDataModel.getToEmirateEn());
        vh.ToRegionEnName2.setText(routeDataModel.getToRegionEn());
        vh.ToStreetName.setText(routeDataModel.getToStreetName());
        //vh.NoOfSeats.setText(routeDataModel.getNoOfSeats());
        vh.NationalityEnName.setText(routeDataModel.getNationalityEn());
        vh.PrefLanguageEnName.setText(routeDataModel.getPrefLanguageEn());
        return v;
    }


    static class ViewHolder
    {
        TextView RouteNameEn;
        TextView FromRegionEnName;
        TextView ToRegionEnName;
        TextView StartFromTime;
        TextView EndToTime;
        TextView Basis;
        TextView StartFromTime2;
        TextView StartToTime;
        TextView EndFromTime;
        TextView EndToTime2;
        TextView FromEmirateEnName;
        TextView FromRegionEnName2;
        TextView FromStreetName;
        TextView ToEmirateEnName;
        TextView ToRegionEnName2;
        TextView ToStreetName;
        TextView NoOfSeats;
        TextView NationalityEnName;
        TextView PrefLanguageEnName;
    }

}
