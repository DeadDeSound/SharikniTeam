package com.example.nezarsaleh.shareknitest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.AppController;
import com.example.nezarsaleh.shareknitest.Arafa.Classes.CircularNetworkImageView;
import com.example.nezarsaleh.shareknitest.Arafa.DataModel.BestDriverDataModel;

import java.util.List;

/**
 * Created by Nezar Saleh on 10/10/2015.
 */
public class DriverAlertsForRequestAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<DriverAlertsForRequestDataModel> AlertsItem;
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    String URL = "http://www.sharekni-web.sdg.ae/uploads/personalphoto/";


    public DriverAlertsForRequestAdapter(Activity activity, List<DriverAlertsForRequestDataModel> driverItems) {
        this.activity = activity;
        this.AlertsItem = driverItems;
    }




    @Override
    public int getCount() {
        return AlertsItem.size();
    }

    @Override
    public Object getItem(int position) {
        return AlertsItem.get(position);
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
            convertView = inflater.inflate(R.layout.driver_alerts_request_list_items, null);


        if (imageLoader == null) imageLoader = AppController.getInstance().getImageLoader();


        CircularNetworkImageView Photo = (CircularNetworkImageView) convertView.findViewById(R.id.AccountPhoto);
        TextView PassengerName= (TextView) convertView.findViewById(R.id.PassengerName);
        TextView NationalityEnName= (TextView) convertView.findViewById(R.id.NationalityEnName);

        final DriverAlertsForRequestDataModel model = AlertsItem.get(position);
        Photo.setImageUrl(URL + model.getAccountPhoto(), imageLoader);
        PassengerName.setText(model.getPassengerName());
        NationalityEnName.setText(model.getNationalityEnName());


        return convertView;



    }
}
