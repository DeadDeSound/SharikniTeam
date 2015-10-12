package com.example.nezarsaleh.shareknitest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nezarsaleh.shareknitest.Arafa.DataModel.BestRouteDataModel;

import java.util.List;

/**
 * Created by Nezar Saleh on 10/8/2015.
 */
public class DriverGetReviewAdapter extends BaseAdapter {


    private Activity activity;
    List<DriverGetReviewDataModel> driverGetReviewDataModels_items;
    LayoutInflater layoutInflater;

    public DriverGetReviewAdapter(Activity activity,  List<DriverGetReviewDataModel> items) {

        this.driverGetReviewDataModels_items=items;
        this.activity=activity;

        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }//constructor







    @Override
    public int getCount() {
        return driverGetReviewDataModels_items.size();
    }

    @Override
    public Object getItem(int position) {
        return driverGetReviewDataModels_items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (layoutInflater == null)
            layoutInflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.driver_get_review_listitem, null);


            TextView Review = (TextView) convertView.findViewById(R.id.Driver_Get_Review_Review);
             TextView AccountName = (TextView) convertView.findViewById(R.id.Driver_Get_Review_AccountName);
           TextView  AccountNationalityEn= (TextView) convertView.findViewById(R.id.Driver_Get_Review_Nat);


        DriverGetReviewDataModel driverGetReviewDataModel = driverGetReviewDataModels_items.get(position);
        AccountName.setText(driverGetReviewDataModel.getAccountName());
        AccountNationalityEn.setText(driverGetReviewDataModel.getAccountNationalityEn());
        Review.setText(driverGetReviewDataModel.getReview());



        return convertView;


    }




}
